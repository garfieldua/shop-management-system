package com.naukma.shop.database.Objects;

import com.naukma.shop.database.*;

@Table("employee")
public class Employee extends DaoObject {
	
	@Column(primary = true)
	public int id;
	
	@Column(name = "login")
	public String login;
	
	@Column(name = "pass")
	public String password;
	
	@Column(name = "first_name")
	public String firstName;
	
	@Column(name = "position", required = true)
	public String position;
	
	@Column(name = "department_id")
	public int departmentId;
	
	public Employee(int i) throws Exception {
		super(i);
	}

	public Employee() {
		super();
	}
	
}
