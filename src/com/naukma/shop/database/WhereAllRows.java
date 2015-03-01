package com.naukma.shop.database;

public class WhereAllRows extends WhereClause {

	@Override
	public boolean compare(DaoObject row) {
		return true;
	}
	
}
