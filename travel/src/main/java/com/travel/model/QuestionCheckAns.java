package com.travel.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@Entity
public class QuestionCheckAns {
	//質問ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id", nullable = false, precision = 11)
	private Integer questionId;
	
	//プロジェクトID
	@ManyToOne
	@JoinColumn(name = "project_id",nullable=false)
	private Project project;//FK
	
	//質問名
	@Column(name = "title", length = 100, nullable=false)
	private String title;
	
	//質問の説明
	@Column(name = "question_detail", length = 100)
	private String questionDetail;//max: 100 default:説明なし
	
	//設問の解答期限
	@JsonIgnore
	@DateTimeFormat(pattern = "MM-dd HH:mm")//年も必要？
	@Column(name = "limit_time",nullable=false)
	private Date limitTime;//DATETIME
	
	//解答期限を過ぎているか判別
	@Column(name = "answer_fin")
	private boolean answerFin; //default:FALSE

	
	//answerとの紐づけ
	@JsonIgnore
	@OneToMany(mappedBy = "question")
	private List<Answer> answer;
	
	//choiceとの紐づけ
	@JsonIgnore
	@OneToMany(mappedBy = "question")
	private List<Choice> choiceList;
	
	//解答済みか確認
	private boolean checkAnswer;
		
}
