package com.travel.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ProjectForm {

	/* プロジェクト名 */
	@NotNull
	private String projectName;
	
	/* 開始日時 */
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	/* 終了日時 */
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastDate;
	
}
