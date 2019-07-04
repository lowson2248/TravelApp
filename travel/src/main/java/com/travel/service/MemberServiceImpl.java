package com.travel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.travel.model.Member;
import com.travel.repository.MemberRepository;
import com.travel.repository.ProjectRepository;
import com.travel.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	public Member findByUserDetailsAndProjectId(UserDetails userDetails,int projectId ) {
		return memberRepository.findByUserAndProject(userRepository.findByMailAddress(userDetails.getUsername()),projectRepository.findByProjectId(projectId));
	}
}
