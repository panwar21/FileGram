package org.java.webapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


//@Configuration
//@EnableWebSecurity
public class FilegramSecurity extends WebSecurityConfigurerAdapter{
	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder authBuilder) throws Exception{
//		authBuilder
//		.inMemoryAuthentication()
//		.withUser("dogg1").password("dogg1").roles("User");
//	}
//		
//	/*
//	@Override
//	public void configure(WebSecurity web) {
//		web.ignoring().antMatchers("/login");
//		//do same for sign up as well.
//	}
//	*/
//	
//	@Override
//	protected void configure(HttpSecurity httpSecurity) throws Exception{
//		
//		httpSecurity
//		.authorizeRequests().anyRequest().authenticated()
//		.and()
//		.formLogin().loginPage("/login")
//        .loginProcessingUrl("/login").usernameParameter("userName").passwordParameter("passWord").permitAll();
//		/*.antMatchers("/**").access("hasRole('USER')")
//		.and()
//		.formLogin().loginPage("/login");
//		*/
//		/*
//		.access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
//        .antMatchers("/newuser/**", "/delete-user-*").access("hasRole('ADMIN')").antMatchers("/edit-user-*")
//        .access("hasRole('ADMIN') or hasRole('DBA')").and().formLogin().loginPage("/login")
//        .loginProcessingUrl("/login").usernameParameter("ssoId").passwordParameter("password").and()
//        .rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository)
//        .tokenValiditySeconds(86400).and().csrf().and().exceptionHandling().accessDeniedPage("/Access_Denied");
//		*/
//	
//	}

}
