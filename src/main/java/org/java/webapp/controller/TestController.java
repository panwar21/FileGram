package org.java.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/testing123")
public class TestController {
	
	//http://localhost:8080/Filegram/testing123/login
	@RequestMapping(path = "/login",method=RequestMethod.GET)
	public String test() {
		return "login/login";
	}
	
	@RequestMapping(path = "/login",method=RequestMethod.POST)
	public String testpagePostParams(@RequestParam String userName, @RequestParam String passWord, ModelMap model) {
		
		model.addAttribute("userName", userName);
		System.out.println();
		
		return "login/welcome";
	}
	
	//http://localhost:8080/Filegram/testing123/test?name=dog
	@RequestMapping(path = "/test",method=RequestMethod.GET)
	public String testpageRequestParam(@RequestParam String name, ModelMap model) {
		model.addAttribute("name", name);
		return "test";
	}
	
	//http://localhost:8080/Filegram/testing123/458/testing?name=dog
	@RequestMapping(path = "{testid}/testing",method=RequestMethod.GET)
	public String testpagePathParam(@RequestParam(required = false) String name, @PathVariable("testid") String testid, ModelMap model) {
		
		model.addAttribute("name", name);
		
		model.addAttribute("testid", testid);
		
		return "test";
	}
	
	
	//http://localhost:8080/Filegram/testing123/458/testinghttpservlet?name=dog
	@RequestMapping(path = "{testid}/testinghttpservlet",method=RequestMethod.GET)
	public String testpagePathParamTestinghttpservlet(@RequestParam(required = false) String name, @PathVariable("testid") String testid,
			ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		
		model.addAttribute("name", name);
		
		model.addAttribute("testid", testid);
		
		request.getAttribute("name");
		
		return "test";
	}
	

}
