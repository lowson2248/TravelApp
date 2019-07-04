package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.Answer;
import com.travel.model.Question;
import com.travel.model.User;


public interface AnswerRepository extends JpaRepository<Answer, Integer> {
	public void deleteByQuestion(Question question);
	public Answer findByQuestionAndUser(Question question, User user);
}
