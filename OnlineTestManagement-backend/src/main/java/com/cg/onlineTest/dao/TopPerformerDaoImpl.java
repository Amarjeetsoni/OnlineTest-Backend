package com.cg.onlineTest.dao;


import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.onlineTest.entities.Category;

import com.cg.onlineTest.entities.User_Test;
import com.cg.onlineTest.exceptions.CannotRetrieveDataException;
import com.cg.onlineTest.exceptions.NoDataFoundedException;

/*
 * @Repository annotation is used to indicate that the class provides the mechanism for 
 * storage, retrieval, search, update and delete operation on object.
 */
@Repository("TopPerformerDaoImpl")
/*
 * @Transactional itself define the scope of a single database transaction.
 */
@Transactional
public class TopPerformerDaoImpl implements TopPerformerDao {

	/*
	 * @PersistenceContext annotation is used to inject EntityManager in session bean. 
	 */
	@PersistenceContext
	private EntityManager entityManager;
	
	Logger logger = LoggerFactory.getLogger(TopPerformerDao.class);
	
	/*
	 * This method is used to get all test list assigned to particular user.
	 * @return List<User_Test> this return List of user if data available otherwise throw exception.
	 */
	@Override
	public List<User_Test> topPerformers() throws Exception {
		try {
			logger.info("topPerformers dao method is accessed.");
			String statement = "SELECT user FROM User_Test user WHERE isAttempted=1";
			TypedQuery<User_Test> query = entityManager.createQuery(statement, User_Test.class);
			List<User_Test> user_Test = query.getResultList();
			if(user_Test.size() == 0) {
				logger.error("No user Details is founded in USER_TEST table.");
				throw new NoDataFoundedException("No Test Assigned to particular User...");
			}
			
			Comparator<User_Test> compareByMarks = Comparator.comparingLong(ob -> ob.getMarksScored());
			user_Test.sort(compareByMarks.reversed());
			
			if(user_Test.size()<4) {
				logger.info("3 or less user found and send to admin dashboard interface.");
				return user_Test;
			}
				
			logger.info("Data founded and sent to admin dashboard interface.");
			return user_Test.subList(0, 3);
		}
		catch(NoDataFoundedException exception) {
			throw new NoDataFoundedException(exception.getMessage());
		}
	}

	@Override
	public long getTotalUsers() throws CannotRetrieveDataException {
		try {
			logger.info("getTotalUsers dao method is accessed.");
			String statement = "SELECT COUNT(user.userId) FROM User user WHERE isAdmin=0";
			TypedQuery<Long> query = entityManager.createQuery(statement, Long.class);
			long numberOfUsers = (Long)query.getSingleResult();
				
			logger.info("getTotalUsers executed, data send to admin dashboard interface.");
			return numberOfUsers;
		}
		catch(Exception exception) {
			throw new CannotRetrieveDataException(exception.getMessage());
		}
	}

	@Override
	public long getTotalTests() throws CannotRetrieveDataException {
		try {
			logger.info("getTotalTests dao method is accessed.");
			String statement = "SELECT COUNT(test.test_Id) FROM Test test";
			TypedQuery<Long> query = entityManager.createQuery(statement, Long.class);
			long numberOfTest = (Long)query.getSingleResult();
				
			logger.info("getTotalTests executed, data send to admin dashboard interface.");
			return numberOfTest;
		}
		catch(Exception exception) {
			throw new CannotRetrieveDataException(exception.getMessage());
		}
	}

	@Override
	public long getTotalQuestions() throws CannotRetrieveDataException {
		try {
			logger.info("getTotalQuestions dao method is accessed.");
			String statement = "Select count(question.questionId) from Question question";
			TypedQuery<Long> query = entityManager.createQuery(statement, Long.class);
			long numberOfQuestions = (Long)query.getSingleResult();
				
			logger.info("getTotalQuestions executed, data send to admin dashboard interface.");
			return numberOfQuestions;
		}
		catch(Exception exception) {
			throw new CannotRetrieveDataException(exception.getMessage());
		}
	}

	@Override
	public List<User_Test> allExams() throws Exception {
		try {
			logger.info("allExams dao method is accessed.");
			String statement = "SELECT ut FROM User_Test ut";
			TypedQuery<User_Test> query = entityManager.createQuery(statement, User_Test.class);
			List<User_Test> user_Test = query.getResultList();
			if(user_Test.size() == 0) {
				logger.error("No Details is founded in USER_TEST table.");
				throw new NoDataFoundedException("No Details is founded in USER_TEST table...");
			}
			
				
			logger.info("Exams datafound and send to admin dashboard interface.");
			return user_Test;
		}
		catch(NoDataFoundedException exception) {
			throw new NoDataFoundedException(exception.getMessage());
		}
	}

	@Override
	public HashMap<String, Long> questionsCategory() throws NoDataFoundedException {
		try {
			logger.info("questionsCategory dao method is accessed.");
			String statement = "SELECT category FROM Category category";
			TypedQuery<Category> query = entityManager.createQuery(statement, Category.class);
			List<Category> category = query.getResultList();
			if(category.size() == 0) {
				logger.error("No Details is founded in USER_TEST table.");
				throw new NoDataFoundedException("No Details is founded in Category table...");
			}
			HashMap<String, Long> data = new HashMap<String, Long>();
			
			category.forEach(e-> {
				
				long number = (Long)e.getAllQuestion().stream().count();
				data.put(e.getName(), number);
				
			});
					
			logger.info("Exams datafound and send to admin dashboard interface.");
			return data;
		}
		catch(NoDataFoundedException exception) {
			throw new NoDataFoundedException(exception.getMessage());
		}		
	}
}
