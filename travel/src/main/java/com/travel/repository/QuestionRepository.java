package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer>{
	
}
