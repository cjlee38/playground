package com.example.aopplayground.vanilla;


import com.example.aopplayground.ProxyCreator;
import com.example.aopplayground.target.GreetingService;
import com.example.aopplayground.target.HelloService;
import com.example.aopplayground.target.MyGreetingService;

import java.lang.reflect.Proxy;

public class JdkProxy implements ProxyCreator {

    public MyGreetingService createProxy() {
        MyGreetingService service = new MyGreetingService();
        return (MyGreetingService) Proxy.newProxyInstance(
                MyGreetingService.class.getClassLoader(),
                new Class[]{GreetingService.class, HelloService.class},
                (proxy, method, args1) -> {
                    System.out.println("hello Jdk proxy;");
                    method.invoke(service, args1);
                    System.out.println("bye Jdk proxy;");
                    return null;
                }
        );
    }
}
