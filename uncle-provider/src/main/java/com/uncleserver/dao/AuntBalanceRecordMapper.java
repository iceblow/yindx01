package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.AuntBalanceRecord;

public interface AuntBalanceRecordMapper {
    int deleteByPrimaryKey(Integer recordid);

    int insert(AuntBalanceRecord record);

    int insertSelective(AuntBalanceRecord record);

    AuntBalanceRecord selectByPrimaryKey(Integer recordid);

    int updateByPrimaryKeySelective(AuntBalanceRecord record);

    int updateByPrimaryKey(AuntBalanceRecord record);
    
    List<AuntBalanceRecord> selectRecordByPage(int userid, int usertype,
			int pageoffset, int pagesize);
    
    Long selectRecordByPageCount(int userid, int usertype);
}