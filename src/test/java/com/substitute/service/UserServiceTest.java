package com.substitute.service;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.substitute.BaseTest;
import com.substitute.entity.Users;

public class UserServiceTest extends BaseTest{
	@Autowired
	private UserService userService;
	
	@Test
	public void testSelectAllUser() {
		List<Users> userlist=userService.getUserList();
		System.out.println(userlist+"123456");
		assertEquals("ZhangMan", userlist.get(0).getUsername());
	}
	@Test
	public void testRegister() 
	{
		Users record=new Users();
//		record.setUsername("test02");
//		record.setTel("15149039299");
		record.setEmail("lilei.kuge@gmail.com");
		record.setPassword("123");
		if(record.getUsername()!=null && userService.selectByUsername(record)==null) 
		{
			userService.insertSelective(record);
		}else if(record.getTel()!=null && userService.selectByTel(record)==null) 
		{
			userService.insertSelective(record);
		}else if(record.getEmail()!=null && userService.selectByEmail(record)==null) 
		{
			userService.insertSelective(record);
		}
		else
		{ 
			System.out.println("错");
		}
	}
}
