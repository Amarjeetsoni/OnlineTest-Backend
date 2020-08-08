package com.cg.onlineTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.onlineTest.services.TopPerformerService;

@SpringBootTest
class TopPerfomerTESTING {

	@Mock
	TopPerformerService dao;
	
	@Test
	void testGetTopPerformer() {
		when(dao.topPerformers()).thenReturn(null);
		assertEquals(null, dao.topPerformers());
	}
	
	@Test
	void testGetTotalUsers() {
		when(dao.getTotalUsers()).thenReturn(10);
		assertEquals(10, dao.getTotalUsers());
	}
	
	@Test
	void testGetActiveUsers() {
		when(dao.getActiveUsers()).thenReturn(8);
		assertEquals(8, dao.getActiveUsers());
	}

}
