package com.example.aopplayground.vanilla;

import com.example.aopplayground.target.GreetingService;
import com.example.aopplayground.target.HelloService;
import org.junit.jupiter.api.Test;

class JdkProxyTest {

    @Test
    void test() {
        Object myProxy = new JdkProxy().createProxy();

        GreetingService greetingService = (GreetingService) myProxy;
        greetingService.greetings();
        System.out.println("====================================");

        HelloService helloService = (HelloService) myProxy;
        helloService.hello();
        System.out.println("====================================");
    }
}