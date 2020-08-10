package com.cg.onlineTest.controller;


import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

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

import com.cg.onlineTest.dao.DaoTestClass;
import com.cg.onlineTest.entities.Category;
import com.cg.onlineTest.entities.Question;
import com.cg.onlineTest.entities.Test;
import com.cg.onlineTest.entities.User;

@SpringBootApplication
@RestController
@CrossOrigin("*")
@RequestMapping("/checkOnly")
public class CheckTableDataController {
	
	
	@Autowired
	private DaoTestClass dao;
	
	@RequestMapping(value="/hello")
	public String sayHello() {
		/*
		 * this function is just for checking the application works properly or no.
		 * this function works when we pass url with /hello value. it uses ReqiestMapping to match function with url. 
		 */
		return "Hello World! from Spring Framework!";
	}
	
	//asldnaskjdb
	
	@PostMapping("/saveuser")
	public ResponseEntity<Object> registerUser(@RequestBody NewUser user){
		try {
			   User adduser = new User();
			   adduser.setUserName(user.userName);
			   adduser.setAdmin(user.isAdmin);
			   adduser.setEmailId(user.emailId);
			   adduser.setUserPassword(user.userPassword);
		       dao.AddUser(adduser);
		       return new ResponseEntity<Object>("User Data Saved in database", HttpStatus.OK);
		}
		catch(Exception exception) {
			return new ResponseEntity<Object>("An Error HAs been occured", HttpStatus.BAD_GATEWAY);
			
		}
		
	}
	
	
	@PostMapping("/savetest")
	public ResponseEntity<Object> registertest(@RequestBody NewTest user){
		try {
			   Test addTest = new Test();
			   addTest.setTestTitle(user.testTitle);
			   addTest.setTestDuration(user.testDuration);
			   addTest.setTestTotalMarks(user.testTotalMarks);
			   addTest.setStartDate(user.startDate);
			   addTest.setEndDate(user.endDate);
			   addTest.setTotalQuestion(user.totalQuestion);
			   dao.addTest(addTest);
		       return new ResponseEntity<Object>("User test Saved in database", HttpStatus.OK);
		}
		catch(Exception exception) {
			return new ResponseEntity<Object>("An Error HAs been occured", HttpStatus.BAD_GATEWAY);
			
		}
		
	}
	@PostMapping("/AddQuestion")
	public ResponseEntity<Object> addQuestion(@RequestBody Testclass test){
	   try {
		    Question ques = new Question();
		    ques.setQuestionTitle(test.questionTitle);
		    ques.setQuestionAnswer(test.questionAnswer);
		    ques.setQuestionMarks(test.questionMarks);
		    dao.addQuestion(ques);
		    return new ResponseEntity<Object>("Question Saved in database", HttpStatus.OK);
		    
	   }
	   catch(Exception exception) {
		   return new ResponseEntity<Object>("An Error HAs been occured", HttpStatus.BAD_GATEWAY);
	   }
	}
	
	@PostMapping("/Addcategory")
	public ResponseEntity<Object> addcategory(@RequestBody Stringclass Name){
	   try {
		    Category cat = new Category();
		    cat.setName(Name.name);
		    dao.addCategory(cat);
		    return new ResponseEntity<Object>("category Saved in database", HttpStatus.OK);
		    
	   }
	   catch(Exception exception) {
		   return new ResponseEntity<Object>("An Error HAs been occured", HttpStatus.BAD_GATEWAY);
	   }
	}
	
	@PostMapping("/addOption")
	public ResponseEntity<Object> addOption(@RequestBody Options option){
		try {
			long question_id = option.question_Id;
			Set<String> options = new HashSet<>();
			options.add(option.option1);
			options.add(option.option2);
			options.add(option.option3);
			options.add(option.option4);
			dao.addOptionInTest(question_id, options);
			return new ResponseEntity<Object>("Options added for question", HttpStatus.OK);
		}
		catch(Exception exception){
			return new ResponseEntity<Object>("An Error HAs been occured", HttpStatus.BAD_GATEWAY);
		}
	}
	
	@PostMapping("/addCategoryTest")
	public ResponseEntity<Object> addCategoryQuetion(@RequestBody addCategory cat){
		try {
			System.out.println(cat.question_id + " " + cat.category_id);
			dao.addCategory(cat.question_id, cat.category_id, cat.test_id);
			return new ResponseEntity<Object>("category and test added for question", HttpStatus.OK);
		}
		catch(Exception exception){
			return new ResponseEntity<Object>("An Error HAs been occured", HttpStatus.BAD_GATEWAY);
		}
	}
	
	@PostMapping("/updateTest")
	public ResponseEntity<Object> updateTestDetails(@RequestBody TestDetails test){
		try {
			dao.updateTestDetails(test.test_id, test.testTitle, test.testDuration, test.startDate, test.endDate, test.totalQuestion);
			return new ResponseEntity<Object>("Details updated successfully", HttpStatus.OK);
			
		}
		catch(Exception exception) {
			return new ResponseEntity<Object>("An Error HAs been occured", HttpStatus.BAD_GATEWAY);
		}
		
	}
	
	
	@GetMapping("/getAllUser")
	public ResponseEntity<Object> getUser(@RequestParam("userId") long userId){
		try {
			System.out.println("This Works..");
			return new ResponseEntity<Object>(dao.getAllUserList(userId), HttpStatus.OK);
		}
		catch(Exception exception) {
			return new ResponseEntity<Object>("Not Founded...", HttpStatus.BAD_GATEWAY);
		}
	}
	
	@GetMapping("/getAllTest")
	public ResponseEntity<Object> getTest(@RequestParam("testId") long testId){
		try {
			System.out.println("This Works..");
			return new ResponseEntity<Object>(dao.getAllTestList(testId), HttpStatus.OK);
		}
		catch(Exception exception) {
			return new ResponseEntity<Object>("Not Founded...", HttpStatus.BAD_GATEWAY);
		}
	}
	
	@GetMapping("/assignTest")
	public ResponseEntity<Object> assignTest(@RequestParam("testId") long testId, @RequestParam("userId") long userId){
		try {
	    dao.assignTest(testId, userId);
	    return new ResponseEntity<Object>("User Founded... and Test Assigned", HttpStatus.OK);
		}
		catch(Exception exception) {
			return new ResponseEntity<Object>("Not Founded...", HttpStatus.BAD_GATEWAY);
		}
	    
	}
	
	
	@GetMapping("/UpdateTest")
	public ResponseEntity<Object> updateTest(@RequestParam("UserId") long userId){
		try {
		
			return new ResponseEntity<Object>(dao.updateTest(userId), HttpStatus.OK);
		}
		catch(Exception exception) {
			return new ResponseEntity<Object>("Not Founded...", HttpStatus.BAD_GATEWAY);
		}
	}
	
	
	@GetMapping("/getQuestion")
	public ResponseEntity<Object> getQuestion(){
		try {
		   
			return new ResponseEntity<Object>(dao.getQuestion(), HttpStatus.OK);
		}
		catch(Exception exception) {
			return new ResponseEntity<Object>("Not Founded...", HttpStatus.BAD_GATEWAY);
		}
	}
	
	
	@GetMapping("/updateQuestion")
	public ResponseEntity<Object> updateQuestion(){
		try {
		   
			return new ResponseEntity<Object>("", HttpStatus.OK);
		}
		catch(Exception exception) {
			return new ResponseEntity<Object>("Not Founded...", HttpStatus.BAD_GATEWAY);
		}
	}
}

class NewUser{
	public String userName;
	public boolean isAdmin;
	public String userPassword;
	public String emailId;
}

class NewTest{
	public String testTitle;
	public long testDuration;
	public long testTotalMarks;
	public long totalQuestion;
	public Timestamp startDate;
	public Timestamp endDate;
}

class Testclass{
	public String questionTitle;
	public int questionAnswer;
	public long questionMarks;
}

class Stringclass{
	public String name;
}

class Options{
	public long question_Id;
	public String option1;
	public String option2;
	public String option3;
	public String option4;
}

class addCategory{
	public long test_id;
	public long question_id;
	public long category_id;
}

class TestDetails{
	public long test_id;
	public String testTitle;
	public long testDuration;
	public long totalQuestion;
	public Timestamp startDate;
	public Timestamp endDate;
}
