package com.spring.aop.ascpect;

import lombok.Data;

import java.lang.reflect.Method;
@Data
public class MyAdvice {
    private  Object aspect;
    private Method aspectMethod;
    private  String  throwName;

    public MyAdvice(Object aspect, Method aspectMethod) {
        this.aspect = aspect;
        this.aspectMethod = aspectMethod;
    }
}
