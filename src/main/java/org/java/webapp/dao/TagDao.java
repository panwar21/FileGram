package org.java.webapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.java.webapp.model.FileTable;
import org.java.webapp.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TagDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Tag getTagLazyFetch(String tagName) {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Tag.class);
		criteria.add(Restrictions.eq("tagName", tagName));
		Tag tag = (Tag)criteria.uniqueResult();
		return tag;
	}
	
	public void save(Tag tag) {
		sessionFactory.getCurrentSession().persist(tag);
	}

	public Tag getTagAllData(String tagName) {
		
		Tag tag = getTagLazyFetch(tagName);
		if(tag!=null) {
			Hibernate.initialize(tag.getFilesTaggedList());
			return tag;
		}
		return null;
		
	}

	public void update(Tag tag) {
		sessionFactory.getCurrentSession().update(tag);
	}

}
