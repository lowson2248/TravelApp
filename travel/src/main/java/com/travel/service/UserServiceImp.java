package com.travel.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	BCryptPasswordEncoder passwordEncoder;
	
	public void createUser(User user, String rawPassword) {
//		System.out.println(userRepository.findByMailAddress(user.getMailAddress()));
		if(userRepository.findByMailAddress(user.getMailAddress()) != null) {
			System.out.println("もう使ってるよ");
		}else {
			String encodedPassword = passwordEncoder.encode(rawPassword);
			user.setPassword(encodedPassword);
			userRepository.saveAndFlush(user);
		}
	}
	
	public User findByUserId(int id) {
		return userRepository.findByUserId(id);
	}
}
