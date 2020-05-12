package com.gupaoedu.vip.spring.framework.aop.aspect;

import java.lang.reflect.Method;

/**
 * Created by Tom.
 */
public interface GPJoinPoint {
     //获取方法
     Method getMethod();
     //获取参数
     Object[] getArguments();
     //获取实例
     Object getThis();
     //设置参数
     void setUserAttribute(String key,Object value);
     //获取参数
     Object getUserAttribute(String key);
}
