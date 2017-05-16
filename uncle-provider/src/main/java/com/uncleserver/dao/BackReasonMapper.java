package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.BackReason;

public interface BackReasonMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(BackReason record);

	int insertSelective(BackReason record);

	BackReason selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(BackReason record);

	int updateByPrimaryKey(BackReason record);

	List<BackReason> selectListByType(short type);
}