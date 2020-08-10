package com.cg.onlineTest;



import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.onlineTest.services.TestService;

@SpringBootTest
class TestTESTING {

	@Mock
	TestService dao;
	
	@Test
	public void deleteTest() 
	{
		
		long test_Id=500;
		dao.deleteTest(test_Id);
//		verify(dao,times(1)).deleteTest(test_Id);
	}

}
