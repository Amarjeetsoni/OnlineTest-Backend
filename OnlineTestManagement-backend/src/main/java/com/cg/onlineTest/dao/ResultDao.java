package com.cg.onlineTest.dao;

import java.util.List;
import java.util.Set;

import com.cg.onlineTest.entities.User;
import com.cg.onlineTest.entities.User_Test;

public interface ResultDao {

	List<User_Test> getResult(Long userId);
	
}
