package com.substitute.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.substitute.entity.Activities;
import com.substitute.entity.Users;
import com.substitute.entity.Vip;
import com.substitute.service.ActivitiesService;
import com.substitute.service.UserService;
import com.substitute.service.VipService;
import com.substitute.util.DateChangeutil;


/**
 * @author lilei
 *
 * 2020年6月4日
 */
@Controller
@Component
@RequestMapping("/util")
public class ScheduledController extends BaseController{
	@Autowired
	private ActivitiesService activitiesService;
	@Autowired
	private VipService vipService;
	@Autowired
	private UserService userService;
	/**
	 * 逾时活动失败
	 */
	@Scheduled(cron="0 0/1 * * * ? ")
	public void AutoChangeActivitiesStatus() 
	{
		List<Activities> list = new ArrayList<Activities>();
		list = activitiesService.SelectAllActivitiesWithStatus();
			for(Activities activities:list) 
			{
				System.out.println("监听活动状态中");
				int compareTo = new Date().compareTo(DateChangeutil.WeekAddOne(activities.getOvertime()));
				if(compareTo==1) 
				{
					Users demo = userService.selectByPrimaryKey(activities.getReceiverid());
					if(demo!=null) 
					{
						activities.setStatus(1);
						demo.setCreditpoints(demo.getCreditpoints()+5);
						userService.updateByPrimaryKeySelective(demo);
						activities.setSatisfaction(5);
						activitiesService.updateByPrimaryKeySelective(activities);
					}else 
					{
						activities.setStatus(3);
						activities.setSatisfaction(0);
						activitiesService.updateByPrimaryKeySelective(activities);
					}
				}
			}
	}
	
	
	@Scheduled(cron="0 0/1 * * * ? ")
	public void AutoChangeVIPStatus() 
	{
		List<Vip> list = new ArrayList<Vip>();
		list = vipService.selectAllWithStatus();
		Users demo = new Users();
			for(Vip vip:list) 
			{
				System.out.println("监听VIP状态中");
				int compareTo = new Date().compareTo(vip.getOvertime());
				if(compareTo==1) 
				{
					vip.setStatus(0);
					vipService.updateByPrimaryKeySelective(vip);
					demo = userService.selectByPrimaryKey(vip.getUsersid());
					demo.setUsertype(0);
					userService.updateByPrimaryKeySelective(demo);
				}
			}
		}
}
