package com.travel.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.model.Choice;
import com.travel.model.Project;
import com.travel.model.Question;
import com.travel.repository.ChoiceRepository;
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
	
	@Autowired
	ChoiceRepository choiceRepository;
	
	public List<Question> findAll() {
		return questionRepository.findAll();
	}
	
	public void save(Question question) {
		questionRepository.saveAndFlush(question);
	}
	
	public Question findById(int id) {
		return questionRepository.findByquestionId(id);
	}
	
	public void saveQuestion(String title, Date limitTime, int projectId, String titleDetails, List<String> choices) {
		Project project = projectRepository.findByProjectId(projectId);
		
		Question questionModel = new Question();
		questionModel.setProject(project);
		questionModel.setTitle(title);
		questionModel.setAnswerFin(false);
		questionModel.setQuestionDetail(titleDetails);
		questionModel.setLimitTime(limitTime);
		questionRepository.saveAndFlush(questionModel);
		
		Question question = questionRepository.findByquestionId(questionModel.getQuestionId());
		for(int i=0; i<choices.size(); i++) {
			Choice choiceModel = new Choice();
			String choice = choices.get(i);
			choiceModel.setChoiceText(choice);
			choiceModel.setQuestion(question);
			choiceRepository.saveAndFlush(choiceModel);
		}
	}

}
