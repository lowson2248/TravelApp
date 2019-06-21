package com.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.travel.common.LogUtils;
import com.travel.model.Chat;
import com.travel.service.ChatService;

@Controller
public class ChatController {
	@Autowired
	ChatService chatService;

	@GetMapping("/")
	public ModelAndView chat(ModelAndView mav) {
		//chatテーブル取得、
		List<Chat> chatList = chatService.findAll();
		LogUtils.info("chatList():"+"テキスト:"+chatList.get(0).getText());
		mav.addObject("chatList",chatList);
		mav.setViewName("index");
		return mav;
	}
}
