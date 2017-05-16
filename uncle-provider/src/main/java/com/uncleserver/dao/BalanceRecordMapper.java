package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.BalanceRecord;

public interface BalanceRecordMapper {
    int deleteByPrimaryKey(Integer recordid);

    int insert(BalanceRecord record);

    int insertSelective(BalanceRecord record);

    BalanceRecord selectByPrimaryKey(Integer recordid);

    int updateByPrimaryKeySelective(BalanceRecord record);

    int updateByPrimaryKey(BalanceRecord record);
    
    List<BalanceRecord> selectByPage(Integer offset,Integer pageSize, int userid);
}