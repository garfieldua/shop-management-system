package com.naukma.shop.database.Objects;

import com.naukma.shop.database.Column;
import com.naukma.shop.database.DaoObject;
import com.naukma.shop.database.DaoObjectException;
import com.naukma.shop.database.Table;

@Table("product")
public class Product extends DaoObject {
	
	@Column(primary = true)
	public int id;
	
	@Column(name = "title")
	public String title;
	
	@Column(name = "description")
	public String description;
	
	@Column(name = "origin")
	public String origin;
	
	@Column(name = "department_id")
	public int departmentId;
	
	// TODO: bad decision to save price in float
	@Column(name = "price")
	public float price;
	
	@Column(name = "quantity")
	public int quantity;
	
	@Column(name = "min_amount")
	public int minAmount;
	
	@Override
	public String toString() {
		return title;
	}

	public Product() {
		super();
	}
	
	public Product(int id) throws DaoObjectException {
		super(id);
	}
}
