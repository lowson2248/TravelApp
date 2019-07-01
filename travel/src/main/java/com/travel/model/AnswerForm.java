package com.travel.model;


import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AnswerForm {

	@NotNull
	private Integer choiceId;

}
