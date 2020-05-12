package com.spring.aop.ascpect;

import java.lang.reflect.Method;

public interface MyJoinPoint {
    Method getMethod();

    Object[] getArguments();

    Object getThis();

    void setUserAttribute(String key,Object value);

    Object getUserAttribute(String key);
}
