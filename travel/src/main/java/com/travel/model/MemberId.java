package com.travel.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
class MemberId implements Serializable {
	
    @JoinColumn(name = "project_id", nullable=false)
	private Integer projectId;//複合主キー FK
    @JoinColumn(name = "user_id", nullable=false)
	private Integer userId;//複合主キー FK
}