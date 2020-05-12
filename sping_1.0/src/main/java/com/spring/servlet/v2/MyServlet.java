package com.spring.servlet.v2;

import com.spring.annotation.MyAutowired;
import com.spring.annotation.MyController;
import com.spring.annotation.MyRequestMapping;
import com.spring.annotation.MyService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.net.URL;

import java.util.*;

public class MyServlet extends HttpServlet {

    //加载配置文件
    private  final Properties config=new Properties();

    //缓存
    private  final List<String> className=new ArrayList<>();
    //ioc容器
    private  final Map<String,Object> ioc=new HashMap<>();
    //handleMapping
    private  final  Map<String, Method> handleMapping=new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doGet(req, resp);
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDispatch(req,resp);
        
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
        String uri = req.getRequestURI().replaceAll("/+","/");
        StringBuffer requestURL = req.getRequestURL();
        System.err.println(requestURL.toString());
        //  String contextPath = req.getContextPath();
        String[] names = req.getParameterValues("name");
        String para = Arrays.toString(names).replaceAll("\\[|\\]", "");
       try {
           if(!handleMapping.containsKey(uri)){
               resp.getWriter().write("404 the page is not exist");
               return;
           }
           Method method = handleMapping.get(uri);
           String name = toFirstToLower(method.getDeclaringClass().getSimpleName());
           method.invoke(ioc.get(name),new Object[]{req,resp,para});
       }catch (Exception e){
           e.printStackTrace();
       }


    }

    @Override
    public void destroy() {
        //super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //加载配置文件
        loadConfig(config.getInitParameter("contextConfigLocation"));
        //扫描包
        scanPackage(this.config.getProperty("scanPackage"));

        //创建实例
        doInstance();
        
        //依赖注入
        doAutowired();

        //映射
        doHandleMapping();


        

    }

    //映射
    private void doHandleMapping() {
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if(!clazz.isAnnotationPresent(MyController.class)){continue;}
            MyRequestMapping clazzAnnotation = clazz.getAnnotation(MyRequestMapping.class);
            String classPath = clazzAnnotation.value();
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if(!method.isAnnotationPresent(MyRequestMapping.class)){continue;}
                MyRequestMapping methodPath = method.getAnnotation(MyRequestMapping.class);
               handleMapping.put(("/"+classPath+"/"+methodPath.value()).replaceAll("/+","/")+".do",method);
            }
        }
    }

    //依赖注入
    private void doAutowired() {
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if(!field.isAnnotationPresent(MyAutowired.class)){ continue;}
                MyAutowired autowired = field.getAnnotation(MyAutowired.class);
                String fieldName = autowired.value();
                if("".equals(fieldName)){
                    fieldName=  field.getType().getName();
                }
                field.setAccessible(true);

                try {
                    field.set(entry.getValue(),ioc.get(fieldName));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //创建实例
    private void doInstance() {
        for (String classPath : className) {
            try {
                Class<?> clazz = Class.forName(classPath);
                if(clazz.isAnnotationPresent(MyController.class)){
                    String className =toFirstToLower(clazz.getSimpleName()) ;
                    ioc.put(className,clazz.newInstance());

                }else if(clazz.isAnnotationPresent(MyService.class)) {
                    String beanName="";
                    MyService myService = clazz.getAnnotation(MyService.class);
                    beanName=myService.value();
                    if("".equals(myService.value())){
                        beanName=toFirstToLower(clazz.getSimpleName());
                    }
                    ioc.put(beanName,clazz.newInstance());
                    Class<?>[] interfaces = clazz.getInterfaces();
                    for (Class<?> anInterface : interfaces) {
                            if(ioc.containsKey(anInterface.getName())){
                                throw  new  Exception("bean is exist");
                            }
                            ioc.put(anInterface.getName(),clazz.newInstance());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String toFirstToLower(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] +=32;
        return new String(chars);
    }


    private void scanPackage(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/"+scanPackage.replaceAll("\\.","/"));

       File files = new File(url.getFile());
        for (File file : files.listFiles()) {
            if (file.isDirectory()) {
                scanPackage(scanPackage + "." + file.getName());
            }else {
                if(!file.getName().endsWith(".class")){continue;}
                className.add(scanPackage+"."+file.getName().replace(".class",""));
            }

        }
    }

    //加载配置文件
    private void loadConfig(String configName) {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(configName);
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
}
