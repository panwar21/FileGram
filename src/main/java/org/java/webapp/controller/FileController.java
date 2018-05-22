package org.java.webapp.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.java.webapp.model.FileTable;
import org.java.webapp.model.Tag;
import org.java.webapp.model.UserProfile;
import org.java.webapp.service.FileTableService;
import org.java.webapp.service.IMyUserProfileService;
import org.java.webapp.service.LikesService;
import org.java.webapp.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/file")
public class FileController {
	
	@Autowired
	private FileTableService fileTableService;
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	@Qualifier("myUserProfileService")
	private IMyUserProfileService imyUserProfileService;
	
	@Autowired
	private LikesService likesService;
	
	@Autowired
    ApplicationContext applicationContext;
	
	private void printBeansDispatcherServletContext() {
		System.out.println("************");
			System.out.println(applicationContext.getDisplayName());
	        System.out.println(Arrays.asList(applicationContext.getBeanDefinitionNames()));
	        System.out.println("************");
	    
	}
	
	//it did not work
	private void printBeansRootApplicationContext(HttpServletRequest request) {
		System.out.println("************");
		System.out.println(request.getServletContext().getServletContextName());
        //System.out.println(Arrays.asList(WebApplicationContextUtils.getRequiredWebApplicationContext(org.springframework.web.context.WebApplicationContext.ROOT);
        System.out.println("************");
    
	}
	
	@RequestMapping(path = "/upload", method = RequestMethod.GET)
	public String fileUpload(HttpServletRequest request, ModelMap model) {
		
		HttpSession httpSession = request.getSession();
		String userName = (String) httpSession.getAttribute("userName");
		model.addAttribute("userName", userName);
		//this.printBeansRootApplicationContext(request);
		this.printBeansDispatcherServletContext();
		return "file/upload";
		
	}
	
	
	@RequestMapping(path = "/upload", method = RequestMethod.POST)
	public String fileUploadPost(@RequestParam("fileName") MultipartFile file,
			String tagsString,
			RedirectAttributes redirectAttributes,
			HttpServletRequest request,
			ModelMap model
			) throws Exception{
		
		HttpSession httpSession = request.getSession();
		String userName = (String) httpSession.getAttribute("userName");
		model.addAttribute("userName", userName);
				
		if(file.isEmpty()) {
			redirectAttributes.addFlashAttribute("uploadStatus", "failure");
			return "redirect:uploadStatus";
		}
		
		UserProfile userProfile = imyUserProfileService.getUserProfileDetailsAllData(userName);
		byte[] fileBytes = this.readFileInBytes(file);
		FileTable fileTable = new FileTable();
		fileTable.setFileName(file.getOriginalFilename());
		//String fileType = file.getOriginalFilename().split(".")[1];
		//fileTable.setFileType(fileType);
		
		Collection<Tag> tagsList = fileTable.getTagsList();
		//System.out.println(tagsString);
		//TODO sanitize the tagsString here
		String[] tagsArray = tagsString.split(" ");
		for(String tagName : tagsArray) {
			//Tag tag = tagService.getTagLazyFetch(tagName);
			Tag tag = tagService.getTagAllData(tagName);
			if(tag!=null) {
				System.out.println("tag files list = " + tag.getFilesTaggedList());
				tag.getFilesTaggedList().add(fileTable);
				
				//TODO check if line below is needed
				tagService.updateTag(tag);
				tagsList.add(tag);
			}else {
				//this tag does not exists in database, lets create it
				tag = new Tag();
				tag.setTagName(tagName);
				tag.setDateCreation(new Date());
				tag.getFilesTaggedList().add(fileTable);
				tagService.saveTag(tag);
				tagsList.add(tag);
			}
		}
		
		fileTable.setDateCreated(new Date());
		fileTable.setFileBlob(fileBytes);
		
		//add all relationships
		fileTable.setUserProfile(userProfile);
		userProfile.getFilesList().add(fileTable);
		
		//save or update all objects
		fileTableService.save(fileTable);
		imyUserProfileService.updateUserProfile(userProfile);
		
			
		redirectAttributes.addFlashAttribute("uploadStatus", "success");
		return "redirect:uploadStatus";
		
		
		
	}
	
	@RequestMapping(path = "/uploadStatus", method = RequestMethod.GET)
	public String fileUploadStatus(HttpServletRequest request,
			ModelMap model) {
		
		HttpSession httpSession = request.getSession();
		String userName = (String) httpSession.getAttribute("userName");
		model.addAttribute("userName", userName);
		return "file/uploadStatus";
		
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public String viewFileInfo(@PathVariable("id") int fileId, HttpServletRequest request,
			ModelMap model) {
		
		//TODO check if this id is int
		//check if file with this id exists and 
		//this user has permission to view it
		
		HttpSession httpSession = request.getSession();
		String userName = (String) httpSession.getAttribute("userName");
		UserProfile userProfile = imyUserProfileService.getUserProfileDetails(userName);
		FileTable file = fileTableService.getFileAllData(fileId);
		String uploader = file.getUserProfile().getUserName();
		boolean isFilelikedByUser = likesService.fileLikedByUser(userProfile.getUserId(), fileId);
		//System.out.println("noOfComments = " + file.getCommentsList().size());
		model.addAttribute("userName", userName);
		model.addAttribute("uploader", uploader);
		model.addAttribute("file", file);
		model.addAttribute("isFileLikedByUser", isFilelikedByUser);
		return "file/fileDetails";
		
	}
	
	@RequestMapping(path = "download/{id}", method = RequestMethod.GET)
	public void downloadFile(@PathVariable("id") int fileId, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		
		//TODO check if this id is int
		//check if file with this id exists and 
		//this user has permission to view it
		
		HttpSession httpSession = request.getSession();
		String userName = (String) httpSession.getAttribute("userName");
		model.addAttribute("userName", userName);
		FileTable file = fileTableService.getFileAllData(fileId);
		byte[] fileBytes = file.getFileBlob();
		String fileNameFull = file.getFileName();
		
		//inform browser about the content type
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition",
		"attachment;filename="+fileNameFull);
		//write file to response output stream
		InputStream in = new ByteArrayInputStream(fileBytes);
		ServletOutputStream outputStream = null;
		try { 
			outputStream = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] outputByte = new byte[4096];
		//copy binary connect to output stream
		try {
			while(in.read(outputByte, 0, 4096) != -1)
			{
				outputStream.write(outputByte, 0, 4096);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			in.close();
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@RequestMapping(path = "editDetails/{id}", method = RequestMethod.GET)
	public String editFileGet(HttpServletRequest request,
			@PathVariable("id") int id,
			ModelMap model) {
		
		FileTable file = fileTableService.getFileAllData(id);
		model.addAttribute("file", file);
		return "file/editDetails";
	}
	
	@RequestMapping(path = "editDetails/{id}", method = RequestMethod.POST)
	public String editFilePost(HttpServletRequest request,
			@PathVariable("id") int id,
			String fileName,
			String tags,
			RedirectAttributes redirectAttributes,
			ModelMap model
			) throws Exception{
		
		HttpSession httpSession = request.getSession();
		String userName = (String) httpSession.getAttribute("userName");
		model.addAttribute("userName", userName);
		
		UserProfile userProfile = imyUserProfileService.getUserProfileDetails(userName);

		FileTable fileTable = new FileTable();
		fileTable.setFileName(fileName);
		//fileTable.setDateLastEdited(new Date());
		//fileTable.setTags(tags);
		boolean result = fileTableService.update(fileTable, id);
		
		if(result) {	
			redirectAttributes.addFlashAttribute("editMessage", "file details edited successfully");
			return "redirect:/file/"+id;
		}
		redirectAttributes.addFlashAttribute("editMessage", "file details could not be edited");
		return "redirect:/file/"+id;
		
	}
	
	@RequestMapping(path = "delete/{id}", method = RequestMethod.POST)
	public String deleteFile(HttpServletRequest request,
			@PathVariable("id") int id,
			ModelMap model) {
		
		HttpSession httpSession = request.getSession();
		String userName = (String) httpSession.getAttribute("userName");
		model.addAttribute("userName", userName);
		
		
		FileTable file = fileTableService.getFileAllData(id);
		
		if(file!=null) {
			
			model.addAttribute("fileName", file.getFileName());
			fileTableService.delete(file);
		
			model.addAttribute("message", "file deleted succeesfully");
		}else {
			model.addAttribute("message", "file could not be deleted");
		}
		return "file/deleted";
	}
	
	
	
	
	
	
	private byte[] readFileInBytes(MultipartFile file) {
		byte[] biteArrayFile = null;
		try {
			biteArrayFile = file.getBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return biteArrayFile;
	}
	
	/* upload save the file in a folder
	@RequestMapping(path = "/upload", method = RequestMethod.POST)
	public String fileUploadPost(@RequestParam("fileName") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		
		String assetsPath = "C:\\Users\\panwar\\eclipse-workspace\\Filegram\\src\\main\\webapp\\assets\\images\\";
		
		if(file.isEmpty()) {
			//set upload message here
			redirectAttributes.addFlashAttribute("uploadStatus", "failure");
			return "redirect:uploadStatus";
		}
		
		String fileName = file.getOriginalFilename();
		try {
			//create directory if it does not exist
			if(!new File(assetsPath).exists()) {
				new File(assetsPath).mkdirs();
			}
			//transfer file
			file.transferTo(new File(assetsPath + fileName));
			redirectAttributes.addFlashAttribute("uploadStatus", "success");
			return "redirect:uploadStatus";
		}catch(IOException e) {
			
		}
		return "fileUpload/uploadStatus";
	}
	*/

}
