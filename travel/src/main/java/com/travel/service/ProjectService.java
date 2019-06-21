package com.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.model.Project;
import com.travel.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;

	public Project findById(int id){
		return projectRepository.findByProjectId(id);
	}
	
	public void save(Project project) {
		projectRepository.saveAndFlush(project);
	}

}
