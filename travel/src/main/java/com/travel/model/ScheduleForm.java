package com.travel.model;

import lombok.Data;

@Data
public class ScheduleForm {
	private String title;
	private int cateId;
	private String day;
	private String start;
	private String end;
	private String text;
}
