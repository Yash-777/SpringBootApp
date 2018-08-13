package com.github.yash777.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.yash777.componets.AppPropertiesComponent;
import com.github.yash777.services.AppPropertiesService;

@RestController
@RequestMapping(value = { "/properties", "/p" })
public class AppPropertiesController {
    /*@Autowired
    private ApplicationService applicationService;
    
    @RequestMapping("/getEnv")
    public void hello() {
        applicationService.printProperties();
    }*/
	
	@RequestMapping("/test")
    public String test() {
        return "test";
    }
	
    @Autowired
    private AppPropertiesService applicationService;
    @RequestMapping("/getAppProperties")  // It will return properties from application.properties file
    public AppPropertiesComponent hello() {
        return applicationService.getAppProperties();
    }
}
