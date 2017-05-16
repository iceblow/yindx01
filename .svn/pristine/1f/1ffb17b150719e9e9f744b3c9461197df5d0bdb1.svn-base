package com.uncleserver.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.uncleserver.model.OrderPool;

public interface OrderPoolMapper {
	int deleteByPrimaryKey(Integer orderid);

	int insert(OrderPool record);

	int insertSelective(OrderPool record);

	OrderPool selectByPrimaryKey(Integer orderid);

	int updateByPrimaryKeySelective(OrderPool record);

	int updateByPrimaryKey(OrderPool record);

	List<OrderPool> selectListByUserIdAndType(int userid, int usertype);
	
	List<OrderPool> selectListByOrderUser(int orderUserid, int state);

	int deleteByUserOrderId(int orderid_user);

	int deleteAuntOrCompanyData(int orderid_user, int userid, int usertype);

	List<OrderPool> getPendingOrder(@Param(value="startPage")int startPage, @Param(value="rows")int rows,@Param(value="companyid") int companyid);

	long getPendingOrderCount(@Param(value="companyid") int companyid);
	
	List<OrderPool> selectListByUserOrderId(int orderid);
	
	int deleteOrderPoolDate(int orderid);

}