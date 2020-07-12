package com.substitute.util;

import java.util.Calendar;
import java.util.Date;

public class DateChangeutil {
	
	public static Date MonthAddOne(Date date) 
	{
		Calendar cdate = Calendar.getInstance();
		//获取当前时间
		cdate.setTime(date);
		cdate.set(Calendar.MONTH, cdate.get(Calendar.MONTH)+1);

		Date startDate=cdate.getTime();
		return startDate;
	}
	public static Date YearAddOneHundred(Date date) 
	{
		Calendar cdate = Calendar.getInstance();
		//获取当前时间
		cdate.setTime(date);
		cdate.set(Calendar.YEAR, cdate.get(Calendar.YEAR)+100);

		Date startDate=cdate.getTime();
		return startDate;
	}
	public static Date YearReduceOneHundred(Date date) 
	{
		Calendar cdate = Calendar.getInstance();
		//获取当前时间
		cdate.setTime(date);	
		cdate.set(Calendar.YEAR, cdate.get(Calendar.YEAR)-100);

		Date startDate=cdate.getTime();
		return startDate;
	}
	public static Date WeekAddOne(Date date) 
	{
		Calendar cdate = Calendar.getInstance();
		//获取当前时间
		cdate.setTime(date);
		cdate.set(Calendar.DAY_OF_MONTH, cdate.get(Calendar.DAY_OF_MONTH)+7);

		Date startDate=cdate.getTime();
		return startDate;
	}
}
