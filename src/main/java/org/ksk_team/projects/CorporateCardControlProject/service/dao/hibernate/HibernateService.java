package org.ksk_team.projects.CorporateCardControlProject.service.dao.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.ksk_team.projects.CorporateCardControlProject.service.dao.DatabaseConnection;
import org.ksk_team.projects.CorporateCardControlProject.service.dao.dto.Role;
import org.ksk_team.projects.CorporateCardControlProject.service.dao.dto.User;
import org.ksk_team.projects.CorporateCardControlProject.service.dao.dto.transaction.Transaction;
import org.ksk_team.projects.CorporateCardControlProject.service.dao.dto.transaction.TransactionType;

public class HibernateService implements DatabaseConnection{
	
	private static HibernateService instance;
	
	private Session session;
	
	private SessionFactory factory;
	
	private HibernateService(){
		factory = new Configuration().configure().buildSessionFactory();
	}
	
	private HibernateService(String configPath){
		factory = new Configuration().configure(configPath).buildSessionFactory();
	}
	
	public void openSession(){
		this.session = factory.openSession();
	}
	
	public void closeSession(){
		factory.getCurrentSession().close();
		session = null;
	}
	
	public static HibernateService getInstance(){
		if(instance != null)
			return instance;
		else
			return instance = new HibernateService();
	}
	
	public static HibernateService getInstance(String configPath){
		if(instance != null)
			return instance;
		else
			return instance = new HibernateService(configPath);
	}
	
	public void destroy(){
		factory.close();
	}

	@Override
	public void insert(Object obj){
		Session session = this.session;
		session.beginTransaction();
		session.save(obj);
		session.getTransaction().commit();
	}

	@Override
	public void update(Object obj) {
		Session session = this.session;
		session.beginTransaction();
		session.update(obj);
		session.getTransaction().commit();
	}

	@Override
	public void delete(Object obj) {
		Session session = this.session;
		session.beginTransaction();
		session.delete(obj);
		session.getTransaction().commit();
	}

	@Override
	public <T extends Serializable, V> V read(T id, Class<V> objClass) {
		V result = null;
		Session session = this.session;
		session.beginTransaction();
		result = session.get(objClass, id);
		session.getTransaction().commit();
		return result;
	}

	@Override
	public <T> List<T> getAllEntities(Class<T> objClass) {
		List<T> resultList = new ArrayList<>();

		Session session = this.session;
		session.beginTransaction();
		Criteria criteria = session.createCriteria(objClass);
		resultList = criteria.list();
		session.getTransaction().commit();
		
		return resultList;
	}

	public static void main(String[] args) {
		HibernateService service = HibernateService.getInstance();
		service.openSession();
		
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
		
		service.insert(type);
		service.insert(role);
		service.insert(user);
		service.insert(transaction);
		
		List<Transaction> list = service.getAllEntities(Transaction.class);
		
		System.out.println(list.size());
		service.destroy();
	}
}
