package com.youbenzi;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	private int num = 100;
	
	public int getNum() {
		return num;
	}
	
	@ExecutionTime
	public String name(UserService userService) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return "World";
	}
}
