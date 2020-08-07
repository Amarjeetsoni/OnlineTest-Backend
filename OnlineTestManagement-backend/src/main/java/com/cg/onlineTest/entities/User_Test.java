package com.cg.onlineTest.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "USER_TEST_TAB")
public class User_Test {
	
	@Id
	@Column(name = "User_Test_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long user_Test_Id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "User_Id")
	@JsonManagedReference
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn( name = "Test_Id")
	@JsonManagedReference
	private Test test;
	

	@Column(name = "Total_Attempt_Question")
	private long totalAttempt;
	
	@ElementCollection
	@CollectionTable(name="listOfAnswers")
	private List<Integer> UsertestAnswer;
	
	@ElementCollection
	@CollectionTable(name="listOfCorrectAnswer")
	private List<Boolean> testCorrectAnswer;
	
	@Column(name = "IsAttempted")
	private boolean isAttempted;
	
	@Column(name = "MarksScored")
	private long marksScored;
	
	public User_Test() {
		
	}
	
	
	public User_Test(User user, Test test, long totalAttempt) {
		super();
		this.user = user;
		this.test = test;
		this.totalAttempt = totalAttempt;
	}

	public long getTotalAttempt() {
		return totalAttempt;
	}

	public void setTotalAttempt(long totalAttempt) {
		this.totalAttempt = totalAttempt;
	}

	

	public List<Integer> getUsertestAnswer() {
		return UsertestAnswer;
	}


	public void setUsertestAnswer(List<Integer> usertestAnswer) {
		UsertestAnswer = usertestAnswer;
	}


	public List<Boolean> getTestCorrectAnswer() {
		return testCorrectAnswer;
	}


	public void setTestCorrectAnswer(List<Boolean> testCorrectAnswer) {
		this.testCorrectAnswer = testCorrectAnswer;
	}


	public boolean isAttempted() {
		return isAttempted;
	}


	public void setAttempted(boolean isAttempted) {
		this.isAttempted = isAttempted;
	}


	public long getUser_Test_Id() {
		return user_Test_Id;
	}

	public void setUser_Test_Id(long user_Test_Id) {
		this.user_Test_Id = user_Test_Id;
	}

	public User getUserId() {
		return user;
	}

	public void setUserId(User userId) {
		this.user = userId;
	}

	public Test getTestId() {
		return test;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}


	
	
}
