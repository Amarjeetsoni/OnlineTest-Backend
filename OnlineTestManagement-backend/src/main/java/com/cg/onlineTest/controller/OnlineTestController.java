package com.cg.onlineTest.controller;

import java.util.List;


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

import com.cg.onlineTest.entities.FeedBack;
import com.cg.onlineTest.entities.Question;
import com.cg.onlineTest.entities.Test;
import com.cg.onlineTest.services.OnlineTestService;

@SpringBootApplication
@RestController
@CrossOrigin("*")
@RequestMapping("/Start")
public class OnlineTestController {
	
	@Autowired
	private OnlineTestService testService;
	
	@GetMapping("getAllTestByUserId")
	public ResponseEntity<List<Test>> getTestByUSerId(@RequestParam("userId") long userId) throws Exception{
			 return new ResponseEntity<List<Test>>(testService.getAllTestAssignToPerticularUser(userId) ,HttpStatus.OK);
	}
	
	
	
	@GetMapping("getUpcommingTestByUserId")
	public ResponseEntity<List<Test>> getUpcomingTest(@RequestParam("userId") long userId) throws Exception{
		return new ResponseEntity<List<Test>>(testService.getAllUpcomingTest(userId), HttpStatus.OK);
	}
	
	
	@GetMapping("getActiveTestByUserId")
	public ResponseEntity<Test> getActiveTest(@RequestParam("userId") long userId) throws Exception{
		return new ResponseEntity<Test>(testService.getActiveTest(userId), HttpStatus.OK);
	}
	
	
	@GetMapping("getAllQuestion")
	public ResponseEntity<List<Question>> getAllQuestion(@RequestParam("testId") long testId) throws Exception{
		return new ResponseEntity<List<Question>>(testService.getAllQuestion(testId), HttpStatus.OK);
	}
	
	
	@PostMapping("addFeedback")
	public ResponseEntity<Object> getAllQuestion(@RequestBody FeedbackFromUser feedback) throws Exception{
		testService.addFeedback(feedback.feedback, feedback.test_id, feedback.user_id);
		return new ResponseEntity<Object>("Feedback add successfully", HttpStatus.OK);
	}
	
	@GetMapping("getAllFeedbackByTestId")
	public ResponseEntity<List<FeedBack>> getAllFeedback(@RequestParam("testId") long testId) throws Exception{
		return new ResponseEntity<List<FeedBack>>(testService.getAllFeedback(testId), HttpStatus.OK);
	}
	
	@PostMapping("setTestAnswer")
	public ResponseEntity<Object> serUserAnswers(@RequestBody GetAnswer getAnswer) throws Exception{
		testService.submitTest(getAnswer.answer,getAnswer.testId, getAnswer.userId);
		return new ResponseEntity<Object>("User Answer Updated", HttpStatus.OK);
	}
}

class FeedbackFromUser{
	public long user_id;
	public List<String> feedback;
	public long test_id;
	
}

class GetAnswer{
	public List<Integer> answer;
	public long userId;
	public long testId;
}
