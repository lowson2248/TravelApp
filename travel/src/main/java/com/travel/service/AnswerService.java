package com.travel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.travel.model.Answer;
import com.travel.model.Choice;
import com.travel.model.Question;
import com.travel.repository.AnswerRepository;
import com.travel.repository.QuestionRepository;
import com.travel.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerService {
	
	@Autowired
	AnswerRepository answerRepository;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public void save(Answer answer) {
		answerRepository.saveAndFlush(answer);
	}
	
	public boolean find(Question q, UserDetails userDetails ) {
		System.out.println("チェックQuestionID："+q.getQuestionId());
		for(Choice cc : q.getChoiceList())System.out.println("ChoiceID:"+cc.getChoiceId());
		//,userRepository.findByMailAddress(userDetails.getUsername())
		if(CollectionUtils.isEmpty(answerRepository.findByQuestionAndUser(q,userRepository.findByMailAddress(userDetails.getUsername())))) {
			System.out.println("true");
			return true;
		}else
		System.out.println("false");
		return false;
	}
}
