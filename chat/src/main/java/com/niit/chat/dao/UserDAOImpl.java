package com.niit.chat.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.chat.model.Users;

@EnableTransactionManagement
//connects to DB by taking attribute from pojo class
@Repository("userDAO")
public class UserDAOImpl implements UserDAO
{
	//IT WILL CREATE AN OBJECT WITHOUT HELP OF NEW OPERATOR
	@Autowired
	private SessionFactory sessionFactory;
	
	private UserDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	//used to transcation from model to dao class
	@Transactional
	public void addUser(Users user)
	{
		sessionFactory.getCurrentSession().save(user);
	}
}
