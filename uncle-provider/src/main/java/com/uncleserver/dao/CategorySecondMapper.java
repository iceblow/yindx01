package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.CategorySecond;

public interface CategorySecondMapper {
    int deleteByPrimaryKey(Integer dataid);

    int insert(CategorySecond record);

    int insertSelective(CategorySecond record);

    CategorySecond selectByPrimaryKey(Integer dataid);

    int updateByPrimaryKeySelective(CategorySecond record);

    int updateByPrimaryKey(CategorySecond record);
    
    List<CategorySecond> selectByFid(Integer categoryid);

	List<CategorySecond> selectAllCategorySecond();
}