package com.github.yash777.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfigurer interface provides the default methods
 * to customize the java-based configuration for Spring  MVC application.
 * 
 * @author yashwanth.m
 *
 */
@Configuration @EnableWebMvc
public class PreConfigured_ControllerResponse_Views implements WebMvcConfigurer {
	
	/*@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// A RequestDispatcher could not be located for the default servlet '/multiview/testJsp'
		String defaultServletName = "/multiview/testJsp";
		configurer.enable(defaultServletName);;
	}*/
	
	// https://stackoverflow.com/a/30256916/5081877
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		System.out.println("WebMvcConfigurer - addViewControllers() function get loaded...");
		// redirect:myAction, forward:myAction paths to another controller
		// https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/servlet/view/UrlBasedViewResolver.html
		registry.addViewController("/preconfigured/homepage").setViewName("redirect:/view/index");
		
		// thymeleaf static resources... « "src/main/resources/templates"
		// classpath:/templates/
		registry.addViewController("/preconfigured/resources").setViewName("Index.html");
		registry.addViewController("/preconfigured/resources/css").setViewName("static/style.css"); // [templates/static/**]
		registry.addViewController("/preconfigured/resources/js").setViewName("static/staticJS.js");
		
		// [/WEB-INF/pages/*.jsp] - [/WEB-INF/pages/error/404.jsp]
		registry.addViewController("/preconfigured/resources/thymleaf").setViewName("Index"); // (template: "templates/Index.html")
		registry.addViewController("/preconfigured/resources/jsp").setViewName("/preconfigured/myJSPView");
	}
}
