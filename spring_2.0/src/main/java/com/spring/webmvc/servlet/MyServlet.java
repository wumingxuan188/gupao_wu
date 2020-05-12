package com.spring.webmvc.servlet;

import com.demo.service.Impl.DemoService;
import com.spring.annotation.MyController;
import com.spring.annotation.MyRequestMapping;
import com.spring.context.MyApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyServlet extends HttpServlet {

   private   MyApplicationContext context;

    private List<MyHandlerMapping> handlerMappings=new ArrayList<>();
    //路径适配器
    private Map<MyHandlerMapping,MyHandlerAdapter> handlerAdapters = new HashMap<MyHandlerMapping, MyHandlerAdapter>();

    private List<MyViewResolver> viewResolvers = new ArrayList<MyViewResolver>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            dispatch(req,resp);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        MyHandlerMapping handle = getHandle(req);
        if(handle==null){
            return;
        }
        MyHandlerAdapter ha = getHandlerAdapter(handle);

        MyModelAndView mv = ha.hand(req, resp, handle);

        processDispatchResult(req,resp,mv);

    }

    private void processDispatchResult(HttpServletRequest req, HttpServletResponse resp, MyModelAndView mv) throws Exception {
        if(mv==null){return;}
        if(viewResolvers.isEmpty()){return;}
        for (MyViewResolver viewResolver : viewResolvers) {
            MyView view = viewResolver.resolveViewName(mv.getViewName());
            view.render(req,resp,mv.getModel());
            return;
        }
    }

    private MyHandlerAdapter getHandlerAdapter(MyHandlerMapping handle) {
        if(handlerAdapters.isEmpty()){return  null;}

        return handlerAdapters.get(handle);
    }

    private MyHandlerMapping getHandle(HttpServletRequest req) {
        if(this.handlerMappings.isEmpty()) {return null;}
        String uri = req.getRequestURI().replaceAll("/+","/");
        int i = uri.indexOf("do");
        String s = uri.substring(0, i-1);

        for (MyHandlerMapping handle : handlerMappings) {
            Matcher matcher = handle.getPattern().matcher(s);
            if(matcher.matches()){
                return  handle;
            }
        }
        return null;
    }


    @Override
    public void init(ServletConfig config) throws ServletException {
        //初始化容器
        context  = new MyApplicationContext(config.getInitParameter("contextConfigLocation"));
       initStrategies(context);
    }

    private void initStrategies(MyApplicationContext context) {

        //初始话路径和方法对应
        initHandlerMappings(context);

        //初始话Adapters
        initHandlerAdapters(context);

        initViewResolvers(context);
    }

    private void initViewResolvers(MyApplicationContext context) {
        String root = context.getConfig().getProperty("templateRoot");
        String filePath = this.getClass().getClassLoader().getResource(root).getFile();
        File files = new File(filePath);
        for (File file : files.listFiles()) {
            viewResolvers.add(new MyViewResolver(root));

        }

    }

    private void initHandlerAdapters(MyApplicationContext context) {
        for (MyHandlerMapping mapping : handlerMappings) {
            handlerAdapters.put(mapping,new MyHandlerAdapter());
        }

    }

    //初始话路径
    private void initHandlerMappings(MyApplicationContext context) {
        int size = context.getDefinitionSize();
        if(size==0){return;}
        for (String beanName : context.getBeanDefinitionNames()) {
            Object bean = context.getBean(beanName);
            Class<?> clazz = bean.getClass();
            if(!clazz.isAnnotationPresent(MyController.class)){continue;}
            String path = "";
            if(clazz.isAnnotationPresent(MyRequestMapping.class)){
                path="/"+clazz.getAnnotation(MyRequestMapping.class).value();
            }
            for (Method method : clazz.getMethods()) {
                if(!method.isAnnotationPresent(MyRequestMapping.class)){continue;}
                String url=path+"/"+ method.getAnnotation(MyRequestMapping.class).value();
                url=url.replaceAll("/+","/");
                Pattern pattern = Pattern.compile(url);
                handlerMappings.add(new MyHandlerMapping(pattern,method,bean));
            }
        }

    }


}
