package com.travel.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "members",uniqueConstraints=@UniqueConstraint(columnNames={"user_id", "project_id"}))
public class Member {
	//メンバーＩＤ
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id", nullable = false, precision = 11)
	private Integer memberId;

    
	//プロジェクト参加しているuser_id
	@JoinColumn(name = "user_id",nullable=false)
	@ManyToOne
	private User user;
	
	//プロジェクトID
	@JoinColumn(name = "project_id",nullable=false)
	@ManyToOne
	private Project project;

	//権限の指定
	@Column(name = "auth_id", nullable=false)
	private Integer authId;//FK
	
}

