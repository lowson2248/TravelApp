package com.travel.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Data
@Entity
@Table(name = "answeres")
public class Answer {
	//解答ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "answer_id", nullable = false, precision = 11)
	private Integer answer_id;
	
	//質問ID ch
	@OneToOne
	@JoinColumn(name = "questionr_id", nullable=false)
	private Question question;//FK
	
	//ユーザーID
	@ManyToOne
	@JoinColumn(name = "user_id", nullable=false)
	private User user;//FK
	
	//チョイスID
	@JsonIgnore
	@OneToMany(mappedBy = "answer")
	private List<Choice> choiceList;//FK
}
