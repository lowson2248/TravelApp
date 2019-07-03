package com.travel.service;

import java.util.List;

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
	
	LoginUserDetails userDetail;
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

	/*
	 * アカウント更新処理
	 */
	@Override
	public String upDateUser(String loginUserMailAddress,String userName, String mailAddress, String password) {
		/*
		 * 入力されたメールアドレスが登録されていない
		 * or
		 * ログイン中ユーザーのメールアドレスと入力されたメールアドレスが一致
		 */
		if(userRepository.findByMailAddress(mailAddress) == null || loginUserMailAddress.equals(mailAddress)) {
			String encodePassword = passwordEncoder.encode(password);
			User user = userRepository.findByMailAddress(loginUserMailAddress);
			//テスト出力
			System.out.println(loginUserMailAddress);
			System.out.println(userName);
			System.out.println(mailAddress);
			System.out.println(encodePassword);
			
			user.setAccountName(userName);
			user.setMailAddress(mailAddress);
			user.setPassword(encodePassword);
			userRepository.saveAndFlush(user);	
			return "redirect:/logout";
		}else {
			System.out.println("既に登録されているメールアドレスです");
			return "redirect:/userEdit";
		}
	}

	/*
	 * ユーザー削除処理
	 */
	@Override
	public void deleteUser(String mailAddress) {
		userRepository.delete(userRepository.findByMailAddress(mailAddress));
	}
}
