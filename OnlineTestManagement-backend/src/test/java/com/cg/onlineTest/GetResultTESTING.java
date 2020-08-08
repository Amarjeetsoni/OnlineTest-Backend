package com.cg.onlineTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.onlineTest.entities.User;
import com.cg.onlineTest.entities.User_Test;
import com.cg.onlineTest.services.GetResultService;

@SpringBootTest
class GetResultTESTING {

	@Mock
	GetResultService dao;
	
	@Test
	void testGetResult()
	{
		User user=new User();
		when(dao.getResult(user)).thenReturn(null);
		assertEquals(null, dao.getResult(user));
	}
	
	@Test
	void testActiveTest()
	{
		com.cg.onlineTest.entities.Test test=new com.cg.onlineTest.entities.Test();
		when(dao.activeTest(test)).thenReturn(null);
		assertEquals(null, dao.activeTest(test));
		
	}
	
	@Test
	void testAssignedTest()
	{
		User_Test userTest=new User_Test();
		when(dao.getAssignedTest(userTest)).thenReturn(null);
		assertEquals(null, dao.getAssignedTest(userTest));
	}

}
