package com.uncleserver.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uncleserver.model.CouponUser;

public interface CouponUserMapper {
	int deleteByPrimaryKey(Integer dataid);

	int insert(CouponUser record);

	int insertSelective(CouponUser record);

	CouponUser selectByPrimaryKey(Integer dataid);

	int updateByPrimaryKeySelective(CouponUser record);

	int updateByPrimaryKey(CouponUser record);

	List<CouponUser> selectCouponById(Integer userid, String dateStart, String dateEnd);

	Long selectByUserid(Integer couponid, Integer userid);
	
	CouponUser selectCouponByUserid(Integer couponid, Integer userid);

	List<CouponUser> selectCanUseCoupon(@Param("priceCondition") float priceCondition, @Param("cid") int cid,
			@Param("companyid") int companyid, @Param("userid") int userid);

}