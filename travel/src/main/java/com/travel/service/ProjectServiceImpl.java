package com.travel.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.model.Member;
import com.travel.model.Project;
import com.travel.model.User;
import com.travel.repository.MemberRepositry;
import com.travel.repository.ProjectRepository;
import com.travel.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	ProjectRepository projectRepositry;
	@Autowired
	MemberRepositry memberRepositry;
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<Project> getProjectByUserId(Integer user_id) {
		// TODO 自動生成されたメソッド・スタブ	
		List<Project> projects = memberRepositry.findByMemberId_userId(user_id);
		return projects;
	}
	
	
	/* プロジェクトを一つだけ取得 */
	@Override
	public Project getOneProject(Integer project_id) {
		return projectRepositry.getOne(project_id);
	}

	/*
	 *  プロジェクト新規作成 
	 */
	@Override
	public void createProject(String projectName,Date startDate,Date lastDate,String mailAddress) {

		//現在時間取得（作成日のため）
		Date now = new Date();
		//現在ログインしているユーザを取得する必要がある。
		//プロジェクト作成処理
		Project project = new Project();
		User user = userRepository.findByMailAddress(mailAddress);
		
		/*プロジェクトへのデータ挿入*/
		project.setUser(user);
		project.setProjectName(projectName);
		project.setStartDate(startDate);
		project.setLastDate(lastDate);
		project.setCreateDate(now);
		projectRepositry.saveAndFlush(project);
		
		//メンバーへのデータ挿入
		Member member = new Member();
		member.setMemberId(project.getProjectId(), user.getUserId());
		member.setAuthId(1);
		memberRepositry.saveAndFlush(member);

	}

	@Override
	public Project updateProject(Project project) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void deleteProject(Integer project_id) {
		// TODO 自動生成されたメソッド・スタブ	
	}

	@Override
	public void deleteMember(Integer project_id) {
		// TODO 自動生成されたメソッド・スタブ	
	}	

}
