package com.dynamicProxy.cglibProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    public Object getInstance(Object obj){
        Enhancer en=new Enhancer();
        en.setSuperclass(obj.getClass());
        en.setCallback(this);
        return  en.create();
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object obj = methodProxy.invokeSuper(o, objects);
        after();
        return obj;
    }

    private void after() {
        System.err.println("开始交往");
    }

    private void before() {
        System.err.println("开始无色");
    }
}
