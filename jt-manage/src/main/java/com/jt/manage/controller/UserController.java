package com.jt.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jt.manage.pojo.User;
import com.jt.manage.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//返回userLIst页面
	@RequestMapping("/findAll")
	public String findAll(Model model){
		
		List<User> userList = userService.findAll();
		//将数据保存到域中 page/request/session/context
		model.addAttribute("userList", userList);
		return "userList";
	}
}
