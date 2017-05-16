package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.HomeItem;

public interface HomeItemMapper {
    int deleteByPrimaryKey(Integer dataid);

    int insert(HomeItem record);

    int insertSelective(HomeItem record);

    HomeItem selectByPrimaryKey(Integer dataid);

    int updateByPrimaryKeySelective(HomeItem record);

    int updateByPrimaryKey(HomeItem record);
    
    List<HomeItem> selectByCityAndType(String city, Integer type);

	List<HomeItem> selectByCityAndPage(String cityName, Integer type,
			int startPage, int rows);
	
	List<HomeItem> selectByPage(int startPage, int rows);

	long managePageItemCount(String cityName, Integer type);
	
	long selectByPageCount();
	
	Integer selectMaxSort(String cityName,Integer type);
    
    Integer selectMinSort(String cityName,Integer type);
    
    HomeItem selectBeforeSort(Integer sort,String cityName,Integer type);
    
    HomeItem selectAfterSort(Integer sort,String cityName,Integer type);

	
}