package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

}
