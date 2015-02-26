package com.naukma.shop.database;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

abstract public class DaoObject {

	protected final static String DEFAULT_PRIMARY = "id";

	protected String _tableName = "";
	protected String _primaryKey = "";

	private boolean _isLoaded = false;
	private Dao db = Dao.getInstance();

	public DaoObject(){
		this.initialize();
	}

	public DaoObject(int primaryKey) throws Exception {

		this.initialize();

		Field pKeyField;


		System.out.println(this.getClass().getName()+ " "+this._primaryKey);
		pKeyField = this.getClass().getField(this._primaryKey);
		pKeyField.set(this, primaryKey);

		this.parseColumns(this.getClass());





	}

	private void initialize() {
		if (this._tableName == "") {
			String [] splitName = this.getClass().getName().split("\\.");
			this._tableName = splitName[splitName.length-1].toLowerCase();
		}

		if (this._primaryKey == "") {
			this._primaryKey = DaoObject.DEFAULT_PRIMARY;
		}
	}

	private void parseColumns(Class<?> clazz) throws Exception {


		HashMap<String, String> row;

		Field pKeyField;
		String sql = "";
		pKeyField = clazz.getField(this._primaryKey);
		sql = "SELECT * FROM "+this._tableName+" WHERE "+this._primaryKey+" = '"+pKeyField.get(this)+"'";

		try {
			row = db.executeRawQuery(sql).data().get(0);

			Field[] fields = clazz.getFields();
			for (Field m : fields) {
				if (Modifier.isPublic(m.getModifiers())) {


					Field _field = clazz.getField(m.getName());

					String _fieldType = m.getType().getCanonicalName().toString();
					Object _fieldValue = null;

					String colName = m.getName().toLowerCase();

					if (m.isAnnotationPresent(Column.class)) {
						Column anotation = m.getAnnotation(Column.class);
						colName = anotation.name();
					} 

					String rowValue = row.get(colName); 

					switch (_fieldType) {

					case "int": _fieldValue = Integer.parseInt(rowValue); break;
					case "float": _fieldValue = Float.parseFloat(rowValue); break;
					case "double": _fieldValue = Double.parseDouble(rowValue); break;
					case "java.lang.String": _fieldValue = rowValue; break;

					}

					_field.set(this,_fieldValue);
				}
			}	

			this._isLoaded = true;
		} catch (IndexOutOfBoundsException e) {
			throw new Exception("SQL error. Can't get column from a database. Such row not exists");
		}



	}

	@Override
	public String toString() {

		StringBuilder result =  new StringBuilder();

		Field[] fields = this.getClass().getFields();

		try {
			for (Field f : fields) {
				if (Modifier.isPublic(f.getModifiers())) {
					result.append(f.getName()+" = "+f.get(this)+",");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		result.deleteCharAt(result.length()-1);

		return result.toString();


	}

	public void remove() throws Exception {

		StringBuilder sql = new StringBuilder();

		Field pKeyField = null;



		pKeyField = this.getClass().getField(this._primaryKey);
		if (!pKeyField.get(this).toString().equals("") && !pKeyField.get(this).toString().equals("0")) {
			this._isLoaded = true;
		}

		if (this._isLoaded) {
			sql.append("DELETE FROM "+this._tableName+" WHERE "+this._primaryKey+" = '"+pKeyField.get(this)+"'");
		}

		pKeyField.set(this,0);

		db.executeRawQuery(sql.toString());
	}

	public void save() {

		StringBuilder sql = new StringBuilder();
		int fieldsPublic = 0;

		boolean actualyLoaded = this._isLoaded;

		Field pKeyField = null;

		try {

			pKeyField = this.getClass().getField(this._primaryKey);
			if (!pKeyField.get(this).toString().equals("") && !pKeyField.get(this).toString().equals("0")) {
				this._isLoaded = true;
			}

			if (this._isLoaded) {
				sql.append("UPDATE ");
			} else {
				sql.append("INSERT INTO ");
			}

			sql.append(this._tableName+" SET ");


			Field[] fields = this.getClass().getFields();

			try {

				for (Field f : fields) {
					if (Modifier.isPublic(f.getModifiers())) {

						String colName = f.getName();

						if (f.isAnnotationPresent(Column.class)) {
							Column anotation = f.getAnnotation(Column.class);
							colName = anotation.name();
						} 

						sql.append("`"+colName+"` = '"+f.get(this)+"',");
						fieldsPublic++;
					}
				}

				if (fieldsPublic > 0) {
					sql.deleteCharAt(sql.length()-1);
				}

			} catch (Exception e) {
				System.out.println("DaoObject misconfiguration: please review column types");
			}

			if (fieldsPublic > 0) {
				if (this._isLoaded) {
					sql.append(" WHERE "+this._primaryKey+" = '"+pKeyField.get(this)+"'");
				}

				if (!actualyLoaded) {
					int id = Integer.parseInt(db.executeRawQuery(sql.toString()).data().get(0).get("GENERATED_KEY"));
					pKeyField.set(this,id);
				}			


			} else {
				System.out.println("Nothing to save");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}







};


