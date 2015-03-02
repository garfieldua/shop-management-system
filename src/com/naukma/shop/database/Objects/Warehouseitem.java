package com.naukma.shop.database.Objects;

import com.naukma.shop.database.*;

public class Warehouseitem extends DaoObject {
	
	public int id;
	public int quantity;
	
	@Column(name = "product_id")
	public int productId;
	
	public Warehouseitem() {
		super();
	}
	
	public Warehouseitem(int id) throws DaoObjectException {
		super(id);
	}
	
}
