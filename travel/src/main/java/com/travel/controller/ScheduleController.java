package com.travel.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.travel.model.Project;
import com.travel.model.Schedule;
import com.travel.model.ScheduleForm;
import com.travel.service.ProjectService;
import com.travel.service.ScheduleService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ScheduleController {
	
	@Autowired
	ScheduleService scheduleService;
	@Autowired
	ProjectService projectService;
	
	@GetMapping("/schedule")
	public ModelAndView showScedule() {
		return new ModelAndView("redirect:/schedule/top");
	}
	
	@GetMapping("project{projectId}/schedule")
	public ModelAndView showSceduleTop(ModelAndView mav, @PathVariable("projectId") int projectId) {
		Project selectedProject = projectService.getOneProject(projectId);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(selectedProject.getStartDate());
		
		Calendar nextCal = Calendar.getInstance();
		nextCal.setTime(selectedProject.getLastDate());
		nextCal.add(Calendar.DAY_OF_MONTH, 1);
		
		String endDate = sdf.format(nextCal.getTime());
		
		mav.setViewName("schedule/schedule");
		mav.addObject("projectId", projectId);
		mav.addObject("startDate", startDate);
		mav.addObject("endDate", endDate);
		return mav;
	}
	
	@GetMapping("project{projectId}/schedule/detail")
	public ModelAndView showSceduleDetail(ModelAndView mav,@PathVariable("projectId") int projectId) {
		mav.addObject("scheduleDetail", scheduleService.findAllSchadule(1));
		mav.setViewName("schedule/scheduleDetail");
		mav.addObject("projectId", projectId);
		return mav;
	}
	
	@GetMapping("project{projectId}/schedule/detail{id}")
	public ModelAndView showOneSceduleDetail(ModelAndView mav, @PathVariable("projectId") int projectId,@PathVariable("id") int id) {
		mav.addObject("scheduleDetail", scheduleService.findOne(id));
		mav.setViewName("schedule/scheduleDetail");
		mav.addObject("projectId", projectId);
		return mav;
	}
	
	
	@GetMapping("project{projectId}/schedule/add")
	public ModelAndView showSceduleAdd(ModelAndView mav, @PathVariable("projectId") int projectId) {
		mav.setViewName("schedule/scheduleAdd");
		mav.addObject("projectId", projectId);
		return mav;
	}
	
	@GetMapping("project{projectId}/schedule/edit{id}")
	public ModelAndView showSceduleEdit(ModelAndView mav, @PathVariable("projectId") int projectId,@PathVariable("id") int id) {
		
		ScheduleForm beforeEditForm = new ScheduleForm();
		Schedule selectSchedule = scheduleService.findOne(id);
		String day = new SimpleDateFormat("yyyy-MM-dd").format(selectSchedule.getStartTime());
		String startTime = new SimpleDateFormat("HH:mm").format(selectSchedule.getStartTime());
		String endTime = new SimpleDateFormat("HH:mm").format(selectSchedule.getLastTime());
		
		beforeEditForm.setCateId(selectSchedule.getCategory().getCategoryId());
		beforeEditForm.setDay(day);
		beforeEditForm.setStart(startTime);
		beforeEditForm.setEnd(endTime);
		beforeEditForm.setText(selectSchedule.getDetails());
		beforeEditForm.setTitle(selectSchedule.getScName());
		
		mav.addObject("projectId", projectId);
//		mav.addObject("id",id);
		mav.addObject("editSchedule", beforeEditForm);
		mav.setViewName("schedule/scheduleEdit");
		
		return mav;
	}
	
	@PostMapping(value="project{projectId}/schedule/add") 
		public ModelAndView scheduleAddCreate(ModelAndView mav, @Validated ScheduleForm addForm ,BindingResult bindingresult, @PathVariable("projectId") int projectId) {
		System.out.println(addForm.getTitle());
		System.out.println(addForm.getCateId());
//		System.out.println(addForm.getDay());
//		System.out.println(addForm.getStart());
		
		mav.setViewName("schedule/schedule");
		
		scheduleService.create(addForm,projectId);
		return mav;
	}
	
	@PostMapping(value="project{projectId}/schedule/edit{id}") 
	public ModelAndView scheduleEdit(ModelAndView mav, @Validated ScheduleForm editForm ,BindingResult bindingresult, @PathVariable("id") int id,@PathVariable("projectId") int projectId) {	
		Schedule schedule = new Schedule();
		schedule.setScId(id);
		scheduleService.update(editForm,schedule,projectId);
		mav.setViewName("schedule/schedule");
		return mav;
	}

}
