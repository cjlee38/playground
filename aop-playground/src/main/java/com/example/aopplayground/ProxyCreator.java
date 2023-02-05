package com.example.aopplayground;

import com.example.aopplayground.target.MyGreetingService;

public interface ProxyCreator {
    MyGreetingService createProxy();
}
