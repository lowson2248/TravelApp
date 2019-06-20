package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
//	public User findByAccount_name(String name);
	public User findByMailAddress(String mailAddress);
}
