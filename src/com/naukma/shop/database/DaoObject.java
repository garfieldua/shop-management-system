package com.naukma.shop.database;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Vector;

abstract public class DaoObject {

	class FieldInfo {
		public String name;
		public String type;
		public boolean required; // is field required to save

	}
	private String _tableName = "";
	private String _primaryKey = "";
	private boolean _isLoaded = false;
	private static Dao db = Dao.getInstance();
	private final static String DEFAULT_PRIMARY = "id";
	private HashMap<String,FieldInfo> FIELDS = new HashMap<String,FieldInfo>(); 
	
	
	
	public DaoObject(){
		this.initialize();
	}

	public DaoObject(int primaryKey) throws DaoObjectException {
		this.initialize();
		HashMap<String,String> primary = new HashMap<String, String>();
		primary.put(this.PrimaryKey(),String.valueOf(primaryKey));
		this.fill(primary);
		this.parseColumns();
	}

	public DaoObject(HashMap<String,String> data) throws DaoObjectException {
		this.initialize();
		this.fill(data);
	} 	
	
	public void remove() throws DaoObjectException{
		StringBuilder sql = new StringBuilder();
		try {
			Field pKeyField = this.getClass().getField(this._primaryKey);

			if (!pKeyField.get(this).toString().equals("") && !pKeyField.get(this).toString().equals("0")) {
				this._isLoaded = true;
			}
			if (this._isLoaded) {
				sql.append("DELETE FROM "+this._tableName+" WHERE "+this._primaryKey+" = '"+pKeyField.get(this)+"'");
				db.executeRawQuery(sql.toString());
				pKeyField.set(this,0);
			} else {
				throw new DaoObjectException("Can't delete unloaded objects!");
			}
		} catch (Exception e) {
			throw new DaoObjectException("Please review column types and PrimaryKey()/TableName()");
		}
	}

	public void save() throws DaoObjectException {
		StringBuilder sql = new StringBuilder();
		boolean actualyLoaded = this._isLoaded;

		if (this.FIELDS.size() > 0) {
			
			try {
				
				StringBuilder fieldsValues = new StringBuilder();
				for (Entry<String, FieldInfo> f : this.FIELDS.entrySet()) {
					Field field = this.getClass().getField(f.getValue().name);
					Object value = field.get(this);
					if (f.getValue().required && (value.toString().equals("") || value.toString().equals("0"))) {
						throw new DaoObjectException("Not all required fields are filled");
					}
					fieldsValues.append("`"+f.getKey()+"` = '"+value+"',");
				}
				fieldsValues.deleteCharAt(fieldsValues.length()-1);
				
				Field pKeyField = this.getClass().getField(this._primaryKey);

				if (!pKeyField.get(this).toString().equals("") && !pKeyField.get(this).toString().equals("0")) {
					this._isLoaded = true;
				}
				if (this._isLoaded) {
					sql.append("UPDATE ");
				} else {
					sql.append("INSERT INTO ");
				}
				sql.append(this._tableName+" SET "+fieldsValues);
				if (this._isLoaded) {
					sql.append(" WHERE "+this._primaryKey+" = '"+pKeyField.get(this)+"'");
				}
				if (!actualyLoaded) {
					int id = Integer.parseInt(db.executeRawQuery(sql.toString()).data().get(0).get("GENERATED_KEY"));
					pKeyField.set(this,id);
				}
			} catch (DaoObjectException e) {
				throw e;
			} catch (Exception e) {
				throw new DaoObjectException("DaoObject misconfiguration: please review column types");
			} 
		} else {
			System.out.println("Nothing to save");
		}
	}

	public String TableName() {
		
		if (this._tableName.equals("")) {
			if (this.getClass().isAnnotationPresent(Table.class)) {
				this._tableName =  this.getClass().getAnnotation(Table.class).value();
			} else {
				this._tableName = this.getClass().getSimpleName().toLowerCase();
			}
		}
		
		return this._tableName;
	}

	/**
	 * Excute only after parseFeildsInfo()
	 * @return
	 */
	public String PrimaryKey() {
		
		if (this._primaryKey.equals("")) {
			this._primaryKey = DaoObject.DEFAULT_PRIMARY;
		}
		
		return this._primaryKey; 
	}

	/**
	 * Ignore non exist field in class when trying to assign values through fill()  
	 * @return boolean
	 */
	protected boolean ThrowOnFill() {
		return false;
	}

	public void fill(HashMap<String,String> data) throws DaoObjectException {
		for (Entry<String, String> f : data.entrySet()) {
			if (this.FIELDS.containsKey(f.getKey()) ){
				FieldInfo fInfo = this.FIELDS.get(f.getKey());
				try {
					Field _field = this.getClass().getField(fInfo.name);
					String _fieldType = fInfo.type;
					Object _fieldValue = null;
					String columnValue = f.getValue();
					
					switch (_fieldType) {
						case "int": _fieldValue = Integer.parseInt(columnValue); break;
						case "float": _fieldValue = Float.parseFloat(columnValue); break;
						case "double": _fieldValue = Double.parseDouble(columnValue); break;
						case "java.lang.String": _fieldValue = columnValue; break;
					}
					_field.set(this,_fieldValue);
				} catch (Exception e) {
					throw new DaoObjectException("No field '"+f.getKey()+"' found while filling "+this.getClass().getSimpleName());
				}
			} else if (this.ThrowOnFill()) {
				throw new DaoObjectException("Trying to assign non exist field '"+f.getKey()+"'");
			} 
		}
	}

	@Override
	public String toString() {
		StringBuilder result =  new StringBuilder();

		try {			
			for (Entry<String, FieldInfo> f : this.FIELDS.entrySet()) {
				Field _field = this.getClass().getField(f.getValue().name);
				result.append(f.getValue().name+" = "+_field.get(this)+",");
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName());
			e.printStackTrace();
			System.out.println("some problems with output a DaoObject");
		}

		result.deleteCharAt(result.length()-1);
		return result.toString();
	}
	
	private void parseFieldsInfo() {
		if (this.FIELDS.isEmpty()) {
			Field[] fields = this.getClass().getFields();
			for (Field m : fields) {
				if (Modifier.isPublic(m.getModifiers()) && !Modifier.isStatic(m.getModifiers())) {
					String colName = m.getName();
					FieldInfo info  = new FieldInfo();
					if (m.isAnnotationPresent(Column.class)) {
						Column colAnn = m.getAnnotation(Column.class);
						info.required = colAnn.required();
						if (!colAnn.name().equals("")) {
							colName = colAnn.name(); 
						}
						if (colAnn.primary()){
							this._primaryKey = colName;
						}
					}
					
					info.name = m.getName();
					info.type = m.getType().getName();
					
					this.FIELDS.put(colName,info);
				}
			}
		}
	}

	private void initialize() {
		this.parseFieldsInfo();
		this._tableName = this.TableName();
		this._primaryKey = this.PrimaryKey();

	}
	
	private void parseColumns() throws DaoObjectException {
		try {
			Field pKeyField = this.getClass().getField(this.PrimaryKey());
			String sql = "SELECT * FROM "+this._tableName+" WHERE "+this._primaryKey+" = '"+pKeyField.get(this)+"'";
			this.fill(db.executeRawQuery(sql).data().get(0));
			this._isLoaded = true;
		} catch (NoSuchFieldException e) {
			throw new DaoObjectException("Primary key not found or misconfirated");
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException e )  {
			throw new DaoObjectException("Error parsing a data from database. Perhaps such data not exists");
		} catch (IndexOutOfBoundsException e) {
			throw new DaoObjectException("Error parsing a data from database. MySQl returned 0 rows");
		} 		
	}
};