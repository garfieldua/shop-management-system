package com.naukma.shop.database.Objects;

import java.util.Date;

import com.naukma.shop.database.*;

@Table("supplied_item")
public class SuppliedItem extends DaoObject {
	
	@Column(primary = true)
	public int id;
	
	@Column(timestamp = true)
	public Date date;

	@Column(required = true) 
	public int quantity;
	
	@Column(name="supplier_id",required = true) // "required = true" - will fire DaoObjectException if this field would be blank
	public int supplierId;
	
	@Column(name="product_id",required = true) 
	public int productId;

	public SuppliedItem(int productId, int supplierId, int quantity) {
		this.productId = productId;
		this.supplierId = supplierId;
		this.quantity = quantity;
	}
	
	public SuppliedItem(int id) throws DaoObjectException {
		super(id);
	}
	
	@Override
	public void save() throws DaoObjectException {
		super.save();
		
		Product relatedProduct = new Product(this.productId);
		relatedProduct.quantity += this.quantity;
		relatedProduct.save();
		
	}
	
	
	
}
