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
	 * プロジェクト作成
	 */
	public void createProject(String projectName,Date startDate,Date lastDate,String mailAddress);

	public Project updateProject(Project project);
	public void deleteProject(Integer project_id);
	public void deleteMember(Integer project_id);
	public Project findById(int id);
	public void save(Project project);
	
	
	/*public void saveProject(Project project, Member member);
	public Project getProjectById(int project_id);
	void deleteProject(Project project_id);
	void deleteMember(Member project_id);
	*/
}
