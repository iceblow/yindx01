package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.Proviences;

public interface ProviencesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Proviences record);

    int insertSelective(Proviences record);

    Proviences selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Proviences record);

    int updateByPrimaryKey(Proviences record);
    
    List<Proviences> selectAll();
}