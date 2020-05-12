package com.spring.webmvc.servlet;

import java.io.File;

public class MyViewResolver {
    private  final String DEFAULT_TEMPLATE_SUFFIX=".html";
    private File file;

    public MyViewResolver(String templatePath) {
        String filePath = this.getClass().getClassLoader().getResource(templatePath).getFile();
        this.file = new File(filePath);
    }

    public MyView resolveViewName(String viewName){
        if(null==viewName||"".equals(viewName)){return null;}
        viewName=viewName.endsWith(DEFAULT_TEMPLATE_SUFFIX) ? viewName : viewName+DEFAULT_TEMPLATE_SUFFIX;
        return  new MyView(new File((file.getPath()+"/"+viewName).replaceAll("/+","/")));
    }
}
