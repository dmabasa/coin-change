package za.co.fnb.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


//This configuration creates a Servlet Filter known as the springSecurityFilterChain 
//which is responsible for all the security (protecting the application URLs, validating 
//submitted username and passwords, redirecting to the log in form, etc) within our application.

@Configuration //indicates that this class contains one or more bean methods annotated with 
//@Bean producing bean manageable by spring container
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	//configures AuthenticationManagerBuilder with user credentials and allowed roles
	//This AuthenticationManagerBuilder creates AuthenticationManager which is responsible for processing any authentication request
	//used in-memory authentication while you are free to choose from JDBC, LDAP and other authentications
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user1").password("password").roles("USER");
		auth.inMemoryAuthentication().withUser("user2").password("password").roles("ADMIN");
	}
	
	
	//configures HttpSecurity which allows configuring web based security for specific http requests
	//By default it will be applied to all requests, but can be restricted using requestMatcher(RequestMatcher)/antMathchers 
	//or other similar methods.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  
	  http.authorizeRequests()
	  	.antMatchers("/").access("hasRole('USER')")
	  	.and().formLogin().loginPage("/login")
	  	.usernameParameter("ssoId").passwordParameter("password")
	  	.and().csrf()
	  	.and().exceptionHandling().accessDeniedPage("/Access_Denied");
	}
}
