package org.java.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.java.webapp.model.FileTable;
import org.java.webapp.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@Autowired
	private DataService dataService;
	
	@RequestMapping(path="welcome", method=RequestMethod.GET)
	public String welcomePage(HttpServletRequest request, ModelMap model) {
		String userName = (String)request.getSession().getAttribute("userName");
		List<FileTable> listFiles = dataService.getFilesUploadedByUser(userName);
		long noOfFiles = dataService.getNoOfFilesUploadedByUser(userName);
		model.addAttribute("listOfFiles", listFiles);
		model.addAttribute("noOfFiles", noOfFiles);
		
		
		return "home/welcome";
	}
	
	
}
