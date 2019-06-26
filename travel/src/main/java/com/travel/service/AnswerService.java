package com.travel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.model.Answer;
import com.travel.repository.AnswerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerService {
	
	@Autowired
	AnswerRepository answerRepository;
	
	public void save(Answer answer) {
		answerRepository.saveAndFlush(answer);
	}
}
