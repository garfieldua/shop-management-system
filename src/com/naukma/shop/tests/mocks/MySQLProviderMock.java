package com.naukma.shop.tests.mocks;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.naukma.shop.database.AbstractDataProvider;
import com.naukma.shop.database.DaoResult;

public class MySQLProviderMock extends AbstractDataProvider {

	@Override
	public DaoResult execute(String query) {
		
		query = query.trim().toLowerCase();
		
		ArrayList<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();
		
		switch (query) {
			case "select 'test' as text": 
				HashMap<String,String> line =  new HashMap<String,String>();
				line.put("text","test");
				result.add(line);
				break;
				default:
					
					break;
		
		}
		
		
		
		return new DaoResult(result);
		
	}

}
