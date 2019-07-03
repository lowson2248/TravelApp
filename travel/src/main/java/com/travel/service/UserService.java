package com.travel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.model.User;
import com.travel.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
public interface UserService {
	
	public User findByUserId(int id);
	
	public void createUser(User user, String rawPassword);
}
