package com.dynamicProxy.jdkProxy;

import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestJdk {
    @Test
    public void test() throws IOException {

        ZhangSan san = new ZhangSan();
        //返回值必须是接口
        Person zhangSan = (Person) new JdkProxy().getInstance(san);
        zhangSan.find_love();
        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy2", new Class[]{Person.class});
        FileOutputStream out = new FileOutputStream("E:\\$Proxy2.class");
        out.write(bytes);
        out.close();


    }
}
