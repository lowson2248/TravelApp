package com.travel.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
@Table(name = "users")
public class User {
	//ユーザID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false, precision = 11)
	private Integer userId;
	
	//ユーザー名
	@Size(min = 1, max = 30, message = "1~30文字で入力してください。")
	@Column(name = "account_name", length = 30, nullable=false)
	private String accountName;
	
	
	//パスワード
	@Size(min = 1, max = 100, message = "1~100文字で入力してください。")

	@JsonIgnore
	@Column(name = "password", length = 100, nullable=false)
	private String password;
	
	
	//E-mailアドレス
	@Size(min = 3, max = 100, message = "3~100文字で入力してください。")
	@Pattern(regexp="^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", message="メールアドレスではありません。")
	@JsonIgnore
	@Column(name = "mailaddress", length = 100, nullable=false,unique =true)
	private String mailAddress;
	
	
	//Answerとの紐づけ
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Answer> answerList;
	
	//projectとの紐づけ
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Project> project;
	
	//chatとの紐づけ
	@JsonIgnore
	@ManyToMany(mappedBy ="userList")
	private List<Chat> chatList;
}
