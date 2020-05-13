package com.openclassrooms.SpringSecurityOauth.Controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	
	
	@RolesAllowed("USER")
	@RequestMapping("/*")
	public String userLogin() {
		return "Hello User" ;
	}
	
	@RolesAllowed({"ADMIN", "USER"})
	@RequestMapping("/admin")
	public String adminLogin() {
		return "Welcome Admin";
	}
}
