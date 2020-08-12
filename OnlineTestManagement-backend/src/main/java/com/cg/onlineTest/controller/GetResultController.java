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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineTest.entities.Test;
import com.cg.onlineTest.services.GetResultService;

@SpringBootApplication
@RestController
@CrossOrigin("*")
@RequestMapping("/getResult")
public class GetResultController {
	

	@Autowired
	private GetResultService resultService;
	
	Logger logger = LoggerFactory.getLogger(OnlineTestController.class);

	
	/*
	 * getTestByUserId Method takes userId as argument and return list of All test assigned to that particular user. 
	 */
	@GetMapping("getCountOfAllTestByUserId")
	public ResponseEntity<Integer> getTestByUserId(@RequestParam("userId") long userId) throws Exception{
		     logger.trace("GetTestByUserId Method Accessed...");    						// Default level is Info and trace is not upto the Info level so we have to set the property in application.context 
			 return new ResponseEntity<Integer>(resultService.getAssignedTest(userId) ,HttpStatus.OK);
	}
	
	
	/*
	 * getUpcomingTest Method takes userId as argument and return list of all upcoming test details.
	 */
	@GetMapping("getCountOfUpcomingTestByUserId")
	public ResponseEntity<Integer> getUpcomingTest(@RequestParam("userId") long userId) throws Exception{
		logger.trace("getUpcomingTest Method Accessed...");   
		return new ResponseEntity<Integer>(resultService.getUpcomingTest(userId), HttpStatus.OK);
	}
	
	
	/*
	 *  getActiveTest Method takes userId as Argument and return only test which is currently active.
	 */
	@GetMapping("getCountActiveTestByUserId")
	public ResponseEntity<Integer> getActiveTest(@RequestParam("userId") long userId) throws Exception{
		logger.trace("getActiveTest Method Accessed...");   
		return new ResponseEntity<Integer>(resultService.getActiveTest(userId), HttpStatus.OK);
	}
	
	@GetMapping("/assignTest")
	public ResponseEntity<Object> assignTest(@RequestParam("testId") long testId, @RequestParam("userId") long userId){
		try {
		resultService.assignTest(testId, userId);
	    return new ResponseEntity<Object>("User Founded... and Test Assigned", HttpStatus.OK);
		}
		catch(Exception exception) {
			return new ResponseEntity<Object>("Somting Wents wrong...", HttpStatus.BAD_GATEWAY);
		}
	    
	}
	

}