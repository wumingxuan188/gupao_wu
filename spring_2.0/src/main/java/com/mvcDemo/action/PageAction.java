package com.mvcDemo.action;



import com.mvcDemo.service.IQueryService;
import com.spring.annotation.MyAutowired;
import com.spring.annotation.MyController;
import com.spring.annotation.MyRequestMapping;
import com.spring.annotation.MyRequestParam;
import com.spring.webmvc.servlet.MyModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 公布接口url
 * @author Tom
 *
 */
@MyController
@MyRequestMapping("/")
public class PageAction {

    @MyAutowired
    IQueryService queryService;

    @MyRequestMapping("/first")
    public MyModelAndView query(@MyRequestParam("teacher") String teacher){
        String result = queryService.query(teacher);
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("teacher", teacher);
        model.put("data", result);
        model.put("token", "123456");
        return new MyModelAndView("first.html",model);
    }

}
