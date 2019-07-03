package com.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.travel.model.User;
import com.travel.model.UserEditForm;
import com.travel.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@ModelAttribute
	public UserEditForm setupForm() {
		return new UserEditForm();
	}
	
	/* ユーザー編集画面へ遷移 */
	@GetMapping("/userEdit")
    public ModelAndView userEditForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/accountEdit");
		return mav;
    }
	
	/* ユーザー編集処理 */
	@PostMapping(path = "/userEdit")
	public ModelAndView editUser(@Validated UserEditForm userEditForm,BindingResult result,@AuthenticationPrincipal UserDetails userDetails) { 
		String redirect = userService.upDateUser(userDetails.getUsername(),userEditForm.getUsername(),userEditForm.getMailAddress(),userEditForm.getPassword());
		return new ModelAndView(redirect);
	}
	
	/* ユーザー削除処理 */
	@DeleteMapping(path = "/userEdit")
	public ModelAndView deleteUser(@AuthenticationPrincipal UserDetails userDetails) {
		userService.deleteUser(userDetails.getUsername());
		return new ModelAndView("/login");
	}
}
