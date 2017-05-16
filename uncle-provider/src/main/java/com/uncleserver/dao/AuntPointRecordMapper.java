package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.AuntPointRecord;

public interface AuntPointRecordMapper {
    int deleteByPrimaryKey(Integer recordid);

    int insert(AuntPointRecord record);

    int insertSelective(AuntPointRecord record);

    AuntPointRecord selectByPrimaryKey(Integer recordid);

    int updateByPrimaryKeySelective(AuntPointRecord record);

    int updateByPrimaryKey(AuntPointRecord record);
    
    List<AuntPointRecord> selectByPage(Integer offest,Integer pageSize,Integer auntid);
    
}