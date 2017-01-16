package com.niit.newbackend.test;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.newbackend.dao.UserDAO;
import com.niit.newbackend.model.User;

public class UsersTest {

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		
	    UserDAO userDAO= (UserDAO) context.getBean("userDAO");

		User user=(User) context.getBean("users");

		//users.setId(9);
		user.setUsername("vinay");
		user.setPassword("123");
		user.setEmail("vinay@gmail.com");
		user.setRole("employee");
		user.setStatus(true);
		user.setOnline(false);
		
		userDAO.registerUser(user);
	}

}
