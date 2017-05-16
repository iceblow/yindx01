package com.uncleserver.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uncleserver.model.CategorySecond;
import com.uncleserver.model.Company;
import com.uncleserver.model.Coupon;

public interface CouponMapper {
    int deleteByPrimaryKey(Integer couponid);

    int insert(Coupon record);

    int insertSelective(Coupon record);

    Coupon selectByPrimaryKey(Integer couponid);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKey(Coupon record);
    
    Coupon selectByPrimaryKeyOrNum(String couponid);
    
    List<Coupon> selectCouponList(@Param(value="startPage")int startPage, @Param(value="rows")int rows,
    		@Param(value="rangetype")Integer rangetype,@Param(value="granttype")Integer granttype,@Param(value="delstate")Integer delstate);
    
    long selectCountCoupon(@Param(value="rangetype")Integer rangetype,@Param(value="granttype")Integer granttype,@Param(value="delstate")Integer delstate);
    
    String getCompanyName(Integer companyid);
    
    List<Company> getCompanyList();
    
    List<CategorySecond> getcategorysecond();
    
    
    
}