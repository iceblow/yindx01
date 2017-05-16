package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.AuntLevelSet;


public interface AuntLevelSetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuntLevelSet record);

    int insertSelective(AuntLevelSet record);

    AuntLevelSet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuntLevelSet record);

    int updateByPrimaryKey(AuntLevelSet record);
    
	AuntLevelSet setlectSetByLevel(short level);// 根据等级查询配置

	AuntLevelSet setlectSetByPoint(int point);// 根据积分查询出来该积分段所属于的积分
	
	List<AuntLevelSet> getAllLevelSet();
	
	long getCount();
}