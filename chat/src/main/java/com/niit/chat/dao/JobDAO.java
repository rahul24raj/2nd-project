package com.niit.chat.dao;


import java.util.List;

import com.niit.chat.model.Job;



public interface JobDAO 
{
	public void postJob(Job job);
	public List<Job> getAllJobs();
	public void addJob(Job job);
	
}

