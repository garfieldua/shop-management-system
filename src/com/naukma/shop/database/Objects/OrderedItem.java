package com.naukma.shop.database.Objects;

import java.util.Date;

import com.naukma.shop.database.Column;
import com.naukma.shop.database.DaoObject;
import com.naukma.shop.database.DaoObjectException;
import com.naukma.shop.database.Table;

@Table("ordered_item")
public class OrderedItem extends DaoObject {
	@Column(primary = true)
	public int id;
	
	@Column(timestamp = true)
	public Date date;
	
	public int quantity;
	
	@Column(name="product_id",required = true)
	public int productId;
	
	public OrderedItem(int productId, int quantity) {
		this.productId = productId;
		this.quantity = quantity;
	}
}
