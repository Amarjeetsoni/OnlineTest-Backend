package com.cg.onlineTest.dao;




import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.onlineTest.controller.OnlineTestController;

import com.cg.onlineTest.entities.CategoryResult;

import com.cg.onlineTest.entities.User_Test;
import com.cg.onlineTest.exceptions.DataEnteringException;
import com.cg.onlineTest.exceptions.DataMergingException;

import com.cg.onlineTest.exceptions.NoDataFoundedException;

@Repository
@Transactional
public class CalculateScoreDaoImpl implements CalculateScoreDao{

	
	@Autowired
	private EntityManager entityManager;
	
	Logger logger = LoggerFactory.getLogger(OnlineTestController.class);
	
	/*
	 * getUserTest method returns class User_Test based on the UserTestId
	 * 
	 * parameter accepted is userTestId
	 * 
	 * @return: User_test object based on userTestId
	 */
	@Override
	public User_Test getUserTest(long userTestId) throws Exception{
		logger.info("DAO method to fetch User_Test class based on UserId");
		try {
			return entityManager.find(User_Test.class, userTestId);
		}
		catch(Exception e)
		{
			throw new NoDataFoundedException("No Data Available in database...");
		}
		
	}

	/*
	 * setScore method merges class User_Test into database
	 * 
	 * parameter accepted is User_Test class
	 * 
	 * @return: boolean value
	 */
	@Override
	public boolean setScore(User_Test userTest) throws Exception{
		logger.info("DAO method to merge User_Test class into Database");
		try {
			entityManager.merge(userTest);
			return true;
		}
		catch(Exception e) {
			throw new DataMergingException("Data cannot be merged...");
		}
	}

	
	/*
	 * setCategoryResult method adds details class Category_Reslut into database
	 * 
	 * parameter accepted is Category_Result class
	 * 
	 */
	@Override
	public void setCategoryResult(CategoryResult categoryResult) throws Exception{
		try {
			entityManager.persist(categoryResult);
		}
		catch(Exception e) {
			throw new DataEnteringException("Data cannot be entered to Database...");
		}
		logger.info("DAO method to persist Category_Result class into Database");		
	}
}
