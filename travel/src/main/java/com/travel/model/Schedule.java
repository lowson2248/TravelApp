package com.travel.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "schedules")
public class Schedule {
	//スケジュールID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sc_id", nullable = false, precision = 11)
	private Integer scId;
	
	//プロジェクトID
	@ManyToOne
	@JoinColumn(name = "project_id",nullable=false)
	private Project project;//FK
	
	//スケジュール名
	@Column(name = "sc_name", length = 100, nullable=false)
	private String scName;//max:100
	
	//カテゴリID
	@OneToOne
	@JoinColumn(name = "category_id",nullable=false)
	private Category category;
	
	//スケジュールの開始時間
	@JsonIgnore
	@DateTimeFormat(pattern = "MM-dd HH:mm")
	@Column(name = "start_time",nullable=false)
	private Date startTime;//日付と日次
	
	//スケジュールの終了時間
	@JsonIgnore
	@DateTimeFormat(pattern = "MM-dd HH:mm")
	@Column(name = "last_time",nullable=false)
	private Date lastTime;//start_timeと同様
	
	//スケジュールの詳細
	@Column(name = "details", length = 200)
	private String details;//max:200 default:説明なし
	
	@Column(name = "image")
	private String img;
}
