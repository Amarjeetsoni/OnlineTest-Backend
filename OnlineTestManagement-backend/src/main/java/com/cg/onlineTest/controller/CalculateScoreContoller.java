package com.cg.onlineTest.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineTest.services.CalculateScoreService;

@SpringBootApplication
@RestController
@CrossOrigin("*")
@RequestMapping("/Score")
public class CalculateScoreContoller {
	Logger logger = LoggerFactory.getLogger(OnlineTestController.class);
	
	@Autowired
	CalculateScoreService calculateService;
	
	
	/*
	 * Method : getTestScore Description : Used to calculate the score of a test after user
	 * 										submits the test
	 * 
	 * @param userTestId : Identification of the Test attempted by User.
	 * 
	 * @return : Long value of score calculated for a test by a User.
	 * 
	 * throws Exception i.e.
	 * DataEnteringException : It is raised if there is an sql exception for entering data in the database.
	 * DataMergingException : It is raised if there is an sql exception for merging the data into database.
	 */
	@GetMapping("getScore/{userTestId}")
	public ResponseEntity<Object> getTestScore(@PathVariable Long userTestId) throws Exception{
		logger.info("Controller called for calculating score...");   
		return new ResponseEntity<Object>(calculateService.calculateScoreService(userTestId), HttpStatus.OK);
	}
	
	/*
	 * Method : getTestsList Description : Used to get all tests
	 * 
	 * 
	 * @return : List of type User_Test.
	 * 
	 * 
	 */
	@GetMapping("/getTests")
	public ResponseEntity<Object> getTestsList() throws Exception{
		   
		logger.info("Controller called for returning list of Tests...");
		return new ResponseEntity<Object>(calculateService.getTests(), HttpStatus.OK);
	}
	
	
	
}
