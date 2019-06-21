package com.travel.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.travel.model.Project;
import com.travel.service.ProjectService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProjectController {
	
	private final ProjectService projectServiseImpl;
	
	@GetMapping("/project")
	public ModelAndView showProject() {
		return new ModelAndView("redirect:/project/select");
	}
	
	//プロジェクト選択画面表示処理(未完)
	@GetMapping("/project/select")
	public ModelAndView showProjectSelect(ModelAndView mav) {
		mav.setViewName("project/projectSelect");
		return mav;
	}
	
	//プロジェクト新規登録画面表示処理
	@GetMapping(value = {"/project/create"})
	public ModelAndView showProjectCreate(ModelAndView mav) {
		mav.setViewName("project/projectCreate");
		return mav;
	}
	
	//プロジェクト新規登録処理
	@PostMapping(value = {"/project/create"})
	public ModelAndView saveProject(@ModelAttribute("project") @Validated Project project, ModelAndView mav) {
		
		mav.setViewName("project/projectSelect");
		return mav;
	}
	
	@GetMapping(value = {"/project/edit"})
	public ModelAndView showEditProject(ModelAndView mav) {
		mav.setViewName("addProject");
		return mav;
	}
	
	

}
