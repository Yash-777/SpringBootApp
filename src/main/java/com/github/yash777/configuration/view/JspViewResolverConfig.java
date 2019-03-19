package com.github.yash777.configuration.view;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

//@Configuration
public class JspViewResolverConfig {
	@Bean
	public ViewResolver jspViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/pages/");
		//viewResolver.setPrefix("classpath:/public"); resource/public
		viewResolver.setSuffix(".jsp");
		viewResolver.setContentType("text/html");
		
		//viewResolver.setViewNames(new String[] { "jsp_*" });
		// Make sure > Thymeleaf order & FreeMarker order.
		viewResolver.setOrder( ViewPriority.Jsp.getId() );
		
		return viewResolver;
	}
	/*public void configureViewResolvers(ViewResolverRegistry registry) {
		System.out.println("WebMvcConfigurer - configureViewResolvers() function get loaded...");
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/pages/");
		resolver.setSuffix(".jsp");
		//resolver.setViewClass(JstlView.class);
		registry.viewResolver(resolver);
	}*/
}

