package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.AuntBanner;
import com.uncleserver.model.AuntPush;
import com.uncleserver.model.UserPush;

public interface AuntPushMapper {
	int deleteByPrimaryKey(Integer dataid);

	int insert(AuntPush record);

	int insertSelective(AuntPush record);

	AuntPush selectByPrimaryKey(Integer dataid);

	int updateByPrimaryKeySelective(AuntPush record);

	int updateByPrimaryKey(AuntPush record);

	AuntPush selectByUserId(int useridInt, short usertypeShort);
	
	AuntPush selectByKeyAndType(String pushkey,short devicetypeShort);
	
	List<AuntPush> getWithLimit(Integer start, Integer limit);
	
	List<AuntPush> selectByUserType(Integer userType, Integer isaccept);
	
	Integer getCount();
	
}