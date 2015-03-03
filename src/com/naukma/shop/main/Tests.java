package com.naukma.shop.main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import java.util.Vector;

import com.naukma.shop.database.Dao;
import com.naukma.shop.database.DaoObject;
import com.naukma.shop.database.WhereClause;

import com.naukma.shop.database.Objects.*;
import com.sun.jmx.snmp.Timestamp;



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
		
	    GregorianCalendar d = new GregorianCalendar(2015, 2, 2);	 
		d.set(Calendar.HOUR_OF_DAY,23);
		d.set(Calendar.MINUTE,0);
		d.set(Calendar.SECOND,0);
		
		final Date barrier = d.getTime();
		Vector<SoldItem> solditemsAfter =  Dao.getInstance().Where(new WhereClause<SoldItem>(){
			public boolean compare(SoldItem row) {
				return row.date.getTime() > barrier.getTime();
			}
		}).find(new SoldItem());
		
		
		long start = System.currentTimeMillis();
		
		System.out.println("\nList of sold items after "+barrier+":");
		for(SoldItem p: solditemsAfter) {
			Product pInfo = new Product(p.productId);
			System.out.println("\t "+p.productId+" "+pInfo+" ["+p.quantity+"] "+p.date);
		}
		
		Dao.enableCache(false);
		
		long startCahce = System.currentTimeMillis();
		
		System.out.println("\nList of sold items after "+barrier+":");
		for(SoldItem p: solditemsAfter) {
			Product pInfo = new Product(p.productId);
			System.out.println("\t "+p.productId+" "+pInfo+" ["+p.quantity+"] "+p.date);
		}
		
		System.out.println("with caching done in "+ (startCahce - start)+" ms");
		System.out.println("without caching done in "+ (System.currentTimeMillis() - start)+" ms");
		
	}
}