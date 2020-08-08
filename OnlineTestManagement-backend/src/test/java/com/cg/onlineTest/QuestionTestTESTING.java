package com.cg.onlineTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.cg.onlineTest.entities.Question;
import com.cg.onlineTest.services.QuestionTestService;

@RunWith(MockitoJUnitRunner.class)
class QuestionTestTESTING {

	//QuestionDao dao;
		@Mock
		private QuestionTestService dao;
		
		
	   //Deepika
		@Test
		void testretrieveAllQuestion() {
			
			when(dao.retrieveAllQuestion()).thenReturn(null);
			assertEquals(0, dao.retrieveAllQuestion());
		}
		
		@Test
		 void testaddQuestion() throws Exception{
			Question question1 = new Question();
			when(dao.addQuestion(question1)).thenReturn(null);
			assertEquals(0, dao.addQuestion(question1));
			
		}
		
		@Test
		void testdeleteQuestion() throws Exception {
			when(dao.deleteQuestion(1)).thenReturn(null);
			assertEquals(null, dao.deleteQuestion(1));
		}
		
		@Test
		void testupdateQuestion() throws Exception {
			Question question1 = new Question();
			when(dao.updateQuestion(question1)).thenReturn(null);
			assertEquals(0, dao.updateQuestion(question1));
		}

}
