package com.substitute.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.substitute.BaseTest;
import com.substitute.entity.Users;

public class UserDaoTest extends BaseTest{
@Autowired
private UserDao userDao;

@Test
public void testSelectAllUser() {
	List<Users> userList=userDao.SelectAllUser();
	System.out.println(userList);
	assertEquals(6, userList.size());
}
@Test
public void updateByPrimaryKeySelective() {
	Users demo = new Users();
	demo.setUsername("lilei");
	demo = userDao.selectByUsername(demo);
	System.out.println(demo.getPassword());
	demo.setPassword("1234");
	userDao.updateByPrimaryKeySelective(demo);
	System.out.println(demo.getPassword());
}
}
