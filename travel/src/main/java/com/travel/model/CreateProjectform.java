package com.travel.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreateProjectform {

	@NotNull
	private String str;
}
