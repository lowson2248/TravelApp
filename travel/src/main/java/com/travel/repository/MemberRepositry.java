package com.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.travel.model.Member;
import com.travel.model.Project;

@Repository
public interface MemberRepositry extends JpaRepository<Member, Integer> {	
	
	/*メンバーからプロジェクトを逆引き*/
	public List<Member> findByMemberId_UserId(int userId);
    public List<Member> findByMemberId_ProjectId(int projectId);
}
