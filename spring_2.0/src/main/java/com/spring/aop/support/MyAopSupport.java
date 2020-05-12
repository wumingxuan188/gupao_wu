package com.spring.aop.support;

import com.spring.aop.ascpect.MyAdvice;
import com.spring.aop.ascpect.MyAfterReturningAdviceInterceptor;
import com.spring.aop.config.AopConfig;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyAopSupport {
   private  AopConfig config;

   private  Object target;

   private  Class targetClass;

   private Pattern classPathPattern;

   //private Map<Method,Map<String , MyAdvice>> methodCache;

    private Map<Method, List<Object>> methodCache;
    public MyAopSupport(AopConfig aopConfig) {
            this.config=aopConfig;
    }

    public void parse(){

        String pointCut = config.getPointCut()
                .replaceAll("\\.", "\\\\.")
                .replaceAll("\\\\.\\*", ".*")
                .replaceAll("\\(", "\\\\(")
                .replaceAll("\\)", "\\\\)");
        String pointCutForClassRegex =pointCut.substring(0,pointCut.lastIndexOf("\\(")-4);
        classPathPattern = Pattern.compile("class "+pointCutForClassRegex.substring(pointCutForClassRegex.lastIndexOf(" ") + 1));
        methodCache=new HashMap<>();
        Pattern methodPatter = Pattern.compile(pointCut);

        Map<String,Method> aspectMap=new HashMap<>();

        String aspectClass = config.getAspectClass();

        try {
            Class<?> aspectClazz = Class.forName(aspectClass);
            for (Method method : aspectClazz.getMethods()) {
                aspectMap.put(method.getName(),method);
            }

            for (Method method : this.targetClass.getMethods()) {
                Map<String,MyAdvice> adviceMap=new HashMap<>();
                String methodStr = method.toString();
                if(methodStr.contains("throws")){
                    methodStr=methodStr.substring(0,methodStr.lastIndexOf("throws"));
                }
                Matcher methodMatcher = methodPatter.matcher(methodStr);
                if(methodMatcher.matches()){
                    List<Object> advices = new LinkedList<Object>();
                    if(!(null == config.getAspectBefore() || "".equals(config.getAspectBefore()))){
                   // adviceMap.put("before",new MyAdvice(aspectClazz.newInstance(),aspectMap.get(this.config.getAspectBefore())));
                      //  advices.add(new GPMethodBeforeAdviceInterceptor(aspectClass.newInstance(),aspectMethods.get(config.getAspectBefore())));
                        advices.add(new MyAfterReturningAdviceInterceptor(aspectClazz.newInstance(),aspectMap.get(config.getAspectAfter())));

                    }
                    if(!(null == config.getAspectAfter() || "".equals(config.getAspectAfter()))){
                      //  adviceMap.put("after",new MyAdvice(aspectClazz.newInstance(),aspectMap.get(this.config.getAspectAfter())));

                    }
                    if(!(null == config.getAspectAfterThrow() || "".equals(config.getAspectAfterThrow()))){
                      //  MyAdvice afterThrow = new MyAdvice(aspectClazz.newInstance(), aspectMap.get(this.config.getAspectAfterThrow()));
                      //  afterThrow.setThrowName(this.config.getAspectAfterThrowingName());
                       // adviceMap.put("afterThrow",afterThrow);

                    }
                    methodCache.put(method,advices);
                }


            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
       public   Map<String, MyAdvice>  getAdvice(Method method) throws NoSuchMethodException {
      /*  if(methodCache.isEmpty()){return null;}
            Map<String, MyAdvice> advice = methodCache.get(method);
        if(advice==null){
            Method m = targetClass.getMethod(method.getName(), method.getParameterTypes());
            advice = methodCache.get(m);
            this.methodCache.put(m,advice);
        }
        return advice;*/
      return  null;
        }
    public boolean pointCutMath() {
        return classPathPattern.matcher(this.targetClass.toString()).matches();
    }
    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class targetClass) {
        this.targetClass = targetClass;
        parse();
    }
    public List<Object> getInterceptorsAndDynamicInterceptionAdvice(Method method, Class<?> targetClass) throws Exception {

        // 从缓存中获取
        List<Object> cached = this.methodCache.get(method);
        // 缓存未命中，则进行下一步处理
        if (cached == null) {
            Method m = targetClass.getMethod(method.getName(),method.getParameterTypes());
            cached = this.methodCache.get(m);
            // 存入缓存
            this.methodCache.put(m, cached);
        }
        return cached;
    }
}
