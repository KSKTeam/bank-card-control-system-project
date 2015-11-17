package org.kskteam.projects.cardcontrolproject.service.dao.jpa.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateSession {
	private static SessionFactory factory = HibernateFactory.getInstance(); 
	
	public static Session getSession(){
		return factory.openSession();
	}
}
