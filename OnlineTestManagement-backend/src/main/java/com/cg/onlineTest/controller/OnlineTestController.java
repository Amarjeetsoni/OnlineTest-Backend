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

import com.cg.onlineTest.entities.FeedBack;
import com.cg.onlineTest.entities.Question;
import com.cg.onlineTest.entities.Test;
import com.cg.onlineTest.services.OnlineTestService;

@SpringBootApplication
@RestController
@CrossOrigin("*")
@RequestMapping("/Start")
public class OnlineTestController {
	
	/*
	 * @Autowired is used to
	 * Inject the dependency of OnlineTestService class into OnlineTestCobtroller class.
	 * It internally uses setter or constructor to inject dependency. 
	 */
	@Autowired
	private OnlineTestService testService;
	
	/*
	 * Logger used to Record unusual circumstances or error that may be happening in the program.
	 * also use to get info what is going in the application.
	 */
	Logger logger = LoggerFactory.getLogger(OnlineTestController.class);
	
	
	/*
	 * getTestByUserId Method takes userId as argument and return list of All test assigned to that particular user. 
	 */
	@GetMapping("getAllTestByUserId")
	public ResponseEntity<List<Test>> getTestByUserId(@RequestParam("userId") long userId) throws Exception{
		     logger.trace("GetTestByUserId Method Accessed...");    						// Default level is Info and trace is not upto the Info level so we have to set the property in application.context 
			 return new ResponseEntity<List<Test>>(testService.getAllTestAssignToPerticularUser(userId) ,HttpStatus.OK);
	}
	
	
	/*
	 * getUpcomingTest Method takes userId as argument and return list of all upcoming test details.
	 */
	@GetMapping("getUpcommingTestByUserId")
	public ResponseEntity<List<Test>> getUpcomingTest(@RequestParam("userId") long userId) throws Exception{
		logger.trace("getUpcomingTest Method Accessed...");   
		return new ResponseEntity<List<Test>>(testService.getAllUpcomingTest(userId), HttpStatus.OK);
	}
	
	
	/*
	 *  getActiveTest Method takes userId as Argument and return only test which is currently active.
	 */
	@GetMapping("getActiveTestByUserId")
	public ResponseEntity<Test> getActiveTest(@RequestParam("userId") long userId) throws Exception{
		logger.trace("getActiveTest Method Accessed...");   
		return new ResponseEntity<Test>(testService.getActiveTest(userId), HttpStatus.OK);
	}
	
	
	/*
	 *  getAllQuestion Method takes testId as argument and return List of question assigned to particular test.
	 */
	@GetMapping("getAllQuestion")
	public ResponseEntity<List<Question>> getAllQuestion(@RequestParam("testId") long testId) throws Exception{
		logger.trace("getAllQuestion Method Accessed...");
		return new ResponseEntity<List<Question>>(testService.getAllQuestion(testId), HttpStatus.OK);
	}
	
	
	/*
	 * addFeedback takes the details of FeedbackFromUser class and add the feedback to particular test and return the message.
	 */
	@PostMapping("addFeedback")
	public ResponseEntity<Object> addFeedback(@RequestBody FeedbackFromUser feedback) throws Exception{
		logger.trace("addFeedback Method Accessed...");
		testService.addFeedback(feedback.feedback, feedback.test_id, feedback.user_id);
		return new ResponseEntity<Object>("Feedback add successfully", HttpStatus.OK);
	}
	
	/*
	 *  getAllFeedback takes testId as argument and return the list of feedback by particular user.
	 */
	@GetMapping("getAllFeedbackByTestId")
	public ResponseEntity<List<FeedBack>> getAllFeedback(@RequestParam("testId") long testId) throws Exception{
		logger.trace("getAllFeedback Method Accessed...");
		return new ResponseEntity<List<FeedBack>>(testService.getAllFeedback(testId), HttpStatus.OK);
	}
	
	/*
	 * setUserAnswers takes GetAnswer class object as an input and returns the response as a message. 
	 */
	@PostMapping("setTestAnswer")
	public ResponseEntity<Object> setUserAnswers(@RequestBody GetAnswer getAnswer) throws Exception{
		logger.trace("setUserAnswers Method Accessed...");
		testService.submitTest(getAnswer.answer,getAnswer.testId, getAnswer.userId);
		return new ResponseEntity<Object>("User Answer Updated", HttpStatus.OK);
	}
}


// classes required to take more than a parameter from front end.

/*
 * FeedbackFromUser class takes 3 object from front end which is only required to add feedback details.
 */
class FeedbackFromUser{
	public long user_id;
	public List<String> feedback;
	public long test_id;
	
}


/*
 * GetAnswer class takes 3 object from front end which is used to assign all the answers by particular user in the database. 
 */
class GetAnswer{
	public List<Integer> answer;
	public long userId;
	public long testId;
}
