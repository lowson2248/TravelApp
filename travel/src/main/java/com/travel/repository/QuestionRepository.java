package com.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.Project;
import com.travel.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
	public Question findByquestionId(Integer questionId);
	public List<Question> findByProject(Project project);
}
