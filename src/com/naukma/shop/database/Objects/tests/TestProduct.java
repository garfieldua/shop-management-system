package com.naukma.shop.database.Objects.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.naukma.shop.database.DaoObjectException;
import com.naukma.shop.database.Objects.Product;

public class TestProduct {

	Product product;
	
	/**
	 * Creating product, setting attributes for testing
	 * before each test run
	 */
	@Before
    public void setUp() {
        product = new Product();
        product.title = "Test product";
        product.description = "Test purposed product";
        product.minAmount = 777;
        product.quantity = 111;
        product.origin = "Moon";
        product.price = 123.45f;
        product.departmentId = 1;
        
        try {
			product.save();
		} catch (DaoObjectException e) {
			e.printStackTrace();
		}
    }
	
	/**
	 * Removes product from DB after each test run
	 */
	@After
	public void tearDown() {
        try {
			product.remove();
		} catch (DaoObjectException e) {
			e.printStackTrace();
		}
    }
	
	@Test
	public void testLoadProduct() {
		Product productDB = null;
		try {
			productDB = new Product(product.id);
		} catch (DaoObjectException e) {
			e.printStackTrace();
		}
		assertEquals(productDB.id, product.id);
		assertEquals(productDB.title, product.title);
		assertEquals(productDB.departmentId, product.departmentId);
		assertEquals(productDB.description, product.description);
		assertEquals(productDB.minAmount, product.minAmount);
		assertEquals(productDB.origin, product.origin);
		
		// can't check double for equality without epsilon
		assertEquals(productDB.price, product.price, 0.01);
		assertEquals(productDB.quantity, product.quantity);
	}

}
