package com.youbenzi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value="user-service", fallback=UserServiceFallback.class)
public interface UserService {

	@RequestMapping(value="/user")
	public String user();
}