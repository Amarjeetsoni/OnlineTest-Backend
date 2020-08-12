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

import com.cg.onlineTest.entities.Test;
import com.cg.onlineTest.entities.User;
import com.cg.onlineTest.entities.User_Test;
import com.cg.onlineTest.exceptions.DataMismatchExcpetion;
import com.cg.onlineTest.exceptions.NoDataFoundedException;

@Repository("GetResultDaoImpl")
@Transactional


public class GetResultDaoImpl implements GetResultDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	Logger logger = LoggerFactory.getLogger(OnlineTestDaoImpl.class);

	/*
	 * This method is used to get all test list assigned to particular user.
	 * @param userId This is parameter of long type of getAllTestAssignToPerticularUser method.
	 * @return List<Test> this return List of test if data available otherwise throw exception.
	 */
	@Override
	public Integer getAssignedTest(long userId) throws Exception {
		return getAllTestAssign(userId).size();
		
	}

	/*
	 * This method is used to get upcoming test list assigned to particular user.
	 * @param userId This is parameter of long type of getUpcomingTest method.
	 * @return List<Test> this return List of test if data available otherwise throw exception.
	 */
	@Override
	public Integer getUpcomingTest(long userId) throws Exception {
		try {
			logger.info("getUpcomingTest dao method is accessed.");
			List<Test> testList = getAllTestAssign(userId);
			Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
			List<Test> updatedTestList = new ArrayList<>();
			for(Test test: testList) {
				int number = test.getEndDate().compareTo(timeStamp);
				if(number > 0) {
					updatedTestList.add(test);
				}
			
			}
			if(updatedTestList.size() == 0) {
				logger.error("All test is passed... no upcoming test is there for this user");
				throw new NoDataFoundedException("All test are passed");
			}
			logger.info("Upcoming test data is send");
			return updatedTestList.size();
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
	 * This method is used to get a Test object which is currently active.
	 * @param userId This is parameter of long type of getActiveTest method.
	 * @return Test this return test if data available otherwise throw exception.
	 */
	@Override
	public Integer getActiveTest(long userId) throws Exception {
		try {
			
			logger.info("getActiveTest dao method is accessed.");	
			List<Test> testList = getAllTestAssign(userId);
			int count = 0;
			Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
			for(Test test: testList) {
				int startNumber = test.getStartDate().compareTo(timeStamp);
				int endNumber = test.getEndDate().compareTo(timeStamp);
				if(startNumber > 0 && endNumber < 0) {
					logger.info("Test founded and sent to service class.");	
					count++;
				}
			}
			if(count > 0) {
				return count;
			}
			else {
				return 0;
			}
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

	@Override
	public boolean assignTest(long testId, long userId) throws Exception{
		try{
			String statement = "SELECT user FROM User_Test user WHERE User_Id=:pUser and Test_Id=:pTest";
			TypedQuery<User_Test> query = entityManager.createQuery(statement, User_Test.class);
			query.setParameter("pUser", userId);
			query.setParameter("pTest", testId);
			List<User_Test> user_Test = query.getResultList();
			if(!user_Test.isEmpty()) {
				logger.error("User Is assigned in that perticular test already...");
				throw new NoDataFoundedException("User Is assigned in that perticular test already...");
			}
			Test test = entityManager.find(Test.class, testId);
			User user = entityManager.find(User.class, userId);
			if(test == null || user == null) {
				logger.error("User id Or Test Id Is Invalid...");
				throw new NoDataFoundedException("User id Or Test Id Is Invalid...");
			}
			List<Test> testList = new ArrayList<>();
			testList = getAllTestAssign(userId);

				Timestamp testStartDate = test.getStartDate();
				Timestamp testEndDate = test.getEndDate();
				for(Test testObject: testList) {
					Timestamp assignedTestStartDate = testObject.getStartDate();
					Timestamp assignedTestEndDate = testObject.getEndDate();
					int firstCompare = assignedTestStartDate.compareTo(testEndDate);
					int secondCompare = testEndDate.compareTo(assignedTestEndDate);
					int thirdCompare = assignedTestStartDate.compareTo(testStartDate);
					int fourthCompare = testStartDate.compareTo(assignedTestEndDate);
					int fifthcompare =  testStartDate.compareTo(assignedTestStartDate);
					int sixthCompare  = assignedTestEndDate.compareTo(testEndDate);
					System.out.println(firstCompare + " " + secondCompare + " " + " " + thirdCompare + " " + fourthCompare + " " + fifthcompare + " " + sixthCompare);
					if((firstCompare > 0 && secondCompare > 0) || (thirdCompare > 0 && fourthCompare > 0) || (fifthcompare > 0 && sixthCompare > 0)) {
						logger.error("In the given slot user has assigned in another test...");
						System.out.println(assignedTestStartDate + " " + testStartDate + "\n" + assignedTestEndDate + " " + testEndDate);
						throw new NoDataFoundedException("In the given slot user has assigned in another test...");
					}
					else {
						continue;
					}
					
				}
				User_Test usertest = new User_Test(user, test, 0, false, 0);
				test.addUserTestDetails(usertest);
				entityManager.merge(usertest);
				logger.info("Test Assign successfully");
				return true;
			}
			catch(NoDataFoundedException exception) {
				throw new NoDataFoundedException(exception.getMessage());
			}
			catch(Exception exception) {
				throw new Exception("Internal Server Error...");
			}
	}
	
	
	public List<Test> getAllTestAssign(long userId) throws Exception{
		try {
			logger.info("getAssignedTest dao method is accessed.");
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


}