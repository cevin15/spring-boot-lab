package com.youbenzi;

import org.springframework.stereotype.Component;

@Component
public class UserServiceFallback implements UserService {

	@Override
	public String user() {
		return "[error from feign]";
	}

}
