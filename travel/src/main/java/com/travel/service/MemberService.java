package com.travel.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.travel.model.Member;

public interface MemberService {
	public Member findByUserDetailsAndProjectId(UserDetails userDetails,int projectId );

}
