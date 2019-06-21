package com.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.model.Member;
import com.travel.model.Project;
import com.travel.repository.MemberRepositry;
import com.travel.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	ProjectRepository projectRepositry;
	@Autowired
	MemberRepositry memberRepositry;
	
	@Override
	public List<Project> getProjectByUserId(Integer user_id) {
		// TODO 自動生成されたメソッド・スタブ	
		List<Project> projects = memberRepositry.findByMemberId_userId(user_id);
		return projects;
	}
	
	@Override
	public Project getOneProject(Integer project_id) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Project createProject(Project project) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
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
