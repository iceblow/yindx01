package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.SignRecord;

public interface SignRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SignRecord record);

    int insertSelective(SignRecord record);

    SignRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SignRecord record);

    int updateByPrimaryKey(SignRecord record);
    
    Long selectWeekSignCount(String dateStrMonday,String dateStrSunday,int userid);//查询用户本周内签到的天数
    
    List<SignRecord> selectByMonth(String dateStartMonth,String dateEndMonth,int userid);
}