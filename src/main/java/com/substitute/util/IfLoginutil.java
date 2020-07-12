package com.substitute.util;

import javax.servlet.http.HttpSession;

import com.substitute.entity.Users;

public class IfLoginutil {

	public static Boolean IfLogin(HttpSession session) 
	{
		Users record = new Users();
		record = (Users) session.getAttribute("user");
		if(record!=null) 
		{
			return true;
		}else 
		{
			return false;
		}
	}
}
