package com.example.aopplayground.vanilla;

import com.example.aopplayground.ProxyCreator;
import com.example.aopplayground.target.GreetingService;
import com.example.aopplayground.target.HelloService;
import com.example.aopplayground.target.MyGreetingService;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements ProxyCreator {

    public MyGreetingService createProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new MyMethodInterceptor(new MyGreetingService()));
        enhancer.setSuperclass(MyGreetingService.class);
        enhancer.setInterfaces(new Class[]{GreetingService.class, HelloService.class});
        return (MyGreetingService) enhancer.create();
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
