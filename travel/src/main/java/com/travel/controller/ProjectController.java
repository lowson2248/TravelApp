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
	
	@GetMapping(value = {"/project"})
	public ModelAndView showProject() {
		return new ModelAndView("redirect:/project/select");
	}
	
	//プロジェクト選択画面表示処理(未完)
	@GetMapping(value = {"/project/select"})
	public ModelAndView showProjectSelect(ModelAndView mav) {
		List<Project> projectList = projectServiseImpl.getProjectByUserId(2);
		//String projectList = "くぁｗせｄｒｆｔｇｙふじこｌｐ";
		mav.addObject("projectList",projectList);
		mav.setViewName("project/projectSelect");
		return mav;
	}
	
	//プロジェクト新規登録画面表示処理
	@GetMapping(value = {"/project/create"})
	public ModelAndView showProjectCreate(ModelAndView mav) {
		String msgStr = "プロジェクト新規登録";
		mav.addObject("msg",msgStr);
		mav.setViewName("project/projectCreate");
		return mav;
	}
	
	//プロジェクト新規登録処理
	@PostMapping(value = {"/project/create"})
	public ModelAndView saveProject(@ModelAttribute("project") @Validated Project project, ModelAndView mav) {
		projectServiseImpl.createProject(project);
		String msgStr = "登録しました";
		mav.addObject("projectList",msgStr);
		mav.setViewName("project/projectSelect");
		return mav;
	}
	
	@GetMapping(value = {"/project/edit"})
	public ModelAndView showEditProject(ModelAndView mav) {
		String msgStr = "TestEditPage";
		mav.addObject("msg",msgStr);
		mav.setViewName("addProject");
		return mav;
	}
	
	

}
