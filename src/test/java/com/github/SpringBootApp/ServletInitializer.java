package com.github.SpringBootApp;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.github.yash777.BootMainApplication;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		System.out.println("ServletInitializer : SpringApplicationBuilder");
		return application.sources(BootMainApplication.class);
	}

}
