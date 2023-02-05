package com.example.aopplayground.vanilla;

import com.example.aopplayground.target.GreetingService;
import com.example.aopplayground.target.HelloService;
import com.example.aopplayground.target.MyGreetingService;
import org.junit.jupiter.api.Test;

class CglibProxyTest {

    @Test
    void test() {
        CglibProxy creator = new CglibProxy();
        Object myProxy = creator.createProxy();

        GreetingService greetingService = (GreetingService) myProxy;
        greetingService.greetings();
        System.out.println("==================================");
        HelloService helloService = (HelloService) myProxy;
        helloService.hello();
        System.out.println("==================================");
        MyGreetingService myGreetingService = (MyGreetingService) myProxy;
        myGreetingService.greetings();
        System.out.println("=================================");
        myGreetingService.hello();
    }
}