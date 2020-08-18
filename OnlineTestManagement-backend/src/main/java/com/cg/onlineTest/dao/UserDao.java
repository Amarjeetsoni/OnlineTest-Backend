package com.cg.onlineTest.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.cg.onlineTest.entities.User;


@Repository
@Transactional
public class UserDao implements IUserDao{

	@PersistenceContext
	protected EntityManager entityManager;
	
	@Override
	public User getUserByName(String username) {
		String str = "select user from User user where user.userName = :puser";
  		TypedQuery<User> query = entityManager.createQuery(str,User.class);
  		query.setParameter("puser",username);
  		User u = query.getResultList().get(0);
		System.out.println(u.getEmailId() + " " +  u.getUserName());
		return u;
	}
	
	@Override
	public User findById(Integer id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public void createUser(User user) {
		user.setAdmin(false);
		entityManager.persist(user);
	}
	
/**
 * This method signUp is used to register Customer as an User
 */
	@Override
	public void signUp(User customer) {
		customer.setAdmin(false);
		entityManager.persist(customer);

		
	}
	/**
	 * This method is used to fetch all customers registered as user from database
	 */
	@Override
	public List<User> getAllCustomers() {
		TypedQuery<User> query=entityManager.createQuery("select user from User user where user.isAdmin=0", User.class);
		
		  return query.getResultList();
		
	}
	
    public int SignUpEmailValidate(String email) 
     {
      	List<User> passList = new ArrayList<User>();
      	String str = "select login from User login where login.emailId = :emailid";
  		TypedQuery<User> query = entityManager.createQuery(str,User.class);
  		query.setParameter("emailid",email);
  		passList = query.getResultList();
  		if(passList.size()>0) 
  			return 1;
  		else 
  			return 0;
      	}

     public int userExistsOrNot(String userName) {
     	List<User> passList = new ArrayList<User>();
     	String str = "select login from User login where login.userName = :name";
 		TypedQuery<User> query = entityManager.createQuery(str,User.class);
 		query.setParameter("name",userName);
 		passList = query.getResultList();
 		if(passList.size()>0) {
 			return 1;
 		}
 		else 
 			return 0;
 		}
     @Override
	public void createAdmin(User admin) {
		admin.setAdmin(true);;
		entityManager.persist(admin);
}
     
/**
 * This method getAllAdmins() is used to fetch all admins from database
 */
	@Override
	public List<User> getallAdmins() {
		TypedQuery<User> query=entityManager.createQuery("select admin from User admin where admin.isAdmin=1", User.class);
		return query.getResultList();
	}

@Override
public Long getUsrId(String username) {
	String str = "select user from User user where user.userName = :puser";
		TypedQuery<User> query = entityManager.createQuery(str,User.class);
		query.setParameter("puser",username);
		User u = query.getResultList().get(0);
	    return u.getUserId();
}

	}
