package com.substitute.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.substitute.entity.Users;
import com.substitute.entity.Vip;
import com.substitute.service.UserService;
import com.substitute.service.VipService;
import com.substitute.util.DateChangeutil;
import com.substitute.util.IfLoginutil;

/**
 * @author lilei
 *
 * 2020年6月10日
 */
@Controller
@RequestMapping("/vip")
public class VipController extends BaseController{
	@Autowired
	private VipService vipService;
	@Autowired
	private UserService userService;
	/**
	 * 升级vip
	 * 查询是否为过往vip
	 * 修改用户类型
	 * 0普通用户 1管理 2vip
	 * @return
	 */
	@RequestMapping(value="ToVip",method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> ToVip(){
		Map<String,Object> modelMap=new HashMap<>();
		try {
				System.out.println("办理或续费会员");
				Users record = (Users) session.getAttribute("user");
				Vip vip = vipService.selectByusersid(record.getId());
				if(vip!=null) 
				{
					if(vip.getStatus()==1) 
					{
						System.out.println("续费成功");
						vip.setOvertime(DateChangeutil.MonthAddOne(vip.getOvertime()));
						vip.setIntegral(vip.getIntegral()+30);
						System.out.println("结束时间："+vip.getOvertime());
						
						vipService.updateByPrimaryKey(vip);
						record.setUsertype(2);
						userService.updateByPrimaryKeySelective(record);
						session.setAttribute("user", record);
						modelMap.put("message","success");
					}else 
					{
						System.out.println("重新办理");
						vip.setStatus(1);
						vip.setOvertime(DateChangeutil.MonthAddOne(new Date()));
						vip.setIntegral(vip.getIntegral()+30);
						System.out.println("结束时间："+vip.getOvertime());
						
						vipService.updateByPrimaryKey(vip);
						record.setUsertype(2);
						userService.updateByPrimaryKeySelective(record);
						session.setAttribute("user", record);
						modelMap.put("message","success");
					}
				}else 
				{
					System.out.println("办理成功");
					vip = new Vip();
					vip.setUsersid(record.getId());
					vip.setIntegral(30);
					vip.setCreatetime(new Date());
					vip.setOvertime(DateChangeutil.MonthAddOne(new Date()));
					System.out.println("结束时间："+vip.getOvertime());
					
					vipService.insertSelective(vip);
					record.setUsertype(2);
					userService.updateByPrimaryKeySelective(record);
					session.setAttribute("user", record);
					modelMap.put("message","success");
				}
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success",false);
			modelMap.put("errMsg",e.toString());
		}
		return modelMap;
	}
	/**
	 * 获取会员截止时间
	 * @return
	 */
	@RequestMapping(value="GetVipTime",method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> GetVipTime(){
		Map<String,Object> modelMap=new HashMap<>();
		if(IfLoginutil.IfLogin(session)) 
		{
			try {
				Users record = (Users) session.getAttribute("user");
				Vip vip = vipService.selectByusersid(record.getId());
				if(vip!=null) 
				{
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					System.out.println("vip结束时间"+df.format(vip.getOvertime()));
					modelMap.put("time",df.format(vip.getOvertime()));
				}else 
				{
					modelMap.put("message","非会员");
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
}
