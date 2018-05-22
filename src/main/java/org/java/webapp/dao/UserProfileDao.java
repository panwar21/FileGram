package org.java.webapp.dao;



import org.hibernate.SessionFactory;

import org.java.webapp.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;


@Repository
public class UserProfileDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public void createUserProfile(UserProfile userProfile) {
		sessionFactory.getCurrentSession().persist(userProfile);
	}
	
	public UserProfile readUserProfile(String userName) {
		
		
		//logger.info("userName : {}", userName);
		 Criteria crit = sessionFactory.getCurrentSession().createCriteria(UserProfile.class);
		 crit.add(Restrictions.eq("userName", userName));
         UserProfile userProfile = (UserProfile)crit.uniqueResult();
         if(userProfile!=null){
        	  //Hibernate.initialize(userProfile.getUserName());
         }
        return userProfile;
		        
	}
	
	public void updateUserProfile(UserProfile userProfile) {

		sessionFactory.getCurrentSession().update(userProfile);
	}
	
	public void deleteUserProfile(UserProfile userProfile) {
		sessionFactory.getCurrentSession().delete(userProfile);
	}
	
	public Boolean checkIfUserProfileExists(String userName) {
		UserProfile profile = readUserProfile(userName);
		if(profile != null) {
			return true;
		}
		return false;
	}
	//TODO write these as individual methods
	public UserProfile getUserProfileAllData(String userName) {
		UserProfile profile = readUserProfile(userName);
		if(profile!=null) {
			Hibernate.initialize(profile.getFilesList());
			Hibernate.initialize(profile.getFollowSet());
			Hibernate.initialize(profile.getUserCommentsList());
			return profile;
		}
		return null;
	}

}
