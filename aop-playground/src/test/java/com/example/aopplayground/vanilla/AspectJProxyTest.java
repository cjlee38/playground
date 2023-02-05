package com.example.aopplayground.vanilla;

import com.example.aopplayground.target.MyGreetingService;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;

class AspectJProxyTest {

    @Test
    void test() {
        MyGreetingService myGreetingService = new MyGreetingService();
        boolean aopProxy = AopUtils.isAopProxy(myGreetingService);
        System.out.println("aopProxy = " + aopProxy);
        myGreetingService.greetings();
    }
}