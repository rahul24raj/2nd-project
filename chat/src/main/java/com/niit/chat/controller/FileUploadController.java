package com.niit.chat.controller;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.chat.dao.FileUploadDAO;
import com.niit.chat.model.FileUpload;
import com.niit.chat.model.Users;


@RestController
public class FileUploadController {
	private SessionFactory sessionFactory;

	@Autowired
	private FileUploadDAO fileUploadDAO;

	@RequestMapping(value="/doUpload",method=RequestMethod.POST)
	public String handleFileUpload(HttpServletRequest request,HttpSession session,@RequestParam CommonsMultipartFile uploadFile)throws Exception {
	Users users=(Users) session.getAttribute("users");
	if(users==null)
	throw new RuntimeException("Not Logged in");
	System.out.println("User is " +users.getUsername());

	if(uploadFile !=null){
	CommonsMultipartFile aFile=uploadFile;
	System.out.println("Saving file :" +aFile.getOriginalFilename());

	FileUpload fileUpload=new FileUpload();
	fileUpload.setFileName(aFile.getOriginalFilename());
	fileUpload.setData(aFile.getBytes());
	fileUpload.setUsername(users.getUsername());
	fileUploadDAO.save(fileUpload);

	FileUpload getFileUpload=fileUploadDAO.getFile(users.getUsername());
	String name=getFileUpload.getFileName();
	System.out.println(getFileUpload.getData());
	byte[] imagefiles=getFileUpload.getData();
	try{
	String path="D:/proj3/newbackend/src/main/webapp/WEB-INF/resources/image/" +users.getUsername();
	File file=new File(path);
	FileOutputStream fos=new FileOutputStream(file);
	fos.write(imagefiles);
	fos.close();

	}catch(Exception e){
	e.printStackTrace();
	}

	}
	return "Successfully added profile pic";
	}
	}