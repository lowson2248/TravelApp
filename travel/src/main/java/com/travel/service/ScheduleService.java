package com.travel.service;

import java.util.List;

import com.travel.model.Project;
import com.travel.model.Schedule;

public interface ScheduleService {
	public List<Schedule> findAllSchadule(Integer projectId);
	public Schedule findOne(Integer scheduleId);
}
