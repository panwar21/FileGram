package org.java.webapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Follower {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="Follwer")
	private int userFollowerId;
	
	@Column(name="Follwee")
	private int userFollowee;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserFollowerId() {
		return userFollowerId;
	}

	public void setUserFollowerId(int userFollowerId) {
		this.userFollowerId = userFollowerId;
	}

	public int getUserFollowee() {
		return userFollowee;
	}

	public void setUserFollowee(int userFollowee) {
		this.userFollowee = userFollowee;
	}


	
	
	

}
