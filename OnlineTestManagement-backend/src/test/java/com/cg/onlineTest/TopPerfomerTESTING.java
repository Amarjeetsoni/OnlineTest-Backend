package com.cg.onlineTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.onlineTest.exceptions.CannotRetrieveDataException;
import com.cg.onlineTest.services.TopPerformerService;

@SpringBootTest
class TopPerfomerTESTING {

	@Mock
	TopPerformerService dao;
	
	@Test
	void testGetTopPerformer() throws Exception {
		when(dao.topPerformers()).thenReturn(null);
		assertEquals(null, dao.topPerformers());
	}
	
	@Test
	void testGetTotalUsers() throws CannotRetrieveDataException {
		when(dao.getTotalUsers()).thenReturn(10L);
		assertEquals(10, dao.getTotalUsers());
	}
	
	@Test
	void testGetActiveUsers() throws CannotRetrieveDataException {
		when(dao.getTotalQuestions()).thenReturn(8L);
		assertEquals(8, dao.getTotalQuestions());
	}

}
