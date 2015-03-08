package com.naukma.shop.database.Objects;

import java.util.Vector;

import com.naukma.shop.database.Column;
import com.naukma.shop.database.Dao;
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
	
	public Product() {
		super();
	}

	public Product(int id) throws DaoObjectException {
		super(id);
	}
	
	@Override
	public String toString() {
		return title;
	}
	
	public static Vector<Product> getWithLittleQuantity() {
		Vector<Product> result = new Vector<Product>();
		Product instance = new Product();
		
		try {
			int oldlt = Dao.cacheLifetime();
			Dao.setCacheLifetime(2);
			result = Dao.getInstance().executeRawQuery("SELECT * FROM "+instance.TableName()+" WHERE quantity < min_amount").parseObjects(instance);
			Dao.setCacheLifetime(oldlt);
			
		} catch (Exception e) {}
		
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}
