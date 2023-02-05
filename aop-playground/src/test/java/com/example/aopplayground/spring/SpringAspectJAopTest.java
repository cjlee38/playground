package com.example.aopplayground.spring;

import com.example.aopplayground.target.MyGreetingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(SpringAspectJProxy.class)
@EnableAspectJAutoProxy
class SpringAspectJAopTest {

    private final MyGreetingService myGreetingService;

    @Autowired
    public SpringAspectJAopTest(MyGreetingService myGreetingService) {
        this.myGreetingService = myGreetingService;
    }

    @Test
    void asd() {
        myGreetingService.greetings();
    }
}