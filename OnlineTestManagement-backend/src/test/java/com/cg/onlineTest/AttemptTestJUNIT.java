package com.cg.onlineTest;


import static org.junit.Assert.assertThrows;
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
import com.cg.onlineTest.exceptions.DataMismatchExcpetion;
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
		List<com.cg.onlineTest.entities.Test> test = null;
		
		//--------------------- TEST CASE 1 -----------------------------//
		/*
		 * In this test case we are passing a invalid user Id
		 * that function throws  DataMismatchExcpetion with message "No such User Exist...".
		 */
			
		
		assertThrows(DataMismatchExcpetion.class,()->{
			testService.getAllTestAssignToPerticularUser(101);
		});
		
		//--------------------- TEST CASE 2 -----------------------------//
		/*
		 * In this test case we are passing a valid UserId but test are not alloted to that user then
		 * that function throws NoDataFoundedException with message "No Test Assigned to particular User...".
		 */
		
		assertThrows(NoDataFoundedException.class,()->{
			testService.getAllTestAssignToPerticularUser(762);
		});
		
		//--------------------- TEST CASE 3 -----------------------------//
		/*
		 * In this test case we are passing a valid UserId which has assigned Test then
		 * that function return test Details we are checking number of Test assigned.
		 */
		
			test = testService.getAllTestAssignToPerticularUser(735);
		    assertEquals(7, test.size());
		
	}
	
	@Test
	@DisplayName("Testing of getAllTest")
	void getAllUpcomingTestTEST() throws Exception {
		logger.info("Validation of Get All test to a particular user");
		List<com.cg.onlineTest.entities.Test> test = null;
		
		//--------------------- TEST CASE 1 -----------------------------//
		/*
		 * In this test case we are passing a valid UserId and upcoming test is there then
		 * that function returns test details and we are checking number here;
		 */
		test = testService.getAllTest();
		assertEquals(8, test.size());
		
		
	}
	
	@Test
	@DisplayName("Testing of getActiveTest")
	void getActiveTestTEST() throws Exception {
		logger.info("Validation of Active Test");
		com.cg.onlineTest.entities.Test testPredefined = new com.cg.onlineTest.entities.Test();
		testPredefined.setTestTitle("Title of test");
		
		
		//--------------------- TEST CASE 1 -----------------------------//
		/*
		 * In this test case we are passing a valid UserId and valid test id but test is already attempted then
		 * that function throws DataMismatchExcpetion with message "You Have Already Taken the test...".
		 */
		
		 assertThrows(Exception.class,()->{
		     testService.getActiveTest(735, 737);
		 });
		
		//--------------------- TEST CASE 2 -----------------------------//
		/*
		 * In this test case we are passing a valid UserId which has Currently active test then
		 * that function return test Details we are checking Test Title
		 */
		
		 assertEquals("Hindi Test", testService.getActiveTest(736, 737).getTestTitle());
		
	}
	
	
	@Test
	@DisplayName("Testing of getAllQuestion")
	void getAllQuestionTEST() throws Exception {
		logger.info("Validation of Get All Question of peticular test");
		List<Question> question = null;
		
		//--------------------- TEST CASE 1 -----------------------------//
		/*
		 * In this test case we are passing a valid test Id and in that test no question assigned then
		 * that function throws NoDataFoundedException with message "No question details are available for this test".
		 */
			 
		 assertThrows(Exception.class,()->{
			 testService.getAllQuestion(58, 758);	
		 });		
		
		
		//--------------------- TEST CASE 2 -----------------------------//
		/*
		 * In this test case we are passing a valid test id then
		 * that function returns test List we are checking number of test.
		 */
	    	question = testService.getAllQuestion(736, 737);
		    assertEquals(4, question.size());
		
			
	}
	
	
	
	@Test
	@DisplayName("Testing of getAllFeedback")
	void getAllFeedbackTEST() throws Exception {
		logger.info("Validation of Get All Feedback of perticular TestId");
		List<FeedBack> feedback = null;
		
		//--------------------- TEST CASE 1 -----------------------------//
		/*
		 * In this test case we are passing a valid test Id and in that test no feedback is there then
		 * that function throws NoDataFoundedException with message "No Feedback Data Is available..".
		 */
				
		assertThrows(NoDataFoundedException.class,()->{
			testService.getAllFeedback(738);	
		 }); 		
		
		
		//--------------------- TEST CASE 2 -----------------------------//
		/*
		 * In this test case we are passing a valid test id then
		 * that function return all feedback associated with that test id and we are checking number of feedback.
		 */
		
	    	feedback = testService.getAllFeedback(737);
		    assertEquals(4, feedback.size());
		
			
	}
	
	
	@Test
	@DisplayName("Testing of addFeedback")
	void addFeedbackTEST() throws Exception {
		logger.info("Validation of addFeedback");
		boolean feedback = false;
		List<String> fedMessage = new ArrayList<>();
		fedMessage.add("this is feedback");
		fedMessage.add("this is feedback");
		
		//--------------------- TEST CASE 1 -----------------------------//
		/*
		 * In this test case we are passing a valid invalid test Id or user id then
		 * that function  throws DataMismatchExcpetion with message "User Is Not assigned to a particular test".
		 */
			
		assertThrows(DataMismatchExcpetion.class,()->{
			testService.addFeedback(fedMessage, 152, 156);
		 }); 	
		
		
		//--------------------- TEST CASE 2 -----------------------------//
		/*
		 * In this test case we are passing a valid UserId and testId then
		 * that function return true that indicate feedback added successfully.
		 */

			feedback = testService.addFeedback(fedMessage, 737, 735);
		    assertEquals(true, feedback);
		
	}
	
	
	@Test
	@DisplayName("Testing of submitTest")
	void submitTestTEST() throws Exception {
		logger.info("Validation of submitTest");
		List<Integer> answers = new ArrayList<>();
		answers.add(1);
		answers.add(2);
		
		//--------------------- TEST CASE 1 -----------------------------//
		/*
		 * In this test case we are passing a invalid test Id or user id then
		 * that function throws NoDataFoundedException with message "User Details or test Details mismatch!".
		 */
		assertThrows(NoDataFoundedException.class,()->{
			testService.submitTest(answers, 152, 156);
		 }); 	
	    		
		
	}

}
