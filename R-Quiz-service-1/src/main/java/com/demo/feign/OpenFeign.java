package com.demo.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.pojo.AnswerByUser;
import com.demo.pojo.QuestionforDisplay;

@FeignClient("R-QUESTION-SERVICE")
public interface OpenFeign {
	
	@PostMapping("/question/getquesbycat")
    public ResponseEntity<List<Integer>> getQuestionsIdByCategory(@RequestParam String category);
   
   
    @PostMapping("/question/getQuesbasonid")
    public ResponseEntity<List<QuestionforDisplay>> getQuestionsBasedOnId(@RequestBody List<Integer> questionIds);
    
    @PostMapping("/question/getscore")
    public ResponseEntity<Integer> getScoreforQuestions(@RequestBody List<AnswerByUser> answers);
	
	
}
