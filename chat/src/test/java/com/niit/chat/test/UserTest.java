package com.niit.chat.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import com.niit.chat.dao.UserDAO;
import com.niit.chat.model.Users;

public class UserTest {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit.chat");
		context.refresh();
		
		UserDAO userDAO=(UserDAO) context.getBean("userDAO");
		Users user=(Users) context.getBean("user");
		
		user.setId("rahul123");
		user.setName("Rahul");
		user.setEmail("rahul@gmail.com");
		user.setDob("18/10/1994");
		user.setPassword("rahul");
		user.setGender("Male");
		user.setMobile(12132435);
		user.setStatus('u');
		user.setRole("h");
		user.setAddress("PKD");
		user.setIsonline('y');
		
		userDAO.addUser(user);
	}

}
