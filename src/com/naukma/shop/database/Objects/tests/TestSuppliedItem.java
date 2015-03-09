package com.naukma.shop.database.Objects.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.naukma.shop.database.Dao;
import com.naukma.shop.database.DaoObjectException;
import com.naukma.shop.database.Objects.Product;
import com.naukma.shop.database.Objects.SuppliedItem;

public class TestSuppliedItem {
	Product product;
	SuppliedItem suppliedItem;
	
	@BeforeClass
	public static void disableCaching() {
		Dao.enableCache(false);
	}
	
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
        product.quantity = 776;
        product.origin = "Moon";
        product.price = 123.45f;
        product.departmentId = 1;
        
        try {
			product.save();
		} catch (DaoObjectException e) {
			e.printStackTrace();
		}
        
        suppliedItem = new SuppliedItem(product.id, 5, 100);

		try {
			suppliedItem.save();
		} catch (DaoObjectException e) {
			e.printStackTrace();
		}
    }
	
	@After
	public void tearDown() {
        try {
        	suppliedItem.remove();
			product.remove();
		} catch (DaoObjectException e) {
			e.printStackTrace();
		}
    }
	
	@Test
	public void testSuppliedItem() {
		SuppliedItem suppliedDB = null;
		
		try {
			suppliedDB = new SuppliedItem(suppliedItem.id);
		} catch (DaoObjectException e) {
			e.printStackTrace();
		}
		
		assertEquals(suppliedDB.id, suppliedItem.id);
		assertEquals(suppliedDB.productId, suppliedItem.productId);
		assertEquals(suppliedDB.quantity, suppliedItem.quantity);
	}
}
