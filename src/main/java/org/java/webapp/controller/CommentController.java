package org.java.webapp.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.java.webapp.model.Comments;
import org.java.webapp.model.FileTable;
import org.java.webapp.model.UserProfile;
import org.java.webapp.service.CommentsService;
import org.java.webapp.service.FileTableService;
import org.java.webapp.service.IMyUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommentController {
	
	@Autowired
	private FileTableService fileTableService;
	
	@Autowired
	@Qualifier("myUserProfileService")
	private IMyUserProfileService imyUserProfileService;
	
	@Autowired
	private CommentsService commentsService;
	
	@RequestMapping(value="/file/{id}/comment", method=RequestMethod.POST)
	public String createComment(@PathVariable("id") int fileId,
			HttpServletRequest request,
			String commentContent) {
		
		HttpSession httpSession = request.getSession();
		String userName = (String) httpSession.getAttribute("userName");
				
		
		
		
		
		Comments comment = new Comments();
		comment.setCommentContent(commentContent);
		comment.setDateCreated(new Date());
		
		FileTable fileTable = fileTableService.getFileAllData(fileId);
		UserProfile userProfile = imyUserProfileService.getUserProfileDetailsAllData(userName);
		
		//add all relationships
		fileTable.getCommentsList().add(comment);
		comment.setFile(fileTable);
		comment.setUser(userProfile);
		userProfile.getUserCommentsList().add(comment);
		
		//save or update all objects
		commentsService.addComment(comment);
		fileTableService.update(fileTable);
		
		imyUserProfileService.updateUserProfile(userProfile);
		
		return "redirect:/file/"+fileId;
	}

}
