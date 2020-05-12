package com.dynamicProxy.cglibProxy;

import net.sf.cglib.core.DebuggingClassWriter;
import org.junit.Test;

public class TestCglib {
    @Test
    public void test(){
       //生成cglib代理的class文件
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "E:\\cglib_proxy_class");
        ZhangSan san = new ZhangSan();
        Person person = (Person) new CglibProxy().getInstance(san);

        person.find_love();

    }
}
