package com.github.yash777.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.GzipResourceResolver;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
@EnableWebMvc
public class StaticResourceConfiguration implements WebMvcConfigurer {

	// https://stackoverflow.com/a/47553586/5081877
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("WebMvcConfigurer - addResourceHandlers() function get loaded...");
		registry.addResourceHandler("/resources/static/**")
				.addResourceLocations("/resources/");
		
		// https://www.baeldung.com/spring-mvc-static-resources
		registry
			.addResourceHandler("/css/**")
			.addResourceLocations("/css/")
			.setCachePeriod(3600)
			.resourceChain(true)
			.addResolver(new GzipResourceResolver())
			.addResolver(new PathResourceResolver());
	}
	
	// https://stackoverflow.com/a/30256916/5081877
	/*@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("redirect:/index.html");
	}*/
}
