package com.jt.manage.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//采用自下而上开发方式
@JsonIgnoreProperties(ignoreUnknown=true)//忽略未知属性
public class User implements Serializable{

	private static final long serialVersionUID = 2L;
	//切记使用包装类型
	private Integer id;
	private String name;
	private Integer age;
	private String sex;
	
	
	public Integer getIds(){
		
		return id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}
	
	
}
