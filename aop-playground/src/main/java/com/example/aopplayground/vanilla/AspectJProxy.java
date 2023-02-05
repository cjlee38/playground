package com.example.aopplayground.vanilla;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AspectJProxy {

    @Around("execution(* com.example.aopplayground.target.AspectJService.greetings())")
    public Object intercept(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("hello vanilla aspectj");
        Object proceed = joinPoint.proceed();
        System.out.println("bye vanilla aspectj");
        return proceed;
    }
}

