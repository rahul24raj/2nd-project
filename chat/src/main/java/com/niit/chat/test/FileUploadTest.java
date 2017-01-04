package com.niit.chat.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.chat.dao.FileUploadDAO;
import com.niit.chat.model.FileUpload;



public class FileUploadTest
{
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		
	    FileUploadDAO fileuploadDAO= (FileUploadDAO) context.getBean("fileUploadDAO");

		FileUpload fileUpload=(FileUpload) context.getBean("fileUpload");

		//users.setId(9);
		
		fileUpload.setFileName("rahul");
		fileUpload.setFileName("is mad");
			String s="hello";
		byte b[]=s.getBytes();
		
		fileUpload.setData(b);
		
		
		fileuploadDAO.save(fileUpload);
	}

}
