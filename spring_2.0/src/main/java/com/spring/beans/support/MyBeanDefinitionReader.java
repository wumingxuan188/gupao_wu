package com.spring.beans.support;

import com.spring.annotation.MyController;
import com.spring.annotation.MyService;
import com.spring.beans.config.MyBeanDefinition;
import com.sun.org.apache.regexp.internal.RE;
import sun.security.pkcs11.wrapper.CK_AES_CTR_PARAMS;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 解析配置文件
 */
public class MyBeanDefinitionReader {
    //加载配置文件
    private  final Properties config=new Properties();

    //缓存
    private  final List<String> classNames=new ArrayList<>();

    public MyBeanDefinitionReader(String configPath) {

        //加载配置文件
        loadConfig(configPath);

        //扫描类
        doScan(this.config.getProperty("scanPackage"));
    }

    public Properties getConfig(){
        return  config;
    }

    //获取MyBeanDefinition的集合
    public List<MyBeanDefinition> loadBeanDefinition(){
        List<MyBeanDefinition>  beanDefinitions=new ArrayList<>();
        for (String clazzName : classNames) {
            try {
                Class<?> clazz = Class.forName(clazzName);
                //按类名的首字母小写创建
               // if(clazz.isAnnotationPresent(MyController.class)){
               if(clazz.isInterface()){continue;}
                MyBeanDefinition beanDefinition = doCreateBeanDefinition(toFirstToLower(clazz.getSimpleName()), clazz.getName());
                beanDefinitions.add(beanDefinition);
              //  }else if(clazz.isAnnotationPresent(MyService.class)){
                for (Class<?> i : clazz.getInterfaces()) {
                    beanDefinitions.add(doCreateBeanDefinition(i.getName(),clazz.getName()));
                }

              //  }
                //注解自定义传概念
                //接口注入

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return beanDefinitions;
    }


    private MyBeanDefinition doCreateBeanDefinition(String beanName,String clazzName){
        return  new MyBeanDefinition(beanName, clazzName);
    }

    /**
     * 扫描类并缓存起来
     * @param scanPackage
     */
    private void doScan(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/"+scanPackage.replaceAll("\\.","/"));

        File files = new File(url.getFile());
        for (File file : files.listFiles()) {
            if (file.isDirectory()) {
                doScan(scanPackage + "." + file.getName());
            }else {
                if(!file.getName().endsWith(".class")){continue;}
                classNames.add(scanPackage+"."+file.getName().replace(".class",""));
            }

        }
    }

    /**
     * 加载配置文件
     * @param configPath
     */
    private void loadConfig(String configPath) {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(configPath);
        try {
            config.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    /**
     * 首字母小写
     * @param simpleName
     * @return
     */
    private String toFirstToLower(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] +=32;
        return new String(chars);
    }

}
