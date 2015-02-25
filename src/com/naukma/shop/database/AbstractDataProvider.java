package com.naukma.shop.database;

import java.sql.ResultSet;

abstract public class AbstractDataProvider {
	
	abstract public DaoResult execute(String query);
	
}
