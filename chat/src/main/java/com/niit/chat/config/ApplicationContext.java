package com.niit.chat.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import com.niit.chat.model.Users;


//connection to database//


@Configuration
@ComponentScan("com.niit")
@EnableTransactionManagement
public class ApplicationContext
{
	@Bean(name="dataSource")
	public DataSource getDataSource()
	{
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jbdc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1522:XE");
		dataSource.setUsername("system");
		dataSource.setPassword("oracle");
		return dataSource;
	}
	
	
	private Properties getHibernateProperties()
	{
		Properties properties =new Properties();
		properties.put("hibernate.show_sql","true");
		properties.put("hibernate.dialect","org.hibernate.dialect.oracle10gDialect");
		properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;
	}
	
	//contents in database//
	
	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource)
	{
		LocalSessionFactoryBuilder sessionBuilder=new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClass(Users.class);
	
		

		//sessionBuilder.addAnnotatedClass(UserDetails.class);
		System.out.println("connected");
		return sessionBuilder.buildSessionFactory();
	}
	
	@Autowired
	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
		HibernateTransactionManager transactionManager=new HibernateTransactionManager(sessionFactory);
		System.out.println("Transaction...");
		return transactionManager;
	}
	
}
