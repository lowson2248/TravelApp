package com.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.travel.model.AnswerForm;
import com.travel.model.Question;
import com.travel.service.AnswerService;
import com.travel.service.ChoiceService;
import com.travel.service.QuestionService;
import com.travel.service.UserService;


@Controller
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;

	@Autowired
	ChoiceService choiceService;
	
	@Autowired
	AnswerService answerService;
	
	@Autowired
	UserService userService;
	
	
	//ベース
	@GetMapping
	public ModelAndView question(ModelAndView mav) {
		System.out.println("Questionベース画面");
		List<Question> question = questionService.findAll();
		mav.addObject("question",question);
		mav.setViewName("questionbase");
		return mav;
	}
	
	@ModelAttribute
	public AnswerForm form() {
		return new AnswerForm();
	}
	

	//仮アンケート入力フォーム画面
	/*
	 * ProjectIdとuserIdはパラメーターでセットしておく。
	 * アンケート回答後、Idから選択肢の問題Idを選択肢Idを取得し、４つ格納してsave
	 * よってformで入れる変数は選択肢IDのみ
	 */
	@GetMapping("/answer/userid{userid}/projectid{projectid}")
	public ModelAndView questionanswer(ModelAndView mav,@PathVariable("projectid") Integer projectId, @PathVariable("userid") Integer userId) {
		
		//仮情報入力
//		AnswerForm form = new AnswerForm();
//		form.setUserId(userId);
//		form.setQuestionId(1);
//		form.setChoiceId(1);

		mav.setViewName("questionanswer");
		return mav;
	}
	
	
	//回答後処理
	@PostMapping("/answer")
	public String questionans( @Validated AnswerForm form, BindingResult result, ModelAndView mav) {
		System.out.println("回答処理");
		
		if(result.hasErrors())System.out.println("エラー");
		else System.out.println("成功");
		
		/*
		//回答用フォーム生成
		Answer answer = new Answer();
		answer.setQuestion(questionService.findById(form.getQuestionId()));
		answer.setUser(userService.findByUserId(form.getUserId()));
		answer.setChoice(choiceService.findById(form.getChoiceId()));
		*/
		return "redirect:/question";
	}
	
	

	

}
