package com.cg.onlineTest.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.onlineTest.dao.TopPerformerDao;
import com.cg.onlineTest.entities.User;
import com.cg.onlineTest.entities.User_Test;
import com.cg.onlineTest.exceptions.CannotRetrieveDataException;
import com.cg.onlineTest.exceptions.NoDataFoundedException;

@Transactional
@Service("TopPerformerServiceImpl")
public class TopPerformerServiceImpl implements TopPerformerService {

	@Autowired
	TopPerformerDao topPerformersDao;

	Logger logger = LoggerFactory.getLogger(TopPerformerDao.class);

	@Override
	public List<User_Test> topPerformers() throws Exception {
		logger.info("Top performers function is accessed!");
		return topPerformersDao.topPerformers();
	}

	@Override
	public long getTotalUsers() throws CannotRetrieveDataException {
		logger.info("get Total Users function is accessed!");
		return topPerformersDao.getTotalUsers();
	}

	@Override
	public long getTotalTests() throws CannotRetrieveDataException {
		logger.info("get Total Test function is accessed!");
		return topPerformersDao.getTotalTests();
	}

	@Override
	public long getTotalQuestions() throws CannotRetrieveDataException {
		logger.info("get Total Questions function is accessed!");
		return topPerformersDao.getTotalQuestions();
	}

	@Override
	public List<User_Test> allExams() throws Exception {
		logger.info("all Exam function is accessed!");
		return topPerformersDao.allExams();
	}

	@Override
	public HashMap<String, Long> questionsCategory() throws NoDataFoundedException {
		logger.info("Question category function is accessed!");
		return topPerformersDao.questionsCategory();
	}

	@Override
	public HashMap<Long, Avg> topPerformersAvg() throws Exception {
		logger.info("topPerformersAvg function is accessed!");
		List<User_Test> user_Test = topPerformersDao.topPerformersAvg();
		HashMap<Long, Avg> data = new HashMap<Long, Avg>();

		user_Test.forEach(ob -> {
			User u = ob.getUser();
			if (data.containsKey(u)) {
				data.put(u.getUserId(), new Avg(data.get(u).getMarks() + ob.getMarksScored(),
						data.get(u).getCount() + 1, data.get(u).getUserName()));
			}

			else
				data.put(u.getUserId(), new Avg(ob.getMarksScored(), 1, u.getUserName()));
		});

		// Sorting the map
		List<Map.Entry<Long, Avg>> list = new LinkedList<Map.Entry<Long, Avg>>(data.entrySet());

		// Sort the list
		Collections.sort(list, new Comparator<Map.Entry<Long, Avg>>() {
			public int compare(Map.Entry<Long, Avg> o1, Map.Entry<Long, Avg> o2) {
				return (o1.getValue().getAvg() > o2.getValue().getAvg() ? -1 : 1);
			}
		});

		// put data from sorted list to hashmap
		HashMap<Long, Avg> sortedAvgMap = new LinkedHashMap<Long, Avg>();
		for (Map.Entry<Long, Avg> aa : list) {
			sortedAvgMap.put(aa.getKey(), aa.getValue());
		}

		return sortedAvgMap;
	}

}

class Avg {
	long marks;
	long count;
	String userName;

	public Avg(long marks, long count) {
		super();
		this.marks = marks;
		this.count = count;
	}

	public Avg(String userName) {
		super();
		this.userName = userName;
	}

	public long getMarks() {
		return marks;
	}

	public void setMarks(long marks) {
		this.marks = marks;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	float getAvg() {
		return this.marks / this.count;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Avg(long marks, long count, String userName) {
		super();
		this.marks = marks;
		this.count = count;
		this.userName = userName;
	}

}