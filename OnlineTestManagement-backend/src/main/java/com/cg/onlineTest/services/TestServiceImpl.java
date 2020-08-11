package com.cg.onlineTest.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.cg.onlineTest.dao.TestDao;
import com.cg.onlineTest.entities.Test;
import com.cg.onlineTest.exceptions.TestDataInvalidException;


@Transactional
@Service("TestServiceImpl")
public class TestServiceImpl implements TestService {

	@Autowired
    TestDao dao;
	
	
	/*
	 * here this method will call dao layer and get all the tests added to it 
	 */
	@Override
	public void addTest(Test test) 
	{
		dao.addTest(test);
	}
	
	
	/*
	 * this method will call the method in dao layer and store the test is which has to be deleted
	 */
	
	@Override
	public void deleteTest(long test_Id) throws TestDataInvalidException 
	{
		dao.deleteTest(test_Id);
	}
	
	/*
	 * this method will also call dao layer and the requested data to be changed will be saved by it
	 */
	
	@Override
	public void updateTest(long test_Id,String testTitle,long testDuration,Timestamp startDate,Timestamp endDate) throws TestDataInvalidException 
	{
		
		dao.updateTest(test_Id,testTitle,testDuration,startDate,endDate);
	}
	
	/*
	 * this method call dao layer and save the list of all tests
	 */
	@Override
	public List<Test> getAllTests()
	{
		
		return dao.getAllTests();
	}
	
	
	/*
	 * this method call dao layer and save the list of all testtitles
	 */
	
	@Override
	public List<String> getAllTitles() 
	{
		
		return dao.getAllTitles();
	}

/*
* this method will call dao layer and save the test id and question id to add the questions in test
*/
	@Override
	public void addQuestionTest(long questionId, long test_Id) throws TestDataInvalidException 
	{
		
		dao.addQuestionTest(questionId, test_Id);
	}


	

}
