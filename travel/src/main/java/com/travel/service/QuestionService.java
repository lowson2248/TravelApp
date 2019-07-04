package com.travel.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.model.Choice;
import com.travel.model.Project;
import com.travel.model.Question;

import com.travel.repository.AnswerRepository;
import com.travel.repository.ChoiceRepository;
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
	ChoiceRepository choiceRepository;
	
	@Autowired
	AnswerRepository answerRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	ChoiceService choiceService;

	
	public List<Question> findAll() {
		return questionRepository.findAll();
	}
	
	public void save(Question question) {
		questionRepository.saveAndFlush(question);
	}
	
	public Question findById(int id) {
		return questionRepository.findByquestionId(id);
	}
	
	public List<Question> findByProjectId(int  projectId){
		Project project = projectRepository.findByProjectId(projectId);
		return questionRepository.findByProject(project);
	}
	
	public void create(Question question, String title, String questionDetail, Date limitTime) {
		System.out.println("Question登録処理");
		question.setTitle(title);
		System.out.println("タイトルセット完了");
		question.setQuestionDetail(questionDetail);
		System.out.println("説明セット完了");
		question.setLimitTime(limitTime);
		System.out.println("回答期限セット完了");
		
		questionRepository.saveAndFlush(question);
		System.out.println("save完了 " );
	}
	
	public void delete(int questionId) {
		Question question = questionRepository.findByquestionId(questionId);
		answerRepository.deleteByQuestion(question);
		choiceRepository.deleteByQuestion(question);
		questionRepository.deleteById(questionId);
	}
	
	public void saveQuestion(String title, Date limitTime, int projectId, String titleDetails, List<String> choices) {
		Project project = projectRepository.findByProjectId(projectId);
		
		Question questionModel = new Question();
		questionModel.setProject(project);
		questionModel.setTitle(title);
		questionModel.setAnswerFin(false);
		questionModel.setQuestionDetail(titleDetails);
		questionModel.setLimitTime(limitTime);
		questionModel.setAnswerFin(true);
		questionRepository.saveAndFlush(questionModel);
		
		Question question = questionRepository.findByquestionId(questionModel.getQuestionId());
		System.out.println("質問save完了：ID："+questionModel.getQuestionId());

		for(String choiceText :choices) choiceService.create(choiceText,question);

//		for(int i=0; i<choices.size(); i++) {
//			Choice choiceModel = new Choice();
//			String choice = choices.get(i);
//			choiceModel.setChoiceText(choice);
//			choiceModel.setQuestion(questionModel);
//			choiceRepository.saveAndFlush(choiceModel);
//		}
		System.out.println("save完了");
	}

}
