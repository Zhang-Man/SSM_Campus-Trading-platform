package com.substitute.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.substitute.entity.Vip;
import com.substitute.entity.VipExample;

public interface VipDao {
    int countByExample(VipExample example);

    int deleteByExample(VipExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Vip record);

    int insertSelective(Vip record);

    List<Vip> selectByExample(VipExample example);

    Vip selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Vip record, @Param("example") VipExample example);

    int updateByExample(@Param("record") Vip record, @Param("example") VipExample example);

    int updateByPrimaryKeySelective(Vip record);

    int updateByPrimaryKey(Vip record);
    
    Vip selectByusersid(Integer id);
    
    List<Vip> selectAllWithStatus();
}