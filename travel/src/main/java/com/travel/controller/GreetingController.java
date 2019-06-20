package com.travel.controller;
//WebSocketサーバー

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.travel.common.LogUtils;
import com.travel.model.Greeting;
import com.travel.model.Message;

@Controller
public class GreetingController {


    @MessageMapping("/hello")// エンドポイントの指定
    @SendTo("/topic/greetings")// メッセージの宛先指定
    public Greeting greeting(Message message) throws Exception {
    	LogUtils.info("greeting():"+"初期化カウンター:");
    	//コメントのDB登録
    	//DB読み込み
        Thread.sleep(50); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}
