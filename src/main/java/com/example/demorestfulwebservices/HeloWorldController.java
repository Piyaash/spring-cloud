package com.example.demorestfulwebservices;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeloWorldController {
 
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "Hello";
	}
	
	@GetMapping(path="/hello-world-bean/path-variable/{name}")
	public HelloWorldBean helloWorldbean(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello , %s", name));
	}
	
	@GetMapping("/hello-world-internationalized")
	public String helloWorldInternationalized() {
		//return "Good morning";
		return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
	}
	
}
