package com.travel.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

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
            
            List<Schedule> scheduleList = projectService.getOneProject(2).getScheduleList();
            scheduleList.forEach(schedule -> {
            	Event event = new Event();
            	SimpleDateFormat tokyoSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                tokyoSdf.setTimeZone(TimeZone.getTimeZone("UTC"));

                event.setId(schedule.getScId());
            	event.setTitle(schedule.getScName());
            	event.setStart(tokyoSdf.format(schedule.getStartTime()));
            	event.setEnd(tokyoSdf.format(schedule.getLastTime()));
            	// colorのちのち追加
//            	event.setBackgroundColor("yellow");
            	events.add(event);
            });
            
            // FullCalendarにエンコード済み文字列を渡す
            ObjectMapper mapper = new ObjectMapper();
            jsonMsg =  mapper.writerWithDefaultPrettyPrinter().writeValueAsString(events);
        } catch (IOException ioex) {
            System.out.println(ioex.getMessage());
        }
        return jsonMsg;
    }
}
