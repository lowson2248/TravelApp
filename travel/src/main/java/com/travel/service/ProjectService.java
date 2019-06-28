package com.travel.service;

import java.util.Date;
import java.util.List;

import com.travel.model.Member;
import com.travel.model.Project;
import com.travel.model.User;

public interface ProjectService {
	public List<Project> getProjectByUserId(Integer user_id);	
	public Project getOneProject(Integer project_id);
	
	
	/* 
	 * テスト環境用のプロジェクト作成
	 * （作成者固定）
	 */
	public void createProject(String projectName,Date startDate,Date lastDate,String mailAddress);
	
	/*
	 * 本番および通常稼働用のプロジェクト作成処理。
	 * public void createProject(String projectName,User user,Date startDate,Date lastDate);
	 */
	public Project updateProject(Project project);
	public void deleteProject(Integer project_id);
	public void deleteMember(Integer project_id);
	
	
	/*public void saveProject(Project project, Member member);
	public Project getProjectById(int project_id);
	void deleteProject(Project project_id);
	void deleteMember(Member project_id);
	*/
}