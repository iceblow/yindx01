package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.Tutorials;

public interface TutorialsMapper {
    int deleteByPrimaryKey(Integer dataid);

    int insert(Tutorials record);

    int insertSelective(Tutorials record);

    Tutorials selectByPrimaryKey(Integer dataid);

    int updateByPrimaryKeySelective(Tutorials record);

    int updateByPrimaryKey(Tutorials record);
    
    List<Tutorials> selectByPage(Integer offset,Integer pageSize);
}