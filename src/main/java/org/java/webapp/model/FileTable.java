package org.java.webapp.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;




@Entity
public class FileTable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // null (0) when persisting. (null if you use the Integer / Long wrappers
	
	@Column
	private String fileName;
	
	@Column
	private String fileType;
	
	@ManyToMany
	@JoinTable(name="File_Tags",
	joinColumns=@JoinColumn(name="FILE_ID"),
	inverseJoinColumns=@JoinColumn(name="TAG_ID"))
	private Collection<Tag> tagsList = new ArrayList();
	
	@Column
	private String description;
	
	@Column
	@Lob
	private byte[] fileBlob;
	
	@Column
	private Date dateCreated;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID") 
	private UserProfile userProfile;
	
	@OneToMany
	@JoinTable(name="FILE_COMMENTS",
	joinColumns=@JoinColumn(name="FILE_ID"),
	inverseJoinColumns = @JoinColumn(name="COMMENT_ID"))
	private Collection<Comments> commentsList = new ArrayList<>();
	
	@Column
	private int views;
	
	@Column
	private Date lastActivityDate;
	
	
	
	
	public Collection<Comments> getCommentsList() {
		return commentsList;
	}

	public void setCommentsList(Collection<Comments> commentsList) {
		this.commentsList = commentsList;
	}

	public Collection<Tag> getTagsList() {
		return tagsList;
	}

	public void setTagsList(Collection<Tag> tagsList) {
		this.tagsList = tagsList;
	}

	public Date getLastActivityDate() {
		return lastActivityDate;
	}

	public void setLastActivityDate(Date lastActivityDate) {
		this.lastActivityDate = lastActivityDate;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	public byte[] getFileBlob() {
		return fileBlob;
	}

	public void setFileBlob(byte[] fileBlob) {
		this.fileBlob = fileBlob;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	
	
	

}
