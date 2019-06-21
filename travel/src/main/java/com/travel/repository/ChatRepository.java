package com.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.Chat;


public interface ChatRepository extends JpaRepository<Chat, Integer> {
	public List<Chat> findAll();
	public List<Chat> findByChatId(Integer chatId);
}
