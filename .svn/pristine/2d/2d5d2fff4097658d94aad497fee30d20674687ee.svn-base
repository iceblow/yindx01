package com.uncleserver.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uncleserver.model.HomeIcon;

public interface HomeIconMapper {
    int deleteByPrimaryKey(Integer dataid);

    int insert(HomeIcon record);

    int insertSelective(HomeIcon record);

    HomeIcon selectByPrimaryKey(Integer dataid);

    int updateByPrimaryKeySelective(HomeIcon record);

    int updateByPrimaryKey(HomeIcon record);
    
    List<HomeIcon> selectByCity(String city);
    
    List<HomeIcon> selectByCityAndPage(String city,Integer startPage,Integer rows);
    
    List<HomeIcon> selectByPage(Integer startPage,Integer rows);
    
    Long managePageIconCount(String city);
    
    Long selectByPageCount();
    
    Integer selectMaxSort(String cityName);
    
    Integer selectMinSort(String cityName);
    
    HomeIcon selectBeforeSort(Integer sort,String cityName);
    
    HomeIcon selectAfterSort(Integer sort,String cityName);
    
    
}