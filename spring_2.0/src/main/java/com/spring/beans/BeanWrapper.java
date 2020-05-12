package com.spring.beans;


//实例的包装类
public class BeanWrapper {
    private Object instance;

    private Class<?>  clazz;

    public BeanWrapper(Object instance) {
        this.instance = instance;
        this.clazz=instance.getClass();
    }

    public Object getInstance() {
        return instance;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
