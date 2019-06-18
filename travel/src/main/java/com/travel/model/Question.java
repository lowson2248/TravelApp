package com.travel.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "questions")
public class Question {
	//質問ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id", nullable = false, precision = 11)
	private Integer question_id;
	
	//プロジェクトID
//	@JoinColumn(name = "project_id",nullable=false)
//	private Integer project_id;//FK
	
	//質問名
	@Column(name = "title", length = 100, nullable=false)
	private String title;
	
	//質問の説明
	@Column(name = "question_detail", length = 100)
	private String question_detail;//max: 100 default:説明なし
	
	//設問の解答期限
	@JsonIgnore
	@DateTimeFormat(pattern = "MM-dd HH:mm")//年も必要？
	@Column(name = "limit_time",nullable=false)
	private Date limit_time;//DATETIME
	
	//解答期限を過ぎているか判別
	@Column(name = "ansewr_fin")
	private boolean ansewr_fin; //default:FALSE
	
	@JsonIgnore
	@OneToOne(mappedBy = "question")
	private Answer answer;
	
	@JsonIgnore
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<Choice> choiceList;
}
