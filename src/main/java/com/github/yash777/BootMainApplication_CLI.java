package com.github.yash777;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.SpringVersion;

@SpringBootApplication(scanBasePackages = {"com.github.yash777.*"})
public class BootMainApplication_CLI implements CommandLineRunner {

	public static void main(String[] args) {
		
		System.out.println("@BootMainApplication_CLI : Main method");
		ApplicationContext applicationContext = SpringApplication.run(BootMainApplication.class, args);
		System.out.println("Application Context : "+ applicationContext.getId());
		System.out.println("SPRING VERSION: " + SpringVersion.getVersion());
	}
	
	/* Interface used to indicate that a bean should run when it is contained within a SpringApplication.
	 * Multiple CommandLineRunner beans can be defined within the same application context 
	 * and can be ordered using the Ordered interface or @Order annotation. 
	 * 
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	public void run(String... strings) throws Exception {
		// When main method run's Instead of calling controller's from HTTP
		// Using CLI to execute the functionality
	}
}
