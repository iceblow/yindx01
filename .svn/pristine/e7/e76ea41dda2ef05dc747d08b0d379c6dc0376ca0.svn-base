package com.uncleserver.dao;

import java.util.List;
import java.util.Map;

import com.uncleserver.model.SignSet;

public interface SignSetMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(SignSet record);

	int insertSelective(SignSet record);

	SignSet selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SignSet record);

	int updateByPrimaryKey(SignSet record);

	SignSet selectFitSet(int continuityDay);// 根据连续签到的天数查询适合的签到配置
	
	List<SignSet> getSignSetQuery(Map<String,Object> map);//根据条件查询签到配置信息
	
	Long getCount(Map<String,Object> map);
}