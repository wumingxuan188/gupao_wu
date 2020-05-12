package com.mvcDemo.action;


import com.mvcDemo.service.IModifyService;
import com.mvcDemo.service.IQueryService;
import com.spring.annotation.MyAutowired;
import com.spring.annotation.MyController;
import com.spring.annotation.MyRequestMapping;
import com.spring.annotation.MyRequestParam;
import com.spring.webmvc.servlet.MyModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 公布接口url
 * @author Tom
 *
 */
@MyController
@MyRequestMapping("/web")
public class MyAction {

	@MyAutowired
	IQueryService queryService;
	@MyAutowired
	IModifyService modifyService;

	@MyRequestMapping("/query")
	public MyModelAndView query(HttpServletRequest request, HttpServletResponse response,
								@MyRequestParam("name") String name){
		String result = queryService.query(name);
		return out(response,result);
	}
	
	@MyRequestMapping("/add")
	public MyModelAndView add(HttpServletRequest request,HttpServletResponse response,
			   @MyRequestParam("name") String name,@MyRequestParam("addr") String addr){
		String result = modifyService.add(name,addr);
		return out(response,result);
	}
	
	@MyRequestMapping("/remove")
	public MyModelAndView remove(HttpServletRequest request, HttpServletResponse response,
								 @MyRequestParam("id") Integer id){
		String result = modifyService.remove(id);
		return out(response,result);
	}
	
	@MyRequestMapping("/edit.json")
	public MyModelAndView edit(HttpServletRequest request,HttpServletResponse response,
			@MyRequestParam("id") Integer id,
			@MyRequestParam("name") String name){
		String result = modifyService.edit(id,name);
		return out(response,result);
	}
	
	
	
	private MyModelAndView out(HttpServletResponse resp,String str){
		try {
			resp.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
