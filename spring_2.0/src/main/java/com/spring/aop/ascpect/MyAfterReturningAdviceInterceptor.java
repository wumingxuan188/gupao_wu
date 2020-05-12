package com.spring.aop.ascpect;

import com.spring.aop.intercept.MyMethodInterceptor;
import com.spring.aop.intercept.MyMethodInvocation;

import java.lang.reflect.Method;

public class MyAfterReturningAdviceInterceptor extends  MyAbstractAspectJAdvice implements MyMethodInterceptor {

    private  MyJoinPoint jp;
    public MyAfterReturningAdviceInterceptor(Object aspect, Method aspectMethod) {
        super(aspect, aspectMethod);
    }

    public void afterReturning(Object returnValue,Method method, Object[] args,Object target ) throws Exception{
        invokeAdviceMethod(this.jp,returnValue,null);
    }
    @Override
    public Object invoke(MyMethodInvocation mi) throws Throwable {
        this.jp=mi;
        Object returnValue = mi.proceed();
        this.afterReturning(returnValue,mi.getMethod(),mi.getArguments(),mi.getThis());
        return returnValue;
    }
}
