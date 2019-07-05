package com.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.Answer;
import com.travel.model.Question;
import com.travel.model.User;


public interface AnswerRepository extends JpaRepository<Answer, Integer> {
	public void deleteByQuestion(Question question);
	public List<Answer> findByQuestionAndUser(Question question, User user);
	public List<Question> findByQuestion(Question question);
}
