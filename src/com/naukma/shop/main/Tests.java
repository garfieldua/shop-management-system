package com.naukma.shop.main;

import java.util.HashMap;

import com.naukma.shop.database.Dao;
import com.naukma.shop.database.DaoResult;
import com.naukma.shop.database.MySQLProvider;

public class Tests {
	
	public static void main(String[] args) {
		
		Dao db = new Dao(new MySQLProvider());
		
		// DaoResult.data() always return array. 
		// even if there is one element like in this particulary situation 
		DaoResult result = db.executeRawQuery("SELECT * FROM employee"); 

		HashMap<String,String> tableRow = result.data().get(0); // get first element of result array
		
		System.out.println("user = "+tableRow.toString());  // whole array to console
		System.out.println("Password of user = "+tableRow.get("pass"));// get 'pass' column of row
		
		
	}
}
