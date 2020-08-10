package com.cg.onlineTest.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineTest.OnlineTestManagementBackendApplication;
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
	
	
	@PostMapping("addQuestion")
	public ResponseEntity<Object> addQuestion(@RequestBody Question question) throws Exception{
		service.addQuestion(question);
		return new ResponseEntity<Object>("Question added successfully", HttpStatus.OK);
	}
	
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
	

}
