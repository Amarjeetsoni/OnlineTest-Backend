package com.cg.onlineTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.onlineTest.services.GetResultService;

@SpringBootTest
class GetResultTESTING {

	@Mock
	GetResultService dao;
	
	
	@Test
	void testGetUpcomingTest() throws Exception
	{
		when(dao.getUpcomingTest(0)).thenReturn(null);
		assertEquals(null, dao.getUpcomingTest(0));
	}
	
	@Test
	void testActiveTest() throws Exception
	{
		when(dao.getActiveTest(1)).thenReturn(null);
		assertEquals(null, dao.getActiveTest(1));
		
	}
	
	@Test
	void testAssignedTest() throws Exception
	{
		when(dao.getAssignedTest(2)).thenReturn(null);
		assertEquals(null, dao.getAssignedTest(2));
	}
	
	@Test
	void testAssignTest() throws Exception
	{
		when(dao.assignTest(101, 102)).thenReturn(null);
		assertEquals(null, dao.assignTest(101, 102));
	}

}
