package com.dynamicProxy.custom.proxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * 自定义类加载器
 */
public class MyClassLoader extends ClassLoader {
    private File filePath;


    public MyClassLoader() {
        String path = MyClassLoader.class.getResource("").getPath();

        this.filePath = new File(path);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String className = MyClassLoader.class.getPackage().getName()+"."+ name;
        if(filePath!=null){
            File f=new File(filePath,name.replace("\\.","/")+".class");
            if(f.exists()){

                try {
                    FileInputStream in=new FileInputStream(f);
                    ByteArrayOutputStream out=new ByteArrayOutputStream();
                      byte[] data=new byte[1];
                      int len=0;
                      while ((len=in.read(data))!=-1){
                          out.write(data);
                      }
                      return  defineClass(className,out.toByteArray(),0,out.size());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }






        return  null;
    }


}
