package org.ksk_team.projects.CorporateCardControlProject.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestClass {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
	}

}
