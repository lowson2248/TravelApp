package com.travel.service;

import com.travel.model.User;

public interface UserService {
	public void createUser(User user, String rawPassword);
	public String upDateUser(String idBymailAddress,String userName,String mailAddress,String password);
}
