package com.travel.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/all{projectId}")
    public String getEvents(@PathVariable("projectId") int projectId) {
        String jsonMsg = null;
        String[] colorList = {"#ff7f7f"	,"#ff7fbf","#ff7fff","#bf7fff","#7f7fff","#7fbfff",	"#7fff7f","#7fffbf"};
        try {
            List<Event> events = new ArrayList<Event>();
            
            List<Schedule> scheduleList = projectService.getOneProject(projectId).getScheduleList();
            scheduleList.forEach(schedule -> {
            	Event event = new Event();
            	SimpleDateFormat tokyoSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                tokyoSdf.setTimeZone(TimeZone.getTimeZone("UTC"));

                event.setId(schedule.getScId());
            	event.setTitle(schedule.getScName());
            	event.setStart(tokyoSdf.format(schedule.getStartTime()));
            	event.setEnd(tokyoSdf.format(schedule.getLastTime()));
            	// colorのちのち追加
            	
            	event.setBackgroundColor(colorList[(schedule.getCategory().getCategoryId() -1 )]);
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
