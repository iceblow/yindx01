package com.uncleserver.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uncleserver.model.Category;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer dataid);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer dataid);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
    
    List<Category> selectAllCategory();

	List<Category> selectCategory(@Param(value="startPage")int startPage, @Param(value="rows")int rows);

	long selectCategoryCount();

	List<Category> selectAllCategoryTo();

	List<Category> selectByName(@Param(value="name")String name);
}