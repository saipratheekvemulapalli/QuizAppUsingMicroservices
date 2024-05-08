package com.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.feign.OpenFeign;
import com.demo.feign.QuestionDto;
import com.demo.feign.QuestionListDto;
import com.demo.pojo.AnswerByUser;
import com.demo.pojo.Question;
import com.demo.pojo.QuestionforDisplay;
import com.demo.pojo.Quiz;
import com.demo.repo.QuestionForDisplayRepo;
import com.demo.repo.QuizRepo;


@Service
public class Quizservice {
	
	@Autowired
	QuizRepo quizRepo;
	
	
	@Autowired
	OpenFeign openFeign;
	


	
	
	public ResponseEntity<String> create(String category) {

		
//		QuestionDto questionDto= new QuestionDto();
//		questionDto.setCategory(category);
//		List<Integer> list  = openFeign.getQuestionsIdByCategory(questionDto).getBody();
//		Quiz quiz = new Quiz();
//		quiz.setQuestionIds(list);
//		quizRepo.save(quiz);
//		
//		String str = list.toString();
		
		
		List<Integer> questionids = openFeign.getQuestionsIdByCategory(category).getBody();
		
		Quiz quiz = new Quiz();
		quiz.setQuestionIds(questionids);
		quizRepo.save(quiz);
		String str = questionids.toString();
		return new ResponseEntity<>(str,HttpStatus.CREATED);
	}
	
	public ResponseEntity<List<QuestionforDisplay>> getQuizQuestions(Integer id) {
		
		Quiz quiz = quizRepo.findById(id).get();
		List<Integer> list = quiz.getQuestionIds();
		
//		QuestionListDto qld = new QuestionListDto();
//		qld.setQuestionIds(list);
		
		ResponseEntity<List<QuestionforDisplay>> responseentity=openFeign.getQuestionsBasedOnId(list);

		
        return responseentity;

    }
	
	
	
	public ResponseEntity<Integer> getResponse(int id, List<AnswerByUser> answerByUser) {
		
		Integer res = openFeign.getScoreforQuestions(answerByUser).getBody();
		
		
		return new ResponseEntity<>(res,HttpStatus.OK);
		
	}



	
		
	
	
}

	
	
		