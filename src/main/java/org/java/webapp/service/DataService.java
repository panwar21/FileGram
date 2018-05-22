package org.java.webapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.java.webapp.dao.DataDao;
import org.java.webapp.model.FileTable;
import org.java.webapp.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DataService {
	
	@Autowired
	private DataDao dataDao;
	
	@Autowired
	@Qualifier("myUserProfileService")
	private IMyUserProfileService imyUserProfileService; 
	
	
	public long getNoOfFilesUploadedByUser(String userName) {
		UserProfile userProfileEntity = imyUserProfileService.getUserProfileDetails(userName);
		if(userProfileEntity != null) {
			return dataDao.getNoOfFilesUploadedByUser(userProfileEntity.getUserId());
		}
		return -1;
	}
	
	public List<FileTable> getFilesUploadedByUser(String userName){
		UserProfile userProfileEntity = imyUserProfileService.getUserProfileDetails(userName);
		if(userProfileEntity != null) {
			return dataDao.getFilesUploadedByUser(userProfileEntity.getUserId());
		}
		return null;
	}
	

}
