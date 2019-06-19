package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	public User findByName(String name);
	public User findByMailAddress(String mailAddress);
}
