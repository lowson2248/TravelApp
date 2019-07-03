package com.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.travel.model.Member;
import com.travel.model.Project;
import com.travel.model.User;

@Repository
public interface MemberRepositry extends JpaRepository<Member, Integer> {	
	
	/*メンバーを検索*/
	public List<Member> findByUser(User user);
	public List<Member> findByProject(Project project);
}
