package com.travel.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
public class QuestionEditForm {
	
	//質問ID
	private Integer questionId;
		
		
	//質問名
	@NotNull
	private String title;
		
	//質問の説明
	private String questionDetail;//max: 100 default:説明なし
		
	//設問の解答期限
	//@DateTimeFormat(pattern = "MM-dd HH:mm")//年も必要？
	//@Column(name = "limit_time",nullable=false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date limitTime1;//DATETIME
	@DateTimeFormat(pattern = "HH:mm")
	private Date limitTime2;//DATETIME
		
	//choiceとの紐づけ
	private List<String> choiceList;
}
