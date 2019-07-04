package com.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.travel.model.User;
import com.travel.model.UserForm;
import com.travel.service.UserService;

@Controller
public class AuthenticationController {

	@Autowired
	UserService userService;
	
	private String mailAddress;
	
	@ModelAttribute
    public UserForm setupForm() {
        return new UserForm();
    }
	
	/* ログインフォームへ遷移 */
	@GetMapping("/login")
    public ModelAndView loginForm() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
    }
	
	/* メール送信後に遷移 */
	@PostMapping("/email/send")
	public String aaa(String email) {
		return "regist/regist2";
	}
	
	@GetMapping("/regist/registEmail")
	public ModelAndView registEmail() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("regist/regist");
		return modelAndView;
	}
	
	@GetMapping(path = "/regist/registUser",params = "mail")
	public ModelAndView registUser(@RequestParam("mail") String mail) {
		System.out.println(mail);
		mailAddress = mail;
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("regist/regist2");
		
		return modelAndView;
	}
	@PostMapping(path = "/regist/registUser")
	public String create(@Validated UserForm form,
				BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
	        return "login";
	    }
		
		//mailAddressが変な奴だとエラーでるのでとりあえずJSでバリデートしてほしい
		User user = new User();
		user.setMailAddress(mailAddress);
		user.setAccountName(form.getUsername());
		userService.createUser(user, form.getPassword());
		return "login";
	}
	
	@GetMapping("/project/projectSelect")
	public ModelAndView moveProject() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("project/projectSelect");
		return modelAndView;
	}
}
