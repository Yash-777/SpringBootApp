package com.github.yash777.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.GzipResourceResolver;
import org.springframework.web.servlet.resource.PathResourceResolver;

/**
 * Static Resources «
 * You should serve static resources with a Cache-Control and conditional response headers for optimal performance.
 * 
 * 
 * 
 * @author yashwanth.m
 *
 */
// My Post = https://stackoverflow.com/a/47553586/5081877
@Configuration @EnableWebMvc
public class Static_ResourceHandler implements WebMvcConfigurer {

	/* Add handlers to serve static resources such as images, js, and, cssfiles
	 * from specific locations under web application root, the classpath,and others.
	 * 
	 * https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#boot-features-spring-mvc-static-content
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("WebMvcConfigurer - addResourceHandlers() function get loaded...");
		/**
		http://localhost:8080
							/handlerPath	/resource path+name
							/static			/css/myStatic.css
							/webapp			/css/style.css
							/templates		/style.css
		* */
		
		// When overriding default behavior, you need to add default(/) as well as added static paths(/webapp).
		
		// src/main/resources/static/...
		registry
			//.addResourceHandler("/**") // « /css/myStatic.css
			.addResourceHandler("/static/**") // « /static/css/myStatic.css
			.addResourceLocations("classpath:/static/") // Default Static Location
			.setCachePeriod( 3600 )
			.resourceChain(true) // 4.1
			.addResolver(new GzipResourceResolver()) // 4.1
			.addResolver(new PathResourceResolver()); //4.1
		
		// src/main/resources/templates/static/...
		registry
			.addResourceHandler("/templates/**") // « /templates/style.css
			.addResourceLocations("classpath:/templates/static/");
		
		// Do not use the src/main/webapp/... directory if your application is packaged as a jar.
		registry
		.addResourceHandler("/webapp/**") // « /webapp/css/style.css
		.addResourceLocations("/");
		
		// File located on disk
		registry
			.addResourceHandler("/system/files/**")
			.addResourceLocations("file:///D:/");
	}
}