package com.naukma.shop.database.Objects;

import java.util.Date;
import java.util.Vector;

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
	
	@Column(name = "total_price")
	public float totalPrice;
	
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

	public static float getTotalIncomeByPeriodDepartment(long startDate, long endDate, int department) {
		Vector<SoldItem> result = new Vector<SoldItem>();
		SoldItem item = new SoldItem();
		
		try {
			result = Dao.getInstance().executeRawQuery("SELECT * FROM sold_item INNER JOIN product ON sold_item.product_id=product.id WHERE date BETWEEN "+startDate+ " AND " + endDate + " AND product.department_id="+department).parseObjects(item);
		} catch (DaoObjectException e) {
			e.printStackTrace();
		}
		
		float totalSum = 0;
		
		for (SoldItem s: result) {
			totalSum += s.totalPrice;
		}

		return totalSum;
	}
	
	public static float getTotalIncomeByPeriodAll(long startDate, long endDate) {
		Vector<SoldItem> result = new Vector<SoldItem>();
		SoldItem item = new SoldItem();
		
		try {
			result = Dao.getInstance().executeRawQuery("SELECT * FROM sold_item WHERE date BETWEEN "+startDate+ " AND " + endDate).parseObjects(item);
		} catch (DaoObjectException e) {
			e.printStackTrace();
		}
		
		float totalSum = 0;
		
		for (SoldItem s: result) {
			totalSum += s.totalPrice;
		}

		return totalSum;
	}
}
