package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.Cities;

public interface CitiesMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Cities record);

	int insertSelective(Cities record);

	Cities selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Cities record);

	int updateByPrimaryKey(Cities record);

	List<Cities> selectByState(int state);

	List<Cities> selectAll();
	
	List<Cities> selectByProvienceid(Integer provienceid);
	
	Cities selectCityByChildName(String areaname);
	
	Cities selectCityByCityNane(String name);
	
	Cities selectAreaByChildName(String name);
	
	Cities selectCityByChildId(int id);
	
}