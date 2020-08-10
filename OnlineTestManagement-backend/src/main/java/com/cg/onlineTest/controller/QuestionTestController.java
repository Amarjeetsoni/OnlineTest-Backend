package com.cg.onlineTest.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineTest.OnlineTestManagementBackendApplication;
import com.cg.onlineTest.entities.Category;
import com.cg.onlineTest.entities.Question;
import com.cg.onlineTest.services.QuestionTestService;

@SpringBootApplication
@RestController
@CrossOrigin("*")
public class QuestionTestController {

	@Autowired
	QuestionTestService service;
	
	Logger logger = LoggerFactory.getLogger(OnlineTestManagementBackendApplication.class);
	String msg;
	
	
	@PostMapping("/populateData")
	public ResponseEntity<Object> populateData() {
		logger.info("Populating");
		service.populateQuestionsData();
		return new ResponseEntity<Object>("Question Updated successfully", HttpStatus.OK);
	}
	
	@GetMapping("/getAllQuestion")
	public ResponseEntity<List<Question>> getAllQuestion() throws Exception{
		
		logger.info("Retrieving Questions");
		return new ResponseEntity<List<Question>>(service.retrieveAllQuestion(), HttpStatus.OK);
	}
	
	
//	@PostMapping("addQuestion")
//	public ResponseEntity<Object> addQuestion(@RequestBody Question question) throws Exception{
//		service.addQuestion(question);
//		return new ResponseEntity<Object>("Question added successfully", HttpStatus.OK);
//	}
	
	@PostMapping("updateQuestion")
	public ResponseEntity<Object> updateQuestion(@RequestBody Question question) throws Exception{
		service.updateQuestion(question);
		return new ResponseEntity<Object>("Question Updated successfully", HttpStatus.OK);
	}
	
	@GetMapping("deleteQuestion/{questionId}")
	public Boolean deleteQuestion(Long questionId)
	{
		return service.deleteQuestion(questionId);
	}
	
	@PostMapping("/Addcategory")
	public ResponseEntity<Object> addcategory(@RequestBody CategoryHelper Name) throws Exception{
		    Category cat = new Category();
		    cat.setCategoryId(Name.Id);
		    cat.setName(Name.name);
		    service.addCategory(cat);
		    return new ResponseEntity<Object>("category Saved in database", HttpStatus.OK);
		    
	}
	
	@PostMapping("/AddQuestion")
	public ResponseEntity<Object> addQuestion(@RequestBody AddQuestionHelper test){
		    Question ques = new Question();
		    ques.setQuestionId(test.questionId);
		    ques.setQuestionTitle(test.questionTitle);
		    ques.setQuestionAnswer(test.questionAnswer);
		    ques.setQuestionMarks(test.questionMarks);
		    ques.setQuestionOptions(test.option);
		    service.addQuestion(ques, test.category_id);
		    return new ResponseEntity<Object>("Question added successfully", HttpStatus.OK);
		    
	}
	

}


class CategoryHelper{
	public long Id;
	public String name;
}


class AddQuestionHelper{
	public long questionId;
	public String questionTitle;
	public int questionAnswer;
	public long questionMarks;
	public Set<String> option;
	public long category_id;
	
}