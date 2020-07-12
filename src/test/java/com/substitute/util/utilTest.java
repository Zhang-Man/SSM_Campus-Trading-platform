package com.substitute.util;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.substitute.BaseTest;
import com.substitute.entity.Activities;
import com.substitute.service.ActivitiesService;
import com.substitute.util.PhoneCodeUtil;

public class utilTest extends BaseTest{
	@Autowired
	private ActivitiesService activitiesService;
	@Test
	public void PhoneCodeUtil() {
		String skin = "3";//登录
		String phone = "15149039299";
		System.out.println(phone);
		String code = null;
		Map<String,Object> modelMap=new HashMap<>();
		String sign = "1";//签名
		code = PhoneCodeUtil.main(phone,skin,sign);
		if(code!=null) 
		{
			modelMap.put("message", "验证码发送成功");
			modelMap.put("code", code);
			System.out.println(modelMap.get("message"));
			System.out.println(modelMap.get("code"));
		}else 
		{
			modelMap.put("message", "验证码发送失败");
			System.out.println(modelMap.get("message"));
		}
		
	}
	@Test
	public void EmailCodeUtil() {
		int ran = (int) ((Math.random() * 9 + 1) * 100000);
		String code = String.valueOf(ran);
		String mail = "lilei.kuge@qq.com";
		System.out.println(mail);
		Map<String,Object> modelMap=new HashMap<>();
		if(mail!=null) 
		{
			MailUtils.SendMail(mail, code, "corasun项目组");
		}
		if(code!=null) 
		{
			modelMap.put("message", "验证码发送成功");
			modelMap.put("code", code);
			System.out.println(modelMap.get("message"));
			System.out.println(modelMap.get("code"));
		}else 
		{
			modelMap.put("message", "验证码发送失败");
			System.out.println(modelMap.get("message"));
		}
		
	}
	@Test
	public void Time() 
	{
		System.out.println("day:"+new Date().getDay());
		System.out.println("time:"+new Date().getTime());
		System.out.println("TimezoneOffset:"+new Date().getTimezoneOffset());
		System.out.println("date:"+new Date().getDate());
		System.out.println("date:"+new Date());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = df.format(new Date());
	}
	@Test
	public void testTime() 
	{
		List<Activities> list = new ArrayList<Activities>();
		list = activitiesService.SelectAllActivitiesWithStatus();
		if(list!=null) 
		{
			for(Activities activities:list) 
			{
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println("发布任务时间结束时间：:"+df.format(activities.getOvertime()));
				int compareTo = new Date().compareTo(activities.getOvertime());
				System.out.println(compareTo);
//				System.out.println("发布任务时间结束时间：:"+activities.getOvertime());
			}
		}
	}
}
