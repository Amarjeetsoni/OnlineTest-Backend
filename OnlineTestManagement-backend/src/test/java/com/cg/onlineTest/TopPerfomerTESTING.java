package com.cg.onlineTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.onlineTest.exceptions.CannotRetrieveDataException;
import com.cg.onlineTest.services.TopPerformerService;

@SpringBootTest
class TopPerfomerTESTING {

	@Autowired
	TopPerformerService service;
	
	@Test
	void testGetTotalTests() throws Exception {
		assertEquals(4, service.getTotalTests());
	}
	
	@Test
	void testGetTotalUsers() throws CannotRetrieveDataException {
		
		assertEquals(7, service.getTotalUsers());
	}
	
	@Test
	void testGetQuestion() throws CannotRetrieveDataException {
		assertEquals(39, service.getTotalQuestions());
	}
	
	@Test
	void testGetAllExamsException() {
		assertDoesNotThrow(()-> service.allExams());
	}
	
	@Test
	void testquestionsCategoryException() {
		assertDoesNotThrow(()-> service.questionsCategory());
	}
	
	
}
