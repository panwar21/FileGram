package org.java.webapp.security;


import java.util.Collection;


import org.java.webapp.service.MyUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
	 
	 
//@Component
public class MyCustomAuthenticationProvider implements AuthenticationProvider{
	 
	    @Autowired
	    private MyUserProfileService userProfileService;
	   
	    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	          String userName = authentication.getName();
	          String password = (String) authentication.getCredentials();
	          System.out.println("username, password = " + userName + ", " + password);
	            UserDetails user = userProfileService.loadUserByUsername(userName);
	     
	            if (user == null || !user.getUsername().equalsIgnoreCase(userName)) {
	                throw new BadCredentialsException("Username not found.");
	            }
	     
	            if (!password.equals(user.getPassword())) {
	                throw new BadCredentialsException("Wrong password.");
	            }
	     
	            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
	     
	            return new UsernamePasswordAuthenticationToken(user, password, authorities);
	    }
	 
	    public boolean supports(Class<?> arg0) {
	        return true;
	    }
	 
	}
