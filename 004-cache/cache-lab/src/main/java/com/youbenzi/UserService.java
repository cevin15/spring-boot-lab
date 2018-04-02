package com.youbenzi;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Cacheable(value="user", key="#name")
//	@Cacheable(value="user", keyGenerator="ConstantKeyGenerator")
	public String findUser(String name) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "[User] " + name;
	}
	
	@CacheEvict(value="user", key="#name", beforeInvocation=true)
//	@Caching(evict = {@CacheEvict(value="user1", key="#name"), @CacheEvict(value="user2", key="#name")})
//	@CacheEvict(value="user", keyGenerator="ConstantKeyGenerator")
	public void evictUserCache(String name) {
	}
	
}