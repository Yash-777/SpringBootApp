package com.github.yash777;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.SpringVersion;

/*@SpringBootApplication
@ComponentScan(value = {"com.github.yash777"})
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})

@ComponentScan(excludeFilters=@ComponentScan.Filter(type = FilterType.REGEX, pattern="com.github.yash777.controllers.res*"))
*/

@SpringBootApplication(scanBasePackages = {"com.github.yash777.*"})
public class BootMainApplication {

	public static void main(String[] args) {
		
		System.out.println("@BootMainApplication : Main method");
		ApplicationContext applicationContext = SpringApplication.run(BootMainApplication.class, args);
		System.out.println("Application Context : "+ applicationContext.getId());
		System.out.println("SPRING VERSION: " + SpringVersion.getVersion());
		/*
		« Controller [HTTP Requests] « service [Business Logic] « component [DB Operations]
		
		for (String beanName : applicationContext.getBeanDefinitionNames()) {
			System.out.println( beanName );
		}*/
	}
	
	/*@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}*/
}
/*
@SpringBootApplication is a convenience annotation that adds all of the following:
 « @Configuration tags the class as a source of bean definitions for the application context.
 « @EnableAutoConfiguration tells Spring Boot to start adding beans based on classpath settings,
   other beans, and various property settings.
 « Normally you would add @EnableWebMvc for a Spring MVC app, but Spring Boot adds it automatically
   when it sees spring-webmvc on the classpath. This flags the application as a web application and
   activates key behaviors such as setting up a DispatcherServlet.
 « @ComponentScan tells Spring to look for other components, configurations, and services in the
   "Current Package" package, allowing it to find the controllers.
   
@SpringBootApplication defines an automatic component scan on current package,
   But If you want to scan other packages you can list out like:
   `@ComponentScan(value = {"com.github.controllers"})`
   @ComponentScan({"com.github.controllers","com.github.controllers.tests"})
   
   XML Application Context
   <context:component-scan base-package="com.github.controllers" />
*/