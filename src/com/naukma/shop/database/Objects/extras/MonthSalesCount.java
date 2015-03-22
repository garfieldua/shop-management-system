package com.naukma.shop.database.Objects.extras;

import java.util.Vector;

import com.naukma.shop.database.Column;
import com.naukma.shop.database.Dao;
import com.naukma.shop.database.DaoObject;
import com.naukma.shop.database.DaoObjectException;

public class MonthSalesCount extends DaoObject {

	@Column(name = "MONTH")
	public String month;
	
	// just because PK should be integer, really doesn't matter here because
	// we just parse result table, not a relational table
	@Column(primary = true, name = "id")
	public int id;
	
	public MonthSalesCount(int i) throws Exception {
		super(i);
	}

	public MonthSalesCount() {
		super();
	}
	
	public static Vector<MonthSalesCount> getMonthSalesCountByPeriodAll(long startDate, long endDate) {
		Vector<MonthSalesCount> result = new Vector<MonthSalesCount>();
		MonthSalesCount item = new MonthSalesCount();
		
		try {
			result = Dao.getInstance().executeRawQuery("SELECT MONTHNAME( FROM_UNIXTIME( DATE /1000 ) ) AS MONTH , SUM( total_price ) AS id FROM sold_item WHERE date BETWEEN "+startDate+ " AND " + endDate + " GROUP BY MONTH").parseObjects(item);
		} catch (DaoObjectException e) {
			e.printStackTrace();
		}
		
		for (MonthSalesCount c: result) {
			System.out.println(c);
		}
		
		return result;
	}
	
	public static Vector<MonthSalesCount> getMonthSalesCountByPeriodDepartment(long startDate, long endDate, int departmentId) {
		Vector<MonthSalesCount> result = new Vector<MonthSalesCount>();
		MonthSalesCount item = new MonthSalesCount();
		
		try {
			result = Dao.getInstance().executeRawQuery("SELECT MONTHNAME( FROM_UNIXTIME( DATE /1000 ) ) AS MONTH , SUM( total_price ) AS id FROM sold_item INNER JOIN product ON sold_item.product_id=product.id WHERE date BETWEEN "+startDate+ " AND " + endDate + " AND product.department_id="+departmentId + " GROUP BY MONTH").parseObjects(item);
		} catch (DaoObjectException e) {
			e.printStackTrace();
		}
		
		for (MonthSalesCount c: result) {
			System.out.println(c);
		}
		
		return result;
	}
}
