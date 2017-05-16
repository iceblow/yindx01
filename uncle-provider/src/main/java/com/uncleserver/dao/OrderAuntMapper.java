package com.uncleserver.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uncleserver.model.OrderAunt;
import com.uncleserver.modelVo.OrderAuntModel;

public interface OrderAuntMapper {
    int deleteByPrimaryKey(Integer orderid);

    int insert(OrderAunt record);

    int insertSelective(OrderAunt record);

    OrderAunt selectByPrimaryKey(Integer orderid);

    int updateByPrimaryKeySelective(OrderAunt record);

    int updateByPrimaryKeyWithBLOBs(OrderAunt record);

    int updateByPrimaryKey(OrderAunt record);
    
    List<OrderAunt> selectByUserOrderId(Integer user_orderid);
    
    List<OrderAunt> selectRefuseByUserOrderId(Integer user_orderid);
    
    List<OrderAunt> selectAcceptByUserOrderId(Integer user_orderid);
    
    List<OrderAunt> selectSingleAuntAcceptByUserOrderId(Integer user_orderid);

	List<OrderAunt> selectByOrderidAndUserid(@Param("orderid")int orderid,@Param("userid") int userid);
	
	List<OrderAunt> selectByOrderidAndCompanyid(@Param("orderid")int orderid,@Param("userid") int userid);

	List<OrderAunt> getHistoryOrder(@Param(value="startPage")int startPage, @Param(value="rows")int rows,@Param(value="companyid") int companyid);

	List<OrderAunt> getInServiceOrder(@Param(value="startPage")int startPage, @Param(value="rows")int rows,@Param(value="companyid") int companyid);

	long getHistoryOrderCount(@Param(value="companyid") int companyid);

	long getInServiceOrderCount(@Param(value="companyid") int companyid);
	
	long getAuntInServiceOrderCount(@Param(value="dataid") Integer dataid,  @Param(value="user_type")Integer user_type);
	
	Long selectOrderNumCount();
	
	Long selectAuntMCount(Integer user_orderid);
	
	Long selectAuntWCount(Integer user_orderid);
	
	Long selectAuntCompleteOrder(Integer auntid);
	
	Long selectCompanyCompleteOrder(Integer companyid);
	
	long getCompanyAuntOrderCount(@Param(value="companyid") Integer companyid, @Param(value="startDate") String startDate, @Param(value="endDate") String endDate);
	
	Float getCompanyTotalPrice(@Param(value="companyid") Integer companyid, @Param(value="startDate") String startDate, @Param(value="endDate") String endDate);
	
	Float getTotalTimeByCompany(@Param(value="companyid") Integer companyid, @Param(value="startDate") String startDate, @Param(value="endDate") String endDate, @Param(value="type") String type);
	
	List<OrderAunt> getOrderList(@Param(value="auntid")Integer auntid, @Param(value="companyid")Integer companyid,@Param(value="startPage") Integer startPage,@Param(value="rows") Integer rows);
	
	List<OrderAunt> getIngOrder(@Param(value="auntid")Integer auntid, @Param(value="companyid")Integer companyid);
	
	List<OrderAuntModel> selectByCondition(@Param(value="auntid")Integer auntid, @Param(value="companyid")Integer companyid,@Param(value="serverid") Integer serverid
			,@Param(value="comment_type") Integer comment_type,@Param(value="offest") Integer offest,@Param(value="pageSize") Integer pageSize);

}