package com.travel.service;

import java.util.List;

import com.travel.model.Schedule;
import com.travel.model.ScheduleForm;

public interface ScheduleService {
	public List<Schedule> findAllSchadule(Integer projectId);
	public Schedule findOne(Integer scheduleId);
	public void update(ScheduleForm scheduleForm, Schedule schedule, Integer projectId);
	public Schedule create(ScheduleForm addForm, Integer projectId); 
}
