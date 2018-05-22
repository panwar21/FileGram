package org.java.webapp.dao;



import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.java.webapp.model.FileTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FIleTableDao {
	
	@Autowired
	private SessionFactory sessionFactory; 
	
	
	
	public void update(FileTable file) {
		sessionFactory.getCurrentSession().update(file);
	}
	
	public void delete(FileTable file) {
		sessionFactory.getCurrentSession().delete(file);
	}
	
	private void persist(FileTable file) {
		sessionFactory.getCurrentSession().persist(file);
	}
	
	public void save(FileTable file) {
		this.persist(file);
	}
	
	public FileTable getFileAllData(int fileId) {
		FileTable file = (FileTable)sessionFactory.getCurrentSession().get(FileTable.class, fileId);
		Hibernate.initialize(file.getTagsList());
		Hibernate.initialize(file.getCommentsList());
		return file;
	}

}
