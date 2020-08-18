package com.cg.onlineTest.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineTest.entities.User_Test;
import com.cg.onlineTest.exceptions.CannotRetrieveDataException;
import com.cg.onlineTest.exceptions.NoDataFoundedException;
import com.cg.onlineTest.services.TopPerformerService;


@SpringBootApplication
@RestController
@CrossOrigin("*")
@RequestMapping("/dashboard")
public class TopPerformerController {
	/*
	 * @Autowired is used to
	 * Inject the dependency of TopPerformerService class into TopPerformerController class.
	 * It internally uses setter or constructor to inject dependency. 
	 */
	@Autowired
	private TopPerformerService topPerformer;
	
	/*
	 * Logger used to Record unusual circumstances or error that may be happening in the program.
	 * also use to get info what is going in the application.
	 */
	Logger logger = LoggerFactory.getLogger(TopPerformerController.class);
	
	@GetMapping("top_performers")
	public ResponseEntity<List<User_Test>> listofTopPerformers() throws Exception{
		logger.trace("listofTopPerformers Controller method Accessed...");    						 
		return new ResponseEntity<List<User_Test>>(topPerformer.topPerformers() ,HttpStatus.OK);
	}
	
	@GetMapping("total_users")
	public ResponseEntity<Object> getTotalUsers() throws CannotRetrieveDataException{
		logger.trace("getTotalUsers Controller method Accessed...");
		return new ResponseEntity<Object>(topPerformer.getTotalUsers(), HttpStatus.OK);
	}
	
	@GetMapping("total_tests")
	public ResponseEntity<Object> getTotalTests() throws CannotRetrieveDataException{
		logger.trace("getTotalTests Controller method Accessed...");
		return new ResponseEntity<Object>(topPerformer.getTotalTests(), HttpStatus.OK);
	}
	
	@GetMapping("total_questions")
	public ResponseEntity<Object> getTotalQuestions() throws CannotRetrieveDataException{
		logger.trace("getTotalQuestions Controller method Accessed...");
		return new ResponseEntity<Object>(topPerformer.getTotalQuestions(), HttpStatus.OK);
	}

	@GetMapping("all_exams")
	public ResponseEntity<List<User_Test>> allExams() throws Exception{
		logger.trace("allExams Controller method Accessed...");
		return new ResponseEntity<List<User_Test>>(topPerformer.allExams(), HttpStatus.OK);
	}
	
	@GetMapping("questions_category")
	public ResponseEntity<HashMap<String, Long>> questionsCategory() throws NoDataFoundedException{
		logger.trace("questionsCategory Controller method Accessed...");
		return new ResponseEntity<HashMap<String, Long>>(topPerformer.questionsCategory(), HttpStatus.OK);
	}
	
	@GetMapping("top_performer_avg")
	public ResponseEntity<Object> topPerformersAvg() throws Exception{
		logger.trace("topPerformersAvg Controller method Accessed...");
		return new ResponseEntity<Object>(topPerformer.topPerformersAvg(), HttpStatus.OK);
	}

}

