package org.java.webapp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(path = "/")
public class LoginController {
	
	
	protected Log logger = LogFactory.getLog(this.getClass());
	/*
	@RequestMapping(path = "/welcome", method = RequestMethod.GET)
	public String welcome(ModelMap model, HttpServletRequest request) {
		List<String> listTest = new ArrayList();
		listTest.add("List1");
		listTest.add("List2");
		model.addAttribute("list", listTest);
		return "welcome";
	}
	*/
	
	
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String loginGet() {
		
		return "login/login";
	}
	
	/*
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String loginPost(@RequestParam("userName") String userName, 
			@RequestParam("passWord") String password,
			HttpServletRequest request, ModelMap model) {
		model.addAttribute("userName", userName);
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("userName", userName);
		
//		System.out.println("in post request");
//		System.out.println(httpSession.toString());
//		System.out.println(httpSession.getCreationTime() + " time at request = " + new Date());
//		System.out.println(httpSession.getId());
//		System.out.println("isNew session = " + httpSession.isNew());
//		//System.out.println(sessionRegistry.getAllPrincipals().toString());
		
		System.out.println("in login controller session id = " + httpSession.getId());
		return "login/welcome";
	}
	*/
	@RequestMapping(path = "/test", method = RequestMethod.GET)
	public String loginTest(HttpServletRequest request, ModelMap model) {
		
		
		
		
		HttpSession httpSession = request.getSession();
		String userName = (String) httpSession.getAttribute("userName");
		model.addAttribute("userName", userName);
		/*
		System.out.println(httpSession.toString());
		System.out.println(httpSession.getCreationTime() + " time at request = " + new Date());
		System.out.println(httpSession.getId());
		System.out.println("isNew session = " + httpSession.isNew());
		//System.out.println(sessionRegistry.getAllPrincipals().toString());
		*/
		System.out.println(httpSession.getId());
		return "login/testWelcome";
	}
	
	/**
	 * implementing my own logout - not using spring security default logout
	 * more details on - https://stackoverflow.com/questions/36557294/spring-security-logout-does-not-work-does-not-clear-security-context-and-authe
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(path = "/logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
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
		
		//new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY)
		//.logout(request, response, null);
		logger.debug("doing my own custom logout");
		//System.out.println("custom logout happening : jsession_id = " + session.getId());
		
		/*
		System.out.println(httpSession.toString());
		System.out.println(httpSession.getCreationTime() + " time at request = " + new Date());
		System.out.println(httpSession.getId());
		System.out.println("isNew session = " + httpSession.isNew());
		//System.out.println(sessionRegistry.getAllPrincipals().toString());
		*/
		return "login/logout";
	}
	
	
}
