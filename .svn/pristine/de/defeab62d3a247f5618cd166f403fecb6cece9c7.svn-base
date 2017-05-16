package com.uncleserver.dao;

import com.uncleserver.model.AuntPointProgress;


public interface AuntPointProgressMapper {
    int deleteByPrimaryKey(Integer recordid);

    int insert(AuntPointProgress record);

    int insertSelective(AuntPointProgress record);

    AuntPointProgress selectByPrimaryKey(Integer recordid);

    int updateByPrimaryKeySelective(AuntPointProgress record);

    int updateByPrimaryKey(AuntPointProgress record);
    
    AuntPointProgress selectByAuntid(Integer auntid);
    
    AuntPointProgress selectByAuntidToday(Integer userid);
}