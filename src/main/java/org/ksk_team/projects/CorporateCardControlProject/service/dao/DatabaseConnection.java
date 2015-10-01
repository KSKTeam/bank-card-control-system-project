package org.ksk_team.projects.CorporateCardControlProject.service.dao;

import java.io.Serializable;
import java.util.List;

public interface DatabaseConnection {
	public void insert(Object obj);
	
	public <T extends Serializable, V> V read(T id, Class<V> objClass);
	
	public void update(Object obj);
	
	public void delete(Object obj);
	
	public <T>List<T> getAllEntities(Class<T> objClass);
}
