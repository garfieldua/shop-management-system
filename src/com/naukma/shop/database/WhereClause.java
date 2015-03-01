package com.naukma.shop.database;

public abstract class WhereClause<T extends DaoObject>  {
	abstract public boolean compare(T row);
}