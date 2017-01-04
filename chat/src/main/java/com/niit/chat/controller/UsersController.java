package com.niit.chat.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.chat.dao.UsersDAO;
import com.niit.chat.model.Users;
import com.niit.chat.model.Error;
@RestController
public class UsersController {
	Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UsersDAO usersDAO;

	@RequestMapping("/")
	public String getLanding()
	{
		return "index";
	}

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody Users users){
		logger.debug("Entering USERCONTROLLER : LOGIN");
		Users validUsers=usersDAO.authenticate(users);
		if(validUsers==null){
			logger.debug("validUsers is null");
			Error error=new Error(1,"Username and password doesnt exists...");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED); //401
		}
		else{
			validUsers.setOnline(true);
			usersDAO.updateUsers(validUsers); // to update online status in db
			logger.debug("validUsers is not null");
			return new ResponseEntity<Users>(validUsers,HttpStatus.OK);//200
		}
	}
	@RequestMapping(value="/logout",method=RequestMethod.PUT)
	public ResponseEntity<?> logout(HttpSession session){
		Users users=(Users)session.getAttribute("users");
		if(users!=null){
			users.setOnline(false);
			usersDAO.updateUsers(users);
		}
		session.removeAttribute("user");
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	//'?'  - Any Type [User,Error] 
	//ENDPOINT : http://localhost:8080/proj2backend/register 
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ResponseEntity<?> registerUsers(@RequestBody Users users){
		//client will send only username, password, email, role 
		try{
		logger.debug("USERCONTROLLER=>REGISTER " + users);
		users.setStatus(true);
		users.setOnline(false);
		Users savedUsers=usersDAO.registerUsers(users);
		logger.debug("User Id generated is " + savedUsers.getId());
		if(savedUsers.getId()==0){
			Error error=new Error(2,"Couldnt insert user details ");
			return new ResponseEntity<Error>(error , HttpStatus.CONFLICT);
		}
		else
			return new ResponseEntity<Users>(savedUsers,HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			Error error=new Error(2,"Couldnt insert user details. Cannot have null/duplicate values " + e.getMessage());
			return new ResponseEntity<Error>(error , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//@RequestMapping(value="/users", method=RequestMethod.GET)
	//public ResponseEntity<List<Users>> getUsers()
	//{
	//	List<Users> users = usersDAO.usersList();
		//return new ResponseEntity <List<Users>>(users,HttpStatus.OK);
	//}
	@RequestMapping(value="/getUsers",method=RequestMethod.GET)
	public ResponseEntity<?> getAllUsers(HttpSession session){
		Users users=(Users)session.getAttribute("users");
		if(users==null)
		return new	ResponseEntity<Error>(new Error(1,"Unauthorized user"),HttpStatus.UNAUTHORIZED);
		else
		{
			List<Users> list=usersDAO.getAllUsers(users);
			for(Users u:list)
				System.out.println("IsONline " + u.isOnline());
			return new ResponseEntity<List<Users>>(list,HttpStatus.OK);
		}
	}

	
	
	
	
	}