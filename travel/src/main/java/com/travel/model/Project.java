package com.travel.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "projects")
public class Project {
	//プロジェクトID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_id", nullable = false, precision = 11)
	private Integer project_id;
	
	//プロジェクト名
	@Column(name = "project_name", length = 50, nullable=false)
	private String project_name;
	
	//開始日
	@JsonIgnore
	@DateTimeFormat(pattern = "MM-dd")
	@Column(name = "start_date", nullable=false)
	private Date start_date;
	
	//終了日
	@JsonIgnore
	@DateTimeFormat(pattern = "MM-dd")
	@Column(name = "last_date",nullable=false)
	private Date last_date;
	
	//プロジェクトを作成者のuser_id
	@JoinColumn(name = "user_id",nullable=false)
	@OneToOne
	private User user;
	
	//プロジェクト作成日
	@DateTimeFormat(pattern = "MM-dd")
	@Column(name = "created_date",nullable=false)
	private Date created_date;
	
	//chatとの紐づけ
	@JsonIgnore
	@OneToOne(mappedBy = "project")
	private Chat chat;
	
	//scheduleとの紐づけ
	@JsonIgnore
	@OneToMany(mappedBy = "project")
	private List<Schedule> scheduleList;
}
