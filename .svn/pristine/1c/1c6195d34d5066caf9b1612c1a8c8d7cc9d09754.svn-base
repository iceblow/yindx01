package com.uncleserver.dao;

import com.uncleserver.model.VCode;

public interface VCodeMapper {
    int deleteByPrimaryKey(Integer codeid);

    int insert(VCode record);

    int insertSelective(VCode record);

    VCode selectByPrimaryKey(Integer codeid);

    int updateByPrimaryKeySelective(VCode record);

    int updateByPrimaryKey(VCode record);
    
    Long setectTodaySendCount(String phone);//查询手机号今天已经发送的验证码次数
    
    VCode selectLastVcodeByPhoneAndType(String phone,int typeInt);//根据手机号和类型查询最新的一条验证码
}