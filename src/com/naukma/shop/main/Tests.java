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
		
		// suppliers 
		
		Vector<Supplier> suppliers = Dao.getInstance().find(new Supplier(),100);
		
		System.out.println("\n10 Suppliers list:");
		for(Supplier s: suppliers) {
			System.out.println("\t "+s.id+" "+s);
		}
		
		Vector<Product> lowQuantityProducts = Product.getWithLittleQuantity();
		
		System.out.println("\nProducts with litle amount at warhouse("+lowQuantityProducts.size()+"):");
		for(Product p: lowQuantityProducts) {
			System.out.println("\t "+p.id+" "+p+" ["+p.quantity+"]");
		}
		
		
		SoldItem sold = new SoldItem();
		sold.productId = 21;
		sold.supplierId = 12;
		sold.quantity = 100;
		sold.save();
		

		
		
		
		
	}
}