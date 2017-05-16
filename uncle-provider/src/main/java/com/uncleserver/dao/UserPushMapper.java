package com.uncleserver.dao;

import com.uncleserver.model.UserPush;

public interface UserPushMapper {
	int deleteByPrimaryKey(Integer dataid);

	int insert(UserPush record);

	int insertSelective(UserPush record);

	UserPush selectByPrimaryKey(Integer dataid);

	int updateByPrimaryKeySelective(UserPush record);

	int updateByPrimaryKey(UserPush record);

	UserPush selectByUserId(int userInt);

	UserPush selectByKeyAndType(String pushkey, short devicetypeShort);
}