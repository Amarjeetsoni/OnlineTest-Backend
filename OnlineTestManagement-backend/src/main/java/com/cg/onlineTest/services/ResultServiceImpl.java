package com.cg.onlineTest.services;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineTest.OnlineTestManagementBackendApplication;
import com.cg.onlineTest.dao.ResultDao;
import com.cg.onlineTest.entities.CategoryResult;
import com.cg.onlineTest.entities.User_Test;

@Service
@Transactional
public class ResultServiceImpl implements ResultService {

	
	Logger logger = LoggerFactory.getLogger(OnlineTestManagementBackendApplication.class);
	String msg;
	
	@Autowired
	private ResultDao resultDao;
	
	/*
	 * Method : getUserResult Description : Used to fetch the result details of a \
	 * particular user from database.
	 * 
	 * @param userId : Identification of the User attempted the tests.
	 * 
	 * @return List<userTest> : It returns the ArrayList of Results.
	 * 
	 * throws Exception i.e.
	 * NoDataFoundedException : It is raised if there are no bookings in the database.
	 * DataMismatchException : It is raised if there is an sql exception.
	 */
	@Override
	public List<User_Test> getResult(Long userId) throws Exception {
		
		msg = "Communicating with database for the Results of user :" + userId;
		logger.info(msg);
		return resultDao.getResult(userId);
	}

	

	/*
	 * Method : getCategoryResult Description : Used to fetch the category wise result of a \
	 * particular test from database.
	 * 
	 * @param userTestId : Identification of the test attempted by any particular user.
	 * 
	 * @return List<CategoryResult> : It returns the ArrayList of Category wise Results.
	 * 
	 * throws Exception i.e.
	 * NoDataFoundedException : It is raised if there are no bookings in the database.
	 * DataMismatchException : It is raised if there is an sql exception.
	 */
	@Override
	public List<CategoryResult> getCategoryResult(Long userTestId) throws Exception{
		
		msg = "Communicating with database for the Category Results ";
		logger.info(msg);
		return resultDao.getCategoryResult(userTestId);
	}

}
