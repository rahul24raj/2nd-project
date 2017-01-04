package com.niit.chat.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.chat.dao.UsersDAO;
import com.niit.chat.model.Users;



public class UsersTest {

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		
	    UsersDAO usersDAO= (UsersDAO) context.getBean("usersDAO");

		Users users=(Users) context.getBean("users");

		//users.setId(9);
		
		users.setEmail("ama@gmail.com");
		users.setEnabled(true);
		users.setOnline(false);
		users.setPassword("an");
		users.setRole("user");
		users.setUsername("amarth");
		
		usersDAO.registerUsers(users);
	}

}