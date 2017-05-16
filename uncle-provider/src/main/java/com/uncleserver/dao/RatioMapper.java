package com.uncleserver.dao;

import java.util.List;
import java.util.Map;

import com.uncleserver.model.QueryRatio;
import com.uncleserver.model.Ratio;

public interface RatioMapper {
    int deleteByPrimaryKey(Integer ratioid);

    int insert(Ratio record);

    int insertSelective(Ratio record);

    Ratio selectByPrimaryKey(Integer ratioid);
    
    Ratio selectByCity(String city,Integer categoryid,Integer server_type);

    int updateByPrimaryKeySelective(Ratio record);

    int updateByPrimaryKey(Ratio record);
    
    List<QueryRatio> getAllRatios(Map<String,Object> map);
    
    long getCount(Map<String,Object> map);
}