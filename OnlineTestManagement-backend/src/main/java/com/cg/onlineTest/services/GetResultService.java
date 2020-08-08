package com.cg.onlineTest.services;

import java.util.HashMap;
import java.util.Set;

import com.cg.onlineTest.entities.Test;
import com.cg.onlineTest.entities.User;
import com.cg.onlineTest.entities.User_Test;

public interface GetResultService {
	public Set<User_Test> getResult(User user);
	public Set<Test> activeTest(Test test);
	public HashMap<User, Test> getAssignedTest(User_Test userTest);

}
