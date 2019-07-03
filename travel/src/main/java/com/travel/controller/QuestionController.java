package com.travel.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

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

import com.travel.model.QuestionEditForm;
import com.travel.repository.ChoiceRepository;
import com.travel.repository.QuestionRepository;
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
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	ChoiceRepository choiceRepository;
	
	private int userId;
		
	
	@ModelAttribute
	public AnswerForm addForm() {
		return new AnswerForm();
	}
	
	@ModelAttribute
	public QuestionEditForm editForm() {
		return new QuestionEditForm();
	}
	
	public QuestionNewForm questionNewForm() {
		return new QuestionNewForm();
	}
	
	
	//アンケート一覧画面
	@GetMapping("/base{userid}")
	public ModelAndView question(ModelAndView mav,@PathVariable("userid") int userId) {
		System.out.println("Questionベース画面");
		List<Question> question = questionService.findAll();
		this.userId = userId;
		//回答済みか調べてその結果を返す
		
		mav.addObject("questionList",question);
		mav.addObject("userId",userId);
		mav.setViewName("question/questionhome");

		return mav;
	}

	
	//回答後処理
	@PostMapping("/answer{userid}")
	public String questionans( @Validated AnswerForm form, BindingResult result, ModelAndView mav,@PathVariable("userid") int userId) {
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
		return "redirect:/question/base"+userId;
	}
	
	//回答削除処理
	@GetMapping("/question/delete{questionId}")
	public String questionDelete( @Validated AnswerForm form, BindingResult result, ModelAndView mav,@PathVariable("questionId") int questionId) {
				
		System.out.println("回答削除");
		questionService.delete(questionId);
			
		return "redirect:/question/base"+2;
	}
	
	//アンケート編集画面
	@GetMapping("/question/edit{questionid}")
	public ModelAndView questionEdit(ModelAndView mav,@PathVariable("questionid") int questionId) { 
		System.out.println("Question編集画面");
		Question question = questionService.findById(questionId);
		mav.addObject("userId",userId);
		mav.addObject("question",question);
		mav.setViewName("question/questionedit");
		System.out.println(" 日付："+ question.getLimitTime());


		return mav;
	}
	
	//編集後処理
	@PostMapping("/question/editend/{questionid}")
	public String questionEdit( @Validated QuestionEditForm form, BindingResult result, ModelAndView mav,@PathVariable("questionid") int questionId) {
		System.out.println("回答処理");

		if(!result.hasErrors()) {
			System.out.println("編集処理");
			Question question = questionService.findById(questionId);
			
			//選択肢更新
			choiceRepository.deleteByQuestion(question);
			question.getChoiceList().clear();
			System.out.println("選択肢削除完了");
			for(String choiceText :form.getChoiceList())choiceService.create(choiceText,questionService.findById(questionId));
			System.out.println("選択肢登録完了");
			
			//日時更新
			 String data1 = new SimpleDateFormat("yyyy-MM-dd").format(form.getLimitTime1());
			 String data2 = new SimpleDateFormat("HH:mm").format(form.getLimitTime2());
		     data1= data1 + " " + data2;
		     System.out.println("String型 = " + data1);
		    Date date2;
			try {
				System.out.println("データ登録 ");
					date2 =  new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(data1);
					//質問更新処理
					questionService.create(question, form.getTitle(), form.getQuestionDetail(),date2);
			} catch (ParseException e) {
					e.printStackTrace();
			}
			
		}
		return "redirect:/question/2";
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
