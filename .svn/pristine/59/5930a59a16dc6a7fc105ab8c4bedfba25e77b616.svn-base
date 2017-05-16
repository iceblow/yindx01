package com.uncleserver.dao;

import com.uncleserver.model.Agreement;

public interface AgreementMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Agreement record);

	int insertSelective(Agreement record);

	Agreement selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Agreement record);

	int updateByPrimaryKeyWithBLOBs(Agreement record);

	int updateByPrimaryKey(Agreement record);

	Agreement selectByTypeAndRID(short typeShort, int relation_idInt);

	Agreement selectByType(short typeShort);
	
}