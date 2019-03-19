package com.github.yash777.configuration.view;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

//@Configuration
public class FreeMarkerViewResolverConfig {

	@Bean(name = "viewResolver")
	public ViewResolver getViewResolver() {
		FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
		viewResolver.setCache(true);
		/*viewResolver.setPrefix("/freemarker/");
		viewResolver.setSuffix(".ftl");
		viewResolver.setOrder( ViewPriority.FreeMarker.getId() );*/
		//viewResolver.setPrefix("classpath:/public");
		viewResolver.setSuffix(".jsp");
		//viewResolver.setViewNames(new String[] { "fm_*" });
		return viewResolver;
	}
	
	@Bean(name = "freemarkerConfig")
	public FreeMarkerConfigurer freeMarkerConfigurer() {
		FreeMarkerConfigurer config = new FreeMarkerConfigurer();
		// Folder containing FreeMarker templates.
		// 1 - "/WEB-INF/views/"
		// 2 - "classpath:/templates"
		config.setTemplateLoaderPath("classpath:/public"); // templates
		return config;
	}
}
