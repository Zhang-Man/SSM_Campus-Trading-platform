package com.substitute.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.substitute.entity.Users;
import com.substitute.service.UserService;
import com.substitute.util.IfLoginutil;

/**
 * @author lilei
 *
 * 2020年6月4日
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	@Autowired
	private UserService userService;
	/**
	 * logger为日志信息 尽量添加
	 * 每个函数如下格式编写
	 * @return
	 */
	@RequestMapping(value="userlist",method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> listUser(){
		Map<String,Object> modelMap=new HashMap<>();
		List<Users> userList=new ArrayList<Users>();
		try {
			userList=userService.getUserList();
			
			for(Users user:userList) 
			{
				System.out.println("信息： "+user.getUsername());
			}
			modelMap.put("rows", userList);
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success",false);
			modelMap.put("errMsg",e.toString());
		}
		return modelMap;
	}
	/**
	 * 登录
	 * sql查询username password status
	 * @return
	 */
	@RequestMapping(value="Login",method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> Login(@RequestParam String username,@RequestParam String password){
		Map<String,Object> modelMap=new HashMap<>();
		Users record = new Users();
		try {
			record.setUandP(username, password);
			record = userService.SelectUserByUandP(record);
			if(record!=null) 
			{
				session.setAttribute("user", record);
				System.out.println((Users)session.getAttribute("user"));
				modelMap.put("message", "success");
			}else 
			{ 
				modelMap.put("message", "错误的账号或密码！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success",false);
			modelMap.put("errMsg",e.toString());
		}
		return modelMap;
	}
	/**
	 * 登出
	 * @return
	 */
	@RequestMapping(value="Logout",method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> Logout(){
		Map<String,Object> modelMap=new HashMap<>();
		if(IfLoginutil.IfLogin(session)) 
		{
			session.setAttribute("user", null);
			System.out.println("登出");
			modelMap.put("message", "success");
		}else 
		{
			modelMap.put("message","请登录");
		}
		return modelMap;
	}
	/**
	 * 注册
	 * 查询注册方式是否重复
	 * 插入后重新查询用户
	 * 保存session
	 * @return
	 */
	@RequestMapping(value="Register",method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> Register(@RequestParam String username,@RequestParam String password,
										@RequestParam String tel,@RequestParam String email){
		Map<String,Object> modelMap=new HashMap<>();
		Users record = new Users();
		try {
			record.setUandPandTandE(username, password, tel, email);
			if(record.getUsername()!=null && ""!=record.getUsername() && userService.selectByUsername(record)==null) 
			{
				userService.insertSelective(record);
				record = userService.selectByUsername(record);
				session.setAttribute("user", record);
				modelMap.put("message", "success");
				System.out.println("新用户："+record.getUsername());
			}else if(record.getTel()!=null && ""!=record.getTel() && userService.selectByTel(record)==null) 
			{
				userService.insertSelective(record);
				record = userService.selectByTel(record);
				session.setAttribute("user", record);
				modelMap.put("message", "success");
				System.out.println("新用户："+record.getTel());
			}else if(record.getEmail()!=null && ""!=record.getEmail() && userService.selectByEmail(record)==null) 
			{
				userService.insertSelective(record);
				record = userService.selectByEmail(record);
				session.setAttribute("user", record);
				modelMap.put("message", "success");
				System.out.println("新用户："+record.getEmail());
			}else
			{ 
				modelMap.put("message", "姓名或电话或邮箱已被注册");
			}
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success",false);
			modelMap.put("errMsg",e.toString());
		}
		return modelMap;
	}
	/**
	 * 修改密码
	 * session获取user
	 * 更新数据库 session
	 * @return
	 */
	@RequestMapping(value="ChangeP",method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> ChangeP(@RequestParam String password){
		Map<String,Object> modelMap=new HashMap<>();
		Users record = new Users();
		try {
			record=(Users) session.getAttribute("user");
			if(password!=null && ""!=password) 
			{
				System.out.println("新密码："+password);
				record.setPassword(password);
				userService.updateByPrimaryKeySelective(record);
				session.setAttribute("user", record);
				modelMap.put("message","success");
			}else 
			{
				modelMap.put("message","新密码问题");
			}
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success",false);
			modelMap.put("errMsg",e.toString());
		}
		return modelMap;
	}
	/**
	 * 修改信息
	 * session获取user
	 * 更新数据库 session
	 * @return
	 */
	@RequestMapping(value="ChangeInfo",method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> ChangeInfo(@RequestParam String username){
		Map<String,Object> modelMap=new HashMap<>();
		Users record = new Users();
		try {
			record = (Users) session.getAttribute("user");
			if(record!=null && username!=null) 
			{
				System.out.println("新名字："+username);
				record.setUsername(username);
				userService.updateByPrimaryKeySelective(record);
				session.setAttribute("user", record);
				modelMap.put("message", "success");
			}else 
			{ 
				modelMap.put("message", "未登陆或未能接收信息");
			}
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success",false);
			modelMap.put("errMsg",e.toString());
		}
		return modelMap;
	}
	/**
	 * 获取个人信息
	 * @return
	 */
	@RequestMapping(value="Info",method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> Info(){
		Map<String,Object> modelMap=new HashMap<>();
		if(IfLoginutil.IfLogin(session)) 
		{
			try {
				System.out.println("个人信息"); 
				modelMap.put("user", (Users)session.getAttribute("user"));
			} catch (Exception e) {
				e.printStackTrace();
				modelMap.put("success",false);
				modelMap.put("errMsg",e.toString());
			}
		}else 
		{
			modelMap.put("message", "false");
		}
		return modelMap;
	}
	/**
	 * 电话验证登录
	 * 查询电话以及状态
	 * @return
	 */
	@RequestMapping(value="TelLogin",method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> TelLogin(@RequestParam String tel){
		Map<String,Object> modelMap=new HashMap<>();
		Users record = new Users();
		try {
			System.out.println("电话登陆"); 
			record.setTel(tel);
			record = userService.selectByTWithStatus(record);
			if(record!=null) 
			{
				session.setAttribute("user", record);
				System.out.println((Users)session.getAttribute("user"));
				modelMap.put("message", "success");
			}else 
			{
				modelMap.put("message", "错误的电话！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success",false);
			modelMap.put("errMsg",e.toString());
		}
		return modelMap;
	}
	/**
	 * 邮箱验证登录
	 * 查询电话以及状态
	 * @return
	 */
	@RequestMapping(value="EmailLogin",method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> EmailLogin(@RequestParam String email){
		Map<String,Object> modelMap=new HashMap<>();
		Users record = new Users();
		try {
			System.out.println("邮箱登陆"); 
			record = userService.selectByEWithStatus(record);
			if(record!=null) 
			{
				session.setAttribute("user", record);
				System.out.println((Users)session.getAttribute("user"));
				modelMap.put("message", "success");
			}else 
			{
				modelMap.put("message", "错误的邮箱！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success",false);
			modelMap.put("errMsg",e.toString());
		}
		return modelMap;
	}
}
