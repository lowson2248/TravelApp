package com.travel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.travel.model.User;
import com.travel.repository.UserRepository;

public class UserServiceImp {

	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public void createUser(User user, String rawPassword) {
		String encodedPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(rawPassword);
		userRepository.saveAndFlush(user);
	}
}
