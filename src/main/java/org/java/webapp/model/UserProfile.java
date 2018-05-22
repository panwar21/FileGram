package org.java.webapp.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
@Entity
public class UserProfile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Column(unique = true)
	@NotEmpty
	private String userName;
	
	@Column
	@NotEmpty
	private String passWord;
	
	@Column
	@NotEmpty
	private String emailId;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private long phoneNo;
	

	@Column
	private String address;
	
	@Column
	private String aboutMe;
	
	@Column
	private Date dateSignedUp;
	
	@Column
	private Date lastActiveDate;

	@Column
	private int noOfFilesUploaded;
	
	@Column
	private int noOfFilesDownLoaded;
	@Column
	private int noOfComments;
	@Column
	private int noOfLikesRecieved;
	
	@OneToMany
	@JoinTable(name="USER_Files",
	joinColumns=@JoinColumn(name="USER_ID"),
	inverseJoinColumns = @JoinColumn(name="FILE_ID"))
	private Collection<FileTable> filesList = new ArrayList<>();
	
	
	@OneToMany
	@JoinTable(name="USER_COMMENTS",
	joinColumns=@JoinColumn(name="USER_ID"),
	inverseJoinColumns = @JoinColumn(name="COMMENT_ID"))
	private Collection<Comments> userCommentsList = new ArrayList<>();
	
	
	@OneToMany
	@JoinTable(name="USER_ROLES",
	joinColumns=@JoinColumn(name="USER_ID"),
	inverseJoinColumns = @JoinColumn(name="ROLE_ID"))
	private Collection<Comments> userRolesList = new ArrayList<>();
	
	
	@ManyToMany
	@JoinTable(name="USER_FOLLOWER",
	joinColumns=@JoinColumn(name="FOLLOWER_ID"),
	inverseJoinColumns=@JoinColumn(name="FOLLOWEE_ID"))
	private Set<UserProfile> followSet = new HashSet();
	
	


	public Set<UserProfile> getFollowSet() {
		return followSet;
	}

	public void setFollowSet(Set<UserProfile> followSet) {
		this.followSet = followSet;
	}

	public Collection<Comments> getUserRolesList() {
		return userRolesList;
	}

	public void setUserRolesList(Collection<Comments> userRolesList) {
		this.userRolesList = userRolesList;
	}

	public Collection<Comments> getUserCommentsList() {
		return userCommentsList;
	}

	public void setUserCommentsList(Collection<Comments> userCommentsList) {
		this.userCommentsList = userCommentsList;
	}

	public Collection<FileTable> getFilesList() {
		return filesList;
	}

	public void setFilesList(Collection<FileTable> filesList) {
		this.filesList = filesList;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public Date getLastActiveDate() {
		return lastActiveDate;
	}

	public void setLastActiveDate(Date lastActiveDate) {
		this.lastActiveDate = lastActiveDate;
	}

	public int getNoOfFilesUploaded() {
		return noOfFilesUploaded;
	}

	public void setNoOfFilesUploaded(int noOfFilesUploaded) {
		this.noOfFilesUploaded = noOfFilesUploaded;
	}

	public int getNoOfFilesDownLoaded() {
		return noOfFilesDownLoaded;
	}

	public void setNoOfFilesDownLoaded(int noOfFilesDownLoaded) {
		this.noOfFilesDownLoaded = noOfFilesDownLoaded;
	}

	public int getNoOfComments() {
		return noOfComments;
	}

	public void setNoOfComments(int noOfComments) {
		this.noOfComments = noOfComments;
	}

	public int getNoOfLikesRecieved() {
		return noOfLikesRecieved;
	}

	public void setNoOfLikesRecieved(int noOfLikesRecieved) {
		this.noOfLikesRecieved = noOfLikesRecieved;
	}

	public Date getDateSignedUp() {
		return dateSignedUp;
	}

	public void setDateSignedUp(Date dateSignedUp) {
		this.dateSignedUp = dateSignedUp;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String toString() {
		return this.getUserName();
	}
	
}
