package org.ksk_team.projects.CorporateCardControlProject.service.dao.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateService {
	
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
	
	public static void main(String[] args) {
		HibernateService service = HibernateService.getInstance();
		System.out.println(service.factory);
		service.destroy();
	}

}
