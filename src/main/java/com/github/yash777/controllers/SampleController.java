package com.github.yash777.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/view")
public class SampleController {
	@RequestMapping("/index")
	@ResponseBody
	public String handler (Model model) {
		System.out.println("http://localhost:8080/view/index");
		model.addAttribute("msg", "As view is present, So returning view otherwise return message as String");
		model.addAttribute("title", "Woa this works!");
		return "myPage";
	}
	@RequestMapping("/jsp")
	public ModelAndView helloWorld() {
		System.out.println("http://localhost:8080/view/jsp");
		String message = "Hello World, Spring 3.0!";
		return new ModelAndView("myPage", "message", message); 
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/js")
	public String testjs() {
		return "staticJS";
	}
	
	@RequestMapping("/publicjsp")
	public ModelAndView jspViewsPublic() {
		System.out.println("http://localhost:8080/view/publicjsp");
		String message = "Hello World, Spring 3.0!";
		return new ModelAndView("myJSPView", "message", message); 
	}
	
	@RequestMapping("/publichtml")
	public ModelAndView htmlViewsPublic() {
		System.out.println("http://localhost:8080/view/publicjsp");
		String message = "Hello World, Spring 3.0!";
		return new ModelAndView("myView", "message", message); 
	}
	
	@RequestMapping("/jsp_html")
	public ModelAndView jspViews() {
		System.out.println("http://localhost:8080/view/jsp_html");
		String message = "Hello World, Spring 3.0!";
		return new ModelAndView("myJSPView", "message", message); 
	}
	
	
	@RequestMapping("/error")
	//@ResponseBody - as response has not committed it throws error.html
	public String handlerErrorPage (Model model) {
		model.addAttribute("title", "Woa this won't works! as response has not committed");
		return "noViewPage";
	}
	
	@RequestMapping("/viewName")
	@ResponseBody
	public String handlerString (Model model) {
		model.addAttribute("msg", "As view is present, So returning view otherwise return message as String");
		
		return "view Name, as view is not present in server.";
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