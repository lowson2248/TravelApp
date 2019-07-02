package com.travel.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.model.Event;
import com.travel.model.Schedule;
import com.travel.service.ProjectService;
import com.travel.service.ScheduleService;

@RestController
@RequestMapping("/api/event")
public class RestCalendarController {
	
	@Autowired
	ScheduleService scheduleService;
	@Autowired
	ProjectService projectService;
	
	@GetMapping(value = "/all")
    public String getEvents() {
        String jsonMsg = null;
        try {
            List<Event> events = new ArrayList<Event>();
//            Event event = new Event();
//            event.setTitle("first event");
//            event.setStart("2019-07-01");
//            events.add(event);
//
//            event = new Event();
//            event.setTitle("second event");
//            event.setStart("2019-07-01T04:30:00");
//            event.setEnd("2019-07-01T05:30:00");
//            events.add(event);
            
            List<Schedule> scheduleList = projectService.getOneProject(1).getScheduleList();
            scheduleList.forEach(s -> System.out.println(s.getScName()));
//            List<Schedule> scheduleList = scheduleService.find(1);
//            System.out.println(scheduleList.get(0).getScName());
//            scheduleList.forEach(s -> System.out.println(s.getScName()));

            // FullCalendarにエンコード済み文字列を渡す
            ObjectMapper mapper = new ObjectMapper();
            jsonMsg =  mapper.writerWithDefaultPrettyPrinter().writeValueAsString(events);
        } catch (IOException ioex) {
            System.out.println(ioex.getMessage());
        }
        return jsonMsg;
    }
}
