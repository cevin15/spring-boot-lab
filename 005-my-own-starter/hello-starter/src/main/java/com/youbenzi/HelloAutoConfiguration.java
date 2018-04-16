package com.youbenzi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {HelloProperties.class})
@ConditionalOnProperty(prefix = "youbenzi", name = "enable", matchIfMissing = true)
public class HelloAutoConfiguration {

	@Autowired
	private HelloProperties helloProperties;

	@Bean
	public HelloService helloService() {
		HelloService helloService = new HelloService();
		helloService.setName(helloProperties.getName());

		return helloService;
	}
}