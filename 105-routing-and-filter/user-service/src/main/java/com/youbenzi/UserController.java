package com.youbenzi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Value("${server.port}")
	private String port;
	
	@RequestMapping(path = "/list")
	public String list() {
		return "user1,user2,user3[" + port + "]";
	}
}