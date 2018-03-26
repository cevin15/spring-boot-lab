package com.youbenzi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class UserController {
	
	@Value("${message:Hi, I am the message.}")
	private String message;
	
	@RequestMapping(path = "/message")
	public String message() {
		return message;
	}
}