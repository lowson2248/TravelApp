package com.travel.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.travel.model.Answer;
import com.travel.model.AnswerForm;
import com.travel.model.Choice;
import com.travel.model.Project;
import com.travel.model.ProjectForm;
import com.travel.model.Question;
import com.travel.model.QuestionNewForm;
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
	
	
	@ModelAttribute
	public AnswerForm addForm() {
		return new AnswerForm();
	}
	
	@ModelAttribute
	public QuestionNewForm questionNewForm() {
		return new QuestionNewForm();
	}
	
	//アンケート一覧画面
	@RequestMapping(value="/{userid}",method=RequestMethod.GET)
	public ModelAndView question(ModelAndView mav,@PathVariable("userid") int userId) {
		System.out.println("Questionベース画面");
		List<Question> question = questionService.findAll();
		
		mav.addObject("questionList",question);
		mav.addObject("userId",userId);
		mav.setViewName("questionbase");

		return mav;
	}
	
	//回答後処理
	@PostMapping("/answer")
	public String questionans(@Validated AnswerForm form, BindingResult result, ModelAndView mav,@PathVariable("userid") int userId) {
		System.out.println("回答処理:選択肢:"+form.getChoiceId()+":userId:"+userId);
		
		if(!result.hasErrors()) {
			System.out.println("回答生成");
			Answer answer = new Answer();
			Choice choice = choiceService.findById(form.getChoiceId());
			
			answer.setChoice(choice);
			answer.setUser(userService.findByUserId(userId));
			answer.setQuestion(choice.getQuestion());
			answerService.save(answer);
		}
		return "redirect:/question/"+userId;
	}

    //アンケート作成画面
    @RequestMapping(value="/create",method=RequestMethod.GET)
    public ModelAndView showCreateQuestion(ModelAndView mav) {
        mav.setViewName("questionAdd");
        return mav;
    }
    
    @PostMapping("/create/{projectid}")
    public String createQuestions( @Validated QuestionNewForm questionNewForm, BindingResult result, ModelAndView mav, @PathVariable("projectid") int projectId) {
    	//System.out.println("タイトル : " + questionNewForm.getTitle());
    	//System.out.println("最終締め切り日 : " + questionNewForm.getLastDate());
    	//System.out.println("プロジェクトID : " + projectId);
    	//System.out.println("詳細説明 : " + questionNewForm.getQuestionDetails());
    	String title = questionNewForm.getTitle();
    	Date lastDate = questionNewForm.getLastDate();
    	String titleDetails = questionNewForm.getQuestionDetails();	
    	List<String> choice = questionNewForm.getChoice();
    	
    	questionService.saveQuestion(title, lastDate, projectId, titleDetails, choice);
    	return "redirect:/question/" + projectId;
    }
}
