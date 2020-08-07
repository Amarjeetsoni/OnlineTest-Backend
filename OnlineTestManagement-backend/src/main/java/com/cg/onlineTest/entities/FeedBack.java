package com.cg.onlineTest.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Embeddable
@Entity
@Table(name = "FEEDBACK_TAB")
public class FeedBack implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Feedback_Id")
	private long feedbackId;
	
	@ElementCollection  
	@Column(name = "FeedbackMessage")
	@CollectionTable(name="listOfFeedback")
	private List<String> feedBackMessage;
	
	@ManyToOne(fetch =  FetchType.EAGER)
	@JoinColumn(name="Test_Id")
	private Test test;

	public long getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(long feedbackId) {
		this.feedbackId = feedbackId;
	}

	public List<String> getFeedBackMessage() {
		return feedBackMessage;
	}

	public void setFeedBackMessage(List<String> feedBackMessage) {
		this.feedBackMessage = feedBackMessage;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}
	
	
}
