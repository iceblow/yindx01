package com.uncleserver.dao;

import java.util.List;
import java.util.Map;

import com.uncleserver.model.AuntBanner;

public interface AuntBannerMapper {
	int deleteByPrimaryKey(Integer bannerid);

	int insert(AuntBanner record);

	int insertSelective(AuntBanner record);

	AuntBanner selectByPrimaryKey(Integer bannerid);

	int updateByPrimaryKeySelective(AuntBanner record);

	int updateByPrimaryKey(AuntBanner record);

	List<AuntBanner> selectBannerList();
	
	List<AuntBanner> getWithLimit(Integer start, Integer limit);
	
	List<AuntBanner> getAuntBannerByTypeAndCon(Map<String,Object> map);
	
	long getCount(Map<String,Object> map);
	
	long selectCounMaxSort(int num);
	
	 int getMaxBanner();
}