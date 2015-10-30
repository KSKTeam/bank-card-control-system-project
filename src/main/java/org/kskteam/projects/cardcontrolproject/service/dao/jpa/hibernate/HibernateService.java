package org.kskteam.projects.cardcontrolproject.service.dao.jpa.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.kskteam.projects.cardcontrolproject.service.dao.DatabaseConnection;
import org.kskteam.projects.cardcontrolproject.service.dao.dto.transaction.Transaction;
import org.kskteam.projects.cardcontrolproject.service.dao.dto.transaction.TransactionType;
import org.kskteam.projects.cardcontrolproject.service.dao.dto.user.Role;
import org.kskteam.projects.cardcontrolproject.service.dao.dto.user.User;
import org.kskteam.projects.cardcontrolproject.service.dao.jpa.JPAConnection;

public class HibernateService extends JPAConnection{
	
	private static HibernateService instance;
	
	private SessionFactory factory;
	
	private HibernateService(){
		factory = new Configuration().configure().buildSessionFactory();
	}
	
	private HibernateService(String configPath){
		factory = new Configuration().configure(configPath).buildSessionFactory();
	}
	
	public static HibernateService getInstance(){
		if(instance != null)
			return instance;
		else
			return instance = new HibernateService();
	}
	
	/*public static HibernateService getInstance(String configPath){
		if(instance != null)
			return instance;
		else
			return instance = new HibernateService(configPath);
	}*/
	
	public void destroy(){
		factory.close();
	}

	@Override
	public void insert(Object obj) {
		Session session = factory.openSession();
		session.beginTransaction();
		session.save(obj);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void update(Object obj) {
		Session session = factory.openSession();
		session.beginTransaction();
		session.update(obj);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void delete(Object obj) {
		Session session = factory.openSession();
		session.beginTransaction();
		session.delete(obj);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public <T extends Serializable, V> V read(T id, Class<V> objClass) {
		V result = null;
		Session session = factory.openSession();
		session.beginTransaction();
		result = session.get(objClass, id);
		session.getTransaction().commit();
		session.close();
		return result;
	}

	@Override
	public <T> List<T> getAllEntities(Class<T> objClass) {
		List<T> resultList = new ArrayList<>();

		Session session = factory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(objClass);
		resultList = criteria.list();
		session.getTransaction().commit();
		session.close();
		
		return resultList;
	}

	public static void main(String[] args) {
		HibernateService service = HibernateService.getInstance();
		Role role = new Role();
		role.setName("Manager");
		
		User user = new User();
		user.setRole(role);
		
		TransactionType type = new TransactionType();
		type.setName("Food");
		
		Transaction transaction = new Transaction();
		transaction.setCardNumber(128323470L);
		transaction.setCreatedAt(new Date());
		transaction.setTotal(300.45);
		transaction.setType(type);
		transaction.setDescription("Some transaction");
		transaction.setCreatedBy(user);
		
		service.insert(transaction);
		
		List<Transaction> list = service.getAllEntities(Transaction.class);
		
		System.out.println(list.size());
		service.destroy();
	}
}
