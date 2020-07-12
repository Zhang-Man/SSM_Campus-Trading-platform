package com.substitute.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.substitute.BaseTest;
import com.substitute.entity.Activities;
import com.substitute.entity.Users;
import com.substitute.util.DateChangeutil;

public class ActivitiesDaoTest extends BaseTest{
@Autowired
private ActivitiesDao activitiesDao;

@Test
public void SelectMyActivitiesWithStatus() {
	List<Activities> activitiesList=new ArrayList<Activities>();
	Activities demo = new Activities();
	demo.setSponsorid(2);
	demo.setStatus(0);
	activitiesList=activitiesDao.SelectMyActivitiesWithStatus(demo);
	for(Activities activities:activitiesList) 
	{
		System.out.println(activities.getPrice());
	}
}
@Test
public void SelectAllMyActivities() {
	List<Activities> activitiesList=new ArrayList<Activities>();
	Activities demo = new Activities();
	demo.setSponsorid(2);
	activitiesList=activitiesDao.SelectAllMyActivities(demo);
	for(Activities activities:activitiesList) 
	{
		System.out.println(activities.getPrice());
	}
}
@Test
public void SelectAllActivities() {
	List<Activities> activitiesList=new ArrayList<Activities>();
	Map<String, Object> paramMap=new HashMap<>();
	List<Integer> valueslist = new ArrayList<Integer>();
	Date ct = new Date();
	Date ot = new Date();
	valueslist.add(1);
	valueslist.add(2);
	valueslist.add(3);
    paramMap.put("topprice", 99999);
    paramMap.put("botprice",0);
    ct = DateChangeutil.YearReduceOneHundred(new Date());
    ot = DateChangeutil.YearAddOneHundred(new Date());
	activitiesList=activitiesDao.SelectAllActivities(paramMap,valueslist);
	for(Activities activities:activitiesList) 
	{
		int compareTo1 = ct.compareTo(activities.getCreatetime());
		int compareTo2 = ot.compareTo(activities.getOvertime());
		if(compareTo1==-1 && compareTo2==1) 
		{
			System.out.println(activities.getId());
		}
	}
}
}
