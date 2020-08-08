package com.cg.onlineTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.cg.onlineTest.services.OnlineTestService;

class AttemptTestTESTING {

	OnlineTestService testServices = Mockito.mock(OnlineTestService.class);
	
	
	@Test 
	void testisUserExist() {
	
		when(testServices.isUserExist(101)).thenReturn(false);
		assertEquals(true, testServices.isUserExist(101));
		
	}
	
	
	@Test
	void testgetAllTestAssignToPerticularUser() throws Exception {
//		when(testServices.add(5,6)).thenReturn(10);
//		assertEquals(10, testServices.add(5, 6));
//		fail("Not yet implemented");
		
		when(testServices.getAllTestAssignToPerticularUser(101)).thenReturn(null);
		assertEquals(null, testServices.getAllTestAssignToPerticularUser(101));
	}
	
	@Test
	void testgetAllUpcomingTest() throws Exception {
		
		when(testServices.getAllUpcomingTest(101)).thenReturn(null);
		assertEquals(null, testServices.getAllUpcomingTest(101));
		
	}
	
	@Test
	void testgetActiveTest() throws Exception {
		
		when(testServices.getActiveTest(101)).thenReturn(null);
		assertEquals(null, testServices.getActiveTest(101));
		
	}
	
	@Test
	void testgetAllQuestion() throws Exception {
		
		when(testServices.getAllQuestion(101)).thenReturn(null);
		assertEquals(null, testServices.getAllQuestion(101));
	}
	
	@Test 
	void testaddFeedback() throws Exception {
		
		when(testServices.addFeedback(null, 101, 102)).thenReturn(true);
		assertEquals(true, testServices.addFeedback(null, 101, 102));
	}
	
	
	@Test
	void testgetAllFeedback() throws Exception {
		
		when(testServices.getAllFeedback(101)).thenReturn(null);
		assertEquals(null, testServices.getAllFeedback(101));
	}
	
	

}
