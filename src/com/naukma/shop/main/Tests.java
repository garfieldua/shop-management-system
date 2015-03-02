package com.naukma.shop.main;

import java.util.Vector;

import com.naukma.shop.database.Dao;
import com.naukma.shop.database.DaoObject;
import com.naukma.shop.database.WhereClause;

import com.naukma.shop.database.Objects.*;



public class Tests {
	
	public static void main(String[] args) throws Exception {
		
		// 10 last employees
		Vector<Employee> result = Dao.getInstance().Where(new WhereClause<Employee>(){
			public boolean compare(Employee row) {
				return row.firstName.equals("Sergiy");
			}
		}).find(new Employee(),10);		
		
		System.out.println("Count results = "+result.size()); 
		System.out.println("user = "+result.get(0));  // whole array to console
		System.out.println("Password of user = "+result.get(0).password);// get 'pass' column of row
		
		// single employee
		Employee person = new Employee(3); // me
		System.out.println(person);
		
		// departments 
		Vector<Department> depts = Dao.getInstance().find(new Department());
		
		Department one = new Department(2);
		System.out.println(one);
		
		
		Vector<Product> lowQuantityProducts = Product.getWithLittleQuantity();
		
		System.out.println("\nProducts with litle amount(<"+Product.MIN_AT_WARHOUSE+") at warhouse("+lowQuantityProducts.size()+"):");
		for(Product p: lowQuantityProducts) {
			System.out.println("\t"+p+" ["+p.quantity+"]");
		}
		
	}
}