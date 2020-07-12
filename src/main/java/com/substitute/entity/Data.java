package com.substitute.entity;

import java.sql.Date;

public class Data<T,V> {
	private T ColumFirst;
	private V ColumSecond;
	public T getColumFirst() {
		return ColumFirst;
	}
	public void setColumFirst(T columFirst) {
		ColumFirst = columFirst;
	}
	public V getColumSecond() {
		return ColumSecond;
	}
	public void setColumSecond(V columSecond) {
		ColumSecond = columSecond;
	}
	
}
