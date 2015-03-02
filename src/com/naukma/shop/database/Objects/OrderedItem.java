package com.naukma.shop.database.Objects;

import com.naukma.shop.database.Column;
import com.naukma.shop.database.DaoObject;
import com.naukma.shop.database.DaoObjectException;
import com.naukma.shop.database.Table;

@Table("ordered_item")
public class OrderedItem extends DaoObject {
	@Column(primary = true)
	public int id;
	
	public int date;
	
	public int quantity;
	
	@Column(name="product_id",required = true)
	public int productId;
	
	public OrderedItem(int productId, int quantity) {
		this.productId = productId;
		this.quantity = quantity;
	}
	
	@Override
	public void save() throws DaoObjectException {
	 	
	 	this.date = (int)(System.currentTimeMillis() / 1000L); // current timestamp
		super.save();
	}
}
