package com.example.aopplayground.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
class SpringAspectJProxy {

    @Around("execution(* com.example.aopplayground.target.AspectJService.greetings())")
    public Object intercept(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("hello spring-aspectj");
        Object proceed = joinPoint.proceed();
        System.out.println("bye spring-aspectj");
        return proceed;
    }
}

