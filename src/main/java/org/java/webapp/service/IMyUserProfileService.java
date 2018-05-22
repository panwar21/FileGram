package org.java.webapp.service;

import org.java.webapp.model.UserProfile;

public interface IMyUserProfileService {

	void createUserProfile(UserProfile userProfile);

	boolean updateUserProfileMerge(UserProfile userProfile);

	boolean deleteUserProfile(String userName);

	boolean checkIfUserProfileExists(String userName);
	
	UserProfile getUserProfileDetails(String userName);
	
	public UserProfile getUserProfileDetailsAllData(String userName);
	
	public void updateUserProfile(UserProfile entityUserProfile);
	
	

}