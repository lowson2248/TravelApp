package com.travel.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.Choice;
import com.travel.model.Question;

@Transactional
public interface ChoiceRepository extends JpaRepository<Choice, Integer> {

	public Choice findByChoiceId(Integer choiceId);
	public void deleteByChoiceId(Integer choiceId);
	public void deleteByQuestion(Question question);
}
