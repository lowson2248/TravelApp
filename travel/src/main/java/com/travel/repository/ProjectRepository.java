package com.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.model.Member;
import com.travel.model.Project;
import com.travel.model.User;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
	//public List<Project> findByProjectid(int projectId);
	public List<Project> findByUser(User user);

	/* プロジェクト検索(ユーザID) */
	//public List<Project> findProjectsByUserId(int userId);
	
	/* プロジェクト検索(プロジェクトID) */
	//public List<Project> findProjectById(int projectId);
}
