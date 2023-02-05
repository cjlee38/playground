package com.example.aopplayground.target;

import org.springframework.stereotype.Component;

@Component
public class MyGreetingService implements GreetingService, HelloService {

    @Override
    public void greetings() {
        System.out.println("hello MyService !");
    }

    @Override
    public void hello() {
        System.out.println("hello hello !");
    }
}
