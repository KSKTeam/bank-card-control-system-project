package org.kskteam.projects.cardcontrolproject.service.dao.jpa.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.kskteam.projects.cardcontrolproject.service.dao.dto.user.User;

public class UserService {
	private HibernateService service = HibernateService.getInstance();
	
	private static UserService instance;

	/**
	 * Constructor of Singleton instance
	 * 
	 * @param dummyStr
	 *            - to prevent reflection initialization added non-valuable
	 *            String argument
	 * @throws HibernateException
	 *             - if config file is wrong
	 */
	private UserService(String dummyStr) {
	}

	public static UserService getInstance() {
		if (instance != null)
			return instance;
		else
			return instance = new UserService("");
	}
	
	public User getUserByLoginAndPassword(String login, String password){
		Session session = service.getSession();
		session.beginTransaction();
		
		Criteria userCriteria = session.createCriteria(User.class)
				.add(Restrictions.eq("login", login))
				.add(Restrictions.eq("password", password));
		
		List<User> users = userCriteria.list();
		
		session.getTransaction().commit();
		session.close();
		
		if(users != null && users.size() > 0){
			return users.get(0);
		}else{
			return null;
		}
	}
}
