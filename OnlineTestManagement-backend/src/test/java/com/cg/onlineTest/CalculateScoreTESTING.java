package com.cg.onlineTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import com.cg.onlineTest.entities.User_Test;
import com.cg.onlineTest.services.CalculateScoreService;

@SpringBootTest
class CalculateScoreTESTING {

	@Mock
	private CalculateScoreService dao;

	
	@Test
	void calculateScore() {
		User_Test userTest = new User_Test(); 
		when(dao.calculateScore(userTest)).thenReturn(null);
		assertEquals(0, dao.calculateScore(userTest));
	}
	
	@Test
	void registerTest() {
		int userId=1;
		int testId=1;
		when(dao.registerTest(userId, testId)).thenReturn(null);
		assertEquals(0, dao.registerTest(userId, testId));
	}

}
