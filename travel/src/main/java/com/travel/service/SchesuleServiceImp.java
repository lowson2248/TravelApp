package com.travel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.model.Project;
import com.travel.model.Schedule;
import com.travel.model.ScheduleForm;
import com.travel.repository.ProjectRepository;
import com.travel.repository.ScheduleRepository;

@Service
public class SchesuleServiceImp implements ScheduleService{

	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	ScheduleRepository scheduleRepository;
	
	@Override
	public List<Schedule> findAllSchadule(Integer projectId) {
		return projectRepository.findById(projectId).get().getScheduleList();
	}
	
	@Override
	public Schedule findOne(Integer scheduleId) {
		return scheduleRepository.findById(scheduleId).get();
	}

	@Override
	public void update(ScheduleForm scheduleForm, Schedule schedule) {
		String startTime = scheduleForm.getDay() + " " + scheduleForm.getStart();
		String endTime = scheduleForm.getDay() + " " + scheduleForm.getEnd();
		
		System.out.println(startTime);
		schedule.setScName(scheduleForm.getTitle());
		
	}
}
