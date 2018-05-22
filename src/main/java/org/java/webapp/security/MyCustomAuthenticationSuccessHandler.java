package org.java.webapp.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;



public class MyCustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	protected Log logger = LogFactory.getLog(this.getClass());
	 
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	
	
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		session.setAttribute("userName", authentication.getName());
		logger.debug("in MyCustomAuthenticationSuccessHandler after spring authentication is done ");
		//System.out.println(authentication);
		//System.out.println(SecurityContextHolder.getContext().getAuthentication());
		
		//since we have created our custom success handler, its up to us to where
        //we will redirect the user after successfully login
        handle(request, response, authentication);
		
		
	}
	
    protected void handle(HttpServletRequest request, 
    	      HttpServletResponse response, Authentication authentication)
    	      throws IOException {
    	  
    	        String targetUrl = determineTargetUrl(authentication);
    	 
    	        if (response.isCommitted()) {
    	            logger.debug(
    	              "Response has already been committed. Unable to redirect to "
    	              + targetUrl);
    	            return;
    	        }
    	 
    	        redirectStrategy.sendRedirect(request, response, targetUrl);
    	    }
    	 
    	    protected String determineTargetUrl(Authentication authentication) {
    	        boolean isUser = false;
    	        boolean isAdmin = false;
    	        Collection<? extends GrantedAuthority> authorities
    	         = authentication.getAuthorities();
    	        for (GrantedAuthority grantedAuthority : authorities) {
    	            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
    	                isUser = true;
    	                break;
    	            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
    	                isAdmin = true;
    	                break;
    	            }
    	        }
    	 
    	        if (isUser) {
    	            return "/welcome";
    	        } else if (isAdmin) {
    	            return "/welcome-admin";
    	        } else {
    	            throw new IllegalStateException();
    	        }
    	    }
    	 
    	    protected void clearAuthenticationAttributes(HttpServletRequest request) {
    	        HttpSession session = request.getSession(false);
    	        if (session == null) {
    	            return;
    	        }
    	        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    	    }
    	 
    	    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
    	        this.redirectStrategy = redirectStrategy;
    	    }
    	    protected RedirectStrategy getRedirectStrategy() {
    	        return redirectStrategy;
    	    }

}
