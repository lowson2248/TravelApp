package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {


}
