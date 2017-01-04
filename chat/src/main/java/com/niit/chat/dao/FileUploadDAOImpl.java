package com.niit.chat.dao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.chat.dao.FileUploadDAO;
import com.niit.chat.model.FileUpload;
import com.niit.chat.model.Users;


@Repository("fileUploadDAO")
@EnableTransactionManagement
public class FileUploadDAOImpl implements FileUploadDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public FileUploadDAOImpl() {
	}

	public FileUploadDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@Transactional
	public void save(FileUpload fileUpload) {
		Session session=sessionFactory.getCurrentSession();
		session.save(fileUpload);
		//session.flush();
		//session.close();
	}

	@Transactional
	public FileUpload getFile(String username) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from UploadFile where username=?");
		query.setParameter(0, username);
        FileUpload fileUpload=(FileUpload)query.uniqueResult();
		session.close();
		return fileUpload;
	}

}
