package com.uncleserver.dao;

import com.uncleserver.model.AuntLike;

public interface AuntLikeMapper {
	int deleteByPrimaryKey(Integer dataid);

	int insert(AuntLike record);

	int insertSelective(AuntLike record);

	AuntLike selectByPrimaryKey(Integer dataid);

	int updateByPrimaryKeySelective(AuntLike record);

	int updateByPrimaryKey(AuntLike record);

	AuntLike selectLikeData(int userid, int auntid);
	
	Long selectLikeCount(int auntid);
}