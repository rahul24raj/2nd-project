package com.niit.chat.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;


import com.niit.chat.model.Users;

@SuppressWarnings("deprecation")
@EnableTransactionManagement
@Repository("usersDAO")
public class UsersDAOImpl implements UsersDAO
{
Logger logger=LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/*@Transactional
	   public void addUsers(Users users)
	   {
		logger.debug("Entering into addUser.....");
		  sessionFactory.getCurrentSession().saveOrUpdate(users);
	   }*/
	
	@Transactional
	public Users authenticate(Users users) {
		logger.debug("USERDAOIMPL :: AUTHENTICATE");
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(
		"from Users where username=?  and password=?");
		//select * from User where username='smith' and password='123'
		query.setString(0, users.getUsername());
		query.setString(1, users.getPassword());
		Users validUser=(Users)query.uniqueResult();
		//session.flush();
		//session.close();
		if(validUser!=null)
		logger.debug("VALID USER IS  " + validUser.getUsername() + " " + validUser.getRole() + " " + validUser.isOnline());;
		if(validUser==null)
			logger.debug("Valid USER is null");
		return validUser;
		
	}
	
	@Transactional
	public void updateUsers(Users users) {
		logger.debug("USERDAOIMPL::UPDATE");
		logger.debug("ISONLINE VALUE IS [BEFORE UPDATE]" + users.isOnline());
		Session session=sessionFactory.getCurrentSession();
		Users existingUsers=(Users)session.get(Users.class, users.getId());
		//update online status as true
		existingUsers.setOnline(users.isOnline()); 
		
		session.update(existingUsers);
		//session.flush();
		//session.close();
		logger.debug("ISONLINE VALUE IS [AFTER UPDATE] " + existingUsers.isOnline());
	}
	
	@Transactional
	public Users registerUsers(Users users) {
		logger.debug("USERDAOIMPL - registerUsers");
		Session session=sessionFactory.getCurrentSession();
		session.save(users);
		//session.flush();
		//session.close();
		logger.debug("User id in Dao " + users.getId());
		return users;
		
			}
	@Transactional
	public List<Users> usersList()
	{
		List<Users> list= (List<Users>) sessionFactory.getCurrentSession().createCriteria(Users.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return list;
				
	}

	
	@Override
	public List<Users> getAllUsers(Users user) {
		Session session=sessionFactory.openSession();
		SQLQuery query=session.createSQLQuery("select * from proj2_user where username in (select username from proj2_user where username!=? minus(select to_id from friend where from_id=? union select from_id from friend where to_id=?))");
		query.setString(0, user.getUsername());
		query.setString(1, user.getUsername());
		query.setString(2, user.getUsername());
		query.addEntity(Users.class);
		List<Users> users=query.list();
		System.out.println(users);
		session.close();
		return users;
	}
}
