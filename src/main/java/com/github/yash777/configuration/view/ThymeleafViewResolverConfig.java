package com.github.yash777.configuration.view;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * In this application, we configure the  Thymeleaf ViewResolver with the highest priority (order = 0).
 * 
 * https://www.thymeleaf.org/doc/articles/standarddialect5minutes.html
 * @author yashwanth.m
 *
 */
//@Configuration
public class ThymeleafViewResolverConfig {
	private static final String UTF8 = "UTF-8";
	
	@Bean
	public SpringResourceTemplateResolver springResourceTemplateResolver() {
		return new SpringResourceTemplateResolver();
	}
	
	@Bean
	public ViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine( templateEngine() );
		viewResolver.setCharacterEncoding( UTF8 );
		viewResolver.setOrder( ViewPriority.Thymeleaf.getId() );
		
		// Important!!  th_page1.html, th_page2.html, ... - "th_*"
		viewResolver.setViewNames(new String[] { "*", "*.js" });
		return viewResolver;
	}
	// Thymeleaf template engine with Spring integration
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setEnableSpringELCompiler(true);
		
		//templateEngine.setTemplateResolver( templateResolver() );
		
		Set<ITemplateResolver> templateResolvers = new HashSet<>();
		//new TreeSet<>(); « SpringResourceTemplateResolver cannot be cast to java.lang.Comparable
		templateResolvers.add( templateResolver() );
		templateResolvers.add( templateResolver_JAVASCRIPT() );
		
		templateEngine.setTemplateResolvers( templateResolvers );
		
		return templateEngine;
	}
	/*
	 * An error happened during template parsing 
	 * (template: "ServletContext resource [templates/error/404.html]")
	 * (template: "class path resource [templates/error.html]")
	 * */
	@Bean
	public ITemplateResolver templateResolver() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding( UTF8 );
		templateResolver.setCacheable(false);
		templateResolver.setOrder( 1 );
		
		return templateResolver;
	}
	
	@Bean
	public ITemplateResolver templateResolver_JAVASCRIPT() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		
		templateResolver.setPrefix("classpath:/templates/static/");
		//templateResolver.setSuffix(".js");
		
		templateResolver.setTemplateMode(TemplateMode.JAVASCRIPT);
		
		templateResolver.setCharacterEncoding(UTF8);
		templateResolver.setCheckExistence(true);
		
		templateResolver.setOrder( 2 );
		return templateResolver;
	}
	@Bean
	public ITemplateResolver templateResolver_CSS() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		
		templateResolver.setPrefix("classpath:/templates/static/");
		//templateResolver.setSuffix(".css");
		templateResolver.setTemplateMode(TemplateMode.CSS);
		
		templateResolver.setOrder( 3 );
		return templateResolver;
	}
	@Bean
	public ITemplateResolver templateResolver_XML() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("classpath:/templates/static/");
		//templateResolver.setSuffix(".xml");
		templateResolver.setTemplateMode(TemplateMode.XML);
		
		templateResolver.setOrder( 4 );
		return templateResolver;
	}
}
