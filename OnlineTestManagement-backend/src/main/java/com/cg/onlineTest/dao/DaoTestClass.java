package com.cg.onlineTest.dao;



import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.onlineTest.entities.Category;
import com.cg.onlineTest.entities.Question;
import com.cg.onlineTest.entities.Test;
import com.cg.onlineTest.entities.User;
import com.cg.onlineTest.entities.User_Test;
import com.cg.onlineTest.exceptions.NoDataFoundedException;


@Repository("DaoTestClass")
@Transactional
public class DaoTestClass {
	Logger logger = LoggerFactory.getLogger(DaoTestClass.class);

	@Autowired
	private OnlineTestDao onlineTestDao; 
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public boolean AddUser(User user) {
		entityManager.persist(user);
		return true;
	}
	
	public boolean addTest(Test test) {
		entityManager.persist(test);
		return true;
	}
	
	public User getAllUserList(long userId){
//		String statement = "SELECT user FROM User user";
//		 TypedQuery<User> query = entityManager.createQuery(statement, User.class);
//		List<User> userList = query.getResultList();
		User user = entityManager.find(User.class, userId);
		return user;
	}
	
	public Test getAllTestList(long testId){
//		String statement = "SELECT user FROM Test user";
//		 TypedQuery<Test> query = entityManager.createQuery(statement, Test.class);
//		List<Test> userList = query.getResultList();
//		return userList.get(0);
		Test user = entityManager.find(Test.class, testId);
		user.getAllQuestion().forEach(t->System.out.println(t));
		return user;
	}
	
	public boolean addOptionInTest(long question_id, Set<String> option) {
		Question question = entityManager.find(Question.class, question_id);
		question.setQuestionOptions(option);
		entityManager.merge(question);
		return true;
		
		
	}
	
	public boolean assignTest(long testId, long userId) throws Exception {
		
		try{
		String statement = "SELECT user FROM User_Test user WHERE User_Id=:pUser and Test_Id=:pTest";
		TypedQuery<User_Test> query = entityManager.createQuery(statement, User_Test.class);
		query.setParameter("pUser", userId);
		query.setParameter("pTest", testId);
		List<User_Test> user_Test = query.getResultList();
		if(!user_Test.isEmpty()) {
			logger.error("User Is assigned in that perticular test already...------------------------------------------");
			throw new NoDataFoundedException("User Is assigned in that perticular test already...");
		}
		
		Test test = entityManager.find(Test.class, testId);
		User user = entityManager.find(User.class, userId);
		if(test == null || user == null) {
			logger.error("User id Or Test Id Is Invalid...-----------------------------------------");
			throw new NoDataFoundedException("User id Or Test Id Is Invalid...");
		}
		
		List<Test> testList = onlineTestDao.getAllTestAssignToPerticularUser(userId);
		Timestamp testStartDate = test.getStartDate();
		Timestamp testEndDate = test.getEndDate();
		
		for(Test testObject: testList) {
			Timestamp assignedTestStartDate = testObject.getStartDate();
			Timestamp assignedTestEndDate = testObject.getEndDate();
			int firstCompare = testEndDate.compareTo(assignedTestStartDate);
			int secondCompare = assignedTestEndDate.compareTo(testStartDate);
			
			if(firstCompare > 0 && secondCompare > 0) {
				continue;
			}
			else {
				logger.error("In the given slot user has assigned in another test...");
				throw new Exception("In the given slot user has assigned in another test...");
			}
			
		}
		
		User_Test usertest = new User_Test(user, test, 0, false, 0);
		test.addUserTestDetails(usertest);
		entityManager.merge(usertest);
		return true;
		}
		catch(NoDataFoundedException exception) {
			throw new NoDataFoundedException(exception.getMessage());
		}
		catch(Exception exception) {
			throw new Exception("Internal Server Error..");
		}
	}
	
	public User_Test updateTest(long val) {
        User_Test user = entityManager.find(User_Test.class, val);
//		entityManager.merge(user);
        return user;
	}
	
	public boolean addQuestion(Question test) {
		entityManager.persist(test);
		return true;
	}
	
	public Question getQuestion() {
		long var = 733;
		Question ques = entityManager.find(Question.class, var);
		return ques;
	}
	
	public boolean addCategory(Category cat) {
		entityManager.persist(cat);
		return true;
	}
	
	
	
	public boolean addCategory(long var, long catId, long testId) {
		Question ques = entityManager.find(Question.class, var);
		Category cat = entityManager.find(Category.class, catId);
		Test test = entityManager.find(Test.class, testId);
		cat.addQuestion(ques);
		test.addQuestion(ques);
		entityManager.merge(cat);
		entityManager.merge(test);
	    entityManager.merge(ques);
		return true;
	}

	public boolean updateTestDetails(long test_id, String testTitle, long testDuration, Timestamp startDate,
			Timestamp endDate, long totalQuestion) {
		
		Test test = entityManager.find(Test.class, test_id);
		test.setEndDate(endDate);
		test.setStartDate(startDate);
		test.setTotalQuestion(totalQuestion);
		test.setTestDuration(testDuration);
		test.setTestTitle(testTitle);
		entityManager.persist(test);
		return true;
	}
	
	
}
