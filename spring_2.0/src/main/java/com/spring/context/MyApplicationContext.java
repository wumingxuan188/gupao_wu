package com.spring.context;

import com.spring.annotation.MyAutowired;
import com.spring.annotation.MyController;
import com.spring.annotation.MyService;
import com.spring.aop.MyJdkDynamicAopProxy;
import com.spring.aop.config.AopConfig;
import com.spring.aop.support.MyAopSupport;
import com.spring.beans.BeanWrapper;
import com.spring.beans.config.MyBeanDefinition;
import com.spring.beans.support.MyBeanDefinitionReader;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class MyApplicationContext <T >{

    MyBeanDefinitionReader reader;
    private final Map<String, MyBeanDefinition> beanDefinitionMap = new HashMap<>();
    private final Map<String, BeanWrapper> beanFactory = new HashMap<>();
    private final Map<String, BeanWrapper> autowired = new HashMap<>();

    /**
     * 初始话容器
     *
     * @param configPath
     */
    public MyApplicationContext(String configPath) {
        //加载
         reader = new MyBeanDefinitionReader(configPath);

        List<MyBeanDefinition> definitions = reader.loadBeanDefinition();

        doRegistBeanDefinition(definitions);

        //注入(此时不创建实例)
        doaAutowired();
        //doaAutowiredSec();
        System.err.println("init finish");
    }

    public Properties getConfig(){
        return reader.getConfig();
    }

    public int getDefinitionSize(){
        return beanDefinitionMap.size();
    }
    private void doaAutowiredSec() {
        for (Map.Entry<String, BeanWrapper> entry : autowired.entrySet()) {
            populateBean(entry.getKey(),null,entry.getValue());
        }
    }

    private void doaAutowired() {
        for (Map.Entry<String, MyBeanDefinition> entry : beanDefinitionMap.entrySet()) {
            getBean(entry.getKey());
        }
    }

    //缓存bean的信息
    private void doRegistBeanDefinition(List<MyBeanDefinition> definitions) {
        for (MyBeanDefinition definition : definitions) {
            if (beanDefinitionMap.containsKey(definition.getBeanName())) {
                throw new RuntimeException("beanName "+ definition.getBeanName() +"is exist");
            }
            this.beanDefinitionMap.put(definition.getBeanName(), definition);
           this.beanDefinitionMap.put(definition.getClassName(), definition);
        }
    }
    //获取BeanDefinitionMap的key
    public String[] getBeanDefinitionNames(){
        return beanDefinitionMap.keySet().toArray(new String[beanDefinitionMap.size()]);
    }

    public Object getBean(String beanName) {
        MyBeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);
        //创建实例
        Object o = doInstance(beanDefinition);
        //包装类
        BeanWrapper wrapper = new BeanWrapper(o);
        //放入ioc中
        beanFactory.put(beanName, wrapper);
        //依赖注入
        populateBean(beanName, beanDefinition, wrapper);
        return wrapper.getInstance();
    }

    //依赖注入
    private void populateBean(String beanName, MyBeanDefinition beanDefinition, BeanWrapper wrapper) {

        Class<?> clazz = wrapper.getClazz();
        if(!(clazz.isAnnotationPresent(MyController.class)||clazz.isAnnotationPresent(MyService.class))){return;}
        for (Field field : clazz.getDeclaredFields()) {

               if (!field.isAnnotationPresent(MyAutowired.class)){continue;}

               MyAutowired annotation = field.getAnnotation(MyAutowired.class);
               String name = annotation.value().trim();
               if("".equals(name)){
                   name=field.getType().getName();
               }
               field.setAccessible(true);
          try {
              if(this.beanFactory.get(name)==null){
               //   autowired.put(beanName,wrapper);
                 continue;
              }
               field.set(wrapper.getInstance(),this.beanFactory.get(name).getInstance());
           }catch (Exception e){
               e.printStackTrace();
           }


        }

    }

    //获取实例
    private Object doInstance(MyBeanDefinition beanDefinition) {
        try {
            String className = beanDefinition.getClassName();
           // String beanName = beanDefinition.getBeanName();
            if (className == null) {
                return null;
            }
            Class<?> clazz = Class.forName(className);
            Object instance = null;
            /*String[] split = className.split("\\.");

            String name =split[split.length-1];
           if(this.beanFactory.containsKey(toFirstToLower(name))){
               return this.beanFactory.get(toFirstToLower(name)).getInstance();
           }else {
               Object instance = clazz.newInstance();
               return instance;
           }*/
            instance = clazz.newInstance();
           //=======================aop开始==================================
            MyAopSupport support=initAopConfig();
            support.setTarget(instance);
            support.setTargetClass(clazz);
            if(support.pointCutMath()){
                instance = new MyJdkDynamicAopProxy(support).getProxy();
            }
            //=======================aop结束==================================

            return instance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private MyAopSupport initAopConfig() {
        Properties properties = this.reader.getConfig();
        AopConfig aopConfig = new AopConfig();
        aopConfig.setPointCut(properties.getProperty("pointCut"));
        aopConfig.setAspectClass(properties.getProperty("aspectClass"));
        aopConfig.setAspectBefore(properties.getProperty("aspectBefore"));
        aopConfig.setAspectAfter(properties.getProperty("aspectAfter"));
        aopConfig.setAspectAfterThrow(properties.getProperty("aspectAfterThrow"));
        aopConfig.setAspectAfterThrowingName(properties.getProperty("aspectAfterThrowingName"));

        return new MyAopSupport(aopConfig);
    }

    public Object getBean(Class<?> clazz) {
        return getBean(clazz.getSimpleName());
    }


    private String toFirstToLower(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] +=32;
        return new String(chars);
    }
}
