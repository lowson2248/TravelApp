package com.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.travel.model.Chat;
import com.travel.service.ChatService;

@Controller
public class ChatController {
	@Autowired
	ChatService chatService;
}
