package com.naukma.shop.main;

import java.util.HashMap;

import com.naukma.shop.database.Dao;
import com.naukma.shop.database.DaoResult;
import com.naukma.shop.database.MySQLProvider;

import com.naukma.shop.database.Objects.*;



public class Tests {
	
	public static void main(String[] args) throws Exception {
		
		
		// straight forward  
		
		Dao db = new Dao(new MySQLProvider());
		
		// DaoResult.data() always return array. 
		// even if there is one element like in this particulary situation 
		DaoResult result = db.executeRawQuery("SELECT * FROM employee"); 

		HashMap<String,String> tableRow = result.data().get(0); // get first element of result array
		
		System.out.println("user = "+tableRow.toString());  // whole array to console
		System.out.println("Password of user = "+tableRow.get("pass"));// get 'pass' column of row*/
		
		
		// Nano orm example
		
		Employee p = new Employee(6);
		
		System.out.println(p);
		System.out.println("Password of user = "+p.password);// 
		
		/*
		Employee newEmployee = new Employee();
		
		newEmployee.firstName = "Test insert";
		newEmployee.password = Dao.md5Custom("password");
		newEmployee.login = "test";
		
		// this will insert a new record to database
		newEmployee.save();
		//while this removes
		newEmployee.remove();
		*/

	}
}