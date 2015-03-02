package com.naukma.shop.database.Objects;

import com.naukma.shop.database.*;

@Table("sold_item")
public class SoldItem extends DaoObject {
	public int id;
	
	@Column(name = "supplier_id")
	public int supplierId;
	
	@Column(name = "product_id")
	public int productId;
	
	public int quantity;
	public int date;
}
