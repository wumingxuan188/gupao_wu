package com.spring.aop;

import com.spring.aop.intercept.MyMethodInvocation;
import com.spring.aop.support.MyAopSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class MyJdkDynamicAopProxy1 implements MyAopProxy, InvocationHandler {
    private  MyAopSupport config;

    public MyJdkDynamicAopProxy1(MyAopSupport config) {
        this.config=config;
    }

    @Override
    public Object getProxy() {

        return this.getProxy(this.getClass().getClassLoader());
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return Proxy.newProxyInstance(classLoader,this.config.getTargetClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<Object> chain = this.config.getInterceptorsAndDynamicInterceptionAdvice(method, this.config.getTargetClass());
        MyMethodInvocation invocation = new MyMethodInvocation(proxy, this.config.getTarget(),method,this.config.getTargetClass(),chain,args);

        return invocation.proceed();
    }
}
