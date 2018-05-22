package org.java.webapp.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.java.webapp.model.FileTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DataDao {
	
	@Autowired 
	private SessionFactory sessionFactory;
	
	public long getNoOfFilesUploadedByUser(int userId ) {
		
		String queryString = "select count(*) from FileTable where userProfile = :userProfile";
		Query query = sessionFactory.getCurrentSession().createQuery(queryString);
		query.setInteger("userProfile", userId);
		@SuppressWarnings("unchecked")
		List<Long> listResult = (List<Long>)query.list();
		long res = listResult.get(0);
		return res;
	}
	
	public List<FileTable> getFilesUploadedByUser(int userId){
		String queryString = "from FileTable where userProfile = :userProfile";
		Query query = sessionFactory.getCurrentSession().createQuery(queryString);
		query.setInteger("userProfile", userId);
		@SuppressWarnings("unchecked")
		List<FileTable> listResult = (List<FileTable>)query.list();
		for(FileTable fileTable : listResult) {
			Hibernate.initialize(fileTable.getTagsList());
		}
		return listResult;
	}

}
