package com.substitute.dao;

import com.substitute.entity.Users;
import com.substitute.entity.UsersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    int countByExample(UsersExample example);

    int deleteByExample(UsersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByExample(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
    
    List<Users> SelectAllUser();
    
    Users selectByUAndPWithStatus(Users record);
    
    Users selectByUsername(Users record);
    
    Users selectByTel(Users record);
    
    Users selectByEmail(Users record);
    
    Users selectByTWithStatus(Users record);
    
    Users selectByEWithStatus(Users record);
    
}