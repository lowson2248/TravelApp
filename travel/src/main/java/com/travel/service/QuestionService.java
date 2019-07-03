package com.travel.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.model.Project;
import com.travel.model.Question;
import com.travel.repository.ProjectRepository;
import com.travel.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {

	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	public List<Question> findAll() {
		return questionRepository.findAll();
	}
	
	public void save(Question question) {
		questionRepository.saveAndFlush(question);
	}
	
	public Question findById(int id) {
		return questionRepository.findByquestionId(id);
	}
	
	public void saveQuestion(String title, Date limitTime, int projectId, String titleDetails) {
		Project project = projectRepository.findByProjectId(projectId);
		
		Question question = new Question();
		question.setProject(project);
		question.setTitle(title);
		question.setAnswerFin(true);
		question.setQuestionDetail(titleDetails);
		question.setLimitTime(limitTime);
		questionRepository.saveAndFlush(question);
	}

}
