package com.travel.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
	
	public List<Question> findAll() {
		return questionRepository.findAll();
	}
	
	public void save(Question question) {
		questionRepository.saveAndFlush(question);
	}
	
	public Question findById(int id) {
		return questionRepository.findByquestionId(id);
	}
	
	public void create(Question question, String title, String questionDetail, Date limitTime) {
		System.out.println("Question登録処理");
		question.setTitle(title);
		System.out.println("タイトルセット完了");
		question.setQuestionDetail(questionDetail);
		System.out.println("説明セット完了");
		question.setLimitTime(limitTime);
		System.out.println("回答期限セット完了");
		
		questionRepository.saveAndFlush(question);
		System.out.println("save完了 " );
	}

}
