package com.youbenzi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CacheLabApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CacheLabApplication.class, args);
	}
}