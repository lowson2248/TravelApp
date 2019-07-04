package com.travel.model;

import java.util.Date;
import java.util.List;

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
	//日付
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date limitTime1;
	//時間
	@NotNull
	@DateTimeFormat(pattern = "HH:mm")
	private Date limitTime2;
	//選択肢
	private List<String> choiceList;
}
