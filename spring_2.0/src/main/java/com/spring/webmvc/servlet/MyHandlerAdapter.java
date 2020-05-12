package com.spring.webmvc.servlet;

import com.spring.annotation.MyRequestParam;
import com.sun.xml.internal.ws.wsdl.writer.document.ParamType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MyHandlerAdapter {

    //执行
    public MyModelAndView hand(HttpServletRequest req, HttpServletResponse resp, MyHandlerMapping handle) throws InvocationTargetException, IllegalAccessException {
        //保存形参
        Map<String ,Integer> paramIndex=new HashMap<>();
        Method method = handle.getMethod();
        //获取形参列表(获取带MyRequestParam注解的参数位置)
        Annotation[][] annotations = method.getParameterAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            for (Annotation annotation : annotations[i]) {
                if(annotation instanceof MyRequestParam){
                    String value = ((MyRequestParam) annotation).value();
                    if(!("".equals(value)&&null==value)){
                        paramIndex.put(value,i);
                    }
                }
            }
        }
        //获取没有带注解的参数位置
        Class<?>[] types = method.getParameterTypes();
        for (int i = 0; i < types.length; i++) {
            Class<?> type = types[i];
            if(type==HttpServletRequest.class||type==HttpServletResponse.class){
                paramIndex.put(type.getName(),i);
            }
        }
        //实参的值
        Object[] paraValue = new Object[types.length];
        //获取实参
        Map<String ,String[]> parameterMap = req.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            if(!paramIndex.containsKey(entry.getKey())){continue;}
            Integer index = paramIndex.get(entry.getKey());
            paraValue[index]= castStringValue(Arrays.toString(entry.getValue())
                    .replaceAll("\\[|\\]","")
                    .replaceAll("\\s+",","),types[index]);;
        }
        if(paramIndex.containsKey(HttpServletRequest.class.getName())){
            Integer index = paramIndex.get(HttpServletRequest.class.getName());
            paraValue[index]=req;
        }
        if(paramIndex.containsKey(HttpServletResponse.class.getName())){
            int index = paramIndex.get(HttpServletResponse.class.getName());
            paraValue[index] = resp;
        }
        Object res = method.invoke(handle.getInstance(), paraValue);
        if(res==null || res instanceof Void){  return null;}
        Class<?> returnType = method.getReturnType();
        if(returnType==MyModelAndView.class){return  (MyModelAndView)res;}
        return null;
    }


   //转换
    private Object castStringValue(String value, Class<?> paramType) {
        if(String.class == paramType){
            return value;
        }else if(Integer.class == paramType){
            return Integer.valueOf(value);
        }else if(Double.class == paramType){
            return Double.valueOf(value);
        }else {
            if(value != null){
                return value;
            }
            return null;
        }

    }

}
