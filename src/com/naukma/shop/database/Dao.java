package com.naukma.shop.database;

import java.sql.ResultSet;

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
	
	/**
	 * Must return something like TableObject
	 * @return
	 */
	public ResultSet getAllItems() {
		return this.provider.execute("SELECT * FROM items");
	} 
	
	public ResultSet getItem(int id) {
		return this.provider.execute("SELECT * FROM items WHERE id="+id);
	} 
	
	/**
	 * another methods
	 */
	
}
