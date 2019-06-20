package com.travel.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "members")
@NoArgsConstructor
public class Member {
	@Embedded
	@Id
    private MemberId memberId;

	//権限の指定
	@Column(name = "auth_id", nullable=false)
	private Integer auth_id;//FK
	
	
	//project_idとuser_idの複合主キー
	@Data
	public class MemberId implements Serializable {
	    @JoinColumn(name = "project_id", nullable=false)
		private Integer project_id;//複合主キー FK
	    @JoinColumn(name = "user_id", nullable=false)
		private Integer user_id;//複合主キー FK
	}
}
