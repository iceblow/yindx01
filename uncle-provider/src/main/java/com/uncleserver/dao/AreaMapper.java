package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.Area;


public interface AreaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);
    
    List<Area> selectByState(short state);
   
    Area selectByName(String name);

	List<Area> selectByCityid(Integer id);
}