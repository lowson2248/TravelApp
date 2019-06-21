package com.travel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SceduleController {
	
	@GetMapping("/scedule")
	public ModelAndView showScedule() {
		return new ModelAndView("redirect:/scedule/top");
	}
	
	@GetMapping("/scedule/top")
	public ModelAndView showSceduleTop(ModelAndView mav) {
		mav.setViewName("scedule/scedule");
		return mav;
	}
	
	@GetMapping("/scedule/detail")
	public ModelAndView showSceduleDetail(ModelAndView mav) {
		mav.setViewName("scedule/sceduleDetail");
		return mav;
	}
	
	
	@GetMapping("/scedule/add")
	public ModelAndView showSceduleAdd(ModelAndView mav) {
		mav.setViewName("scedule/sceduleAdd");
		return mav;
	}
	
	@GetMapping("/scedule/edit")
	public ModelAndView showSceduleEdit(ModelAndView mav) {
		mav.setViewName("scedule/sceduleEdit");
		return mav;
	}

}
