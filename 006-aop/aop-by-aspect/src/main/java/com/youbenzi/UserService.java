package com.youbenzi;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@ExecutionTime
	public String name() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return "World";
	}
	
	public int num(int i) {
		return i;
	}
}
