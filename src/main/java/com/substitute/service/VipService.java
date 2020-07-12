package com.substitute.service;

import java.util.List;

import com.substitute.entity.Users;
import com.substitute.entity.Vip;

public interface VipService {
	public  int insertSelective(Vip record);
	
	public Vip selectByusersid(Integer id);
	
	public int updateByPrimaryKey(Vip record);
	
	public List<Vip> selectAllWithStatus();
	
	public int updateByPrimaryKeySelective(Vip record);
}
