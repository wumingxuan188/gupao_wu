package com.spring.aop.intercept;

public interface MyMethodInterceptor {

    Object invoke(MyMethodInvocation invocation ) throws  Exception, Throwable;
}
