package com.cg.onlineTest.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
	 * 
	 * throws Exception i.e.
	 * 
	 */
	@GetMapping("getScore/{userTestId}")
	public Long getTestScore(@PathVariable Long userTestId) throws Exception{
		logger.info("Controller called for calculating score...");   
		Long totalMarks =  calculateService.calculateScoreService(userTestId);
		System.out.println(totalMarks);
		return totalMarks;
	}
	
	
	
	
	
}
