package com.travel.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class QuestionNewForm {
	//アンケート名
	@NotNull
	private String title;
	//アンケート詳細
	private String questionDetails;
	//回答期限（始め）
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	//回答期限（終わり）
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastDate;
	//選択肢
	private String choice;
}
