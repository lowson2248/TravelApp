package com.travel.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.model.Project;
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

	/* テスト環境用プロジェクト新規作成 
	 * (制作者はtravel固定)
	 */
	@Override
	public void createProject(String projectName,Date startDate,Date lastDate) {

		//現在時間取得（作成日のため）
		Date now = new Date();
		//現在ログインしているユーザを取得する必要がある。
		//プロジェクト作成処理
		Project project = new Project();
		//テストとしてユーザ固定で作成
		project.setUser(userRepository.findByMailAddress("travel@tcmobile.jp"));
		project.setProjectName(projectName);
		project.setStartDate(startDate);
		project.setLastDate(lastDate);
		project.setCreateDate(now);
		//プロジェクト保存
		projectRepositry.saveAndFlush(project);
		
		/*
		 * 制作者をメンバーとして登録する処理も必要
		 * MemberRepositoryとMemberService求ム！
		 * */
	}
	
	/*通常稼働用プロジェクト新規作成 
	@Override
	public void createProject(User user,String projectName,Date startDate,Date lastDate) {
		//プロジェクト作成処理
		Project project = new Project();
		project.setUser(user);
		project.setProjectName(projectName);
		project.setStartDate(startDate);
		project.setLastDate(lastDate);
		//プロジェクト保存
		projectRepositry.saveAndFlush(project);
	}*/

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
