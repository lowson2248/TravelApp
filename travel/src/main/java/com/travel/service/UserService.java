package com.travel.service;

import org.springframework.stereotype.Service;

import com.travel.model.User;

public interface UserService {
	public void createUser(User user, String rawPassword);
}
