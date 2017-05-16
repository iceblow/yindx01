package com.uncleserver.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uncleserver.model.CategoryThird;

public interface CategoryThirdMapper {
    int deleteByPrimaryKey(Integer dataid);

    int insert(CategoryThird record);

    int insertSelective(CategoryThird record);

    CategoryThird selectByPrimaryKey(Integer dataid);
    
    List<CategoryThird> selectByCategoryid(Integer Categoryid);
    
    List<CategoryThird> selectByFid(Integer fid);

    int updateByPrimaryKeySelective(CategoryThird record);

    int updateByPrimaryKey(CategoryThird record);

	List<CategoryThird> getServiceTwoCategory(@Param(value="startPage")int startPage, @Param(value="rows")int rows,
			@Param(value="category")String category, @Param(value="threeCategory")String threeCategory);

	long getServiceTwoCategoryCount(@Param(value="category")String category, @Param(value="threeCategory")String threeCategory);

	List<CategoryThird> selectByCategoryIds(@Param(value="category")String category);

	List<CategoryThird> selectByNameTwo(@Param(value="name")String name, @Param(value="id")String id,@Param(value="threeCategoryTwo")String threeCategoryTwo);
}