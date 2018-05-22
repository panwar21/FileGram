package org.java.webapp.model;

import java.io.Serializable;

public enum UserRolesType implements Serializable{

	USER("USER"),
    DBA("DBA"),
    ADMIN("ADMIN");
     
    String UserRolesType;
     
    private UserRolesType(String userRolesType){
        this.UserRolesType = userRolesType;
    }
     
    public String getUserProfileType(){
        return UserRolesType;
    }
	
}
