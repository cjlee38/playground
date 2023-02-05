package com.example.aopplayground.spring;

import com.example.aopplayground.target.MyGreetingService;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;

public class ProxyFactoryProxy {

    public Object createProxy() {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvisor(new MyAdvisor());
        proxyFactory.setTarget(new MyGreetingService());
        return proxyFactory.getProxy();
    }

    static class MyAdvisor implements Advisor {

        @Override
        public Advice getAdvice() {
            return new MyMethodInterceptor();
        }

        @Override
        public boolean isPerInstance() {
            return false;
        }
    }

    static class MyMethodInterceptor implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            System.out.println("hello proxy factory");
            Object proceed = invocation.proceed();
            System.out.println("bye proxy factory");
            return proceed;
        }
    }
}
