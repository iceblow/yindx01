package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.AuntMessageDetail;

public interface AuntMessageDetailMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(AuntMessageDetail record);

	int insertSelective(AuntMessageDetail record);

	AuntMessageDetail selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(AuntMessageDetail record);

	int updateByPrimaryKey(AuntMessageDetail record);

	void deleteByMessageid(Integer messageid);

	List<AuntMessageDetail> selectByPage(Integer messageid, int offest, int pageSize);

	Long selectPageCount(int messageid);

	AuntMessageDetail selectLastByMid(int messageid);
}