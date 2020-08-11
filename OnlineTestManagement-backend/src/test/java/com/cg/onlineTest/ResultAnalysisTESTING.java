package com.cg.onlineTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.onlineTest.entities.User;
import com.cg.onlineTest.entities.User_Test;
import com.cg.onlineTest.services.ResultService;

@SpringBootTest
class ResultAnalysisTESTING {

	@Mock
	ResultService resultAnalysisService;
	
	@Test
	void testGetResult() throws Exception {
		User user = new User();
		when(resultAnalysisService.getResult(101L)).thenReturn(null);
		assertEquals(null, resultAnalysisService.getResult(101L));
	}

	@Test
	void testCategoryAnalysis() throws Exception {
		User_Test userTest = new User_Test();
		when(resultAnalysisService.getCategoryResult(101L)).thenReturn(null);
		assertEquals(null, resultAnalysisService.getCategoryResult(101L));
	}

}
