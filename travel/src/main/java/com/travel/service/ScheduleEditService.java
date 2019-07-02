package com.travel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.travel.model.Project;
//import com.travel.model.Schedule;
import com.travel.repository.ScheduleEditRepository;

@Service
public class ScheduleEditService {
	@Autowired
	ScheduleEditRepository scheduleEditRepository;
	
//	@Override
//	public Project updateSchedule(Schedule schedule) {
//		// TODO 自動生成されたメソッド・スタブ
//		return null;
//	}
}
