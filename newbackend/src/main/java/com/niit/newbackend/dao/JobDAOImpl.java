package com.niit.newbackend.dao;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.newbackend.dao.JobDAO;
import com.niit.newbackend.model.Job;

@EnableTransactionManagement
@Repository("jobDAO")
public class JobDAOImpl implements JobDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	   public void addJob(Job job)
	   {
		  System.out.println("Entering to addUser.....");
		  sessionFactory.getCurrentSession().saveOrUpdate(job);
	   }
	
	
		
		@Transactional
		public void postJob(Job job) {
			Session session=sessionFactory.getCurrentSession();
			session.save(job);
			/*session.flush();
			session.close();*/
			

		}

		@Transactional
		public List<Job> getAllJobs() {
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("from Job");
			List<Job> jobs=query.list();
			session.close();
			return jobs;
		}
}