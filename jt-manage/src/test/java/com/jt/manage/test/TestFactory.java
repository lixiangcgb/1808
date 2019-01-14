package com.jt.manage.test;

import java.util.Calendar;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestFactory {
	
	@Test
	public void testStatic(){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("/spring/factory.xml");
		
		Calendar calendar1 = 
				(Calendar) context.getBean("calendar1");
		System.out.println("获取时间:"+calendar1.getTime());
		
		Calendar calendar2 = 
				(Calendar) context.getBean("calendar2");
		System.out.println(calendar2.getTime());
		
		Calendar calendar3 = (Calendar) context.getBean("calendar3");
		System.out.println(calendar3.getTime());
	}
	
	
	
	
	
	
}
