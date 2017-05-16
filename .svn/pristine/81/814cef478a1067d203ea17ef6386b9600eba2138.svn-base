package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.BalanceRecord;
import com.uncleserver.model.PointRecord;

public interface PointRecordMapper {
    int deleteByPrimaryKey(Integer recordid);

    int insert(PointRecord record);

    int insertSelective(PointRecord record);

    PointRecord selectByPrimaryKey(Integer recordid);

    int updateByPrimaryKeySelective(PointRecord record);

    int updateByPrimaryKey(PointRecord record);
    
    List<PointRecord> selectByPage(Integer offset,Integer pageSize, int userid);
}