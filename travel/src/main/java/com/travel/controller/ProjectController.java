package com.travel.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
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
import org.springframework.web.bind.annotation.RequestParam;
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

	@Autowired
	private final ProjectService projectServise;
	@Autowired
	private final ProjectRepository projectRepository;
	@Autowired
	private final MemberRepositry memberRepository;
	@Autowired
	private final UserRepository userRepository;
	@Autowired 
	MailSender mailSender;

	/*メール送信用*/
	public void MailSender(MailSender mailSender) {
	    this.mailSender = mailSender;
	}
	
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
		List<Member> memberList = memberRepository.findByUser(loginUser);

		mav.setViewName("project/projectSelect");
		
		//Viewに認識されるようにする
		mav.addObject("joinList",memberList);	
		return mav;
	}
	
	
	
	//プロジェクト新規登録画面表示処理
	@GetMapping(value = {"project/create"})
	public ModelAndView showProjectCreate(ModelAndView mav) {
		mav.setViewName("project/projectCreate");
		mav.addObject("projectForm",create());
		return mav;
	}
	
	
	
	//プロジェクト新規登録処理
	@PostMapping(value = {"project/createProject"})
	public ModelAndView saveProject(@ModelAttribute("project") @Validated ProjectForm projectForm, BindingResult result,@AuthenticationPrincipal UserDetails userDetails, ModelAndView mav) {	
		
		//プロジェクトを作成
		int projectId = projectServise.createProject(projectForm.getProjectName(), projectForm.getStartDate(), projectForm.getLastDate(),userDetails.getUsername());
		mav.setViewName("redirect:/project"+projectId+"/schedule");
		mav.addObject("projectForm",projectForm);
		return mav;
	}
	
	
	//プロジェクト編集画面表示処理
	@GetMapping(value = {"project{project_id}/edit"})
	public ModelAndView showEditProject(ModelAndView mav,@PathVariable("project_id") int projectId) {
		Project project = projectRepository.findByProjectId(projectId);
		mav.setViewName("project/projectEdit");
		mav.addObject("project",project);
		mav.addObject("projectId",projectId);
		return mav;
	}
	
	/*
	 * プロジェクト編集処理
	 */
	@PostMapping(value = {"project{project_id}/edit"})
	public ModelAndView editProject(@PathVariable("project_id") int projectId,@ModelAttribute("project") @Validated ProjectEditForm projectEditForm,BindingResult result,ModelAndView mav) {		
		
		//更新したいプロジェクトを取得
		Project project = projectRepository.findByProjectId(projectId);
		//プロジェクトを更新
		projectId = projectServise.updateProject(project,projectEditForm.getProjectName(), projectEditForm.getStartDate(), projectEditForm.getLastDate(),projectEditForm.getAddMemberList());
		System.out.println("===============================");
		for(String test : projectEditForm.getAddMemberList()) {
			System.out.println("追加されたユーザ"+test);
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
	        simpleMailMessage.setTo(test);
	        simpleMailMessage.setSubject("【返信不可】TravelNavi認証メール");
	        simpleMailMessage.setText(project.getProjectName()+"に招待されました。");
	        mailSender.send(simpleMailMessage);
		}
		System.out.println("===============================");
		

		/*View認識用処理*/
		mav.setViewName("redirect:/project"+projectId+"/schedule");
		mav.addObject("projectId",projectId);
		mav.addObject("projectName",project.getProjectName());
		
		return mav;
	}

	/*
	 *プロジェクト削除処理 
	 */
	@DeleteMapping(value = {"project{project_id}/edit"})
	public ModelAndView deleteProject(ModelAndView mav,@PathVariable int project_id) {
		projectServise.deleteProject(project_id);
		//projectServise.deleteMember(project_id);
		return new ModelAndView("redirect:/project/select");
	}
}
