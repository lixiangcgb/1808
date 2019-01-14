package com.jt.manage.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.manage.pojo.User;

public class TestObjectMapper {
	
	
	@Test
	public void toJSON() throws IOException{
		User user = new User();
		user.setId(100);
		user.setName("json测试");
		user.setAge(18);
		user.setSex("男");
		
		//将对象转化为json  依赖对象中的get方法
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(user);
		System.out.println(result);
		
		//将json数据转化为对象  依赖对象中的set方法
		User objUser = 
		mapper.readValue(result,User.class);
		
		System.out.println("获取对象:"+objUser);
	}
	
	@Test
	public void toListJSON() throws IOException{
		User user = new User();
		user.setId(100);
		user.setName("json测试");
		user.setAge(18);
		user.setSex("男");
		
		User user1 = new User();
		user1.setId(1000);
		user1.setName("json测试2");
		user1.setAge(19);
		user1.setSex("男1");
		
		List<User> userList = new ArrayList<>();
		userList.add(user);
		userList.add(user1);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String result = 
		objectMapper.writeValueAsString(userList);
		System.out.println(result);
		
		//将ListJSON转化为List对象
		List<User> uList = 
		objectMapper.readValue(result, userList.getClass());
		System.out.println(uList);
		
		User[] users = objectMapper.readValue(result, User[].class);
		//数组转化为List集合
		List<User> arrayList = Arrays.asList(users);
							   
		System.out.println(arrayList);
		
	}
	
	
	
	
	
	
	
}
