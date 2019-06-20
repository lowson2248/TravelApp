package com.travel.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "chats")
public class Chat {
	//チャットID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "chat_id", nullable = false, precision = 11)
	private Integer chatId;
	
	//プロジェクトID
	@OneToOne 
	@JoinColumn(name = "project_id",nullable=false)
	private Project project;//FK
	
	//ユーザーID
	@ManyToMany
	@JoinTable(
			name = "chuser_id",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "chat_id"))
	private List<User> userList;//FK
	
	//チャット内容
	@Size(min = 1, max = 100)
	@Column(name = "text", length = 100, nullable=false)
	private String text;//最大100
	
	//チャット送信時間
	@JsonIgnore
	@DateTimeFormat(pattern = "YYYY-MM-dd HH:mm")
	@Column(name = "send_time",nullable=false)
	private Date sendTime;//TimeStamp 日付・時間
}
