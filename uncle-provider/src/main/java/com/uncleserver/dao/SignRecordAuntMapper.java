package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.SignRecordAunt;

public interface SignRecordAuntMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SignRecordAunt record);

    int insertSelective(SignRecordAunt record);

    SignRecordAunt selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SignRecordAunt record);

    int updateByPrimaryKey(SignRecordAunt record);
    
    Long selectWeekSignCount(String dateStrMonday,String dateStrSunday,int userid);//查询用户本周内签到的天数
    
    List<SignRecordAunt> selectByMonth(String dateStartMonth,String dateEndMonth,int userid);
}