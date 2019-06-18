package com.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public class ProjectController {
	
	//@Autowired
	//ProjectService projectService;
	
	@GetMapping(value= {"/project"})
	public ModelAndView showChoiceProject(ModelAndView mav) {
		return new ModelAndView("redirect:/project/top");
	}

	//プロジェクト選択画面
	@GetMapping("/project/top")
	public ModelAndView showProjectTop(ModelAndView mav){
	
		/*List<Project> projectList = projectService.getAllProjects();
		mav.addObject("projectList",projectList);
		mav.setViewName("/schedule/top");*/
		return mav;
	}
	
	//プロジェクト新規作成画面
	@GetMapping("/project/add")
	public ModelAndView showProjectAdd(ModelAndView mav){
		mav.setViewName("/schedule/add");
		return mav;
	}
	
	//プロジェクト編集画面
	@GetMapping("/project/edit")
	public ModelAndView showProjectEdit(ModelAndView mav){
		mav.setViewName("/schedule/edit");
		return mav;
	}

}
