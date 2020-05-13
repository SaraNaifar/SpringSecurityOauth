package com.openclassrooms.SpringSecurityOauth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//this annotation is to indicate that this class is the one reponsible for configuring security in app
@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	
	//Adding AuthenticationManagerBuilder for securing credentials
	@Override
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("springuser").password(passwordencode().encode("user123")).roles("USER")
			.and()
			.withUser("springAdmin").password(passwordencode().encode("admin123")).roles("ADMIN", "USER");
			
	}
	
	
	
	
	
	//this method is for authorization for each role according to their access
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/user").hasRole("USER")
			.and()
			.formLogin();
	}
	

	@Bean
	public PasswordEncoder passwordencode() {
		// TODO Auto-generated method stub
		return new BCryptPasswordEncoder();
	}

}
