package com.youbenzi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogController {

	@Value("${server.port}")
	private String port;
	
	@RequestMapping(path = "/list")
	public String list() {
		return "blog1,blog2,blog3[" + port + "]";
	}
	
}