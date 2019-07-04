package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.Member;
import com.travel.model.Project;
import com.travel.model.User;

public interface MemberRepository extends JpaRepository<Member, Integer> {
	public Member findByUserAndProject(User user, Project project);

}
