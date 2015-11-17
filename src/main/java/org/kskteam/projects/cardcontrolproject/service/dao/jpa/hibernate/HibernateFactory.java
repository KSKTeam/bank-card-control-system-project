package org.kskteam.projects.cardcontrolproject.service.dao.jpa.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HibernateFactory {

	private static SessionFactory factory;

	private HibernateFactory(SessionFactory factory) {
		this.factory = factory;
	}

	public static SessionFactory getInstance() {
		if (factory == null) {
			factory = new Configuration().configure().buildSessionFactory();
		}
		return factory;
	}

	public static void destroy() {
		if (factory != null)
			factory.close();
	}
}
