package com.substitute.service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.substitute.BaseTest;
import com.substitute.entity.Activities;
import com.substitute.entity.Users;

public class ActivitiesServiceTest extends BaseTest{
	@Autowired
	private ActivitiesService activitiesService;
	
	/**
	 * 修改发布任务信息
	 */
	@Test
	public void testChangeRelease() {
		Activities activities = new Activities();
		
		System.out.println("修改发布任务信息");
		activities.setId(1);
		activities.setPrice(1123);
		activities.setOvertime(new Date());
		
		System.out.println(activities);
		activitiesService.updateByPrimaryKeySelective(activities);
	}
	/**
	 * 发布任务信息
	 */
	@Test
	public void testRelease() {
		Activities activities = new Activities();
		activities.setPrice(123);
		activities.setCreatetime(new Date());
		activities.setSponsorid(2);
		System.out.println(activities);
		activitiesService.insertSelective(activities);
	}
	/**
	 * 结束任务
	 */
	@Test
	public void testOverActivities() {
		Activities activities = new Activities();
		Integer id = 1;
		Integer status = 1;
//		Integer status = 2;
		System.out.println("完成活动id："+id+"状态："+status);
		activities = activitiesService.selectByPrimaryKey(id);
		activities.setStatus(status);
		activitiesService.updateByPrimaryKeySelective(activities);
	}
}
