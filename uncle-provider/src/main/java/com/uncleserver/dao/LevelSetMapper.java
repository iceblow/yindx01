package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.LevelSet;
import com.uncleserver.model.MessageDetail;

public interface LevelSetMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(LevelSet record);

	int insertSelective(LevelSet record);

	LevelSet selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(LevelSet record);

	int updateByPrimaryKey(LevelSet record);

	LevelSet setlectSetByLevel(short level);// 根据等级查询配置

	LevelSet setlectSetByPoint(int point);// 根据积分查询出来该积分段所属于的积分
	
	List<LevelSet> getAllLevelSet();
	
	long getCount();
}