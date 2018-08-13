package com.github.yash777.controllers.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingMessage {
	
	
	@RequestMapping("/exception")
	public void exception() {
		throw new RuntimeException("test exception");
	}
	
	@RequestMapping("/")
	public String home() {
		return "Spring Boot! « Home page.";
	}
	
	@RequestMapping("/msg")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	
	
}
