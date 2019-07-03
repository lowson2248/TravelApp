package com.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.model.Chat;
import com.travel.model.Project;
import com.travel.repository.ChatRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatService {

	@Autowired
	private ChatRepository chatRepository;

	public List<Chat> getAllChat(){
		return chatRepository.findAll();
	}
	
	public void createChat(){
		chatRepository.findAll();
	}
	
	public void save(Chat chat) {
		chatRepository.saveAndFlush(chat);
	}
	
	public List<Chat> findById() {
		return chatRepository.findByChatId(2);
	}
	
	public List<Chat> findAll() {
		return chatRepository.findAll();
	}
}
