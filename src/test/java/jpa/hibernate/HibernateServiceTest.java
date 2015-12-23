package jpa.hibernate;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kskteam.projects.cardcontrolproject.service.dao.dto.transaction.Transaction;
import org.kskteam.projects.cardcontrolproject.service.dao.dto.transaction.TransactionType;
import org.kskteam.projects.cardcontrolproject.service.dao.dto.user.Role;
import org.kskteam.projects.cardcontrolproject.service.dao.dto.user.User;
import org.kskteam.projects.cardcontrolproject.service.dao.jpa.hibernate.HibernateService;

public class HibernateServiceTest {

	private static HibernateService service;
	
	private Transaction transaction;

	@BeforeClass
	public static void setUpClass() {
		service = HibernateService.getInstance();
	}

	@AfterClass
	public static void setDownClass() {
		if (service != null) {
			service.destroy();
		}
	}

	@Before
	public void beforeTest(){
		transaction = createTransaction();
		
		if(transaction != null)
			service.insert(transaction);
	}
	
	@After
	public void afterTest(){
		if(transaction != null)
			service.delete(transaction);
	}
	
	@Test
	public void testGetInstance() {
		HibernateService service1 = HibernateService.getInstance();
		HibernateService service2 = HibernateService.getInstance();
		boolean result = service1 == service2;
		boolean expResult = true;
		assertEquals(expResult, result);
	}

	@Test
	public void testRead() {
		Transaction result = service.read(transaction.getId(), Transaction.class);
		Transaction expResult = transaction;
		assertEquals(expResult, result);
	}

	@Test
	public void testDelete() {
		service.delete(transaction);
		Transaction result = service.read(transaction.getId(), Transaction.class);
		assertNull(result);
		transaction = null;
	}

	@Test
	public void testUpdate() {
		transaction.setDescription("Another test description");
		service.update(transaction);
		Transaction updatedTransaction = service.read(transaction.getId(), Transaction.class);
		Transaction expResult = transaction;
		assertEquals(expResult, updatedTransaction);
	}

	private Transaction createTransaction(){
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
		return transaction;
	}
}
