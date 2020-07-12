package com.substitute.dao;

import com.substitute.entity.Oam;
import com.substitute.entity.OamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OamDao {
    int countByExample(OamExample example);

    int deleteByExample(OamExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Oam record);

    int insertSelective(Oam record);

    List<Oam> selectByExample(OamExample example);

    Oam selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Oam record, @Param("example") OamExample example);

    int updateByExample(@Param("record") Oam record, @Param("example") OamExample example);

    int updateByPrimaryKeySelective(Oam record);

    int updateByPrimaryKey(Oam record);
}