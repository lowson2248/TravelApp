package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.Answer;
import com.travel.model.Question;


public interface AnswerRepository extends JpaRepository<Answer, Integer> {
	public void deleteByQuestion(Question question);

}
