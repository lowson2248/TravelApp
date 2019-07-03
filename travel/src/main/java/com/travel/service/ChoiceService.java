package com.travel.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.model.Choice;
import com.travel.model.Question;
import com.travel.repository.ChoiceRepository;
import com.travel.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ChoiceService {
	
	@Autowired
	ChoiceRepository choiceRepository;
	
	@Autowired
	QuestionRepository questionRepository;

	public void save(Choice choice) {
		choiceRepository.saveAndFlush(choice);
	}
	
	public Choice findById(int id) {
		return choiceRepository.findByChoiceId(id);
	}
	
	public List<Choice> findAll() {
		return choiceRepository.findAll();
	}
	
	public Choice create(String choiceText,Question question) {
		Choice choice = new Choice();
		choice.setChoiceText(choiceText);
		choice.setQuestion(question);
		choiceRepository.saveAndFlush(choice);
		return choice;
	}
	
	public void deletByQuestionId(int questionId) {
		List<Choice> choiceList = questionRepository.findByquestionId(questionId).getChoiceList();
		for(Choice c :choiceList)choiceRepository.delete(c);
	}
	
	public void update(String choiceText,Question question, int choiceId) {
		Choice choice = choiceRepository.findByChoiceId(choiceId);
		choice.setChoiceText(choiceText);
		choice.setQuestion(question);
		choiceRepository.saveAndFlush(choice);
	}
	
	public void delete(Choice choice) {
		choiceRepository.delete(choice);
	}

}
