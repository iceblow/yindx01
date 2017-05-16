package com.uncleserver.dao;

import java.util.List;
import java.util.Map;

import com.uncleserver.model.AuntSignSet;


public interface AuntSignSetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuntSignSet record);

    int insertSelective(AuntSignSet record);

    AuntSignSet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuntSignSet record);

    int updateByPrimaryKey(AuntSignSet record);
    
    AuntSignSet selectFitSet(int continuityDay);// 根据连续签到的天数查询适合的签到配置
    
    List<AuntSignSet> getSignSetQuery(Map<String,Object> map);//根据条件查询签到配置信息
	
	Long getCount(Map<String,Object> map);
}