package com.substitute.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.substitute.entity.Activities;
import com.substitute.service.ActivitiesService;

@Component
public class Scheduledutil {
	@Autowired
	private ActivitiesService activitiesService;
	/**
	 * 逾时自动完成活动
	 */
	@Scheduled(cron="0/1 * * * * ? ")
	public void AutoChangeActivitiesStatus() 
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
				if(compareTo==1) 
				{
					activities.setStatus(1);
					activitiesService.updateByPrimaryKeySelective(activities);
				}
			}
		}
	}
}
