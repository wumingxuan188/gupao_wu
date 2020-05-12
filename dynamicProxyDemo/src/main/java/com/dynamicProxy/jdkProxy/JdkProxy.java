package com.dynamicProxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk代理
 */
public class JdkProxy implements InvocationHandler {
    private  Object object;

    public Object getInstance(Object target){
       this.object=target;
        Class<?> aClass = target.getClass();
        return Proxy.newProxyInstance(aClass.getClassLoader(),aClass.getInterfaces(),this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
       before();

       Object obj = method.invoke(object, args);
        after();

        return obj;
    }

    private void after() {
        System.err.println("准备交往");
    }

    private void before() {
        System.err.println("开始物色");

    }
}
