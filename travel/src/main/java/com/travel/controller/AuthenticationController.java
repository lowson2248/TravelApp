package com.travel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticationController {

	@GetMapping("/login")
    public ModelAndView loginForm() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
    }
	
	@GetMapping("/regist/registEmail")
	public ModelAndView registEmail() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("regist/regist");
		return modelAndView;
	}
}
