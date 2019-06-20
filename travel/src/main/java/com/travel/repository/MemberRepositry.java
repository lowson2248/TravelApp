package com.travel.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepositry extends JpaRepository<Member, Integer> {	
	public List<Member> findByProjectId(int project_id);
	public List<Member> findByUserId(int user_id);
}
