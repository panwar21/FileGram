package org.java.webapp.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique=true)
	private String tagName;
	
	private String description;
	
	private Date dateCreation;
	
	@ManyToMany(mappedBy="tagsList")
	private Collection<FileTable> filesTaggedList = new ArrayList();
	
	private int noOfFiles;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Collection<FileTable> getFilesTaggedList() {
		return filesTaggedList;
	}

	public void setFilesTaggedList(Collection<FileTable> filesTaggedList) {
		this.filesTaggedList = filesTaggedList;
	}

	public int getNoOfFiles() {
		return noOfFiles;
	}

	public void setNoOfFiles(int noOfFiles) {
		this.noOfFiles = noOfFiles;
	}
	
	public String toString() {
		return this.getTagName();
	}
	
	

}
