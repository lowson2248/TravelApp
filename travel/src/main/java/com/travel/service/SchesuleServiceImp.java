package com.travel.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.model.Category;
import com.travel.model.Project;
import com.travel.model.Schedule;
import com.travel.model.ScheduleForm;
import com.travel.repository.CategoryRepository;
import com.travel.repository.ProjectRepository;
import com.travel.repository.ScheduleRepository;

@Service
public class SchesuleServiceImp implements ScheduleService{

	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	ScheduleRepository scheduleRepository;
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public List<Schedule> findAllSchadule(Integer projectId) {
		return projectRepository.findById(projectId).get().getScheduleList();
	}
	
	@Override
	public Schedule findOne(Integer scheduleId) {
		return scheduleRepository.findById(scheduleId).get();
	}
	
	//スケジュール新規登録データ保存
	@Override
	public Schedule create(ScheduleForm addForm) {
		Category category = categoryRepository.findById(addForm.getCateId()).get();
		Project project = projectRepository.findById(1).get();
		Schedule schedule = new Schedule();
		schedule.setScName(addForm.getTitle());
		String Start=addForm.getDay()+addForm.getStart();
		SimpleDateFormat startFormat = new SimpleDateFormat("yyyy-MM-ddhh:mm");
		startFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date startTime = null;
		try {
			startTime = startFormat.parse(Start);
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		schedule.setStartTime(startTime);
		String Last=addForm.getDay()+addForm.getEnd();
		SimpleDateFormat lastFormat = new SimpleDateFormat("yyyy-MM-ddhh:mm");
		lastFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date lastTime = null;
        try {
			lastTime = lastFormat.parse(Last);
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		schedule.setLastTime(lastTime);		
		schedule.setCategory(category);
		schedule.setDetails(addForm.getText());
		schedule.setProject(project);
		return scheduleRepository.saveAndFlush(schedule);
	}
}
