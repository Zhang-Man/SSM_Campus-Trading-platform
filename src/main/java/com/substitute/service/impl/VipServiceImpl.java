package com.substitute.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.substitute.dao.UserDao;
import com.substitute.dao.VipDao;
import com.substitute.entity.Users;
import com.substitute.entity.UsersExample;
import com.substitute.entity.Vip;
import com.substitute.service.UserService;
import com.substitute.service.VipService;

@Service
public class VipServiceImpl implements VipService {
	@Autowired
	private VipDao vipDao;

	@Override
	public int insertSelective(Vip record) {
		return vipDao.insertSelective(record);
	}

	@Override
	public Vip selectByusersid(Integer id) {
		return vipDao.selectByusersid(id);
	}

	@Override
	public int updateByPrimaryKey(Vip record) {
		return vipDao.updateByPrimaryKey(record);
	}

	@Override
	public List<Vip> selectAllWithStatus() {
		return vipDao.selectAllWithStatus();
	}

	@Override
	public int updateByPrimaryKeySelective(Vip record) {
		return vipDao.updateByPrimaryKeySelective(record);
	}
}
