package com.youbenzi;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	private static final String[] USERS = new String[] {"Jack", "Rose", "Peter"};
	
	public String user() {
		Random random = new Random();
		return USERS[random.nextInt(USERS.length)];
	}
}