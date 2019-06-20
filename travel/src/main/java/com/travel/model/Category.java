package com.travel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "categories")
public class Category {
	//カテゴリーID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id", nullable = false, precision = 11)
	private Integer categoryId;
	
	//カテゴリー名
	@Column(name = "category_name", length = 50, nullable=false)
	private String categoryName;
	
	//スケジュールとの紐づけ
	@JsonIgnore
	@OneToOne(mappedBy = "category")
	private Schedule schedule;
}
