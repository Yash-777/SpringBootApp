package com.github.yash777.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.yash777.componets.AppPropertiesComponent;

@Service
public class AppPropertiesService {
	
	@Autowired
	private AppPropertiesComponent appProperties;
	public AppPropertiesComponent getAppProperties(){
		return appProperties;
	}
}