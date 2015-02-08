package com.spring.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping("/helloWorld")
	public String helloWorld(String userName, Model model) {
		System.out.println(userName);
		model.addAttribute("message", "Hello World!");
		String message = messageSource.getMessage("model.test.message", null, "Default", null);
		System.out.println(message);
		String message2 = messageSource.getMessage("argument.required",
				new Object [] {"userDao"}, "Required", null);
				System.out.println(message2);
		return "helloWorld";
	}
}
