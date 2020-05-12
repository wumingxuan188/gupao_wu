package com.dynamicProxy.custom.client;



import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

public class TestCustomer {
    @Test
    public void test() throws IOException {

       // ZhangSan san = new ZhangSan();
        ZhangSan san=new ZhangSan();
        //返回值必须是接口
       Person per = (Person) new MyProxyImpl().getInstance(san);
        per.find_love(1,"test");
        /**
         * 编译代理class
         */
        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy2", new Class[]{Person.class});
        FileOutputStream out = new FileOutputStream("E:\\$Proxy2.class");
        out.write(bytes);
        out.close();


    }
}
