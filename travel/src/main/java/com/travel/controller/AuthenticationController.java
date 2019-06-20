package com.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.travel.model.User;
import com.travel.model.UserForm;
import com.travel.service.UserService;

@Controller
public class AuthenticationController {

	@Autowired
	UserService userService;
	
	@ModelAttribute
    public UserForm setupForm() {
        return new UserForm();
    }
	
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
	
	@GetMapping("/regist/registUser")
	public ModelAndView registUser() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("regist/regist2");
		return modelAndView;
	}
	@PostMapping("/regist/registUser")
	public String create(@Validated UserForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
	        return "login";
	    }
		System.out.println("form = " + form.getUsername() + form.getPassword());
		User user = new User();
		user.setMailAddress("rsasaki");
		user.setAccount_name(form.getUsername());
		userService.createUser(user, form.getPassword());
		return "login";
	}
}
