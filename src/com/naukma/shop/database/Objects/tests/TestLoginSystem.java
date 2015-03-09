package com.naukma.shop.database.Objects.tests;

import static org.junit.Assert.*;

import org.junit.*;

import com.naukma.shop.database.Dao;
import com.naukma.shop.database.DaoObjectException;
import com.naukma.shop.database.MySQLProvider;
import com.naukma.shop.database.Objects.Employee;
import com.naukma.shop.database.Objects.Product;
import com.naukma.shop.database.Objects.SoldItem;
import com.naukma.shop.database.Objects.SuppliedItem;

public class TestLoginSystem {
	
	Employee em;
	
	String login = "login";
	String password = "password";
	
	@BeforeClass
	public static void disableCaching() {
		Dao.enableCache(false);
	}
	
	@Before
    public void setUp() {
		// generate encrypted password
        String passHash = Dao.md5Custom(password);
        
		em = new Employee();
		em.departmentId = 1;
		em.firstName = "test";
		em.position = "seller";
		
		em.login = login;
		em.password = passHash;
		
		try {
			em.save();
		} catch (DaoObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
	
	@After
	public void tearDown() {
        try {
        	em.remove();
		} catch (DaoObjectException e) {
			e.printStackTrace();
		}
    }
	
	@Test
	public void testLogin() {
		
		Dao db = new Dao(new MySQLProvider());
        
		// check if user logins correctly
		boolean result = db.checkLogin(login,password);
		assertEquals(result, true);
		
		// check if user found correctly
		try {
			Employee testEmployee = db.getEmployeeByName(login);
			
			assertEquals(testEmployee.id, em.id);
			assertEquals(testEmployee.login, em.login);
			assertEquals(testEmployee.password, em.password);
			
		} catch (DaoObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
