package com.youbenzi;

public class HelloService {

	private String name;

	public String hello() {
		return "Hello, " + name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
