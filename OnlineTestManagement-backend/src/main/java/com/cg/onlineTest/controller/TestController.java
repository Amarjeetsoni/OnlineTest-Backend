package com.cg.onlineTest.controller;

import java.sql.Timestamp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineTest.entities.Test;
import com.cg.onlineTest.exceptions.DateInvalidException;
import com.cg.onlineTest.exceptions.TestDataInvalidException;

import com.cg.onlineTest.services.TestService;

@SpringBootApplication
@RestController
@CrossOrigin("*")
@RequestMapping("/Test")
public class TestController {
	
	@Autowired
	private TestService service;
	
	@GetMapping(value="/tests")
	public List<Test> getAllTests()
	{
		return service.getAllTests();
		
	}

	@GetMapping(value="/titles")
	public List<String> getAllTitles()
	{
		return service.getAllTitles();
		
	}

	@PostMapping(value="/test/new",consumes= {"application/json"})
	public String addTest(@RequestBody NewTest1 test) throws DateInvalidException, TestDataInvalidException
	{
		
		
		Test test1=new Test();
		
		test1.setTestTitle(test.testTitle);
		
		test1.setTestTotalMarks(test.testTotalMarks);
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		int number = test.startDate.compareTo(timeStamp);
		if(number < 0) {
			System.out.println("Invalid Start Date... Start date must be greater then current date");
			throw new TestDataInvalidException("Invalid Start Date... Start date must be greater then current date");
		}
		
		if(service.getAllTitles().contains(test.testTitle)) {
			System.out.println("Test Title already exist...");
			throw new TestDataInvalidException("title already exists");
		}
		test1.setTestDuration(test.testDuration);
		if(test1.getTestDuration()<=0)
			throw new TestDataInvalidException("Test Duration can not be negative");
		
		test1.setTotalQuestion(test.TotalQuestion);
		if(test1.getTotalQuestion()<=0)
			throw new TestDataInvalidException("Total questions can not be negative");
		
		test1.setStartDate(test.startDate);
		
		test1.setEndDate(test.endDate);
		
		int date1=test1.getStartDate().compareTo(test1.getEndDate());
		if( date1>0)
			throw new DateInvalidException("start date must be less than end date");
		
		service.addTest(test1);

		
		return "new test added successfully";
		
	}

	@DeleteMapping(value="delete/{test_Id}")
	public String deleteTest(@PathVariable long test_Id) throws TestDataInvalidException
	{
		System.out.println("In the delete Test");
		service.deleteTest(test_Id);
		return "test deleted successfully";
		
	}


	@PostMapping(value="/test/question",consumes= {"application/json"})
	public String addQuestionTest(@RequestBody AddQuestion add) throws TestDataInvalidException
	{
		service.addQuestionTest(add.questionId, add.test_Id);
		return "question added to test";
		
	}


	@PutMapping(value="update/test",consumes= {"application/json"})
	public String updateTest(@RequestBody NewTest2 test4) throws TestDataInvalidException
	{

			service.updateTest(test4.test_Id,test4.testTitle,test4.testDuration,test4.startDate,test4.endDate);
			
		return "test updated successfully";
		
	}




	}
	class NewTest1
	{
		public String testTitle;
		public long testDuration;
		public long testTotalMarks;
		public long TotalQuestion;
		public Timestamp startDate;
		public Timestamp endDate;

		}
	class NewTest2
	{
		public long test_Id;
		public String testTitle;
		public long testDuration;
	    public Timestamp startDate;
	    public Timestamp endDate;

	}

	class AddQuestion
	{
		public long test_Id;
		public long questionId;
	}
		