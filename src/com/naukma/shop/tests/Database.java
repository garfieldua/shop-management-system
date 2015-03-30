package com.naukma.shop.tests;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.naukma.shop.database.Dao;
import com.naukma.shop.database.DaoObjectException;
import com.naukma.shop.database.DaoResult;
import com.naukma.shop.database.Objects.Product;
import com.naukma.shop.tests.mocks.MySQLProviderMock;


public class Database {

		private Dao mockDao = new Dao(new MySQLProviderMock()); 
		
		@Test
		public void testSelectQuery() {		
			DaoResult mData = mockDao.executeRawQuery("Select 'test' as text");
			DaoResult data = Dao.getInstance().executeRawQuery("Select 'test' as text");
			
			assertEquals(mData.data(), data.data());
		}
		
	}

