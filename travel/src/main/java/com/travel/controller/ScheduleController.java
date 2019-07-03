package com.travel.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.model.Event;
import com.travel.model.Project;
import com.travel.model.Schedule;
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
	
	@GetMapping("/schedule/edit")
	public ModelAndView showSceduleEdit(ModelAndView mav) {
		System.out.println("aaa");
		mav.setViewName("schedule/scheduleEdit");
		return mav;
	}

}
