package com.travel.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Data
public class QuestionFilter {
	//質問ID
	private Integer questionId;
	
	//プロジェクトID
	private Project project;
	
	//質問名
	private String title;
	
	//質問の説明
	private String questionDetail;
	
	//設問の解答期限
	private Date limitTime;
	
	//解答期限を過ぎているか判別
	private boolean answerFin;

	
	//answerとの紐づけ
	private List<Answer> answer;
	
	//choiceとの紐づけ
	private List<Choice> choiceList;
	
	private boolean checkAnswer;
	
	public void create(Question question,boolean checkAnswer ) {
		this.questionId = question.getQuestionId();
		this.project = question.getProject();
		this.title = question.getTitle();
		this.questionDetail = question.getQuestionDetail();
		this.limitTime = question.getLimitTime();
		this.answerFin = question.getAnswerFin();
		this.answer = question.getAnswer();
		this.choiceList = question.getChoiceList();
		this.checkAnswer = checkAnswer ;
	}


}
