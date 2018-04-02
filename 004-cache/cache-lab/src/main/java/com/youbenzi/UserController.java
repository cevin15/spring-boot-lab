package com.youbenzi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class UserController {
	
	@Autowired 
	private UserService userService;
	
	@GetMapping(path = "/find")
	public String find(@RequestParam(defaultValue="World") String name) {
		long current = System.currentTimeMillis();
		String user = userService.findUser(name);
		return user + "，耗时：" + (System.currentTimeMillis() - current) + " ms";
	}

	@GetMapping(path = "/evict-cache")
	public String evictCache(@RequestParam(defaultValue="World") String name) {
		userService.evictUserCache(name);
		return "success";
	}
	
}