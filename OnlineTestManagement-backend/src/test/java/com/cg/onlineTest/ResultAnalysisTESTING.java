package com.cg.onlineTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.onlineTest.services.ResultService;

@SpringBootTest
class ResultAnalysisTESTING {

	@Mock
	ResultService resultAnalysisService;
	
	@Test
	void testGetResult() throws Exception {
		
		when(resultAnalysisService.getResult(101L)).thenReturn(null);
		assertEquals(null, resultAnalysisService.getResult(101L));
	}

	@Test
	void testCategoryAnalysis() throws Exception {
		
		when(resultAnalysisService.getCategoryResult(101L)).thenReturn(null);
		assertEquals(null, resultAnalysisService.getCategoryResult(101L));
	}

}
