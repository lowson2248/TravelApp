package com.travel.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
@Table(name = "users")
public class User {
	//ユーザID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, precision = 11)
	private Integer user_id;
	
	//ユーザー名
	@Size(min = 1, max = 30, message = "1~30文字で入力してください。")
	@Column(name = "account_name", length = 30, nullable=false)
	private String name;
	
	
	//パスワード
	@Size(min = 1, max = 30, message = "1~30文字で入力してください。")
	@JsonIgnore
	@Column(name = "password", length = 30, nullable=false)
	private String password;
	
	
	//E-mailアドレス
	@Size(min = 3, max = 30, message = "3~30文字で入力してください。")
	@JsonIgnore
	@Column(name = "mailaddress", length = 50, nullable=false,unique =true)
	private String mailAddress;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Answer> answerList;
}
