package com.travel.controller;
//WebSocketサーバー

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.travel.common.LogUtils;
import com.travel.model.Chat;
import com.travel.model.Greeting;
import com.travel.model.Message;
import com.travel.model.User;
import com.travel.service.ChatService;
import com.travel.service.ProjectService;
import com.travel.service.UserService;

@Controller
public class GreetingController {
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	ChatService chatService;
	
	@Autowired
	UserService userService;


    @MessageMapping("/hello")// エンドポイントの指定
    @SendTo("/topic/greetings")// メッセージの宛先指定
    public Greeting greeting(Message message) throws Exception {
    	LogUtils.info("greeting():"+"初期化カウンター:");
    	//コメントのDB登録
    	Chat chat = new Chat();
    	chat.setText(message.getName());	
    	chat.setProject(projectService.findById(1));
    	chat.setUser(userService.findByUserId(1));
    	chat.setSendTime(new Date());
    	
    	
    	chatService.save(chat);

    	
    	//DB読み込み
        Thread.sleep(50); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}
