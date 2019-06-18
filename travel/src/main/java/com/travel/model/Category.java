package com.travel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "categories")
public class Category {
	//カテゴリーID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id", nullable = false, precision = 11)
	private Integer category_id;
	
	//カテゴリー名
	@Column(name = "category_name", length = 50, nullable=false)
	private String category_name;
}
