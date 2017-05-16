package com.uncleserver.dao;

import com.uncleserver.model.AuntWatch;

public interface AuntWatchMapper {
    int deleteByPrimaryKey(Integer dataid);

    int insert(AuntWatch record);

    int insertSelective(AuntWatch record);

    AuntWatch selectByPrimaryKey(Integer dataid);

    int updateByPrimaryKeySelective(AuntWatch record);

    int updateByPrimaryKey(AuntWatch record);
    
    AuntWatch selectByTutorialid(Integer userid,Integer tutorialid,Integer user_type);
}