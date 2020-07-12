package com.substitute.service;

import java.util.List;

import com.substitute.entity.Users;

public interface UserService {
	public List<Users> getUserList();
	
	public Users SelectUserByUandP(Users record);
	
	public Users selectByUsername(Users record);
	
	public Users selectByTel(Users record);
    
	public Users selectByEmail(Users record);
	
	public Users selectByPrimaryKey(Integer id);
	
	public int insertSelective(Users record);
	
	public int updateByPrimaryKeySelective(Users record);
	
	public Users selectByTWithStatus(Users record);
	
	public Users selectByEWithStatus(Users record);
}
