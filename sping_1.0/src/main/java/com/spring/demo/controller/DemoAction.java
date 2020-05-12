package com.spring.demo.controller;

import com.spring.annotation.MyAutowired;
import com.spring.annotation.MyController;
import com.spring.annotation.MyRequestMapping;
import com.spring.annotation.MyRequestParam;
import com.spring.demo.service.IDemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//虽然，用法一样，但是没有功能
@MyController
@MyRequestMapping("/demo")
public class DemoAction {

  	@MyAutowired
	private IDemoService demoService;

	@MyRequestMapping("/query")
	public void query(HttpServletRequest req, HttpServletResponse resp,
					  @MyRequestParam("name") String name){
		//String result = demoService.get(name);
	String result = "<h1> My name is " + name+"</h1>";
		try {
			resp.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@MyRequestMapping("/add")
	public void add(HttpServletRequest req, HttpServletResponse resp,
					@MyRequestParam("a") Integer a, @MyRequestParam("b") Integer b){
		try {
			resp.getWriter().write(a + "+" + b + "=" + (a + b));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@MyRequestMapping("/sub")
	public void sub(HttpServletRequest req, HttpServletResponse resp,
					@MyRequestParam("a") Double a, @MyRequestParam("b") Double b){
		try {
			resp.getWriter().write(a + "-" + b + "=" + (a - b));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@MyRequestMapping("/remove")
	public String  remove(@MyRequestParam("id") Integer id){
		return "" + id;
	}

}
