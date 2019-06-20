package com.travel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistController {

    private MailSender mailSender;

    /* registControllerコンストラクタ */
    public RegistController(@Autowired MailSender mailSender) {
        this.mailSender = mailSender;
    }
    
    /* eメール送信処理 */
    @PostMapping("/regist/mail/send")
    public String sendPost(@RequestParam("email") String to,HttpServletRequest request) {
    	System.out.println(to);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        
        /*
         * to : 宛先メールアドレス　regist.htmlのフォームから送られる。
         */
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("【返信不可】TravelNavi認証メール");
        simpleMailMessage.setText(request.getScheme() + "://" + request.getHeader("host") + "/regist/registUser");
        this.mailSender.send(simpleMailMessage);
        
        //registリダイレクト先（送信後遷移画面）
        return "redirect:/regist/regist2";
    }
}