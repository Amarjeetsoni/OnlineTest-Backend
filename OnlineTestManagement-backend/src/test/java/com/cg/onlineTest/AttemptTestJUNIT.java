package com.cg.onlineTest;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.onlineTest.controller.OnlineTestController;
import com.cg.onlineTest.entities.FeedBack;
import com.cg.onlineTest.entities.Question;
import com.cg.onlineTest.exceptions.NoDataFoundedException;
import com.cg.onlineTest.services.OnlineTestService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class AttemptTestJUNIT {

	/*
	 * Logger used to Record unusual circumstances or error that may be happening in the program.
	 * also use to get info what is going in the application.
	 */
	Logger logger = LoggerFactory.getLogger(OnlineTestController.class);
	
	/*
	 * @Autowired is used to
	 * Inject the dependency of walletService class into WalletApplicationProjectBackendApplicationTests class.
	 * It internally uses setter or constructor to inject dependency.  
	 */
	@Autowired
	private OnlineTestService testService;
	/*
	 * @BeforeAll annotation is used to signal that this method should be executed before any test case runs only once.
	 */
	@BeforeAll
	static void setUpBeforeClass() {
		System.out.println("Feating resouce Fetching resources for testing ...");
	}
	
	/*
	 *  @BeforeEach annotation is used to signal that this method should be executed before all test cases.
	 */
	@BeforeEach
	void setup() {
		System.out.println("Test Case Started");
	}
	
	
	/*
	 *  @AfterEach annotation is used to signal that this method should be executed after all test cases.
	 */
	@AfterEach
	void tearDown() {
		System.out.println("Test Case Over");
	}
	
	
	@Test
	@DisplayName("Testing of getAllTestAssignToPerticularUser")
	void getAllTestAssignToPerticularUserTEST() throws Exception {
		logger.info("Validation of Get All test Assign to perticular user");
		String message = null;
		List<com.cg.onlineTest.entities.Test> test = null;
		
		//--------------------- TEST CASE 1 -----------------------------//
		/*
		 * In this test case we are passing a invalid user Id
		 * that function is "No such User Exist...".
		 */
				
		 try {
			 
			test = testService.getAllTestAssignToPerticularUser(101);
		}
		catch(NoDataFoundedException exception) {
			message = exception.getMessage();
		}
		assertEquals("No such User Exist...", message);
		
		//--------------------- TEST CASE 2 -----------------------------//
		/*
		 * In this test case we are passing a valid UserId but test are not alloted to that user then
		 * that function is "No Test Assigned to particular User...".
		 */
		
	    try {
			test = testService.getAllTestAssignToPerticularUser(762);
		}
		catch(NoDataFoundedException exception) {
			message = exception.getMessage();
		}
		assertEquals("No Test Assigned to particular User...", message);
		
		//--------------------- TEST CASE 3 -----------------------------//
		/*
		 * In this test case we are passing a valid UserId which has assigned Test then
		 * that function return test Details we are checking number of Test assigned.
		 */
		
	    try {
			test = testService.getAllTestAssignToPerticularUser(735);
		}
		catch(Exception exception) {
			message = exception.getMessage();
		}
		assertEquals(4, test.size());
		
	}
	
	@Test
	@DisplayName("Testing of getAllUpcomingTest")
	void getAllUpcomingTestTEST() throws Exception {
		logger.info("Validation of Get All upcoming test to a particular user");
		String message = null;
		List<com.cg.onlineTest.entities.Test> test = null;
		
		//--------------------- TEST CASE 1 -----------------------------//
		/*
		 * In this test case we are passing a invalid user Id
		 * that function is "No such User Exist...".
		 */
				
		 try {
			 
			test = testService.getAllUpcomingTest(101);
		}
		catch(NoDataFoundedException exception) {
			message = exception.getMessage();
		}
		assertEquals("No such User Exist...", message);		
		
		
		//--------------------- TEST CASE 2 -----------------------------//
		/*
		 * In this test case we are passing a valid UserIdand upcoming test is there then
		 * that function returns test details and we are checking number here;
		 */
		
	    try {
			test = testService.getAllUpcomingTest(736);
		}
		catch(NoDataFoundedException exception) {
			message = exception.getMessage();
		}
		assertEquals(1, test.size());
		
		//--------------------- TEST CASE 3 -----------------------------//
		/*
		 * In this test case we are passing a valid UserId which has assigned upcoming Test then
		 * that function return test Details we are checking number of Test assigned
		 */
		
	    try {
			test = testService.getAllUpcomingTest(735);
		}
		catch(Exception exception) {
			message = exception.getMessage();
		}
		assertEquals(1, test.size());
		
	}
	
	@Test
	@DisplayName("Testing of getActiveTest")
	void getActiveTestTEST() throws Exception {
		logger.info("Validation of Active Test");
		String message = null;
		com.cg.onlineTest.entities.Test test = null;
		
		//--------------------- TEST CASE 1 -----------------------------//
		/*
		 * In this test case we are passing a invalid user Id
		 * that function is "No such User Exist...".
		 */
				
		 try {
			 
			test = testService.getActiveTest(101);
		}
		catch(NoDataFoundedException exception) {
			message = exception.getMessage();
		}
		assertEquals("No such User Exist...", message);		
		
		
		//--------------------- TEST CASE 2 -----------------------------//
		/*
		 * In this test case we are passing a valid UserId but No test is active then
		 * that function is "Currently No test is Active for given user".
		 */
		
	    try {
			test = testService.getActiveTest(736);
		}
		catch(NoDataFoundedException exception) {
			message = exception.getMessage();
		}
		assertEquals("Currently No test is Active for given user", message);
		
		//--------------------- TEST CASE 3 -----------------------------//
		/*
		 * In this test case we are passing a valid UserId which has Currently active test then
		 * that function return test Details we are checking number of Test assigned
		 */
		
	    try {
			test = testService.getActiveTest(735);
		}
		catch(Exception exception) {
			message = exception.getMessage();
		}
		assertEquals("Title of test", test.getTestTitle());
		
	}
	
	
	@Test
	@DisplayName("Testing of getAllQuestion")
	void getAllQuestionTEST() throws Exception {
		logger.info("Validation of Get All Question of peticular test");
		String message = null;
		List<Question> question = null;
		
		//--------------------- TEST CASE 1 -----------------------------//
		/*
		 * In this test case we are passing a valid test Id and in that test no question assigned then
		 * that function is "No question details are available for this test".
		 */
				
		 try {
			 
			 question = testService.getAllQuestion(758);
		}
		catch(NoDataFoundedException exception) {
			message = exception.getMessage();
		}
		assertEquals("No question details are available for this test", message);		
		
		
		//--------------------- TEST CASE 2 -----------------------------//
		/*
		 * In this test case we are passing a valid UserId but No test is active then
		 * that function is "Currently No test is Active for given user".
		 */
		
	    try {
	    	question = testService.getAllQuestion(737);
		}
		catch(NoDataFoundedException exception) {
			message = exception.getMessage();
		}
		assertEquals(5, question.size());
		
			
	}
	
	
	
	@Test
	@DisplayName("Testing of getAllFeedback")
	void getAllFeedbackTEST() throws Exception {
		logger.info("Validation of Get All Feedback of perticular TestId");
		String message = null;
		List<FeedBack> feedback = null;
		
		//--------------------- TEST CASE 1 -----------------------------//
		/*
		 * In this test case we are passing a valid test Id and in that test no feedback is there then
		 * that function is "No Feedback Data Is available..".
		 */
				
		 try {
			 
			 feedback = testService.getAllFeedback(738);
		}
		catch(NoDataFoundedException exception) {
			message = exception.getMessage();
		}
		assertEquals("No Feedback Data Is available..", message);		
		
		
		//--------------------- TEST CASE 2 -----------------------------//
		/*
		 * In this test case we are passing a valid UserId but No test is active then
		 * that function is "Currently No test is Active for given user".
		 */
		
	    try {
	    	feedback = testService.getAllFeedback(737);
		}
		catch(NoDataFoundedException exception) {
			message = exception.getMessage();
		}
		assertEquals(2, feedback.size());
		
			
	}
	
	
	@Test
	@DisplayName("Testing of addFeedback")
	void addFeedbackTEST() throws Exception {
		logger.info("Validation of addFeedback");
		String message = null;
		boolean feedback = false;
		List<String> fedMessage = new ArrayList<>();
		fedMessage.add("this is feedback");
		fedMessage.add("this is feedback");
		
		//--------------------- TEST CASE 1 -----------------------------//
		/*
		 * In this test case we are passing a valid invalid test Id or user id then
		 * that function is throws Exception with message "User Is Not assigned to a particular test".
		 */
				
		 try {
			 feedback = testService.addFeedback(fedMessage, 152, 156);
		}
		catch(NoDataFoundedException exception) {
			message = exception.getMessage();
		}
		assertEquals("User Is Not assigned to a particular test", message);		
		
		
		//--------------------- TEST CASE 2 -----------------------------//
		/*
		 * In this test case we are passing a valid UserId and testId then
		 * that function return true that indicate feedback added successfully.
		 */
		
		 try {

			 feedback = testService.addFeedback(fedMessage, 758, 735);
		}
		catch(NoDataFoundedException exception) {
			message = exception.getMessage();
		}
		assertEquals(true, feedback);
		
	}
	
	
	@Test
	@DisplayName("Testing of submitTest")
	void submitTestTEST() throws Exception {
		logger.info("Validation of submitTest");
		String message = null;
		List<Integer> answers = new ArrayList<>();
		answers.add(1);
		answers.add(2);
		
		//--------------------- TEST CASE 1 -----------------------------//
		/*
		 * In this test case we are passing a valid invalid test Id or user id then
		 * that function is throws Exception with message "User Details or test Details mismatch!".
		 */
				
		 try {
			 testService.submitTest(answers, 152, 156);
		}
		catch(NoDataFoundedException exception) {
			message = exception.getMessage();
		}
		assertEquals("User Details or test Details mismatch!", message);		
		
	}

}
