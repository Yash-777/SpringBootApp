package com.github.yash777.configuration;

import javax.annotation.PostConstruct;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.yash777.componets.AppPropertiesComponent;

/**
 * Indicates that a class declares one or more <b>@Bean</b> methods and may be processed by the Spring container to
 * generate bean definitions and service requests for those beans at runtime, for example:
 * 
<pre>{@code
 @Configuration
 public class AppConfig {

     @Bean
     public MyBean myBean() {
         // instantiate, configure and return bean ...
     }
 }
}</pre>
 * 
 * @author yashwanth.m
 *
 */
@Configuration
public class TomcatHttpAndHttpsConfig {
	
	
	@Value("${app.http.port:8080}")
	private int httpPort;
	@Value("${app.https.port:8443}")
	private int httpsPort;
	
	@Value("${app.ssl.redirect:false}")
	private boolean sslRedirect;
	
	@Value("${app.domain}")
	private String applicationDomain;
	@PostConstruct
	public void print() {
		System.out.println("============");
		System.out.println("Application Domain Name : "+ applicationDomain);
		System.out.println("SSL Redirect            : "+ sslRedirect);
		System.out.println("============");
	}
	
	@Bean
	public ServletWebServerFactory servletContainer(){
		// embedded.tomcat.TomcatWebServer «  Tomcat started on port(s): 8443 (https) 8080 (http) with context path ''
		if( sslRedirect ) {
			return this.tomcatRedirectHttpToHttpsConfig();
		} else {
			return this.tomcatHttpToHttpsConfig();
		}
	}

	/* 
	 * https://localhost:8443/ - Response status 200
	 * http://localhost:8080/  - Response status 200
	 */
	private TomcatServletWebServerFactory tomcatHttpToHttpsConfig() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
		return tomcat;
	}
	
	/* 
	 * IN-Request 8080 - Redirect Request with status code 302 - In-Request 8443
	 * https://localhost:8443/ - Response status 200
	 * http://localhost:8080/  - Response status 302 - [ReDirect request with 8443] - status 200 
	 */
	private TomcatServletWebServerFactory tomcatRedirectHttpToHttpsConfig() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				securityConstraint.addCollection(collection);
				context.addConstraint(securityConstraint);
			}
		};
		tomcat.addAdditionalTomcatConnectors(redirectConnector());
		
		return tomcat;
	}
	
	private Connector initiateHttpConnector(){
		Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
		connector.setScheme("http");
		connector.setPort( this.httpPort );
		connector.setSecure(false);
		return connector;
	}
	
	private Connector redirectConnector(){
		Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
		connector.setScheme("http");
		connector.setPort( this.httpPort );
		connector.setSecure(false);
		connector.setRedirectPort( this.httpsPort );
		return connector;
	}
}