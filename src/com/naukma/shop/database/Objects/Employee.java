package com.naukma.shop.database.Objects;

import com.naukma.shop.database.*;


public class Employee extends DaoObject {
	
	public int id;
	public String login;
	
	@Column(name = "pass")
	public String password;
	
	@Column(name = "first_name")
	public String firstName;
	
	
	public Employee(int i) throws Exception {
		super(i);
	}

	public Employee() {
		super();
	}
	
}
