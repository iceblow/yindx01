package com.uncleserver.dao;

import java.util.List;
import java.util.Map;

import com.uncleserver.model.HomeBanner;

public interface HomeBannerMapper {
    int deleteByPrimaryKey(Integer bannerid);

    int insert(HomeBanner record);

    int insertSelective(HomeBanner record);

    HomeBanner selectByPrimaryKey(Integer bannerid);

    int updateByPrimaryKeySelective(HomeBanner record);

    int updateByPrimaryKey(HomeBanner record);
    
    List<HomeBanner> selectByCity(String city);
    
    List<HomeBanner> getWithLimit(Integer start, Integer limit);
    
    long getCount(Map<String,Object> obj);
    
    int getMaxBanner();
    
    long selectCounMaxSort(int num);
    
    List<HomeBanner> getHomeBannerByCity(Map<String,Object> obj);
}