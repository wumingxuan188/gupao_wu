package com.spring.aop;


import com.spring.aop.ascpect.MyAdvice;
import com.spring.aop.support.MyAopSupport;
import lombok.SneakyThrows;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

public class MyJdkDynamicAopProxy implements InvocationHandler {
    private MyAopSupport config;


    public MyJdkDynamicAopProxy(MyAopSupport config) {
        this.config = config;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

       //   前置通知
        adviceInvoke(config.getAdvice(method).get("before"));
       //具体业务
        Object res = method.invoke(config.getTarget(),args);

        adviceInvoke(config.getAdvice(method).get("after"));

        return res;
    }

    @SneakyThrows
    private void adviceInvoke(MyAdvice advice) {
            advice.getAspectMethod().invoke(advice.getAspect());
    }


    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),this.config.getTargetClass().getInterfaces(),this);
    }

}
