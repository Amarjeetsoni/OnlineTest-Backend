package com.cg.onlineTest.services;

import java.util.List;

import com.cg.onlineTest.entities.Test;

public interface GetResultService {

	/*
	 * This method is used to check whether the user exist or not.
	 * @param userId This is parameter of long type of isUserExist method.
	 * @return boolean this return true if user is there and false if no such user is there.
	 */
	public boolean isUserExist(long userId);
	
	/*
	 * This method is used to get all upcoming test list assigned to particular user.
	 * @param userId This is parameter of long type of getUpcomingTest method.
	 * @return List<Test> this return List of test if data available otherwise throw exception.
	 */
	public Integer getUpcomingTest(long userId) throws Exception;	
	
	/*
	 * This method is used to get a Test object which is currently active.
	 * @param userId This is parameter of long type of getActiveTest method.
	 * @return Test this return test if data available otherwise throw exception.
	 */
	public Integer getActiveTest(long userId) throws Exception;
	
	/*
	 * This method is used to get all test list assigned to particular user.
	 * @param userId This is parameter of long type of getAssignedTest method.
	 * @return List<Test> this return List of test if data available otherwise throw exception.
	 */
	public Integer getAssignedTest(long userId) throws Exception;
	
	/*
	 * This method is used to assign test to particular user.
	 * @param userId This is parameter of long type of assignTest method.
	 * @param testId This is parameter of long type of assignTest method.
	 * @return boolean this return List of test if data available otherwise throw exception.
	 */
	public boolean assignTest(long testId, long userId) throws Exception;

}