package com.uncleserver.dao;

import com.uncleserver.model.VCode;
import com.uncleserver.model.VCodeAunt;

public interface VCodeAuntMapper {
    int deleteByPrimaryKey(Integer codeid);

    int insert(VCodeAunt record);

    int insertSelective(VCodeAunt record);

    VCodeAunt selectByPrimaryKey(Integer codeid);

    int updateByPrimaryKeySelective(VCodeAunt record);

    int updateByPrimaryKey(VCodeAunt record);
    
    Long setectTodaySendCount(String phone);//查询手机号今天已经发送的验证码次数
    
    VCodeAunt selectLastVcodeByPhoneAndType(String phone,int typeInt,int userType);//根据手机号和类型查询最新的一条验证码
    
}