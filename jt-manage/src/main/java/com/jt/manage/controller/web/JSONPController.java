package com.jt.manage.controller.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.common.po.User;

@Controller  //由前台页面直接访问后台服务.
public class JSONPController {
	
	//http://manage.jt.com/web/testJSONP?callback=jQuery111106027760137953468_1544692289224&_=1544692289225
	//1.使用传统ajax形式回传数据
	//@RequestMapping("/web/testJSONP")
	public void testJSONP(String callback,HttpServletResponse response) throws IOException{
		
		String json = "{\"id\":\"1000\",\"name\":\"tomcat\"}";
		
		//进行特殊格式封装
		String result = callback + "("+ json +")";
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(result);
	}
	
	//2.使用工具API形式回传数据
	@RequestMapping("/web/testJSONP")
	@ResponseBody
	public JSONPObject testJP(String callback){
		User user =new User();
		user.setId(2000L);
		user.setUsername("tomcat猫");
		MappingJacksonValue jacksonValue = 
				new MappingJacksonValue(user);
		jacksonValue.setJsonpFunction(callback);
		JSONPObject object = 
				new JSONPObject(callback, user);
		return object;
	}
}
