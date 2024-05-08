package com.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.pojo.AnswerByUser;
import com.demo.pojo.Question;
import com.demo.pojo.QuestionforDisplay;
import com.demo.repo.Questionrepo;

@Service
public class QuestionService {

	@Autowired
	Questionrepo questionrepo;
	

	public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionrepo.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        
		
   }
	

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionrepo.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>( HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<Question> addQuestion(Question question) {
    	try {
    		 questionrepo.save(question);
    		 return new ResponseEntity<>(HttpStatus.CREATED);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

   
    }
    
    public  ResponseEntity< String> deleteById(int id) {
    	try {
  		  questionrepo.deleteById(id);
    		return new ResponseEntity<>("deleted",HttpStatus.OK);
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	return new ResponseEntity<>("not deleted",HttpStatus.BAD_REQUEST);
    	
   }


	public Optional<Question> getQuestionById(int id) {
		
		return questionrepo.findById(id);
	}
//-------------------------------------------------------------------------------------------

	public ResponseEntity<List<Integer>> getQuestionsIdByCategory(String category) {
		
		List<Integer> questionIds =  questionrepo.getQuestionsIdByCategory(category);
		
		return new ResponseEntity<>(questionIds,HttpStatus.OK);
	}


	public ResponseEntity<List<QuestionforDisplay>> getQuestionsBasedOnId(List<Integer> questionIds) {
		
		Question question1 = new Question();
		List<QuestionforDisplay> quesfordisp = new ArrayList<>();
		for(int i=0;i<questionIds.size();i++) {
			Question question = questionrepo.findById(questionIds.get(i)).get();
			QuestionforDisplay qfd = new QuestionforDisplay();
			qfd.setId(question.getId());
			qfd.setQuestion(question.getQuestion());
			qfd.setOption1(question.getOption1());
			qfd.setOption2(question.getOption2());
			qfd.setOption3(question.getOption3());
			qfd.setOption4(question.getOption4());
			
			questionrepo.save(qfd);
			quesfordisp.add(qfd);

		}
		return new ResponseEntity<>(quesfordisp,HttpStatus.OK);
	}


	public ResponseEntity<Integer> getScoreforQuestions(List<AnswerByUser> answers) {
		int count=0;
		for(AnswerByUser ans:answers) {
			Question question  = questionrepo.findById(ans.getId()).get();
			if(ans.getAnswerByUser().equals(question.getAnswer())) {
				count++;
			}
		}
		
		
		return new ResponseEntity<>(count,HttpStatus.OK);
	}
}
