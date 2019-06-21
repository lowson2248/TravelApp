package com.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.travel.model.Answer;
import com.travel.model.Choice;
import com.travel.model.Question;

import com.travel.service.AnswerService;
import com.travel.service.ChoiceService;
import com.travel.service.QuestionService;

@Controller
public class QuestionController {
	
	@Autowired
	QuestionService questionService;

	@Autowired
	ChoiceService choiceService;
	
	@Autowired
	AnswerService answerService;
	
	//質問作成
	@PostMapping("/qestion/create")
	public String createQestion() {
		//質問作成
		Question question = new Question();
		questionService.save(question);
		//選択肢作成
		Choice choice = new Choice();
		choiceService.save(choice);
		return "";
	}
	
	//アンケート回答画面
	@GetMapping("/question")
	public ModelAndView question(ModelAndView mav) {
		
		return mav;
	}
	
	//アンケート回答後処理
	@PostMapping("/question")
	public ModelAndView questionFin(ModelAndView mav) {
		//回答登録
		Answer answer = new Answer();
		answerService.save(answer);
		return mav;
	}
	

}
