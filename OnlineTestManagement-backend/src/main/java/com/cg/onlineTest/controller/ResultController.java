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

import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineTest.OnlineTestManagementBackendApplication;
import com.cg.onlineTest.services.ResultService;

@SpringBootApplication
@RestController
@CrossOrigin("*")
public class ResultController {

	@Autowired
	private ResultService resultService;
	
	Logger logger = LoggerFactory.getLogger(OnlineTestManagementBackendApplication.class);
	String msg;
	
	/*
	 * Method : getUserResult Description : Used to fetch the result details of a \
	 * particular user from database.
	 * 
	 * @param userId : Identification of the User attempted the tests.
	 * 
	 * @return List<userTest> : It returns the ArrayList of Results.
	 * 
	 * throws Exception i.e.
	 * NoDataFoundedException : It is raised if there are no bookings in the database.
	 * DataMismatchException : It is raised if there is an sql exception.
	 */
	@GetMapping("/getResult/{userId}")
	public ResponseEntity<Object> getUserResult(@PathVariable Long userId) throws Exception{
		   
		msg = "Fetching the Results of user :" + userId;
		logger.info(msg);
		return new ResponseEntity<Object>(resultService.getResult(userId), HttpStatus.OK);
	}
	

	/*
	 * Method : getCategoryResult Description : Used to fetch the category wise result of a \
	 * particular test from database.
	 * 
	 * @param userTestId : Identification of the test attempted by any particular user.
	 * 
	 * @return List<CategoryResult> : It returns the ArrayList of Category wise Results.
	 * 
	 * throws Exception i.e.
	 * NoDataFoundedException : It is raised if there are no bookings in the database.
	 * DataMismatchException : It is raised if there is an sql exception.
	 */
	@GetMapping("/getCategoryResult/{userTestId}")
	public ResponseEntity<Object> getCategoryResult(@PathVariable Long userTestId) throws Exception{
		
		msg = "Fetching the Category wise results :";
		logger.info(msg);
		return new ResponseEntity<Object>(resultService.getCategoryResult(userTestId), HttpStatus.OK);
	}
	
}
