package com.substitute.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	private Connection connection;
	private static DbUtil instance = new DbUtil();
	
	private DbUtil(){}
	
	public static DbUtil getInstance(){
		return instance;
	}
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public Connection getConnection(){
		try {
			connection = DriverManager.getConnection("jdbc:mysql://112.126.57.92:3306/substitute?useUnicode=true&characterEncoding=utf8", "root", "1234");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public static void main(String[] args) {
		System.out.println(DbUtil.getInstance().getConnection().toString());
	}
	
}
