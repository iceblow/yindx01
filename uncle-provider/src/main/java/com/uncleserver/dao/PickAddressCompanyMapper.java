package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.PickAddressCompany;

public interface PickAddressCompanyMapper {
	int deleteByPrimaryKey(Integer addressid);

	int insert(PickAddressCompany record);

	int insertSelective(PickAddressCompany record);

	PickAddressCompany selectByPrimaryKey(Integer addressid);

	int updateByPrimaryKeySelective(PickAddressCompany record);

	int updateByPrimaryKey(PickAddressCompany record);

	int updateUserDefault(int userid);// 更新用户下的所有服务地址为非默认

	List<PickAddressCompany> getUserAllPickAddress(int userid);// 获取用户的所有服务地址
}