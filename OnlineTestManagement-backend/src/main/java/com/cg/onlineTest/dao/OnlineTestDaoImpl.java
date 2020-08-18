package com.cg.onlineTest.dao;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.onlineTest.entities.FeedBack;
import com.cg.onlineTest.entities.Question;
import com.cg.onlineTest.entities.Test;
import com.cg.onlineTest.entities.User;
import com.cg.onlineTest.entities.User_Test;
import com.cg.onlineTest.exceptions.NoDataFoundedException;
import com.cg.onlineTest.exceptions.DataMismatchExcpetion;


/*
 * @Repository annotation is used to indicate that the class provides the mechanism for 
 * storage, retrieval, search, update and delete operation on object.
 */
@Repository("OnlineTestDaoImpl")
/*
 * @Transactional itself define the scope of a single database transaction.
 */
@Transactional
public class OnlineTestDaoImpl implements OnlineTestDao{
	
	/*
	 * @PersistenceContext annotation is used to inject EntityManager in session bean. 
	 */
	@PersistenceContext
	private EntityManager entityManager;
	
	Logger logger = LoggerFactory.getLogger(OnlineTestDaoImpl.class);
	/*
	 * Logger used to Record unusual circumstances or error that may be happening in the program.
	 * also use to get info what is going in the application.
	 */
  
	
	/*
	 * This method is used to get all test list assigned to particular user.
	 * @param userId This is parameter of long type of getAllTestAssignToPerticularUser method.
	 * @return List<Test> this return List of test if data available otherwise throw exception.
	 */
	@Override
	public List<Test> getAllTestAssignToPerticularUser(long userId) throws Exception {
		try {
			logger.info("getAllTestAssignToPerticularUser dao method is accessed.");
			if(!isUserExist(userId)){
				 throw new DataMismatchExcpetion("No such User Exist...");
			}
			String statement = "SELECT user FROM User_Test user WHERE User_Id=:pUser";
			TypedQuery<User_Test> query = entityManager.createQuery(statement, User_Test.class);
			query.setParameter("pUser", userId);
			List<User_Test> user_Test = query.getResultList();
			if(user_Test.size() == 0) {
				logger.error("No user Details is founded in USER_TEST table.");
				throw new NoDataFoundedException("No Test Assigned to particular User...");
			}
			List<Test> testList = new ArrayList<>();
			user_Test.forEach(t->System.out.println(t.getTestId().getTest_Id()));
			for(User_Test val: user_Test) {
				Test test = entityManager.find(Test.class, val.getTestId().getTest_Id());
				testList.add(test);
			}
			logger.info("Data founded and sent to user interface.");
			Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
			for(Test test: testList) {
				int number = test.getEndDate().compareTo(timeStamp);
				int secondComp = test.getStartDate().compareTo(timeStamp);
				int lastComp = timeStamp.compareTo(test.getEndDate());
				
				if(number > 0) {
					test.setTestStatus(1);
				}
				else {
					test.setTestStatus(-1);
				}
				if(secondComp <= 0 && lastComp < 0) {
					test.setTestStatus(0);
					
				}
				
			}
			return testList;
			
		}
		catch(DataMismatchExcpetion exception) {
			throw new DataMismatchExcpetion(exception.getMessage());
		}
		catch(NoDataFoundedException exception) {
			throw new NoDataFoundedException(exception.getMessage());
		}
		catch(Exception exception) {
			throw new DataMismatchExcpetion("Internal server error!");
		}
		
		
	}

	/*
	 * This method is used to get all upcoming test list assigned to particular user.
	 * @param userId This is parameter of long type of getAllUpcomingTest method.
	 * @return List<Test> this return List of test if data available otherwise throw exception.
	 */
	@Override
	public List<Test> getAllTest() throws Exception{
		
		try {
			logger.info("getAllTest dao method is accessed.");
			String statement = "SELECT test FROM Test test";
			TypedQuery<Test> query = entityManager.createQuery(statement, Test.class);
			List<Test> testList = query.getResultList();
			if(testList.size() == 0) {
				throw new NoDataFoundedException("No Test Is Held Yet!... Wait for Completion of test!!");
			}
			Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
			for(Test test: testList) {
				int number = test.getEndDate().compareTo(timeStamp);
				int secondComp = test.getStartDate().compareTo(timeStamp);
				int lastComp = timeStamp.compareTo(test.getEndDate());
				
				if(number > 0) {
					test.setTestStatus(1);
				}
				else {
					test.setTestStatus(-1);
				}
				if(secondComp <= 0 && lastComp < 0) {
					test.setTestStatus(0);
				}
				
			}
			return testList;
		}
		catch(NoDataFoundedException exception) {
			throw new NoDataFoundedException(exception.getMessage()); 
		}
		catch(Exception exception) {
			throw new DataMismatchExcpetion("Internal server error!");
		}
	}

	/*
	 * This method is used to get a Test object which is currently active.
	 * @param userId This is parameter of long type of getActiveTest method.
	 * @return Test this return test if data available otherwise throw exception.
	 */
	@Override
	public Test getActiveTest(long userId, long testId) throws Exception {
		try {
		
			logger.info("addFeedback dao method is accessed.");
			String statement = "SELECT user FROM User_Test user WHERE User_Id=:pUser and Test_Id=:pTest";
			TypedQuery<User_Test> query = entityManager.createQuery(statement, User_Test.class);
			query.setParameter("pUser", userId);
			query.setParameter("pTest", testId);
			User_Test user_Test = query.getResultList().get(0);
			if(user_Test.isAttempted()) {
				throw new DataMismatchExcpetion("You Have Already Taken the test...");
			}
			User user = entityManager.find(User.class, userId);
			if(user.isActiveTest()) {
				throw new NoDataFoundedException("A session is Already Active...");
			}
			Test test = entityManager.find(Test.class, testId);
			Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
			long miliSeconds = test.getEndDate().getTime() - timeStamp.getTime();
			long second =  miliSeconds/1000;
			long minutes = (second / 60);
			if(minutes < test.getTestDuration()) {
				throw new NoDataFoundedException("Start Test Time Is Passed... Now You can't start a new Test.");
			}
			return test;
		}
		catch(DataMismatchExcpetion exception) {
			throw new DataMismatchExcpetion(exception.getMessage());
		}
		catch(NoDataFoundedException exception) {
			throw new NoDataFoundedException(exception.getMessage()); 
		}
		catch(Exception Exception) {
			throw new java.lang.Exception("Internal server error!");
		}
	}

	
	/*
	 * This method is used to get a test Question list.
	 * @param testId This is parameter of long type of getAllQuestion method.
	 * @return List<Question> this return List of Questions if data available otherwise throw exception.
	 */
	@Override
	public List<Question> getAllQuestion(long userId, long testId) throws Exception{
	      
		try {
			User user = entityManager.find(User.class, userId);
			if(user.isActiveTest()) {
				throw new NoDataFoundedException("Already a Test session Is Active.");
			}
			logger.info("getAllQuestion dao method is accessed.");	
			Test test = entityManager.find(Test.class, testId);
			List<Question> questionList = test.getAllQuestion();
			if(questionList.size() == 0) {
				logger.error("No Question founded for this test");
				throw new NoDataFoundedException("No question details are available for this test");
			}
			logger.info("Founded test list and passed to service class");
			user.setActiveTest(true);
			entityManager.merge(user);
			return questionList;
		}
		catch(NoDataFoundedException exception) {
			throw new NoDataFoundedException(exception.getMessage());
		}
		catch(Exception Exception) {
			throw new Exception("Internal Server Error");
		}
		
	}


	
	/*
	 * This method is used to Get All feedback of particular test.
	 * @param testId This is parameter of long type of getAllFeedback method.
	 * @return List<Feedback> this return List of Feedback if data available otherwise throw exception.
	 */
	@Override
	public List<FeedBack> getAllFeedback(long testId) throws Exception {
		
		try {
			logger.info("getAllFeedback dao method is accessed.");	
			String statement = "SELECT feed FROM FeedBack feed WHERE Test_Id=:pUser";
			TypedQuery<FeedBack> query = entityManager.createQuery(statement, FeedBack.class);
			query.setParameter("pUser", testId);
			List<FeedBack> feed = query.getResultList();
			if(feed.size() == 0) {
				logger.error("No feedback available.");	
				throw new NoDataFoundedException("No Feedback Data Is available..");
			}
			logger.info("Feedback founded and sent to service class");
			return feed;
		}
		catch(NoDataFoundedException exception) {
			throw new NoDataFoundedException(exception.getMessage());
		}
		catch(Exception exception) {
			throw new Exception("Internal Server Error!!");
		}
	}
	
	
	/*
	 * This method is used to check whether the user exist or not.
	 * @param userId This is parameter of long type of isUserExist method.
	 * @return boolean this return true if user is there and false if no such user is there.
	 */
	@Override
	public boolean isUserExist(long userId) {
		logger.info("isUserExist dao method is accessed.");	
		User user = entityManager.find(User.class, userId);
		if(user != null) {
			logger.info("user Founded.");	
			return true;
		}
		else {
			logger.error("user Not Founded.");	
			return false;
		}
	}

	
	/*
	 * This method is used to add feedback in a particular test by particular user.
	 * @param feedback this contains List of feedback message by user
	 * @param testId long type of addFeedback method contains testId.
	 * @param userId long type of addFeedback method contains userId.
	 * @return boolean this return true if feedback added and false if any error is there.
	 */
	@Override
	public boolean addFeedback(List<String> feedback, long testId, long userId) throws Exception {
		try {
			logger.info("addFeedback dao method is accessed.");
			String statement = "SELECT user FROM User_Test user WHERE User_Id=:pUser and Test_Id=:pTest";
			TypedQuery<User_Test> query = entityManager.createQuery(statement, User_Test.class);
			query.setParameter("pUser", userId);
			query.setParameter("pTest", testId);
			List<User_Test> user_Test = query.getResultList();
			if(user_Test.isEmpty()) {
				logger.error("Wrong Feedback details");
				throw new DataMismatchExcpetion("User Is Not assigned to a particular test");
			}
			if(!user_Test.get(0).isAttempted()) {
				logger.error("User Does Not Attempted the test.");
				throw new DataMismatchExcpetion("User Does Not Attempted the test.");
			}
			FeedBack feedbackObject = new FeedBack();
			feedbackObject.setFeedBackMessage(feedback);
			feedbackObject.setUser_id(userId);
			Test test = entityManager.find(Test.class, testId);
			test.addFeedback(feedbackObject);
			entityManager.persist(feedbackObject);
			entityManager.persist(test);
			return true;
		}
		catch(DataMismatchExcpetion exception) {
			throw new DataMismatchExcpetion(exception.getMessage());
		}
		catch(Exception exception) {
			throw new Exception("Internal Server Error!!");
		}
	}

	
	/*
	 * This method is used to set user response on a particular test.
	 * @param answer this contains List of integer which is response of user
	 * @param testId long type of submitTest method contains testId.
	 * @param userId long type of submitTest method contains userId.
	 * @return boolean this return true if answer is saved otherwise throws an exception.
	 */
	@Override
	public boolean submitTest(List<Integer> answer, long testId, long userId) throws Exception {
		
		try {
			logger.info("submitTest dao method is accessed.");
			String statement = "SELECT user FROM User_Test user WHERE User_Id=:pUser and Test_Id=:pTest";
			TypedQuery<User_Test> query = entityManager.createQuery(statement, User_Test.class);
			query.setParameter("pUser", userId);
			query.setParameter("pTest", testId);
			List<User_Test> listUser_Test = query.getResultList();
			if(listUser_Test.isEmpty()) {
				logger.error("No test Details are available");
				throw new NoDataFoundedException("User Details or test Details mismatch!");
			}
			long totalAttempt = 0;
			for(int number: answer) {
				if(number != 0) {
					totalAttempt++;
				}
			}
			User_Test user_Test = listUser_Test.get(0);
			user_Test.setAttempted(true);
			user_Test.setTotalAttempt(totalAttempt);
			user_Test.setUsertestAnswer(answer);
			System.out.println(user_Test);
			entityManager.merge(user_Test);
			User user = entityManager.find(User.class, userId);
			user.setActiveTest(false);
			entityManager.merge(user);
			logger.info("data saved successfully.");
			return true;
		}
		catch(NoDataFoundedException exception) {
			throw new NoDataFoundedException(exception.getMessage());
		}
		catch(Exception Exception) {
			throw new Exception("Internal Server Error...!");
		}
		
	}
	
	
	

}
