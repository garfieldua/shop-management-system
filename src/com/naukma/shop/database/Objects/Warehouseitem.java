package com.naukma.shop.database.Objects;

import com.naukma.shop.database.*;

public class WarehouseItem extends DaoObject {
	
	public int id;
	public int quantity;
	
	@Column(name = "product_id")
	public int productId;
	
}
