package com.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.travel.model.Member;
import com.travel.model.Project;

@Repository
public interface MemberRepositry extends JpaRepository<Member, Integer> {	
	public List<Project> findByMemberId_projectId(Integer projectId);
    public List<Project> findByMemberId_userId(Integer userId);
}
