package com.travel.model;


import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AnswerForm {

	//解答ID
	@NotNull
	private Integer answerId;
	
	//質問ID 
	@NotNull
	private Integer questionId;
	
	//ユーザーID
	@NotNull
	private Integer userId;

	//チョイスID
	@NotNull
	private Integer choiceId;

}
