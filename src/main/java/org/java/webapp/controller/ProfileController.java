package org.java.webapp.controller;


import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.java.webapp.model.FileTable;
import org.java.webapp.model.UserProfile;
import org.java.webapp.service.DataService;
import org.java.webapp.service.IMyUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/profile")
public class ProfileController {
	
	@Autowired
	@Qualifier("myUserProfileService")
	private IMyUserProfileService imyUserProfileService;
	
	@Autowired
	private DataService dataService;
	
	/*
	 * create
	 */
	@RequestMapping(path = "/signup", method = RequestMethod.GET)
	public String signUpGet() {
		
		return "profile/signup";
	}
	
	@RequestMapping(path = "/signup", method = RequestMethod.POST)
	public String signUpPost(@RequestParam String userName,
			@RequestParam String emailId,
			@RequestParam String passWord,
			@RequestParam String firstName,
			@RequestParam String lastName,
			@RequestParam String phoneNo,
			@RequestParam String address,
			ModelMap model) {
		
		//sanitation check of input values
		
		
		//create UserProfile object
		UserProfile userProfile = new UserProfile();
		userProfile.setUserName(userName);
		//bcrypt the password
		userProfile.setPassWord(passWord);
		userProfile.setEmailId(emailId);
		userProfile.setFirstName(firstName);
		userProfile.setLastName(lastName);
		userProfile.setPhoneNo(Long.parseLong(phoneNo));
		userProfile.setAddress(address);
		
		
		//check if user already exists
		boolean result = imyUserProfileService.checkIfUserProfileExists(userName);
		//if not create and insert new user in database
		if(!result) {
			
			imyUserProfileService.createUserProfile(userProfile);
			
		}else {
			
			model.addAttribute("errorMessage", "User with this user name already exists");
			return "profile/signup";
			
		}
		
		return "profile/signup-success";
	}
	
	/*
	 * read my own profile
	 */
	@RequestMapping(path = "/profile", method = RequestMethod.GET)
	public String readProfileGet(ModelMap model, HttpServletRequest request) {
		//show details of this user
		String userName = (String)request.getSession().getAttribute("userName");
		UserProfile userProfile = imyUserProfileService.getUserProfileDetails(userName);
		model.addAttribute("userName", userProfile.getUserName());
		model.addAttribute("emailId", userProfile.getEmailId());
		model.addAttribute("firstName", userProfile.getFirstName());
		model.addAttribute("lastName", userProfile.getLastName());
		model.addAttribute("address", userProfile.getAddress());
		model.addAttribute("phoneNo", userProfile.getPhoneNo());
		return "profile/profile";
	}
	
	/*
	 * read other user profile
	 */
	@RequestMapping(path = "/{otherUserName}/viewProfile", method = RequestMethod.GET)
	public String readProfileOtherUserGet(ModelMap model,
			HttpServletRequest request,
			@PathVariable("otherUserName") String otherUserName) {
		
		//show few details of other user
		//show all/top liked/downloaded files of the other user
		
		String userName = (String)request.getSession().getAttribute("userName");
		UserProfile userProfile = imyUserProfileService.getUserProfileDetails(otherUserName);
		if(userProfile!=null) {
			model.addAttribute("otherUserName", userProfile.getUserName());
			model.addAttribute("firstName", userProfile.getFirstName());
			model.addAttribute("lastName", userProfile.getLastName());
			
			List<FileTable> listFiles = dataService.getFilesUploadedByUser(otherUserName);
			long noOfFiles = dataService.getNoOfFilesUploadedByUser(otherUserName);
			model.addAttribute("listOfFiles", listFiles);
			model.addAttribute("noOfFiles", noOfFiles);
			
		return "profile/viewProfile";
		}else {
			model.addAttribute("message", "profile does not exist");
			return "profile/error";
		}
	}
	
	
	/*
	 * update
	 */
	@RequestMapping(path = "/edit", method = RequestMethod.GET)
	public String editProfileGet(HttpServletRequest request, ModelMap model) {
		//get details of the user
				String userName = (String)request.getSession().getAttribute("userName");
				UserProfile userProfile = imyUserProfileService.getUserProfileDetails(userName);
				model.addAttribute("userName", userProfile.getUserName());
				model.addAttribute("emailId", userProfile.getEmailId());
				model.addAttribute("firstName", userProfile.getFirstName());
				model.addAttribute("lastName", userProfile.getLastName());
				model.addAttribute("address", userProfile.getAddress());
				model.addAttribute("phoneNo", userProfile.getPhoneNo());
		return "profile/edit";
	}
	
	@RequestMapping(path = "/edit", method = RequestMethod.POST)
	public String editProfilePost(HttpServletRequest request,
			@RequestParam String emailId,
			@RequestParam String firstName,
			@RequestParam String lastName,
			@RequestParam String phoneNo,
			@RequestParam String address,
			ModelMap model,
			RedirectAttributes redirectAttributes) {
		
		//sanitation check of input values
		
		
		//create a user profile, fill it up and send it to service for update
		String userName = (String)request.getSession().getAttribute("userName");
		
		//update the object with input from user
		UserProfile userProfile = new UserProfile();
		userProfile.setUserName(userName);
		userProfile.setEmailId(emailId);
		userProfile.setFirstName(firstName);
		userProfile.setLastName(lastName);
		userProfile.setPhoneNo(Long.parseLong(phoneNo));
		userProfile.setAddress(address);
		
		boolean result = imyUserProfileService.updateUserProfileMerge(userProfile);
		
		if(!result) {
			
			redirectAttributes.addFlashAttribute("message", "Your profile could not be updated");
			return "redirect:profile";
			
		}
		//redirect it to view profile page or home page - use redirect attributes
		redirectAttributes.addFlashAttribute("message", "Your profile has been updated");
		return "redirect:profile";
	}
	
	/*
	 * delete
	 */
	
	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public String deleteProfilePost(HttpServletRequest request, ModelMap model) {
		//get principal
		String userName = (String)request.getSession().getAttribute("userName");
		model.addAttribute("userName", userName);
		boolean result = imyUserProfileService.deleteUserProfile(userName);
		if(!result) {
			
			model.addAttribute("errorMessage", "User could not be deleted");
			return "profile/delete-fail";
			
		}
		//log out user
		//invalidate user's session
		//invalidate the authentication object of this request
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				authentication.setAuthenticated(false);
				
				//clear the context              
				SecurityContextHolder.clearContext();
				//cookie invalid from now on
				for(Cookie cookie : request.getCookies()) {
					cookie.setMaxAge(0);
				}
						
				//invalidate the session
				HttpSession session = request.getSession(false);
				if (session != null) {
					session.invalidate();
				}
		//TODO - delete user's data in database??
		
		return "profile/delete-success";
	}
	
}
