package com.uncleserver.dao;

import com.uncleserver.model.AuntCard;

public interface AuntCardMapper {
    int deleteByPrimaryKey(Integer cardid);

    int insert(AuntCard record);

    int insertSelective(AuntCard record);

    AuntCard selectByPrimaryKey(Integer cardid);

    int updateByPrimaryKeySelective(AuntCard record);

    int updateByPrimaryKey(AuntCard record);
    
    int deleteByAuntid(Integer auntid,Integer userType);
    
    AuntCard selectByAuntidAndType(Integer auntid,Integer userType);
    
}