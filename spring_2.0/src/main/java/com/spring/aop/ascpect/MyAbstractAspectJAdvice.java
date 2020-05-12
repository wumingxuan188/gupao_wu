package com.spring.aop.ascpect;

import java.lang.reflect.Method;

public class MyAbstractAspectJAdvice implements MyAdvice1 {

    private  Object aspect;
    private Method aspectMethod;
    private  String  throwName;

    public MyAbstractAspectJAdvice(Object aspect, Method aspectMethod) {
        this.aspect = aspect;
        this.aspectMethod = aspectMethod;
    }

    protected  Object  invokeAdviceMethod(MyJoinPoint jp,Object returnValue,Throwable t ) throws Exception{
        Class<?>[] parameterTypes = this.aspectMethod.getParameterTypes();
        if(null== parameterTypes || parameterTypes.length==0 ){
            return  this.aspectMethod.invoke(this.aspect);
        }else {
            Object[] args = new Object[parameterTypes.length];
            for (int i = 0; i < parameterTypes.length; i++) {
                if(parameterTypes[i]==MyJoinPoint.class){
                    args[i]=jp;
                }else if(parameterTypes[i] == Throwable.class){
                    args[i] = t;
                }else if(parameterTypes[i] == Object.class){
                    args[i] = returnValue;
                }
            }
            return this.aspectMethod.invoke(aspect,args);
        }
    }
}
