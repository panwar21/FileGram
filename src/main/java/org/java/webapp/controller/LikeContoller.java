package org.java.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.java.webapp.model.FileTable;
import org.java.webapp.model.Likes;
import org.java.webapp.model.UserProfile;
import org.java.webapp.service.FileTableService;
import org.java.webapp.service.IMyUserProfileService;
import org.java.webapp.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LikeContoller {
	
	@Autowired
	@Qualifier("myUserProfileService")
	private IMyUserProfileService imyUserProfileService;
	
	@Autowired
	private FileTableService fileTableService;
	
	@Autowired
	private LikesService likesService;
	
	@RequestMapping(path = "/file/{id}/like", method = RequestMethod.POST)
	public String likeFile(ModelMap model,
			HttpServletRequest request,
			@PathVariable("id") int fileId) {
		
		String userName = (String)request.getSession().getAttribute("userName");
		UserProfile userProfile = imyUserProfileService.getUserProfileDetails(userName);
		FileTable fileTable = fileTableService.getFileAllData(fileId);
		
		Likes like = new Likes();
		like.setType(1);
		like.setFile(fileTable);
		like.setUser(userProfile);
		
		likesService.save(like);
		
		return "redirect:/file/"+fileId;
	}
	
	@RequestMapping(path = "/file/{id}/unlike", method = RequestMethod.POST)
	public String unlikeFile(ModelMap model,
			HttpServletRequest request,
			@PathVariable("id") int fileId) {
		
		String userName = (String)request.getSession().getAttribute("userName");
		UserProfile userProfile = imyUserProfileService.getUserProfileDetails(userName);
		FileTable fileTable = fileTableService.getFileAllData(fileId);
		
		Likes like = likesService.getLike(userProfile.getUserId(), fileId);

		
		likesService.unlike(like);
		
		return "redirect:/file/"+fileId;
	}
}
