package org.kskteam.projects.cardcontrolproject.service.dao.jpa.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.kskteam.projects.cardcontrolproject.service.dao.dto.transaction.Transaction;
import org.kskteam.projects.cardcontrolproject.service.dao.dto.user.User;

public class TransactionService {
	private HibernateService service = HibernateService.getInstance();

	private static TransactionService instance;

	/**
	 * Constructor of Singleton instance
	 * 
	 * @param dummyStr
	 *            - to prevent reflection initialization added non-valuable
	 *            String argument
	 * @throws HibernateException
	 *             - if config file is wrong
	 */
	private TransactionService(String dummyStr) {
	}

	public static TransactionService getInstance() {
		if (instance != null)
			return instance;
		else
			return instance = new TransactionService("");
	}
	
	public List<Transaction> getAllTransactions(String userId){
		Session session = service.getSession();
		session.beginTransaction();
		
		User user = session.get(User.class, userId);
		if(user == null){
			return new ArrayList<>();
		}
		Hibernate.initialize(user.getTransactions());
		
		session.getTransaction().commit();
		session.close();
		
		return user.getTransactions();
	}
	
	public String addTransactionToUser(String userId, Transaction transaction){
		Session session = service.getSession();
		session.beginTransaction();
		
		User user = session.get(User.class, userId);
		if(user == null){
			return null;
		}
		
		transaction.setCreatedBy(user);
		user.getTransactions().add(transaction);
		
		session.getTransaction().commit();
		session.close();
		
		return transaction.getId();
	}
}
