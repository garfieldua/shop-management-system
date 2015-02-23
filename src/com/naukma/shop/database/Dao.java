package com.naukma.shop.database;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This is only a template
 * @author Serho
 *
 */
public class Dao {
	
	private AbstractDataProvider provider;
	
	public Dao(AbstractDataProvider dataprovider) {
		this.provider = dataprovider;
	}
	
	
	// #21 Створити SQL запити для перегляду інформації про товар 
	public ResultSet getProductInfo(int id) {
		
		// handle data as you want 
		// the best practice to return something like ProductObject
		return this.provider.execute("SELECT * FROM product WHERE id="+id);
	} 
	
	// id shouldn't be provided - id is a primary key and auto increment. 
	// if we need specified productId then we need to add another columnt to table 
	// can return id of the created element
	public void addNewProduct(String title,String description,String origin,int department,int quantity,int supplierId) {
		ResultSet res = this.provider.execute("INSERT INTO product set title='"+title+"', description = '"+description+"', origin = '"+origin+"', department_id = '"+department+"'");
		
		long timestamp = System.currentTimeMillis() / 1000L;
		//this.provider.execute("INSERT INTO supplied_item set supplier_id='"+supplierId+"', quantity='"+quantity+"', product_id = '"+res.id+"',date='"+timestamp+"'");
	} 
	
	// #14 Створити SQL запити для фіксації надходження товарів
	public void fixAddProduct(int id,int supplierId,int quantity) {
		ResultSet res = this.provider.execute("UPDATE product SET quantity = quantity + "+quantity+" WHERE id = "+id);
		
		long timestamp = System.currentTimeMillis() / 1000L;
		this.provider.execute("INSERT INTO supplied_item set supplier_id='"+supplierId+"', quantity='"+quantity+"', product_id = '"+id+"',date='"+timestamp+"'");
		
	} 
	
	// #17 Створити SQL запити для фіксації продажів  database
	public void fixBuyProduct(int id,int employeeId,int quantity) {
		ResultSet res = this.provider.execute("UPDATE product SET quantity = quantity - "+quantity+" WHERE id = "+id);
		
		long timestamp = System.currentTimeMillis() / 1000L;
		this.provider.execute("INSERT INTO sold_item set employee_id='"+employeeId+"', quantity='"+quantity+"', product_id = '"+id+"',date='"+timestamp+"'");
	} 
	
	
	//#15 Створити SQL запити для перегляду списку постачальників
	public ResultSet getSuppliers() {
		return this.provider.execute("SELECT * FROM supplier ");
	} 
	
	// #24 Створити SQL запити для оформлення замовлення на товари #24
	public void requestForProduct(int id,int employeeId,int quantity) {

		long timestamp = System.currentTimeMillis() / 1000L;
		
		// status = 0 request is not accepted yet. 1 - request successfuly executed
		this.provider.execute("INSERT INTO warehouseitem set employee_id='"+employeeId+"', status=0, quantity='"+quantity+"', product_id = '"+id+"',date='"+timestamp+"'");
	} 
	
	// get requests for products
	public ResultSet getProductRequests() {		
		return this.provider.execute("SELECT * FROM warehouseitem WHERE status=0");
	} 
	// approve request
	public void approveProductRequest(int requestId) throws SQLException {	
		ResultSet res = this.provider.execute("SELECT * FROM warehouseitem WHERE id="+requestId);
		this.provider.execute("UPDATE product SET quantity = quantity - "+res.getString("quantity")+" WHERE id = "+res.getString("product_id"));
		this.provider.execute("UPDATE warehouseitem SET status=1 WHERE id="+requestId);
	}
	
	// decline request
	public void declineProductRequest(int requestId) {	
		this.provider.execute("UPDATE warehouseitem SET status=1 WHERE id="+requestId);
	}
	
	public boolean checkLogin(String login,String pass) throws SQLException {
		
		String passHash = Dao.md5Custom(pass);
		ResultSet result = this.provider.execute("SELECT * FROM emloyee WHERE login='"+login+"' and pass = '"+passHash+"'");
		
		result.last();
		if (result.getRow() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * another methods (shouldn't be there)
	 */
	public static String md5Custom(String st) {
	    MessageDigest messageDigest = null;
	    byte[] digest = new byte[0];
	 
	    try {
	        messageDigest = MessageDigest.getInstance("MD5");
	        messageDigest.reset();
	        messageDigest.update(st.getBytes());
	        digest = messageDigest.digest();
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	 
	    BigInteger bigInt = new BigInteger(1, digest);
	    String md5Hex = bigInt.toString(16);
	 
	    while( md5Hex.length() < 32 ){
	        md5Hex = "0" + md5Hex;
	    }
	 
	    return md5Hex;
	}
	
}
