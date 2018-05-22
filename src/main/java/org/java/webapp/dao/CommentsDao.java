package org.java.webapp.dao;

import org.hibernate.SessionFactory;
import org.java.webapp.model.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentsDao {
	
	@Autowired
	private SessionFactory sessionFactory; 
	
	public void addComment(Comments comment) {
		sessionFactory.getCurrentSession().persist(comment);
	}
	
	public void deleteComment(Comments comment) {
		sessionFactory.getCurrentSession().delete(comment);
	}

}
