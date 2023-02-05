package com.example.aopplayground.spring;

import com.example.aopplayground.ProxyCreator;
import com.example.aopplayground.target.MyGreetingService;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactoryBean;

public class ProxyFactoryBeanProxy implements ProxyCreator {
    @Override
    public MyGreetingService createProxy() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.addAdvisor(new MyAdvisor());
        proxyFactoryBean.setTargetClass(MyGreetingService.class);
        proxyFactoryBean.setProxyTargetClass(true);
        proxyFactoryBean.setTarget(new MyGreetingService());
//        proxyFactoryBean.setInterfaces(HelloService.class, GreetingService.class);
        return (MyGreetingService) proxyFactoryBean.getObject();
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
            System.out.println("hello proxy factory bean");
            Object proceed = invocation.proceed();
            System.out.println("bye proxy factory bean");
            return proceed;
        }
    }
}
