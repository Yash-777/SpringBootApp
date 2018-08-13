package com.github.yash777.componets;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

/**
 * Indicates that an annotated class is a "component". Such classes are considered as candidates for 
 * auto-detection when using annotation-based configuration and classpath scanning. 
 * 
 * <p>
 * Other class-level annotations may be considered as identifying a component as well, typically 
 * a special kind of component: e.g. the @Repository annotation or AspectJ's @Aspect annotation.
 * </p>
 * 
 * @author yashwanth.m
 *
 */
@Component
//@PropertySource("classpath:global.properties")
@PropertySources({
	@PropertySource(value = "classpath:global.properties", ignoreResourceNotFound=true),
	@PropertySource("classpath:config.properties")
})
@ConfigurationProperties(prefix = "app") // prefix app, find app.* values
// If key available in applicaiton.properties it will read,if not available the search in global.properties.
public class AppPropertiesComponent {
	private String domain;	 // it will contains app.domain propery value
	private String subdomain;
	private int domainCount;
	
	/*@Value("${app.http.port:8080}")
	private int httpPort;*/
	
	public String getDomain() {
		return domain;
	}
	public String getSubdomain() {
		return subdomain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public void setSubdomain(String subdomain) {
		this.subdomain = subdomain;
	}
	public int getDomainCount() {
		return domainCount;
	}
	public void setDomainCount(int domainCount) {
		this.domainCount = domainCount;
	}
}