package com.travel.controller;

import java.text.DateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.travel.model.ProjectForm;
import com.travel.service.ProjectService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProjectController {

	private final ProjectService projectServise;
	
	@ModelAttribute
	public ProjectForm create() {
		return new ProjectForm();
	}
	
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
	@GetMapping(value = {"/project/createProject"})
	public ModelAndView showProjectCreate(ModelAndView mav) {
		mav.addObject("projectForm",create());
		mav.setViewName("project/projectCreate");
		return mav;
	}
	
	//プロジェクト新規登録処理
	@PostMapping(value = {"/project/create"})
	public ModelAndView saveProject(@ModelAttribute("project") @Validated ProjectForm projectForm, BindingResult result,@AuthenticationPrincipal UserDetails userDetails, ModelAndView mav) {	
		
		/*テスト出力*/
		System.out.println("プロジェクト名 : " + projectForm.getProjectName());
		System.out.println("プロジェクト開始日 : " + projectForm.getStartDate());
		System.out.println("プロジェクト終了日 : " + projectForm.getLastDate());
		System.out.println("プロジェクト制作者 : " + userDetails.getUsername());
		
		//プロジェクトを作成
		projectServise.createProject(projectForm.getProjectName(), projectForm.getStartDate(), projectForm.getLastDate(),userDetails.getUsername());
		return new ModelAndView("project/projectSelect");
	}
	
	//プロジェクト編集画面表示処理
	@GetMapping(value = {"/project/edit"})
	public ModelAndView showEditProject(ModelAndView mav) {
		mav.setViewName("project/projectEdit");//projectEdit.htmlは未実装
		return mav;
	}
	
	

}
