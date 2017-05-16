package com.uncleserver.dao;

import com.uncleserver.model.AuntMessageSys;

public interface AuntMessageSysMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuntMessageSys record);

    int insertSelective(AuntMessageSys record);

    AuntMessageSys selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuntMessageSys record);

    int updateByPrimaryKey(AuntMessageSys record);
}