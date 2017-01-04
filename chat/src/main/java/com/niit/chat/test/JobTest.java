package com.niit.chat.test;

import java.util.Date;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import com.niit.chat.dao.JobDAO;
import com.niit.chat.model.Job;


public class JobTest 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		
	    JobDAO jobDAO= (JobDAO) context.getBean("jobDAO");

		Job job=(Job) context.getBean("job");
		

		job.setJobTitle("HR");
		job.setJobDescription("HHH");
		job.setPostedOn(new Date());
		job.setSkillsRequired("Manage");
		job.setSalary("210000");
		job.setLocation("kerala");
		
		
		
		jobDAO.addJob(job);

	}

}
