package com.github.yash777.controllers.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = { "/urltest", "/url" })
public class URLFormatsTest {
	/**
	 * 
	 * https://dzone.com/articles/using-the-spring-requestmapping-annotation
	 * 
	 * http://localhost:8080/p/id?id=5
	 * 
	 * consumes = {"application/JSON","application/XML"}, consumes = MediaType.APPLICATION_JSON_VALUE
	 * headers = {"content-type=text/plain"}
	 */
	@RequestMapping(value = "/id",method = RequestMethod.GET)
    String getIdByValue(@RequestParam("id") String personId) {
        System.out.println("ID is " + personId);
        return "Get ID from query string of URL with value element : "+ personId;
    }
	
    @RequestMapping(value = "/fetch/{id}", method = RequestMethod.GET)
    String getDynamicUriValue(@PathVariable String id) {
        System.out.println("ID is " + id);
        return "Dynamic URI parameter fetched";
    }
    @RequestMapping(value = "/fetch/{id:[a-z]+}/{name}", method = RequestMethod.GET)
    String getDynamicUriValueRegex(@PathVariable("name") String name) {
        System.out.println("Name is " + name);
        return "Dynamic URI parameter fetched using regex";
    }
}
