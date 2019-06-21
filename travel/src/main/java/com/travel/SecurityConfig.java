package com.travel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import com.travel.service.LoginUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	LoginUserDetailsService loginUserDetailsService;
	//全体に関する設定
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**", "/css/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
    		//"/login.htmlはどのユーザもアクセスできる"
            .antMatchers("/login").permitAll()
            .antMatchers("/regist/**").permitAll()
            //それ以外のパスにはアクセスできなくする
            .anyRequest().authenticated()
            .and()
            //ログインに関する設定
            .formLogin()
    		//認証にかかわるパラメータ
    		.loginProcessingUrl("/login")
    		//ログイン画面のURL
            .loginPage("/login")
            //ログインに失敗したときのURL
            .failureUrl("/login?error")
            //認証に成功したらアクセスするURL
            .defaultSuccessUrl("/project/select", true)
            //ログイン画面のhtmlのinputのname属性を見に行っている
            .usernameParameter("email").passwordParameter("password")
            .and()
            .logout()
    		//ログアウト成功したときのURL
            .logoutSuccessUrl("/login");
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
    	//パスワードをhash化アルゴリズムでhash化
        return new BCryptPasswordEncoder();
    }

}
