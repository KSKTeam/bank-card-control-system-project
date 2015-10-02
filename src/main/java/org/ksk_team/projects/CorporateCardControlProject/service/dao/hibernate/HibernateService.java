package org.ksk_team.projects.CorporateCardControlProject.service.dao.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.ksk_team.projects.CorporateCardControlProject.service.dao.DatabaseConnection;
import org.ksk_team.projects.CorporateCardControlProject.service.dao.dto.Transaction;

public class HibernateService implements DatabaseConnection{
	
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
		Transaction transaction = new Transaction();
		service.insert(transaction);
		
		List<Transaction> list = service.getAllEntities(Transaction.class);
		
		System.out.println(list.size());
		service.destroy();
	}
}