package com.naukma.shop.database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;


public class DaoResult {
	
	private int count = 0;
	private ArrayList<HashMap<String,String>> data = new ArrayList<HashMap<String,String>>();
	
	public DaoResult(ResultSet raw){
		try {
			
			int cnt = 0;
			

			ResultSetMetaData rsMetaData = raw.getMetaData();				
			int columnCount = rsMetaData.getColumnCount();
			
			while (raw.next()) {
				
				HashMap<String,String> row = new HashMap<String,String>();
			
				for (int i=0; i < columnCount; i++) {
					String columnName = rsMetaData.getColumnName(i+1);
					row.put(columnName, raw.getString(columnName));
				} 
				
				data.add(row); 
				cnt++;
			}
			
			this.count = cnt;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int length(){ 
		return this.count;
	}
	public ArrayList<HashMap<String,String>> data(){
		return this.data;
	}
	
	
	
	
	
}
