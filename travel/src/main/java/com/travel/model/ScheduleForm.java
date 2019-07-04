package com.travel.model;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

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
	private MultipartFile picture;
}
