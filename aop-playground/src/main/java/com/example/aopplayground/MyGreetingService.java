package com.example.aopplayground;

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
