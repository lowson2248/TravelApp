package com.travel.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.model.Member;
import com.travel.model.Project;
import com.travel.model.ProjectEditForm;
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
	
	
	/* プロジェクトを一つだけ取得 */
	@Override
	public Project getOneProject(Integer project_id) {
		return projectRepositry.getOne(project_id);
	}

	/*
	 *  プロジェクト新規作成 
	 */
	@Override
	public int createProject(String projectName,Date startDate,Date lastDate,String mailAddress) {

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
		member.setProject(project);
		member.setUser(user);
		member.setAuthId(1);
		memberRepositry.saveAndFlush(member);

		return project.getProjectId();
	}
	
	/*
	 * プロジェクト編集
	 */
	@Override
	public int updateProject(Project project,String projectName,Date startDate,Date lastDate,String addAddres) {
		
		//project更新
		project.setProjectName(projectName);
		project.setStartDate(startDate);
		project.setLastDate(lastDate);
		
		
		//member更新
		if(addAddres != null && userRepository.findByMailAddress(addAddres) != null) {
			User user = userRepository.findByMailAddress(addAddres);
			Member member = new Member();
			member.setProject(project);
			member.setUser(user);
			member.setAuthId(3);
			memberRepositry.saveAndFlush(member);
		}
		
		//更新したプロジェクトを保存
		projectRepositry.saveAndFlush(project);
		return  project.getProjectId();
	}

	/*
	 *  プロジェクト削除
	 */
	@Override
	public void deleteProject(Integer project_id) {
		projectRepositry.deleteById(project_id);	
	}

	@Override
	public void deleteMember(Integer project_id) {
		memberRepositry.deleteById(project_id);		
	}

	@Override
	public List<Project> getProjectByUserId(Integer user_id) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}	
	
	public Project findById(int id){
		return projectRepositry.findByProjectId(id);
	}
	
	public void save(Project project) {
		projectRepositry.saveAndFlush(project);
	}

	
}
