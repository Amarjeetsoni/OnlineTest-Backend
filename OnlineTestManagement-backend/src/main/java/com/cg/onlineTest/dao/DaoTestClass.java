package com.cg.onlineTest.dao;




import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.onlineTest.entities.Category;
import com.cg.onlineTest.entities.Question;
import com.cg.onlineTest.entities.Test;
import com.cg.onlineTest.entities.User;
import com.cg.onlineTest.entities.User_Test;


@Repository("DaoTestClass")
@Transactional
public class DaoTestClass {

	
	@PersistenceContext
	private EntityManager entityManager;
	
	public boolean AddUser(User user) {
		entityManager.persist(user);
		return true;
	}
	
	public boolean addTest(Test test) {
		entityManager.persist(test);
		return true;
	}
	
	public User getAllUserList(){
//		String statement = "SELECT user FROM User user";
//		 TypedQuery<User> query = entityManager.createQuery(statement, User.class);
//		List<User> userList = query.getResultList();
		long var1 = 724;
		User user = entityManager.find(User.class, var1);
		return user;
	}
	
	public Test getAllTestList(){
//		String statement = "SELECT user FROM Test user";
//		 TypedQuery<Test> query = entityManager.createQuery(statement, Test.class);
//		List<Test> userList = query.getResultList();
//		return userList.get(0);
		long var1 = 727;
		Test user = entityManager.find(Test.class, var1);
		user.getAllQuestion().forEach(t->System.out.println(t));
		return user;
	}
	
	public boolean assignTest() {
		long var = 727;
		long var1 = 724;
		long question = 20;
		Test test = entityManager.find(Test.class, var);
		User user = entityManager.find(User.class, var1);
		List<Boolean> answers = new ArrayList<>();
		answers.add(true);
		answers.add(false);
		answers.add(true);
		answers.add(true);
		answers.add(false);
		answers.add(true);
		
        System.out.println(test.getTest_Id());
        System.out.println(user.getUserId());
		
		User_Test usertest = new User_Test(user, test, question);
//		usertest.setUser_Test_Id(101);
		test.addUserTestDetails(usertest);
		usertest.setTestCorrectAnswer(answers);
//		
		entityManager.merge(usertest);

		
		return true;
	}
	
	public User_Test updateTest() {
		long val = 730;
        User_Test user = entityManager.find(User_Test.class, val);
//		entityManager.merge(user);
        return user;
	}
	
	public boolean addQuestion(Question test) {
		entityManager.persist(test);
		return true;
	}
	
	public Question getQuestion() {
		long var = 733;
		Question ques = entityManager.find(Question.class, var);
		return ques;
	}
	
	public boolean addCategory(Category cat) {
		entityManager.persist(cat);
		return true;
	}
	
	
	
	public boolean updateQuestion() {
		long var = 733;
		long catId = 734;
		long testId = 727;
		Question ques = entityManager.find(Question.class, var);
		Category cat = entityManager.find(Category.class, catId);
		Test test = entityManager.find(Test.class, testId);
		Set<String> answers = new HashSet<>() ;
		answers.add("5");
		answers.add("6");
		answers.add("7");
		answers.add("8");
		ques.setQuestionOptions(answers);
		cat.addQuestion(ques);
		test.addQuestion(ques);
		entityManager.merge(cat);
		entityManager.merge(test);
	    entityManager.merge(ques);
		return true;
	}
	
	
}
