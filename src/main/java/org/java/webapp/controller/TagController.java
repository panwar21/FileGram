package org.java.webapp.controller;

import java.util.List;

import org.java.webapp.model.FileTable;
import org.java.webapp.model.Tag;
import org.java.webapp.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/tag")
public class TagController {
	
	@Autowired
	private TagService tagService;
	
	@RequestMapping(value="/{tagName}", method=RequestMethod.GET)
	public String viewTag(ModelMap model,
			@PathVariable("tagName") String tagName) {
		
		Tag tag = tagService.getTagAllData(tagName);
		model.addAttribute("tag",tag);
		
		return "tags/viewTag";
	}

}
