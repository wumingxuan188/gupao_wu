package com.dynamicProxy.custom.client;

import com.dynamicProxy.custom.proxy.MyClassLoader;
import com.dynamicProxy.custom.proxy.MyInvocationHandler;
import com.dynamicProxy.custom.proxy.MyProxy;

import java.lang.reflect.Method;

public class MyProxyImpl implements MyInvocationHandler {
    private Object obj;

    public Object getInstance(Object obj){
        this.obj=obj;
        return MyProxy.newProxyInstance(new MyClassLoader(),obj.getClass().getInterfaces(),this);

    }

    //@Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object o = method.invoke(obj, args);
        after();
        return o;
    }

    private void after() {
        System.err.println("准备交往");
    }

    private void before() {
        System.err.println("开始物色");

    }
}
