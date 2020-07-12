package com.substitute.dao;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.substitute.BaseTest;
import com.substitute.entity.Users;
import com.substitute.entity.Vip;
import com.substitute.util.DateChangeutil;

public class VIPDaoTest extends BaseTest{
@Autowired
private VipDao vipDao;

@Test
public void testinsert() {
	Users record = new Users();
	record.setId(2);
	Vip vip = new Vip();
	vip.setUsersid(record.getId());
	vip.setIntegral(30);
	vip.setCreatetime(new Date());
	System.out.println("当前："+new Date()+"加一个月："+DateChangeutil.MonthAddOne(new Date()));
	vip.setOvertime(DateChangeutil.MonthAddOne(new Date()));
	
	vipDao.insertSelective(vip);
}

@Test
public void testupdate() {
	Vip vip = new Vip();
	vip = vipDao.selectByusersid(2);
	if(vip!=null) 
	{
		vip.setStatus(1);
		vip.setIntegral(vip.getIntegral()+30);
		vip.setOvertime(DateChangeutil.MonthAddOne(new Date()));
		System.out.println("结束时间："+vip.getOvertime());
		
		vipDao.updateByPrimaryKey(vip);
	}else 
	{
		System.out.println("空的");
	}
}
}
