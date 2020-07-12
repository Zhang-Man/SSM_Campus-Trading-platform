package com.substitute.dao;

import com.substitute.entity.Activities;
import com.substitute.entity.ActivitiesExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ActivitiesDao {
    int countByExample(ActivitiesExample example);

    int deleteByExample(ActivitiesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Activities record);

    int insertSelective(Activities record);

    List<Activities> selectByExample(ActivitiesExample example);

    Activities selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Activities record, @Param("example") ActivitiesExample example);

    int updateByExample(@Param("record") Activities record, @Param("example") ActivitiesExample example);

    int updateByPrimaryKeySelective(Activities record);

    int updateByPrimaryKey(Activities record);
    
    List<Activities> SelectAllActivities(@Param(value="map")Map paramMap,@Param(value="types")List paramMap2);
    
    List<Activities> SelectAllActivitiesWithStatus();
    
    List<Activities> SelectAllActivitiesWithSAndV();
    
    List<Activities> SelectAllActivitiesWithSAndNV();
    
    List<Activities> SelectAllMyActivities(Activities record);
    
    List<Activities> SelectMyActivitiesWithStatus(Activities record);
    
    List<Activities> SelectMyAcceptActivitiesWithStatus(Activities record);
}