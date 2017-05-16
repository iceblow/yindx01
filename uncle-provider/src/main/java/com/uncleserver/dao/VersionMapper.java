package com.uncleserver.dao;

import java.util.List;
import java.util.Map;

import com.uncleserver.model.Version;
import com.uncleserver.model.Result.PQuery;

public interface VersionMapper {
	int deleteByPrimaryKey(Integer versionid);

	int insert(Version record);

	int insertSelective(Version record);

	Version selectByPrimaryKey(Integer versionid);

	int updateByPrimaryKeySelective(Version record);

	int updateByPrimaryKeyWithBLOBs(Version record);

	int updateByPrimaryKey(Version record);

	Version selectLastVersionByInfo(String version, int apptype, int platformtype);
	
	List<Version> getVsersion(Map<String,Object> map);
	
	long getCount(Map<String,Object> map);
}