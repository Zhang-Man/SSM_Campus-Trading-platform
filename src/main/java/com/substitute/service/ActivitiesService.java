package com.substitute.service;

import java.util.List;
import java.util.Map;


import com.substitute.entity.Activities;

public interface ActivitiesService {
	public int insertSelective(Activities record);
	
	public List<Activities> SelectAllActivitiesWithStatus();
	
	public List<Activities> SelectAllActivitiesWithSAndV();
	
	public List<Activities> SelectAllActivitiesWithSAndNV();
	
	public List<Activities> SelectMyActivitiesWithStatus(Activities record);
	
	public List<Activities> SelectMyAcceptActivitiesWithStatus(Activities record);
	
	public List<Activities> SelectAllMyActivities(Activities record);
	
	public Activities selectByPrimaryKey(Integer id);
	
	public int updateByPrimaryKeySelective(Activities record);
	
	public List<Activities> SelectAllActivities(Map paramMap,List paramMap2);
}
