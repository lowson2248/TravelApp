package com.travel.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.model.Member;
import com.travel.model.Member.MemberId;
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
		
		/*Member member = new Member();
		MemberId memberId = member.new  MemberId(project.getProjectId(), user.getUserId());*/ 
		
		/*プロジェクトへのデータ挿入*/
		project.setUser(user);
		project.setProjectName(projectName);
		project.setStartDate(startDate);
		project.setLastDate(lastDate);
		project.setCreateDate(now);
		projectRepositry.saveAndFlush(project);
		
		//UserIｄをとってこようとすると、なぜかnullになる。
		/*member.setMemberId(memberId);
		member.setAuthId(1);*/
		//System.out.println("プロジェクト作成者ID 　"+ member.getMemberId().getUserId() + ": Authment(権限情報)　" +member.getAuthId() );
		
		//プロジェクトと最初期メンバー（幹事役のみ）保存
		projectRepositry.saveAndFlush(project);
		//memberRepositry.saveAndFlush(member);
		
		/*
		 * 制作者をメンバーとして登録する処理も必要
		 * MemberRepositoryとMemberService求ム！
		 * */
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

	@Override
	public List<Project> getProjectByUserId(Integer user_id) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}	

}
