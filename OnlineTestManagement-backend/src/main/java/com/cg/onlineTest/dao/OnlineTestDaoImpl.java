package com.cg.onlineTest.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.onlineTest.entities.FeedBack;
import com.cg.onlineTest.entities.Question;
import com.cg.onlineTest.entities.Test;
import com.cg.onlineTest.entities.User;
import com.cg.onlineTest.entities.User_Test;
import com.cg.onlineTest.exceptions.NoDataFoundedException;
import com.cg.onlineTest.exceptions.SqlInternalServerError;

@Repository("OnlineTestDaoImpl")
@Transactional
public class OnlineTestDaoImpl implements OnlineTestDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Test> getAllTestAssignToPerticularUser(long userId) throws Exception {
		try {
			String statement = "SELECT user FROM User_Test user WHERE User_Id=:pUser";
			TypedQuery<User_Test> query = entityManager.createQuery(statement, User_Test.class);
			query.setParameter("pUser", userId);
			List<User_Test> user_Test = query.getResultList();
			if(user_Test.size() == 0) {
				throw new NoDataFoundedException("No data Founded...");
			}
			List<Test> testList = new ArrayList<>();
			user_Test.forEach(t->System.out.println(t.getTestId().getTest_Id()));
			for(User_Test val: user_Test) {
				Test test = entityManager.find(Test.class, val.getTestId().getTest_Id());
				testList.add(test);
			}
			return testList;
			
		}
		catch(NoDataFoundedException exception) {
			throw new NoDataFoundedException("No Data Available in database...");
		}
		catch(Exception exception) {
			throw new SqlInternalServerError("Internal server error!");
		}
		
		
	}

	@Override
	public List<Test> getAllUpcomingTest(long userId) throws Exception{
		
		try {
		List<Test> testList = getAllTestAssignToPerticularUser(userId);
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		List<Test> updatedTestList = new ArrayList<>();
		if(updatedTestList.size() == 0) {
			throw new NoDataFoundedException("No data available");
		}
		for(Test test: testList) {
			int number = test.getEndDate().compareTo(timeStamp);
			if(number > 0) {
				updatedTestList.add(test);
			}
			
		}
		return updatedTestList;
		}
		catch(NoDataFoundedException exception) {
			throw new NoDataFoundedException("No data available..."); 
		}
		catch(Exception exception) {
			throw new SqlInternalServerError("Internal server error!");
		}
	}

	@Override
	public Test getActiveTest(long userId) throws Exception {
		try {
		List<Test> testList = getAllTestAssignToPerticularUser(userId);
		if(testList == null) {
			throw new NoDataFoundedException();
		}
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		for(Test test: testList) {
			int startNumber = test.getStartDate().compareTo(timeStamp);
			int endNumber = test.getEndDate().compareTo(timeStamp);
			if(startNumber > 0 && endNumber < 0) {
				return test;
			}
		}
		return null;
		}
		catch(NoDataFoundedException exception) {
			throw new NoDataFoundedException("No data available..."); 
		}
		catch(Exception Exception) {
			throw new SqlInternalServerError("Internal server error!");
		}
	}

	@Override
	public List<Question> getAllQuestion(long testId) throws Exception{
	      
		try {
		Test test = entityManager.find(Test.class, testId);
		List<Question> questionList = test.getAllQuestion();
		if(questionList.size() == 0) {
			throw new NoDataFoundedException();
		}
		return questionList;
		}
		catch(NoDataFoundedException exception) {
			throw new NoDataFoundedException("No Question available");
		}
		catch(Exception Exception) {
			throw new Exception("Internal Server Error");
		}
		
	}


	@Override
	public List<FeedBack> getAllFeedback(long testId) throws Exception {
		
		try {
		String statement = "SELECT feed FROM FeedBack feed WHERE Test_Id=:pUser";
		TypedQuery<FeedBack> query = entityManager.createQuery(statement, FeedBack.class);
		query.setParameter("pUser", testId);
		List<FeedBack> feed = query.getResultList();
		if(feed.size() == 0) {
			throw new NoDataFoundedException();
		}
		return feed;
		}
		catch(NoDataFoundedException exception) {
			throw new NoDataFoundedException("No Feedback Data Is available..");
		}
		catch(Exception exception) {
			throw new Exception("Internal Server Error!!");
		}
	}

	@Override
	public boolean isUserExist(long userId) {
		User user = entityManager.find(User.class, userId);
		if(user != null)
			return true;
		else
			return false;
	}

	@Override
	public boolean addFeedback(List<String> feedback, long testId, long userId) throws Exception {
		try {
		FeedBack feedbackObject = new FeedBack();
		feedbackObject.setFeedBackMessage(feedback);
		feedbackObject.setUser_id(userId);
	    Test test = entityManager.find(Test.class, testId);
	    test.addFeedback(feedbackObject);
	    entityManager.persist(feedbackObject);
	    entityManager.persist(test);
		return true;
		}
		catch(Exception exception) {
			throw new Exception("Internal Server Error!!");
		}
	}

	@Override
	public boolean submitTest(List<Integer> answer, long testId, long userId) throws Exception {
		
		try {
		String statement = "SELECT user FROM User_Test user WHERE User_Id=:pUser and Test_Id=:pTest";
		TypedQuery<User_Test> query = entityManager.createQuery(statement, User_Test.class);
		query.setParameter("pUser", userId);
		query.setParameter("pTest", testId);
	    User_Test user_Test = query.getResultList().get(0);
	    if(user_Test == null) {
	    	throw new NoDataFoundedException();
	    }
	    long totalAttempt = 0;
	    for(int number: answer) {
	    	if(number != 0) {
	    		totalAttempt++;
	    	}
	    }
	    user_Test.setAttempted(true);
	    user_Test.setTotalAttempt(totalAttempt);
	    user_Test.setUsertestAnswer(answer);
	    System.out.println(user_Test);
	    entityManager.merge(user_Test);
	    return true;
		}
		catch(NoDataFoundedException exception) {
			throw new NoDataFoundedException("No User details founded...!");
		}
		catch(Exception Exception) {
			throw new Exception("Internal Server Error...!");
		}
		
	}

}
