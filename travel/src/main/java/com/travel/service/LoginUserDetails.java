package com.travel.service;

import org.springframework.security.core.authority.AuthorityUtils;

import com.travel.model.User;

import lombok.Data;

@Data
public class LoginUserDetails extends org.springframework.security.core.userdetails.User {
    //Spring Securityの認証ユーザからUserオブジェクトを取得
	private final User user;

    public LoginUserDetails(User user2) {
    	//org.springframework.security.core.userdetails.Userのコンストラクタを使って
    	//(ユーザ名、パスワード、認可用のロール（複数のユーザに適用すべく許可パラメータが記述される集団）)を設定する
        super(user2.getMailAddress(), user2.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.user = user2;
    }
}
