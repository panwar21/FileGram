package org.java.webapp.service;

import javax.transaction.Transactional;

import org.java.webapp.dao.CommentsDao;
import org.java.webapp.model.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CommentsService {
	
	@Autowired
	private CommentsDao commentsDao;
	
	
	public void addComment(Comments comment) {
		commentsDao.addComment(comment);
	}
	
	public void readComment() {
		
	}
	
	public void updateComment(Comments comment) {
		
	}
	
	public void deleteComment(Comments comment) {
		
	}

}
