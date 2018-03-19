package com.youbenzi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServicesCentreApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ServicesCentreApplication.class, args);
	}
}