package com.travel.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import com.travel.model.Project;
import com.travel.model.ProjectEditForm;
import com.travel.model.User;
import com.travel.service.LoginUserDetails;
import com.travel.model.Member;
import com.travel.model.Project;
import com.travel.model.ProjectForm;
import com.travel.model.User;
import com.travel.repository.MemberRepositry;
import com.travel.repository.ProjectRepository;
import com.travel.repository.UserRepository;
import com.travel.service.ProjectService;
import com.travel.service.ProjectServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProjectController {

	private final ProjectService projectServise;
	private final ProjectRepository projectRepository;
	private final UserRepository userRepository;
	private final MemberRepositry memberRopository;
	
	@ModelAttribute
	public ProjectForm create() {
		return new ProjectForm();
	}
	
	@ModelAttribute
	public ProjectEditForm updateForm() {
		return new ProjectEditForm();
	}
	
	LoginUserDetails userDetail;
	ProjectService projectService;
	
	@GetMapping("/project")
	public ModelAndView showProject() {
		return new ModelAndView("redirect:/project/select");
	}
	
	//プロジェクト選択画面表示処理(未完)
	@GetMapping("/project/select")
	public ModelAndView showProjectSelect(ModelAndView mav,@AuthenticationPrincipal UserDetails userDetails) {
		
		/*ユーザが参加しているプロジェクトの表示*/
		User loginUser = userRepository.findByMailAddress(userDetails.getUsername());
		List<Member> memberList = memberRopository.findByUser(loginUser);

		//Viewに認識されるようにする
		mav.addObject("joinList",memberList);		
		/*遷移先は本来なら作成したプロジェクトのスケジュール画面！*/
		mav.setViewName("project/projectSelect");
		return mav;
	}
	
	
	
	//プロジェクト新規登録画面表示処理
	@GetMapping(value = {"/project/create"})
	public ModelAndView showProjectCreate(ModelAndView mav) {
		mav.addObject("projectForm",create());
		mav.setViewName("project/projectCreate");
		return mav;
	}
	
	
	
	//プロジェクト新規登録処理
	@PostMapping(value = {"/project/createProject"})
	public ModelAndView saveProject(@ModelAttribute("project") @Validated ProjectForm projectForm, BindingResult result,@AuthenticationPrincipal UserDetails userDetails, ModelAndView mav) {	
		
		/*テスト出力*/
		System.out.println("プロジェクト名 : " + projectForm.getProjectName());
		System.out.println("プロジェクト開始日 : " + projectForm.getStartDate());
		System.out.println("プロジェクト終了日 : " + projectForm.getLastDate());
		System.out.println("プロジェクト制作者 : " + userDetails.getUsername());
		
		//プロジェクトを作成
		int projectId = projectServise.createProject(projectForm.getProjectName(), projectForm.getStartDate(), projectForm.getLastDate(),userDetails.getUsername());
		System.out.println("プロジェクトID："+projectId);
		mav.addObject("projectForm",projectForm);
		mav.setViewName("redirect:/project"+projectId+"/schedule");
		
		return mav;
	}
	
	
	//プロジェクト編集画面表示処理
	@GetMapping(value = {"/project{project_id}/edit"})
	public ModelAndView showEditProject(ModelAndView mav,@PathVariable("project_id") int projectId) {
		mav.addObject(projectId);
		mav.setViewName("project/projectEdit");//projectEditは未実装
		return mav;
	}
	
	/*
	 * プロジェクト編集処理
	 */
	@PostMapping(value = {"/project{project_id}/edit"})
	public ModelAndView editProject(@PathVariable("projectI_d") int projectId,@Validated ProjectEditForm projectEditForm,BindingResult result) {
		System.out.println("===================");
		System.out.println(projectId);
		System.out.println(projectEditForm.getProjectName());
		System.out.println(projectEditForm.getStartDate());
		System.out.println(projectEditForm.getLastDate());
		System.out.println("===================");
		projectService.updateProject(projectId,projectEditForm.getProjectName(),projectEditForm.getStartDate(),projectEditForm.getLastDate());
		
		return new ModelAndView("redirect:/project"+projectId+"/schedule");
	}
	
	/*
	 *プロジェクト削除処理 
	 */
	@DeleteMapping(value = {"/project{project_id}/edit"})
	public ModelAndView deleteProject(ModelAndView mav,@PathVariable int project_id) {
		projectServise.deleteProject(project_id);
		//projectServise.deleteMember(project_id);
		return new ModelAndView("redirect:/project/select");
	}
}
