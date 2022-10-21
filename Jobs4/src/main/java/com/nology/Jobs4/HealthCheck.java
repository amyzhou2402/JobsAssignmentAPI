package com.nology.Jobs4;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")

public class HealthCheck {
	
	@GetMapping
	public String test() {
		return "hello world"; 
	}
	
	

}
