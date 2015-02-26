package com.naukma.shop.main;

import java.util.Vector;

import com.naukma.shop.database.DaoObject;

import com.naukma.shop.database.Objects.*;



public class Tests {
	
	public static void main(String[] args) throws Exception {
		
		// 10 last employees
		Vector<Employee> result = DaoObject.all(new Employee()); 
		
		System.out.println("Count results = "+result.size()); 
		System.out.println("user = "+result.get(1));  // whole array to console
		System.out.println("Password of user = "+result.get(0).password);// get 'pass' column of row*/
		
		// single employee
		Employee person = new Employee(3); // me
		System.out.println(person);
		
		// departments 
		Vector<Department> depts = DaoObject.all(new Department());
		
		Department one = new Department(2);
		System.out.println(one);
		
		
	}
}