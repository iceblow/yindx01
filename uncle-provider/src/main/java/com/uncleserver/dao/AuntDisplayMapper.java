package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.AuntDisplay;

public interface AuntDisplayMapper {
    int deleteByPrimaryKey(Integer dataid);

    int insert(AuntDisplay record);

    int insertSelective(AuntDisplay record);

    AuntDisplay selectByPrimaryKey(Integer dataid);

    int updateByPrimaryKeySelective(AuntDisplay record);

    int updateByPrimaryKey(AuntDisplay record);
    
    int deleteByAuntid(Integer auntid);
    
    List<AuntDisplay> selectByAuntId(int  auntid);
}