package com.example.aopplayground.vanilla;

import com.example.aopplayground.GreetingService;
import com.example.aopplayground.HelloService;
import com.example.aopplayground.MyGreetingService;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

public class CglibProxy {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new MyMethodInterceptor(new MyGreetingService()));
        enhancer.setInterfaces(new Class[]{GreetingService.class, HelloService.class});
        Object myProxy = enhancer.create();
        GreetingService greetingService = (GreetingService) myProxy;
        greetingService.greetings();
        HelloService helloService = (HelloService) myProxy;
        helloService.hello();
    }

    static class MyMethodInterceptor implements MethodInterceptor {

        private final MyGreetingService myGreetingService;

        public MyMethodInterceptor(MyGreetingService myGreetingService) {
            this.myGreetingService = myGreetingService;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("hello cglib proxy");
            method.invoke(myGreetingService, objects);
            System.out.println("bye cglib proxy");
            return null;
        }
    }
}
