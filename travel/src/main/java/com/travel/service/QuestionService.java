package com.travel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.model.Question;
import com.travel.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {

	@Autowired
	QuestionRepository questionRepository;
	
	public void findAll() {
		questionRepository.findAll();
	}
	
	public void save(Question question) {
		questionRepository.saveAndFlush(question);
	}

}
