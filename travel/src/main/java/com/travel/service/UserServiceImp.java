package com.travel.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.travel.model.User;
import com.travel.repository.UserRepository;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@Transactional
public class UserServiceImp implements UserService{

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
