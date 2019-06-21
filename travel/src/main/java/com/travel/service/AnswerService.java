package com.travel.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.travel.model.Answer;
import com.travel.repository.AnswerRepository;

public class AnswerService {
	
	@Autowired
	AnswerRepository answerRepository;
	
	public void save(Answer answer) {
		answerRepository.saveAndFlush(answer);
	}
}
