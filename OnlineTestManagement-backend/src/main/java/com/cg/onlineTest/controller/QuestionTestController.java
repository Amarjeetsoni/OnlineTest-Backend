package com.cg.onlineTest.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineTest.OnlineTestManagementBackendApplication;

import com.cg.onlineTest.entities.Category;
import com.cg.onlineTest.entities.Question;
import com.cg.onlineTest.services.QuestionTestService;

@SpringBootApplication
@RestController
@CrossOrigin("*")
public class QuestionTestController {

	@Autowired
	QuestionTestService questionService;
	

	
	Logger logger = LoggerFactory.getLogger(OnlineTestManagementBackendApplication.class);
	String msg;
	
	
	
	
	/**********************************************************************************
     * Method              - getAllQuestion
     *Description          - To view a All Question
     *Created By            - Deepika                         
 
    *********************************************************************************/
	
	@GetMapping("/getAllQuestion")
	public ResponseEntity<List<Question>> getAllQuestion() throws Exception{
		
		logger.info("Retrieving Questions");
		return new ResponseEntity<List<Question>>(questionService.getAllQuestion(), HttpStatus.OK);
	}
	
	
//	@PostMapping("addQuestion")
//	public ResponseEntity<Object> addQuestion(@RequestBody Question question) throws Exception{
//		service.addQuestion(question);
//		return new ResponseEntity<Object>("Question added successfully", HttpStatus.OK);
//	}
	
	@PostMapping("/updateQuestion")
	public ResponseEntity<Object> updateQuestion(@RequestBody AddQuestionHelp question) throws Exception{
		logger.info("Updating");
		try {
			questionService.updateQuestion(question.questionId, question.questionTitle, question.questionAnswer,question.questionMarks);
			return new ResponseEntity<Object>("Details updated successfully", HttpStatus.OK);
			
		}
		catch(Exception exception) {
			return new ResponseEntity<Object>("An Error HAs been occured", HttpStatus.BAD_GATEWAY);
		}
	}
	
	/*******************************************************************************
     * Method             		  - Delete Question
     *Description          		  - To delete a particular Question
     *@param - questionId          - questionId that we want to delete
     *Created By                  - Deepika                          
 *********************************************************************************/  
	
	
	@GetMapping("deleteQuestion/{questionId}")
		 public ResponseEntity<String> deleteQuestion(@PathVariable("questionId") long questionId) throws Exception
			{
				try
				{
					logger.info("Deleted"+questionId);
					questionService.deleteQuestion(questionId);
					return new ResponseEntity<String>("Deleted Sucessfully",HttpStatus.NO_CONTENT);
				}
				catch(Exception exception)
				{
					return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
				}
			}
	
	
	
	

	/*************************************************************************************
	    * Method              - addCategory
	    *Description          - To add a category of Question
	    * parameter- Category - Category object to add a Category
	    *@RequestBody - Category   -Request body that to be updated
	    *Created By            - Deepika                           
	**************************************************************************************/
	
	
	@PostMapping("/Addcategory")
	public ResponseEntity<Object> addCategory(@RequestBody CategoryHelper Name) throws Exception{
		    Category cat = new Category();
		    cat.setName(Name.name);
		    questionService.addCategory(cat);
		    return new ResponseEntity<Object>("category Saved in database", HttpStatus.OK);
		    
	}
	

	/*************************************************************************************
	    * Method              - addQuestion
	    *Description          - To add  Question
	    * parameter- Question - Question object to add a Question
	    *@RequestBody - AddQuestion   -Request body that to be updated
	    *Created By            - Deepika                           
	**************************************************************************************/
	
	@PostMapping("/AddQuestion")
	public ResponseEntity<Object> addQuestion(@RequestBody AddQuestionHelper test){
		    Question ques = new Question();
		    ques.setQuestionTitle(test.questionTitle);
		    ques.setQuestionAnswer(test.questionAnswer);
		    ques.setQuestionMarks(test.questionMarks);
		    ques.setQuestionOptions(test.option);
		    questionService.addQuestion(ques, test.category_id);
		    return new ResponseEntity<Object>("Question added successfully", HttpStatus.OK);
		    
	}
	

}



// helper classes






class CategoryHelper{
	public String name;
}


class AddQuestionHelper{
	public String questionTitle;
	public int questionAnswer;
	public long questionMarks;
	public Set<String> option;
	public long category_id;
	
}
class AddQuestionHelp{
	public long questionId;
	public String questionTitle;
	public int questionAnswer;
	public long questionMarks;
	
}



