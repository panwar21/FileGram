package org.java.webapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.java.webapp.model.FileTable;
import org.java.webapp.model.Likes;
import org.java.webapp.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LikesDao {
	
	@Autowired
	private SessionFactory sessionFactory; 
	
	public void save(Likes like) {
		sessionFactory.getCurrentSession().persist(like);
	}

	public boolean fileLikedByUser(int userId, int fileId) {
		String queryString = "from Likes where user = :user and file = :file";
		Query query = sessionFactory.getCurrentSession().createQuery(queryString);
		query.setInteger("user", userId);
		query.setInteger("file", fileId);
		@SuppressWarnings("unchecked")
		List<Likes> listResult = (List<Likes>)query.list();
		
		if(!listResult.isEmpty()) {
			Likes like = listResult.get(0);
			int likeType = like.getType();
			return likeType==1?true:false;
		}else {
			return false;
		}
		
		
	}

	public Likes getLike(int userId, int fileId) {
//		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Likes.class);
//		crit.add(Restrictions.eq("file", fileId));
//		crit.add(Restrictions.eq("user", userId));
//		Likes like = (Likes)crit.uniqueResult();
		
		String queryString = "from Likes where user = :user and file = :file";
		Query query = sessionFactory.getCurrentSession().createQuery(queryString);
		query.setInteger("user", userId);
		query.setInteger("file", fileId);
		@SuppressWarnings("unchecked")
		Likes like = (Likes)query.uniqueResult();
        return like;

	}

	public void delete(Likes like) {
		sessionFactory.getCurrentSession().delete(like);
		
	}

}
