package com.naukma.shop.database.Objects;

import com.naukma.shop.database.Objects.*;
import com.naukma.shop.database.*;

@Table("supplied_item")
public class SuppliedItem extends DaoObject {
	
	public int id;
	
	public int date;
	
	@Column(required = true) 
	public int quantity;
	
	@Column(name="supplier_id",required = true) // "required = true" - will fire DaoObjectException if this field would be blank
	public int supplierId;
	
	@Column(name="product_id",required = true) 
	public int productId;

	@Override
	public void save() throws DaoObjectException {
	 	
	 	this.date = (int)(System.currentTimeMillis() / 1000L); // current timestamp
		super.save();
		
		Product relatedProduct = new Product(this.productId);
		relatedProduct.quantity += this.quantity;
		relatedProduct.save();
		
	}
	
	
	
}
