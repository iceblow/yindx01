package com.uncleserver.dao;

import com.uncleserver.model.UserExtra;

public interface UserExtraMapper {
    int deleteByPrimaryKey(Integer dataid);

    int insert(UserExtra record);

    int insertSelective(UserExtra record);

    UserExtra selectByPrimaryKey(Integer dataid);

    int updateByPrimaryKeySelective(UserExtra record);

    int updateByPrimaryKey(UserExtra record);
    
    UserExtra selectByUserId(int userid);
    
    UserExtra selectByAccesstoken(String accesstoken);
}