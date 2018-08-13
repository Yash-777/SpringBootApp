package com.github.yash777.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/view")
public class SampleController {
	@RequestMapping("/")
	@ResponseBody
	public String handler (Model model) {
		model.addAttribute("msg", "a spring-boot example");
		return "myPage";
	}

	@RequestMapping("/exception/runtime")
	@ResponseBody
	public void handler2 () {
		throw new RuntimeException("RuntimeException thrown from controller");
	}
	
	@RequestMapping("/exception")
	public void handler3 () throws Exception {
		throw new Exception("Exception thrown from controller");
	}
	
}
