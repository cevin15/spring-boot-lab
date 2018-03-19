package com.youbenzi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@Value("${server.port}")
	private String port;
	
	@RequestMapping(path = "/user")
	public String user() {
		return userService.user() + "[" + port + "]";
	}
}