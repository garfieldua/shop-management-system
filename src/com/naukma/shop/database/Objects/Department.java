package com.naukma.shop.database.Objects;

import com.naukma.shop.database.*;

public class Department extends DaoObject {
	
	@Column(primary = true)
	public int id;
	
	@Column(name = "name")
	public String name;
	
	public Department(int i) throws Exception {
		super(i);
	}

	public Department() {
		super();
	}

	@Override
	public String toString() {
		return name;
	}
}
