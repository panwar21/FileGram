package org.java.webapp.service;

import javax.transaction.Transactional;

import org.java.webapp.dao.LikesDao;
import org.java.webapp.model.Likes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LikesService {
	
	@Autowired
	private LikesDao likesDao;
	
	public void save(Likes like) {
		likesDao.save(like);
	}
	
	public boolean fileLikedByUser(int userId, int fileId) {
		
		return likesDao.fileLikedByUser(userId, fileId);
	}

	public Likes getLike(int userId, int fileId) {
		
		return likesDao.getLike(userId, fileId);
	}

	public void unlike(Likes like) {
		likesDao.delete(like);
		
	}

}
