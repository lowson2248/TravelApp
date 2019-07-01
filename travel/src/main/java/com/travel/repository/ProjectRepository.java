package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.Project;



public interface ProjectRepository extends JpaRepository<Project, Integer>{

	public Project findByProjectId(Integer projectId);

}
