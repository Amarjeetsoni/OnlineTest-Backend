package com.cg.onlineTest.services;
import java.sql.Timestamp;
import java.util.List;


import com.cg.onlineTest.entities.Test;
import com.cg.onlineTest.exceptions.TestDataInvalidException;


public interface TestService {
	public void addTest(Test test);
	public void deleteTest(long test_Id)throws TestDataInvalidException;
	public void updateTest(long test_Id,String testTitle,long testDuration,Timestamp startDate,Timestamp endDate) throws TestDataInvalidException;
	public List<Test> getAllTests();
	public List<String> getAllTitles();
	public void addQuestionTest(long questionId,long test_Id) throws TestDataInvalidException;

}
