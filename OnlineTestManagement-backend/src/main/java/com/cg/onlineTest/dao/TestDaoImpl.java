package com.cg.onlineTest.dao;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.onlineTest.entities.Question;
import com.cg.onlineTest.entities.Test;
import com.cg.onlineTest.exceptions.TestDataInvalidException;


@Repository("TestDaoImpl")
@Transactional
public class TestDaoImpl implements TestDao {
	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * this method is used to add the test ,parameters such as test id, test title,test duration  will 
	 * be added in the database
	 */
	@Override
	public void addTest(Test test) 
	{
		
		entityManager.persist(test);
		
	}
	
/*
 * this method implements the delete functionality i.e specific test id will be searched 
 * found id will be deleted and then corresponding test will be deleted from database
 */
	@SuppressWarnings("unused")
	@Override
	public void deleteTest(long test_Id) throws TestDataInvalidException 
	{
		Test test6=entityManager.find(Test.class, test_Id);
		if(test6.getAllQuestion().size() > 0) {
			System.out.println("Some Quetions are there so You can only modify the test.");
			throw new TestDataInvalidException("Some Quetion add to the test so You can only modify the test.");
		}
	
		if(test6!=null)
		{
			entityManager.remove(test6);
		}
		else
			throw new TestDataInvalidException("id does not exist");
	}
	
	/*
	 * this method will update the existing record such as id,title, and other attributes
	 * this will find the existing test id and corresponding changes will be made
	 */

	@Override
	public void updateTest(long test_Id,String testTitle,long testDuration,Timestamp startDate,Timestamp endDate) throws TestDataInvalidException 
	{
		Test test2=entityManager.find(Test.class,test_Id);
	
		test2.setTestTitle(testTitle);
		
		test2.setTestDuration(testDuration);
		if(test2.getTestDuration()<=0)
			throw new TestDataInvalidException("Test Duration can not be negative");
		test2.setStartDate(startDate);
		test2.setEndDate(endDate);
		int date2=test2.getStartDate().compareTo(test2.getEndDate());
		if(date2>0)
			throw new TestDataInvalidException("start date must be less than end date");
		
		
		entityManager.merge(test2);
		
		
	}

	/*
	 * if Admin want to see all tests present in the database 
	 * then this method will find all tests and store in a list and return to admin
	 */
	@Override
	public List<Test> getAllTests() 
	{
		String statement = "SELECT test FROM Test test";
		TypedQuery<Test> query = entityManager.createQuery(statement, Test.class);
		return query.getResultList();
	}

	@Override
	public List<String> getAllTitles() 
	{
		
		List<Test> test = getAllTests();
		List<String> testTitle = new ArrayList<>();
		test.forEach(t->testTitle.add(t.getTestTitle()));
		return testTitle;
		
	}
	
	/*
	 *this method is used to add the questions to the test from database
	 *this will find question by question id and set it to desired test id and the question will be then added to test
	 */

	@Override
	public void addQuestionTest(long questionId, long test_Id) throws TestDataInvalidException {
		
		Question question=entityManager.find(Question.class, questionId);
		if(question==null)
			throw new TestDataInvalidException("question id not present");
		Test test=entityManager.find(Test.class, test_Id);
		if(test==null)
			throw new TestDataInvalidException("test id not present");
		test.addQuestion(question);
		entityManager.merge(test);
	}

	

}
