package com.travel.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	private Integer authId;//FK
	
	//project_idとuser_idの複合主キー
	public void setMemberId(Integer projectId,Integer userId) {
		this.memberId = new MemberId(projectId,userId);
	}
}

