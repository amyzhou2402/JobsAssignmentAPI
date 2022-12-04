package com.nology.Jobs4;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")

public class HealthCheck {
	
	@GetMapping
	public String test() {
		return "<p> home page <p/>";
	}
	
	@GetMapping("login")
	public String login() {
		return "please log in";
	}
	
	@GetMapping("profile")
	public String profile() {
		
		// if logged in return () else direct to /login
		
		return "please log in";
	}
	
	@GetMapping("temps")
	public String temps() {
		return "please log in";
	}
	
	@GetMapping("temps/{id}")
	public String tempsId() {
		return "please log in";
	}
	
	@GetMapping("jobs")
	public String jobs() {
		return "please log in";
	}
	
	@GetMapping("jobs/{id}")
	public String jobsId() {
		return "please log in";
	}
}
