package com.uncleserver.dao;

import java.util.List;




import com.uncleserver.model.HomeAd;
import com.uncleserver.model.HomeIcon;



public interface HomeAdMapper {
    int deleteByPrimaryKey(Integer dataid);

    int insert(HomeAd record);

    int insertSelective(HomeAd record);

    HomeAd selectByPrimaryKey(Integer dataid);

    int updateByPrimaryKeySelective(HomeAd record);

    int updateByPrimaryKey(HomeAd record);
    
    List<HomeAd> selectByCity(String city);
    
    List<HomeAd> getWithLimit(Integer start, Integer limit);
    
    Integer getCount();
    
    List<HomeAd> selectByCityAndPage(String city,Integer startPage,Integer rows);
    
    Long managePageIconCount(String city);
    
    Integer selectMaxSort(String cityName);
    
    Integer selectMinSort(String cityName);
    
    HomeAd selectBeforeSort(Integer sort,String cityName);
    
    HomeAd selectAfterSort(Integer sort,String cityName);
    
    
}