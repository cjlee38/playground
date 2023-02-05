package com.example.aopplayground.spring;

import com.example.aopplayground.target.MyGreetingService;
import org.junit.jupiter.api.Test;


class ProxyFactoryProxyTest {

    @Test
    void test() {
        Object proxy = new ProxyFactoryProxy().createProxy();

        MyGreetingService myGreetingService = new MyGreetingService();
        myGreetingService.greetings();
    }
}