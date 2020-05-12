package com.spring.aop;

public interface MyAopProxy {
    Object getProxy();

    Object getProxy(ClassLoader classLoader);
}
