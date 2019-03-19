package com.github.yash777.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
public class MvcConfig_ApplicationContext extends WebMvcConfigurerAdapter {
	
	Since 3.2
	AbstractAnnotationConfigDispatcherServletInitializer extends
	AbstractDispatcherServletInitializer extends
	AbstractContextLoaderInitializer implements WebApplicationInitializer
	
}

In Spring MVC, The DispatcherServlet needs to be declared and mapped for processing all requests
either using java or web.xmlconfiguration.

In a Servlet 3.0+ environment, you can use AbstractAnnotationConfigDispatcherServletInitializer
class to register and initialize the DispatcherServlet programmatically as follows.

 * @author yashwanth.m
 *
 */
public class MyFrontController_DispatcherServlet extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Deprecated @Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// List all the classes that implement WebMvcConfigurer to pass request form DS.
		return new Class[] { };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
