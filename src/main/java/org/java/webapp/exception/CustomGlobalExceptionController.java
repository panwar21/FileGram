package org.java.webapp.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

public class CustomGlobalExceptionController {
	
	
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@ExceptionHandler(value = Exception.class)
	public String handleGlobalException(HttpServletRequest request,
			HttpServletResponse response, Exception exception) {
		logger.error("Request: " + request .getRequestURL() + " raised " + exception);
		exception.printStackTrace();
		return "error/error-global";
	}

}
