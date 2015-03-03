package com.naukma.shop.database;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Vector;

import com.naukma.shop.database.Objects.*;

public class Dao {
	
	class CacheItem {
		
		public int lifetime; // seconds
		public long fetched; 
		public DaoResult data;
		
		public CacheItem(){
			
		}
		
		public CacheItem(DaoResult data){
			this.data = data;
			this.fetched = System.currentTimeMillis();
			this.lifetime = Dao.cacheLifetime;
		}
		
	}	
	
	private AbstractDataProvider provider;
	private static Dao instance = null;
	private WhereClause _wherecomp = new WhereAllRows();
	
	static boolean useCache = true;
	static int cacheLifetime = 1*30;
	static HashMap<String,CacheItem> cache = new HashMap<String,CacheItem>();
	static HashMap<String,String> cachedTables = new HashMap<String,String>();
	
	/*
	public static void printCacheInfo() {
		System.out.println("Dao cache dump ["+Dao.cache.size()+"]:");
		
		for (Entry<String, CacheItem> e: Dao.cache.entrySet()) {
			System.out.println(e.getKey()+" ["+e.getValue().data.length()+" elements] fetched at "+new Date(e.getValue().fetched)+" for "+e.getValue().lifetime+"s.");
		}
		
	}
	*/
	
	public Dao(AbstractDataProvider dataprovider) {
		this.provider = dataprovider;
	}
	
	public Dao Where(WhereClause whereClause) {
		this._wherecomp = whereClause;
		return this;
	}
	
	public <T extends DaoObject> Vector<T> find(T instance) throws DaoObjectException {
		return this.find(instance,0);
	}
	public <T extends DaoObject> Vector<T> find(T instance,int limit) throws DaoObjectException {
		Vector<T> result = new Vector<T>();
		try {
			StringBuilder sql = new StringBuilder("SELECT * FROM "+instance.TableName());
			if (limit > 0) {
				sql.append(" LIMIT "+limit);
			} else if (Dao.useCache) {
				Dao.cachedTables.put(instance.TableName(),Dao.md5Custom(sql.toString()));
			}
			
			result = this.executeRawQuery(sql.toString()).parseObjects(instance,this._wherecomp);
		} catch (Exception e) {
			throw new DaoObjectException(e.getMessage());
		} 		
		
		this._wherecomp = new WhereAllRows(); // clear where clause
		
		return result;
	} 
	
	public static void setCacheLifetime(int seconds) {
		Dao.cacheLifetime = seconds;
	}
	
	public static void enableCache(boolean state) {
		Dao.useCache = state;
	}
	

	// #21 �������� SQL ������ ��� ��������� ���������� ��� ����� 
	// can be changed by new Product(id);
	public Product getProductInfo(int id) throws Exception {
		return new Product(id);
	} 

	// #14 �������� SQL ������ ��� �������� ����������� ������
	public void fixAddProduct(int id,int supplierId,int quantity) {
		this.provider.execute("UPDATE product SET quantity = quantity + "+quantity+" WHERE id = "+id);

		long timestamp = System.currentTimeMillis() / 1000L;
		this.provider.execute("INSERT INTO supplied_item set supplier_id='"+supplierId+"', quantity='"+quantity+"', product_id = '"+id+"',date='"+timestamp+"'");

	} 

	// #17 �������� SQL ������ ��� �������� �������  database
	public void fixBuyProduct(int id,int employeeId,int quantity) {
		this.provider.execute("UPDATE product SET quantity = quantity - "+quantity+" WHERE id = "+id);

		long timestamp = System.currentTimeMillis() / 1000L;
		this.provider.execute("INSERT INTO sold_item set employee_id='"+employeeId+"', quantity='"+quantity+"', product_id = '"+id+"',date='"+timestamp+"'");
	} 


	//#15 �������� SQL ������ ��� ��������� ������ �������������
	public Vector<Supplier> getSuppliers() throws DaoObjectException {
		return this.provider.execute("SELECT * FROM supplier").parseObjects(new Supplier());
	} 
	
	
	// get all products
	/*
	public Vector<Supplier> getSuppliers() throws DaoObjectException {
		return this.provider.execute("SELECT * FROM supplier").parseObjects(new Supplier());
	} 
	*/
	
	// get employee by employee
	public Employee getEmployeeByName(String login) throws DaoObjectException {
		Vector<Employee> vec = new Vector<Employee>(this.provider.execute("SELECT * FROM employee WHERE login='" + login + "'").parseObjects(new Employee()));
		return vec.get(0);
	}
	

	// #24 �������� SQL ������ ��� ���������� ���������� �� ������ #24
	public void requestForProduct(int id,int employeeId,int quantity) {

		long timestamp = System.currentTimeMillis() / 1000L;

		// status = 0 request is not accepted yet. 1 - request successfuly executed
		this.provider.execute("INSERT INTO warehouseitem set employee_id='"+employeeId+"', status=0, quantity='"+quantity+"', product_id = '"+id+"',date='"+timestamp+"'");
	} 

	// get requests for products

	public Vector<Warehouseitem> getProductRequests() throws DaoObjectException {		
		return this.provider.execute("SELECT * FROM warehouseitem WHERE status=0").parseObjects(new Warehouseitem());
	} 
	// approve request
	public void approveProductRequest(int requestId) throws DaoObjectException {	
		Vector<Warehouseitem> res = this.provider.execute("SELECT * FROM warehouseitem WHERE id="+requestId).parseObjects(new Warehouseitem());

		this.provider.execute("UPDATE product SET quantity = quantity - "+res.get(0).quantity+" WHERE id = "+res.get(0).productId);
		this.provider.execute("UPDATE warehouseitem SET status=1 WHERE id="+requestId);
	}

	// decline request
	public void declineProductRequest(int requestId) {	
		this.provider.execute("UPDATE warehouseitem SET status=1 WHERE id="+requestId);
	}

	public boolean checkLogin(String login,String pass) {

		String passHash = Dao.md5Custom(pass);

		try {
			DaoResult result = this.provider.execute("SELECT * FROM employee WHERE login='"+login+"' and pass = '"+passHash+"'");
			if (result.length() > 0) {
				return true;
			} 
		} catch (Exception e) {
			System.out.println("Error:"+e.getLocalizedMessage());
		}
		return false;

	}

	public DaoResult executeRawQuery(String q){
		DaoResult _data = null;
		
		if (this.useCache) {
			String hash = Dao.md5Custom(q);
			CacheItem _cache = null;
			if (Dao.cache.containsKey(hash)) {
				_cache = Dao.cache.get(hash);
				String _typeSql = q.trim().substring(0,6).toLowerCase();
				if (!_typeSql.equals("select")) {
					System.out.println("Dao cache: Similar non-select ("+q+") query was executed "+(System.currentTimeMillis() - _cache.fetched)/1000L+". Is it not a bug?");
				}
			}
			if (_cache != null && (System.currentTimeMillis() - _cache.fetched) < (long)_cache.lifetime*1000 ) {
				_data = _cache.data;
			} else {
				_data = this.provider.execute(q);
				_data.cached = true;
				Dao.cache.put(hash, new CacheItem(_data));
			}
		} else {
			_data = this.provider.execute(q);
		}			
		return _data;
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

	public static Dao getInstance() {
		if (instance == null) {
			instance = new Dao(new MySQLProvider()); // bad way
		}
		return instance;
	}



}
