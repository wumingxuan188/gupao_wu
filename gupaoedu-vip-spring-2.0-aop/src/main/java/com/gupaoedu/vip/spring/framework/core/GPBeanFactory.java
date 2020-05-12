package com.gupaoedu.vip.spring.framework.core;

/**
 * Created by Tom.
 */
public interface GPBeanFactory {
    public Object getBean(Class beanClass);

    public Object getBean(String beanName);
}
