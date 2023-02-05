package com.example.aopplayground.spring;

import com.example.aopplayground.target.MyGreetingService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProxyFactoryBeanProxyTest {

    @Test
    void test() {
        MyGreetingService proxy = new ProxyFactoryBeanProxy().createProxy();
        proxy.greetings();
    }
}