package org.java.webapp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.java.webapp.dao.UserProfileDao;
import org.java.webapp.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("myUserProfileService")
@Transactional
public class MyUserProfileService implements UserDetailsService, IMyUserProfileService{
	
	@Autowired
	private UserProfileDao userProfileDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	public void createUserProfile(UserProfile userProfile) {
		String password = passwordEncoder.encode(userProfile.getPassWord());
		userProfile.setPassWord(password);
		userProfileDao.createUserProfile(userProfile);
	}
	
	public UserProfile getUserProfileDetails(String userName) {
		return userProfileDao.readUserProfile(userName);
	}
	
	public UserProfile getUserProfileDetailsAllData(String userName) {
		return userProfileDao.getUserProfileAllData(userName);
	}
	
	//TODO do it by merge
	public boolean updateUserProfileMerge(UserProfile userProfile) {
		
		UserProfile entity = getUserProfileDetails(userProfile.getUserName());
		if(entity!=null) {
			entity.setEmailId(userProfile.getEmailId());
			//String password = passwordEncoder.encode(userProfile.getPassWord());
			//entity.setPassWord(password);
			entity.setFirstName(userProfile.getFirstName());
			entity.setLastName(userProfile.getLastName());
			entity.setPhoneNo(userProfile.getPhoneNo());
			entity.setAddress(userProfile.getAddress());
			
			userProfileDao.updateUserProfile(entity);
			
			return true;
		}
		
		return false;
		
	}
	
	@Override
	public void updateUserProfile(UserProfile entityUserProfile) {
		userProfileDao.updateUserProfile(entityUserProfile);
	}
	

	public boolean deleteUserProfile(String userName) {
		UserProfile entity = getUserProfileDetails(userName);
		if(entity!=null) {
			userProfileDao.deleteUserProfile(entity);
			return true;
		}
			return false;
	}
	

	public boolean checkIfUserProfileExists(String userName) {
		return userProfileDao.checkIfUserProfileExists(userName);
		
	}

	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		System.out.println("tried login by -- " + userName);
		UserProfile userProfile = userProfileDao.readUserProfile(userName);
		if(userProfile != null) {
			return new User(userName, userProfile.getPassWord(), true, true, true, true, getAllGrantedAuthorities(userProfile));
		}
		else {
			throw new UsernameNotFoundException("user name not found");
		}
	}
	
	private List<GrantedAuthority> getAllGrantedAuthorities(UserProfile userProfile) {
		//TODO by user roles in database
		List<GrantedAuthority> listGrantedAuthorities = new ArrayList<GrantedAuthority>();
		
		listGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		return listGrantedAuthorities;
	}

}
