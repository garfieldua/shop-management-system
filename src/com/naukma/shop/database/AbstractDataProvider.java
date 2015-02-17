package com.naukma.shop.database;

import java.sql.ResultSet;

abstract public class AbstractDataProvider {
	
	abstract public ResultSet execute(String query);
	
}
