package com.example.aopplayground.vanilla;


import com.example.aopplayground.GreetingService;
import com.example.aopplayground.HelloService;
import com.example.aopplayground.MyGreetingService;

import java.lang.reflect.Proxy;

public class JdkProxy {

    public static void main(String[] args) {
        MyGreetingService service = new MyGreetingService();
        Object myProxy = Proxy.newProxyInstance(
                MyGreetingService.class.getClassLoader(),
                new Class[]{GreetingService.class, HelloService.class},
                (proxy, method, args1) -> {
                    System.out.println("hello Jdk proxy;");
                    method.invoke(service, args1);
                    System.out.println("bye Jdk proxy;");
                    return null;
                }
        );
        GreetingService greetingService = (GreetingService) myProxy;
        greetingService.greetings();
        HelloService helloService = (HelloService) myProxy;
        helloService.hello();
    }
}
