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
	ResultAnalysisService resultAnalysisService;
	
	@Test
	void testGetResult() {
		User user = new User();
		when(resultAnalysisService.getResult(user)).thenReturn(null);
		assertEquals(null, resultAnalysisService.getResult(user));
	}

	@Test
	void testCategoryAnalysis() {
		User_Test userTest = new User_Test();
		when(resultAnalysisService.getCategoryAnalysis(userTest)).thenReturn(null);
		assertEquals(null, resultAnalysisService.getCategoryAnalysis(userTest));
	}

}
