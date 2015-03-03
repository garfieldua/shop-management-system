package com.naukma.shop.database;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Vector;

public class DaoResult {
	
	public boolean cached = false;
	HashMap<Integer,DaoObject> _cacheParsed = new HashMap<Integer,DaoObject>();
	
	private int count = 0;
	ArrayList<HashMap<String,String>> data = new ArrayList<HashMap<String,String>>();

	public DaoResult(ResultSet raw) {
		if (raw != null) {
			try {
				int cnt = 0;

				ResultSetMetaData rsMetaData = raw.getMetaData();				
				int columnCount = rsMetaData.getColumnCount();

				while (raw.next()) {

					HashMap<String,String> row = new HashMap<String,String>();

					for (int i=0; i < columnCount; i++) {
						String columnName = rsMetaData.getColumnName(i+1);
						row.put(columnName, raw.getString(columnName));
						
					} 

					data.add(row); 
					cnt++;
				}

				this.count = cnt;

			} catch (SQLException e) {
				// that's mean empty result
			}
		}
	}

	public int length(){ 
		return this.count;
	}
	
	public ArrayList<HashMap<String,String>> data(){
		return this.data;
	}
	
	public <T extends DaoObject> Vector<T> parseObjects(T instance) throws DaoObjectException {
		return this.parseObjects(instance,new WhereAllRows());
	} 
	
	public <T extends DaoObject> Vector<T> parseObjects(T instance,WhereClause comparator) throws DaoObjectException {
		
		Vector<T> result = new Vector<T>();
		
		if (this.count > 0) {
			
			if (Dao.useCache && this._cacheParsed.size() > 0) {
				for (Entry<Integer, DaoObject> row : this._cacheParsed.entrySet()) {
					T _obj = (T)row.getValue();					
					if (comparator.compare(_obj)) {
						result.add(_obj);
					}
				}
			} else {
				Field primarykey = null;
				
				try {
					primarykey = instance.getClass().getField(instance.PrimaryKey());
				} catch (Exception e) {
					throw new DaoObjectException(instance.getClass().getSimpleName()+" Primary key misconfiguraion");
				}

				for (HashMap<String,String> row : this.data()) {
					try {
						T _obj = (T) instance.getClass().newInstance();
						_obj.fill(row);
						
						if (comparator.compare(_obj)) {
							if (Dao.useCache) {
								this._cacheParsed.put(primarykey.getInt(_obj),_obj);
							}
							result.add(_obj);
						}
					} catch (Exception e) {
						throw new DaoObjectException("Error while parsing objects");
					}
				}
			}
		} 
		
		return result;
	} 





}
