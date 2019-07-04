package com.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.travel.model.Member;
import com.travel.model.Project;
import com.travel.model.User;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
	//public List<Project> findByProjectid(int projectId);

	/* プロジェクト検索(ユーザID) */
	public List<Project> findAll();
	public List<Project> findByUser(User user);
	public Project findByProjectId(int projectId);
}