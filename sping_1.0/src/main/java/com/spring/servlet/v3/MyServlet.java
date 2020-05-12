package com.spring.servlet.v3;

import com.spring.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyServlet extends HttpServlet {

    //加载配置文件
    private  final Properties config=new Properties();

    //缓存
    private  final List<String> className=new ArrayList<>();
    //ioc容器
    private  final Map<String,Object> ioc=new HashMap<>();
    //handleMapping
    private  final  Map<String, Method> handleMapping=new HashMap<>();
    private final  List<Handle> urlList=new ArrayList<>();

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
        try {

            Handle handle = getHandle(req);
            if(handle==null){
                resp.getWriter().write("404 the page is not exist");
                return;
            }
            //获取方法参数类型
            Class<?>[] types = handle.getMethod().getParameterTypes();

            Object[] para=new Object[types.length];
            //获取请求的参数
            Map<String,String[]> reqParameter = req.getParameterMap();
            //为参数赋值
            for (Map.Entry<String, String[]> entry : reqParameter.entrySet()) {
                String val = Arrays.toString(entry.getValue()).replaceAll("\\[|\\]", "");

                if(!handle.getParameterMap().containsKey(entry.getKey())){continue;}
                Integer index = handle.getParameterMap().get(entry.getKey());
                para[index]=convert(types[index],val);
            }
            if(handle.getParameterMap().containsKey(HttpServletRequest.class.getName())){
                Integer reqIndex = handle.getParameterMap().get(HttpServletRequest.class.getName());
                para[reqIndex]=req;
            }
            if(handle.getParameterMap().containsKey(HttpServletResponse.class.getName())){
                Integer respIndex = handle.getParameterMap().get(HttpServletResponse.class.getName());
                para[respIndex]=resp;
            }

            handle.getMethod().invoke(handle.getController(),para);


        }catch (Exception e){
            e.printStackTrace();
        }

        //版本1
       /* String uri = req.getRequestURI().replaceAll("/+","/");
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
       }*/
    }

    private Object convert(Class<?> type, String val) {
        if(Integer.class==type){
            return Integer.valueOf(val);
        }else if(Double.class==type){
            return Double.valueOf(val);
        }
        return val;
    }

    private  Handle getHandle(HttpServletRequest request){
        if(this.urlList.isEmpty()) {return null;}
        String uri = request.getRequestURI().replaceAll("/+","/");
        for (Handle handle : urlList) {
            Matcher matcher = handle.getUrl().matcher(uri);
            if(matcher.matches()){
                return  handle;
            }
        }
        return null;
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
                String url = ("/" + classPath + "/" + methodPath.value()).replaceAll("/+", "/") + ".do";
                // handleMapping.put(url,method);
                Pattern pattern = Pattern.compile(url);
                urlList.add(new Handle(entry.getValue(),pattern,method));
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
class Handle{
    private  Object controller;

    private Pattern url;

    private  Method method;

    private  Map<String ,Integer>  parameterMap;

    public Handle(Object controller, Pattern url, Method method) {
        this.controller = controller;
        this.url = url;
        this.method = method;
        parameterMap=new HashMap<>();
        putParameter();
    }

    private  void putParameter(){
        Annotation[][] annotations = method.getParameterAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            for (Annotation a : annotations[i]) {
                if(a instanceof  MyRequestParam){
                    String value = ((MyRequestParam) a).value();
                    parameterMap.put(value,i);
                }
            }

        }
        Class<?>[] types = method.getParameterTypes();
        for (int i = 0; i < types.length; i++) {
            Class<?> clazz = types[i];
            if(clazz==HttpServletRequest.class){
                parameterMap.put(clazz.getName(),i);
            }else if (clazz==HttpServletResponse.class){
                parameterMap.put(clazz.getName(),i);
            }

        }
    }



    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Pattern getUrl() {
        return url;
    }

    public void setUrl(Pattern url) {
        this.url = url;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Map<String, Integer> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<String, Integer> parameterMap) {
        this.parameterMap = parameterMap;
    }
}
