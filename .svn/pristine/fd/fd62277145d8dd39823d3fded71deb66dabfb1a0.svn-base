package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.Config;

public interface ConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKeyWithBLOBs(Config record);

    int updateByPrimaryKey(Config record);
    
    Config selectConfigByKey(String keyname);
    
    List<Config> getConfigs();
    
    long getCount();
}