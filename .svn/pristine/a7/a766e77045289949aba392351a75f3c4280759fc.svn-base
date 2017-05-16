package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.PickAddress;

public interface PickAddressMapper {
	int deleteByPrimaryKey(Integer addressid);

	int insert(PickAddress record);

	int insertSelective(PickAddress record);

	PickAddress selectByPrimaryKey(Integer addressid);

	int updateByPrimaryKeySelective(PickAddress record);

	int updateByPrimaryKey(PickAddress record);

	int updateUserDefault(int userid);// 更新用户下的所有服务地址为非默认

	List<PickAddress> getUserAllPickAddress(int userid);// 获取用户的所有服务地址
}