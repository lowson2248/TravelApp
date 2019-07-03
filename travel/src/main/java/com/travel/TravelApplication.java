package com.travel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.travel.model.User;
import com.travel.service.UserService;

@SpringBootApplication
public class TravelApplication {

	public static void main(String[] args) {
		
//		UserService userService = null;
//		
//		User user = new User();
//		user.setMailAddress("travel@tcmobile.jp");
//		user.setAccount_name("admin");
//		userService.createUser(user, "admin");
		
		SpringApplication.run(TravelApplication.class, args);
	}

}
