package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.AuntExtra;
import com.uncleserver.model.UserExtra;

public interface AuntExtraMapper {
    int deleteByPrimaryKey(Integer dataid);

    int insert(AuntExtra record);

    int insertSelective(AuntExtra record);

    AuntExtra selectByPrimaryKey(Integer dataid);

    int updateByPrimaryKeySelective(AuntExtra record);

    int updateByPrimaryKey(AuntExtra record);
    
    List<AuntExtra> selectByDistance(double longitude,double latitude, double distance);
    
    List<AuntExtra> selectByDistanceWithoutLine(double longitude,double latitude, double distance);
    
    List<AuntExtra> selectByDistanceWithoutLineAndState(double longitude,double latitude, double distance);
    
    AuntExtra selectByAuntId(Integer auntid);
    
    AuntExtra selectByAccesstoken(String accesstoken);
}