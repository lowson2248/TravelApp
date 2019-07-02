package com.travel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.model.Project;
import com.travel.model.Schedule;
import com.travel.repository.ProjectRepository;
import com.travel.repository.ScheduleRepository;

@Service
public class SchesuleServiceImp implements ScheduleService{

	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	ScheduleRepository scheduleRepository;
	
	@Override
	public List<Schedule> find(Integer projectId) {
		Optional<Project> selectedProject = projectRepository.findById(projectId);
		System.out.println(selectedProject.get().getProjectName());
		selectedProject.get().getScheduleList().forEach(s -> System.out.println(s.getDetails()));
//		scheduleRepository.findByProject(selectedProject.get());
		return selectedProject.get().getScheduleList();
	}
}
