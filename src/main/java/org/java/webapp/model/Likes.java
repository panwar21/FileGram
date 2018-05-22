package org.java.webapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Likes {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="FILE_ID")
	private FileTable file;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private UserProfile user;
	
	private int type; //+1 or -1

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FileTable getFile() {
		return file;
	}

	public void setFile(FileTable file) {
		this.file = file;
	}

	public UserProfile getUser() {
		return user;
	}

	public void setUser(UserProfile user) {
		this.user = user;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
	

}
