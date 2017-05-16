package com.uncleserver.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uncleserver.model.CategoryCity;

public interface CategoryCityMapper {
    int deleteByPrimaryKey(Integer dataid);

    int insert(CategoryCity record);

    int insertSelective(CategoryCity record);

    CategoryCity selectByPrimaryKey(Integer dataid);

    int updateByPrimaryKeySelective(CategoryCity record);

    int updateByPrimaryKey(CategoryCity record);
    
    Long selectAreaCidCount(String area,int categoryid);
     
    List<CategoryCity> selectByCity(String area);

		List<CategoryCity> getServiceArea(@Param(value="startPage")int startPage,@Param(value="rows") int rows,@Param(value="city") String city);

	long getServiceAreaCount(@Param(value="city")String city);
}