package com.substitute.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.substitute.dao.UserDao;
import com.substitute.entity.Users;
import com.substitute.entity.UsersExample;
import com.substitute.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	@Transactional(rollbackFor = Exception.class)
	public List<Users> getUserList(){
		return userDao.SelectAllUser();
	}

	@Override
	public Users SelectUserByUandP(Users record) {
		return  userDao.selectByUAndPWithStatus(record);
	}

	@Override
	public Users selectByUsername(Users record) {
		return userDao.selectByUsername(record);
	}

	@Override
	public int insertSelective(Users record) {
		return userDao.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Users record) {
		return userDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public Users selectByTWithStatus(Users record) {
		return userDao.selectByTWithStatus(record);
	}

	@Override
	public Users selectByTel(Users record) {
		return userDao.selectByTel(record);
	}

	@Override
	public Users selectByEmail(Users record) {
		return userDao.selectByEmail(record);
	}

	@Override
	public Users selectByEWithStatus(Users record) {
		return userDao.selectByEWithStatus(record);
	}

	@Override
	public Users selectByPrimaryKey(Integer id) {
		return userDao.selectByPrimaryKey(id);
	}
}
