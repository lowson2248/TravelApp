package com.travel.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
	
	@GetMapping("/schedule/top")
	public ModelAndView showSceduleTop(ModelAndView mav) {
		mav.setViewName("schedule/schedule");
		return mav;
	}
	
	@GetMapping("/schedule/detail")
	public ModelAndView showSceduleDetail(ModelAndView mav) {
		mav.addObject("scheduleDetail", scheduleService.findAllSchadule(1));
		mav.setViewName("schedule/scheduleDetail");
		return mav;
	}
	
	@GetMapping("/schedule/detail{id}")
	public ModelAndView showOneSceduleDetail(ModelAndView mav, @PathVariable("id") int id) {
		mav.addObject("scheduleDetail", scheduleService.findOne(id));
		mav.setViewName("schedule/scheduleDetail");
		return mav;
	}
	
	
	@GetMapping("/schedule/add")
	public ModelAndView showSceduleAdd(ModelAndView mav) {
		mav.setViewName("schedule/scheduleAdd");
		return mav;
	}
	
	@GetMapping("/schedule/edit{id}")
	public ModelAndView showSceduleEdit(ModelAndView mav, @PathVariable("id") int id) {
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
		
		mav.addObject("editSchedule", beforeEditForm);
		mav.setViewName("schedule/scheduleEdit");
		
		return mav;
	}
	
	@PostMapping(value="/schedule/add") 
		public ModelAndView scheduleAddCreate(ModelAndView mav, @Validated ScheduleForm addForm ,BindingResult bindingresult ) {
		System.out.println(addForm.getTitle());
		System.out.println(addForm.getCateId());
//		System.out.println(addForm.getDay());
//		System.out.println(addForm.getStart());
		mav.setViewName("schedule/schedule");
		scheduleService.create(addForm);
		return mav;
	}
	
	@PostMapping(value="/schedule/edit{id}") 
	public ModelAndView scheduleEdit(ModelAndView mav, @Validated ScheduleForm editForm ,BindingResult bindingresult, @PathVariable("id") int id) {	
		Schedule schedule = new Schedule();
		schedule.setScId(id);
		scheduleService.update(editForm,schedule);
		mav.setViewName("schedule/schedule");
		return mav;
	}

}
