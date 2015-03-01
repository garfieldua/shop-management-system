package com.naukma.shop.database.Objects;

import com.naukma.shop.database.Column;
import com.naukma.shop.database.DaoObject;
import com.naukma.shop.database.Table;

@Table("supplier")
public class Supplier extends DaoObject {

	@Column(primary = true)
	public int id;
	
	@Column(name = "name")
	public String name;
	
	@Column(name = "phone")
	public String phone;
	
	@Override
	public String toString() {
		return name;
	}
}
