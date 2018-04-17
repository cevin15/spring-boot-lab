package com.youbenzi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@Autowired
	private UserService userservice;
	
	@RequestMapping(path = "/hello")
	public String hello() {
		return "Hello, " + userservice.name(userservice);
	}
}
