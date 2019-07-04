package com.travel.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ProjectEditForm extends ProjectForm {

	/* 追加したいメンバーのメールアドレス */
	@Valid
	private List<String> memberList;
	
	
	@NotNull
	private String testString;
	
}
