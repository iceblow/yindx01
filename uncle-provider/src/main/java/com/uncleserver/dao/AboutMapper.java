package com.uncleserver.dao;

import com.uncleserver.model.About;

public interface AboutMapper {
    int deleteByPrimaryKey(Integer dataid);

    int insert(About record);

    int insertSelective(About record);

    About selectByPrimaryKey(Integer dataid);

    int updateByPrimaryKeySelective(About record);

    int updateByPrimaryKey(About record);
    
    About selectByAppType(short type);
}