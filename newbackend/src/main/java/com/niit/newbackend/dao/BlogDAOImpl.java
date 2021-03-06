package com.niit.newbackend.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.newbackend.dao.BlogDAO;
import com.niit.newbackend.model.BlogComment;
import com.niit.newbackend.model.BlogPost;
import com.niit.newbackend.model.User;
@Repository("blogDAO")
@EnableTransactionManagement
public class BlogDAOImpl implements BlogDAO 
{
	@Autowired
	private SessionFactory sessionFactory;
	public BlogDAOImpl(SessionFactory sessionFactory) 
	{
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<BlogPost> getBlogPosts() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from BlogPost");
		List<BlogPost> blogPosts=query.list();
		//session.close();
		return blogPosts;
	}

	

	@Transactional
	public BlogPost getBlogPost(int id) {
		Session session=sessionFactory.getCurrentSession();
		BlogPost blogPost=(BlogPost)session.get(BlogPost.class, id);
		/*session.close();*/
		return blogPost;
	}

	@Transactional
	public BlogPost addBlogPost(User user, BlogPost blogPost) {
		Session session=sessionFactory.getCurrentSession();
		blogPost.setCreatedBy(user);
		blogPost.setCreatedOn(new Date());
		session.save(blogPost);
		/*session.flush();*/
		BlogPost addedBlogPost=(BlogPost)session.get(BlogPost.class, blogPost.getId());
		return addedBlogPost;
	}

	@Transactional
	public BlogPost addBlogPostComment(User user, BlogComment blogComment) {
		Session session=sessionFactory.getCurrentSession();
	 blogComment.setCreatedBy(user);
	 blogComment.setCreatedOn(new Date());
	 BlogPost blogPost=(BlogPost)session.get(BlogPost.class, blogComment.getBlogPost().getId());
			 blogComment.setBlogPost(blogPost);
	 session.merge(blogComment);
	 //session.flush();
	 //session.close();
	 return blogPost;
	 
	}

	@Transactional
	public List<BlogComment> getBlogComments(int blogId) {
		Session session=sessionFactory.getCurrentSession();
		BlogPost blogPost=(BlogPost)session.get(BlogPost.class, blogId);
		List<BlogComment> blogComments=blogPost.getComments();
		/*session.close();*/
		return blogComments;
	}

}