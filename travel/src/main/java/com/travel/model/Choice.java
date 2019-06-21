package com.travel.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "choices")
public class Choice {
	//選択肢のID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "choice_id", nullable = false, precision = 11)
	private Integer choiceId;
	
	//質問のID
	@ManyToOne
	@JoinColumn(name = "question_id",nullable=false)
	private Question question;//FK
	
	//選択肢の内容
	@Column(name = "choice_text", length = 100, nullable=false)
	private String choiceText;//max:100
	
	//answerとの紐づけ
	@JsonIgnore
	@OneToMany(mappedBy = "choice")
	private List<Answer> answerList;
}
