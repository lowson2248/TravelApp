package com.travel.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ScheduleForm {
	@NotNull
	private String title;
	@NotNull
	private int cateId;
	@NotNull
	private String startDay;
	@NotNull
	private String endDay;
	@NotNull
	private String start;
	@NotNull
	private String end;
	@NotNull
	private String text;
}
