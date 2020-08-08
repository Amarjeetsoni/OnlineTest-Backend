package com.cg.onlineTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.onlineTest.entities.User;
import com.cg.onlineTest.entities.User_Test;
import com.cg.onlineTest.services.ResultAnalysisService;

@SpringBootTest
class ResultAnalysisTESTING {

	@Mock
	ResultAnalysisService dao;
	
	@Test
	void testGetResult() {
		User user = new User();
		when(dao.getResult(user)).thenReturn(null);
		assertEquals(null, dao.getResult(user));
	}

	@Test
	void testcategoryAnalysis() {
		User_Test userTest = new User_Test();
		when(dao.getCategoryAnalysis(userTest)).thenReturn(null);
		assertEquals(null, dao.getCategoryAnalysis(userTest));
	}

}
