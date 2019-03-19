package com.github.yash777.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * https://o7planning.org/en/11257/using-multiple-viewresolvers-in-spring-boot
 * @author yashwanth.m
 *
 */
@Controller
@RequestMapping("/multiview")
public class Multi_View_Controller {
	@RequestMapping(value = { "/testThymeleaf" }, method = RequestMethod.GET)
	public String testThymeleafView() {
		return "th_page1";
	}
	// When a controller gives a javascript view name, that name already includes the .js suffix.
	@RequestMapping(value = { "/testThymeleaf/js" }, method = RequestMethod.GET)
	public String testThymeleafView_JS() {
		return "message.js";
	}
	
	@RequestMapping(value = { "/testFreeMarker" }, method = RequestMethod.GET)
	public String testFreeMarkerView() {
		return "fm_page1";
	}
	@RequestMapping(value = { "/testJsp" }, method = RequestMethod.GET)
	public String testJspView() {
		return "jsp_page1";
	}
}
