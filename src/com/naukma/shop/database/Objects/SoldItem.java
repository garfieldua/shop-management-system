package com.naukma.shop.database.Objects;

import java.util.Date;

import com.naukma.shop.database.*;

@Table("sold_item")
public class SoldItem extends DaoObject {
	
	public int id;
	
	@Column(name = "supplier_id",required = true)
	public int supplierId;
	
	@Column(name = "product_id",required = true)
	public int productId;
	
	@Column(required = true)
	public int quantity;
	
	@Column(timestamp = true)
	public Date date;
	
	public void save() throws DaoObjectException {
		
		Product relatedProduct = null;
		
		if (this.productId != 0 ) {
			relatedProduct = new Product(this.productId);
			if (relatedProduct.quantity < this.quantity) {
				throw new DaoObjectException("Can't sell more items than in warehouse now");
			}
		}
		
		super.save();
		
		relatedProduct.quantity = relatedProduct.quantity - this.quantity;
		relatedProduct.save();
	}
	
	public SoldItem() {
		super();
	}
	
	public SoldItem(int id) throws DaoObjectException {
		super(id);
	}

	
}
