package com.substitute.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.substitute.entity.Activities;
import com.substitute.entity.Data;
import com.substitute.entity.Users;
import com.substitute.service.ActivitiesService;
import com.substitute.service.UserService;
import com.substitute.util.DateChangeutil;
import com.substitute.util.DbUtil;
import com.substitute.util.IfLoginutil;
import com.substitute.util.JsonUtil;

/**
 * @author lilei
 *
 * 2020年6月5日
 */
@Controller
@RequestMapping("/activities")
public class ActivitiesController extends BaseController{
	@Autowired
	private ActivitiesService activitiesService;
	@Autowired
	private UserService userService;
	/**
	 * 跳转发布任务
	 * @return
	 */
	@RequestMapping(value="ToRelease",method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> ToRelease(){
		Map<String,Object> modelMap=new HashMap<>();
		try {
			if(IfLoginutil.IfLogin(session)) 
			{
				System.out.println("去发布任务"); 
				modelMap.put("message","success");
			}else 
			{
				modelMap.put("message","未登录");
			}
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success",false);
			modelMap.put("errMsg",e.toString());
		}
		return modelMap;
	}
	/**
	 * 获取发布任务信息
	 * @return
	 */
	@RequestMapping(value="Release",method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> Release(@RequestParam String type,@RequestParam String address,@RequestParam String price,@RequestParam String createtime,
										@RequestParam String introduction,@RequestParam String overtime){
		Map<String,Object> modelMap=new HashMap<>();
		Users record = new Users();
		Activities activities = new Activities();
		try {
				System.out.println("发布任务信息");
				record = (Users)session.getAttribute("user");
				activities.setType(Integer.parseInt(type));
				activities.setPrice(Integer.parseInt(price));
				
				overtime = overtime.replace("T", " ");
				createtime = createtime.replace("T", " ");
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date ot = format.parse(overtime);
				Date ct = format.parse(createtime);
				
				System.out.println("开始时间："+ct);
				activities.setCreatetime(ct);
				System.out.println("结束时间："+ot);
				activities.setOvertime(ot);
				activities.setIntroduction(introduction);
				activities.setAddress(address);
				
				if(activities!=null) 
				{
					activities.setSponsorid(record.getId());
					System.out.println(activities);
					activitiesService.insertSelective(activities);
					modelMap.put("message","success");
				}else 
				{
					modelMap.put("message","未能获取发布任务信息");
				}
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success",false);
			modelMap.put("errMsg",e.toString());
		}
		return modelMap;
	}
	/**
	 * 获取任务搜索信息
	 * @return
	 */
	@RequestMapping(value="Select",method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> Select(@RequestParam(value = "types[]") String[] types,@RequestParam String topprice,@RequestParam String botprice,
										@RequestParam String createtime,@RequestParam String overtime){
		Map<String,Object> modelMap=new HashMap<>();
		List<Activities> activitiesbytime = new ArrayList<Activities>();
		List<Activities> activitiesList=new ArrayList<Activities>();
		try {
				List<Integer> valueslist = new ArrayList<Integer>();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date ct = new Date();
				Date ot = new Date();
				//赋值过程
				if(types==null || types.length==0) 
				{
					valueslist.add(1);
					valueslist.add(2);
					valueslist.add(3);
				}else 
				{
					for(String s:types) 
					{
						valueslist.add(Integer.parseInt(s));
					}
				}
				if(topprice==null || topprice=="") 
				{
					topprice = "10000";
				}
				if(botprice==null || botprice=="") 
				{
					botprice = "-1";
				}
				if(createtime==null || createtime=="") 
				{
					ct = DateChangeutil.YearReduceOneHundred(new Date());
				}else 
				{
					createtime = createtime.replace("T", " ");
					ct = format.parse(createtime);
				}
				if(overtime==null || overtime=="") 
				{
					ot = DateChangeutil.YearAddOneHundred(new Date());
				}else 
				{
					overtime = overtime.replace("T", " ");
					ot = format.parse(overtime);
				}
				
				System.out.println("搜索任务信息");
				Map<String, Object> paramMap=new HashMap<>();
				
			    paramMap.put("topprice", topprice);
			    paramMap.put("botprice",botprice);
			    
				activitiesList=activitiesService.SelectAllActivities(paramMap,valueslist);
				for(Activities activities:activitiesList) 
				{
					System.out.println("比较时间前价格："+activities.getPrice());
					int compareTo1 = ct.compareTo(activities.getCreatetime());
					int compareTo2 = ot.compareTo(activities.getOvertime());
					if(compareTo1==-1 && compareTo2==1) 
					{
						activitiesbytime.add(activities);
					}
				}
				modelMap.put("message", "success");
				modelMap.put("activitiesList", activitiesbytime);
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success",false);
			modelMap.put("errMsg",e.toString());
		}
		return modelMap;
	}
	/**
	 * 修改发布任务信息
	 * 获取user session
	 * 已登录才能进入个人中心修改
	 * 不需要判断是否登陆
	 * 获取价格有问题
	 * @return
	 */
	@RequestMapping(value="ChangeRelease",method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> ChangeRelease(@RequestParam String id,@RequestParam String price,@RequestParam String address,
											@RequestParam String introduction,@RequestParam String createtime,@RequestParam String overtime){
		Map<String,Object> modelMap=new HashMap<>();
		Users record = new Users();
		Activities activities = new Activities();
		Activities demo = new Activities();
		if(IfLoginutil.IfLogin(session)) 
		{
			try {
				System.out.println("修改发布任务信息");
				record = (Users)session.getAttribute("user");
				activities.setId(Integer.parseInt(id));
				System.out.println("接收id:"+activities.getId());
				if(activities!=null) 
				{
					demo = activitiesService.selectByPrimaryKey(activities.getId());
					System.out.println("活动发起人id:"+demo.getSponsorid());
					System.out.println("登录用户id:"+record.getId());
					if(demo.getSponsorid().equals(record.getId())) 
					{
						activities.setSponsorid(record.getId());
						activities.setPrice(Integer.parseInt(price));
						activities.setIntroduction(introduction);
						createtime = createtime.replace("T", " ");
						overtime = overtime.replace("T", " ");
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date datedemo = format.parse(createtime);
						activities.setCreatetime(datedemo);
						datedemo = format.parse(overtime);
						activities.setOvertime(datedemo);
						activities.setAddress(address);
						System.out.println(activities);
						activitiesService.updateByPrimaryKeySelective(activities);
						modelMap.put("message","success");
						modelMap.put("id",activities.getId());
					}else 
					{
						modelMap.put("message","不是您发布的活动");
						modelMap.put("id",activities.getId());
					}
					
				}else 
				{
					modelMap.put("message","未能修改的发布任务信息");
				}
			} catch (Exception e) {
				e.printStackTrace();
				modelMap.put("success",false);
				modelMap.put("errMsg",e.toString());
			}
		}else 
		{
			modelMap.put("message","未登录");
		}
		
		return modelMap;
	}
	/**
	 * 获取所有发布任务
	 * status为0
	 * 查询信誉分
	 * @return
	 */
	@RequestMapping(value="AllActivities",method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> listUser(){
		Map<String,Object> modelMap=new HashMap<>();
		List<Activities> activitiesVIPList=new ArrayList<Activities>();
		List<Activities> activitiesNVIPList=new ArrayList<Activities>();
		try {
			activitiesVIPList = activitiesService.SelectAllActivitiesWithSAndV();
			activitiesNVIPList =  activitiesService.SelectAllActivitiesWithSAndNV();
			if(activitiesVIPList!=null || activitiesNVIPList!=null)
			{
				modelMap.put("vipactivities", activitiesVIPList);
				modelMap.put("nvipactivities", activitiesNVIPList);
			}else 
			{
				modelMap.put("message", "没有活动");
			}
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success",false);
			modelMap.put("errMsg",e.toString());
		}
		return modelMap;
	}
	/**
	 * 接受任务
	 * 获取user session
	 * 存储receiverid
	 * 不能接受自己发布的任务
	 * 修改任务状态
	 * 0挂起 1完成 2接受
	 * @return
	 */
	@RequestMapping(value="AcceptActivities",method = RequestMethod.POST) 
	@ResponseBody
	private Map<String,Object> AcceptActivities(@RequestParam String id){
		Map<String,Object> modelMap=new HashMap<>();
		Activities activities = new Activities();
		try {
			if(IfLoginutil.IfLogin(session)) 
			{
				Users record = (Users) session.getAttribute("user");
				if(record.getCreditpoints()<=60) 
				{
					modelMap.put("message", "信誉过低 请联系客服");
					return modelMap;
				}else 
				{
					if(activities!=null && id!=null) 
					{
						System.out.println("接受活动id："+Integer.parseInt(id));
						activities = activitiesService.selectByPrimaryKey(Integer.parseInt(id));
						if(activities.getSponsorid().equals(record.getId())) 
						{
							modelMap.put("message", "您不能接受自己发布的任务");
						}else 
						{
							activities.setReceiverid(record.getId());
							activities.setStatus(2);
							activitiesService.updateByPrimaryKeySelective(activities);
							modelMap.put("message", "success");
						}
					}else 
					{
						modelMap.put("message", "任务获取失败");
					}
				}
			}else 
			{
				modelMap.put("message", "未登录");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success",false);
			modelMap.put("errMsg",e.toString());
		}
		return modelMap;
	}
	/**
	 * 任务完成或失败
	 * 存储receiverid
	 * 只能结束自己的任务
	 * 设置任务满意度
	 * 修改信誉分
	 * @return
	 */
	@RequestMapping(value="OverActivities",method = RequestMethod.POST) 
	@ResponseBody
	private Map<String,Object> OverActivities(@RequestParam String id,@RequestParam String type,@RequestParam String star){
		Map<String,Object> modelMap=new HashMap<>();
		Activities activities = new Activities();
		Users record = new Users();
		if(IfLoginutil.IfLogin(session)) 
		{
			try {
				if(id!=null && ""!=id) 
				{
					record = (Users) session.getAttribute("user");
					System.out.println("完成活动id："+Integer.parseInt(id)+"状态："+Integer.parseInt(type));
					activities = activitiesService.selectByPrimaryKey(Integer.parseInt(id));
					if(activities!=null && record.getId().equals(activities.getSponsorid())) 
					{
						activities.setStatus(Integer.parseInt(type));
						Users demo = userService.selectByPrimaryKey(activities.getReceiverid());
						if(Integer.parseInt(type)==1) 
						{
							activities.setSatisfaction(Integer.parseInt(star));
							demo.setCreditpoints(demo.getCreditpoints()+5);
						}else 
						{
							activities.setSatisfaction(Integer.parseInt(star));
							demo.setCreditpoints(demo.getCreditpoints()-5);
						}
						userService.updateByPrimaryKeySelective(demo);
						activitiesService.updateByPrimaryKeySelective(activities);
						modelMap.put("message", "success");
					}else 
					{
						modelMap.put("message", "任务获取失败或非本人发布的活动");
					}
				}else 
				{
					modelMap.put("message", "未选择");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				modelMap.put("success",false);
				modelMap.put("errMsg",e.toString());
			}
		}else 
		{
			modelMap.put("message", "未登录");
		}
		
		return modelMap;
	}
	/**
	 * 任务详情
	 * 获取activitiesid session
	 * @return
	 */
	@RequestMapping(value="ActivitiesInfo",method = RequestMethod.GET) 
	@ResponseBody
	private Map<String,Object> ActivitiesInfo(@RequestParam String id){
		Map<String,Object> modelMap=new HashMap<>();
		System.out.println(id);
		Activities activities = new Activities();
			try {
				if(id!=null) 
				{
					activities = activitiesService.selectByPrimaryKey(Integer.parseInt(id));
					System.out.println("活动："+activities);
					if(activities!=null) 
					{
						modelMap.put("activities", activities);
					}else 
					{
						modelMap.put("message", "查询失败");
					}
				}else 
				{
					modelMap.put("message", "未选择");
				}
			} catch (Exception e) {
				e.printStackTrace();
				modelMap.put("success",false);
				modelMap.put("errMsg",e.toString());
			}
		
		return modelMap;
	}
	/**
	 * 获取我的任务
	 * 获取status
	 * 没有status全部获取
	 * 获取session user
	 * @return
	 */
	@RequestMapping(value="MyActivities",method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> MyActivities(@RequestParam String releasestatus,@RequestParam String acceptstatus){
		Map<String,Object> modelMap=new HashMap<>();
		List<Activities> activitiesList=new ArrayList<Activities>();
		Users record = (Users) session.getAttribute("user");
		Activities demo = new Activities();
		demo.setSponsorid(record.getId());
		demo.setReceiverid(record.getId());
		try {
			if(releasestatus!=null && ""!=releasestatus) 
			{
					demo.setStatus(Integer.parseInt(releasestatus));
					activitiesList=activitiesService.SelectMyActivitiesWithStatus(demo);
			}else if(acceptstatus!=null && ""!=acceptstatus) 
			{
					demo.setStatus(Integer.parseInt(acceptstatus));
					activitiesList=activitiesService.SelectMyAcceptActivitiesWithStatus(demo);
			}
			else
			{
					activitiesList=activitiesService.SelectAllMyActivities(demo);
			}
				if(activitiesList!=null) 
				{
					System.out.println("我的已发布活动： "+activitiesList);
					modelMap.put("activities", activitiesList);
				}else 
				{
					modelMap.put("message", "没有活动");
					modelMap.put("activities", activitiesList);
				}
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success",false);
			modelMap.put("errMsg",e.toString());
		}
		return modelMap;
	}
	
	@RequestMapping(value = "ActivityAnalyze", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object>ActivityAnalyze() {
		Connection connection = DbUtil.getInstance().getConnection();
		HashMap<String, Object> hashmap=new HashMap<>();
		/**
		 * 每日数量
		 * SELECT COUNT(1) AS countNumber,DATE_FORMAT(createtime,'%m-%d') AS dateTime 
		 * FROM activities GROUP BY DATE_FORMAT(createtime,'%m-%d')
		 */
		Data<Long,String> data;
		List<Data<Long,String>> datas = new ArrayList<>();
		try {
			PreparedStatement pstat = connection.prepareStatement(
					"SELECT COUNT(1) AS countNumber,DATE_FORMAT(createtime,'%m-%d') AS dateTime FROM activities GROUP BY DATE_FORMAT(createtime,'%m-%d')");
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				data = new Data();
				data.setColumFirst(rs.getLong("countNumber"));
				data.setColumSecond(rs.getString("dateTime"));
				datas.add(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/**
		 * 价格分类
		 * select count(*) count,(CASE WHEN price >= 1000 
		 * THEN '1000+' WHEN price < 1000 AND price >= 100 
		 * THEN'100-1000' WHEN price < 100 AND price >= 50 
		 * THEN '50-100' WHEN price<50 and price >=20 
		 * THEN'20-50'ELSE '0-20' END ) as pricetype 
		 * FROM activities GROUP BY pricetype
		 */
		Data<Long,String> data2;
		List<Data<Long,String>> datas2 = new ArrayList<>();
		try {
			PreparedStatement pstat = connection.prepareStatement(
					"SELECT count(*) count,(CASE WHEN price>=1000 THEN '1000+' WHEN price< 1000 AND price>=100 THEN '100-1000' WHEN price< 100 AND price>=50 THEN '50-100' WHEN price< 50 AND price>=20 THEN '20-50' ELSE '0-20' END) AS pricetype FROM activities GROUP BY pricetype");
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				data2 = new Data();
				data2.setColumFirst(rs.getLong("count"));
				data2.setColumSecond(rs.getString("pricetype"));
				datas2.add(data2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/**
		 * 活动分类
		 * SELECT COUNT(1) count,type FROM activities GROUP BY type
		 */
		Data<Long,String> data3;
		List<Data<Long,String>> datas3 = new ArrayList<>();
		try {
			PreparedStatement pstat = connection.prepareStatement(
					"SELECT COUNT(1) count,type FROM activities GROUP BY type");
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				data3 = new Data();
				data3.setColumFirst(rs.getLong("count"));
				data3.setColumSecond(rs.getString("type"));
				datas3.add(data3);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		hashmap.put("DataAnalyze", datas);
		hashmap.put("DataAnalyze2",datas2);
		hashmap.put("DataAnalyze3",datas3);
		return hashmap;
	}
}
