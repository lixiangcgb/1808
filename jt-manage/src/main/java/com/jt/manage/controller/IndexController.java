package com.jt.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/index")
	public String index(){
		
		return "index";
	}
	
	/**
	 * 根据页面的跳转原则,每发起一个请求,
	 * 则需要编辑一个Controller,如果有10个请求,则需要编辑
	 * 10个Controller方法.
	 * 如何优化??
	 * 
	 * 思路:
	 * 		/page/item-add
	 * 		/page/item-list
	 * 		/page/item-param-list
	 * 		/page/xxxx
	 * 从url请求路径中动态获取参数
	   规范:
	   		1.参数与参数之间必须使用/分割
	   		2.需要获取的参数必须使用{}包裹
	   		3.数据转化时,必须使用注解
	 * @return
	 */
	@RequestMapping("/page/{moduleName}")
	public String module(@PathVariable String moduleName){
		
		return moduleName;
	}
	
	
	
	
	
	
	
	
	
	
	
}
