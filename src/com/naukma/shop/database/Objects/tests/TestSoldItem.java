package com.naukma.shop.database.Objects.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.naukma.shop.database.Dao;
import com.naukma.shop.database.DaoObjectException;
import com.naukma.shop.database.Objects.Product;
import com.naukma.shop.database.Objects.SoldItem;
import com.naukma.shop.database.Objects.SuppliedItem;

public class TestSoldItem {
	Product product;
	SoldItem soldItem;
	
	final int startQuantity = 1000;
	final int soldQuanity = 900;
	
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
        product.quantity = startQuantity;
        product.origin = "Moon";
        product.price = 123.45f;
        product.departmentId = 1;
        
        try {
			product.save();
		} catch (DaoObjectException e) {
			e.printStackTrace();
		}
        
        soldItem = new SoldItem();
        soldItem.productId = product.id;
        soldItem.quantity = soldQuanity;
        soldItem.supplierId = 1;
        
		try {
			soldItem.save();
		} catch (DaoObjectException e) {
			e.printStackTrace();
		}
		
    }
	
	@After
	public void tearDown() {
        try {
        	soldItem.remove();
			product.remove();
		} catch (DaoObjectException e) {
			e.printStackTrace();
		}
    }
	
	@Test
	public void testSoldItem() {
		// test sold Item
		SoldItem soldDB = null;
		try {
			soldDB = new SoldItem(soldItem.id);
		} catch (DaoObjectException e) {
			e.printStackTrace();
		}

		assertEquals(soldDB.id, soldItem.id);
		assertEquals(soldDB.productId, soldItem.productId);
		assertEquals(soldDB.quantity, soldItem.quantity);
		
		// test product quanitity
		Product productDB = null;
		try {
			productDB = new Product(product.id);
		} catch (DaoObjectException e) {
			e.printStackTrace();
		}

		assertEquals(productDB.id, product.id);
		assertEquals(productDB.quantity, startQuantity - soldQuanity );
	}
}
