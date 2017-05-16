package com.uncleserver.service.manage;

import java.util.List;

import com.uncleserver.model.CategorySecond;
import com.uncleserver.model.Company;
import com.uncleserver.model.Coupon;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;

public interface ManageCouponService {
	
	QueryResult<Coupon> getCouponList(PQuery pquery,String rangetype,String granttype,String delstate);
	
	String getCompanyName(String companyid);
	
	List<Company> getCompanyList();
	
	List<CategorySecond> getcategorysecond();
	
	int delcoupon(Integer couponid);
	
	int addCouponData(String picId,String coupon_condition,String discount,String rangetype,
			String companyid,String categoryid,String granttype,String stime,String etime,String use_stime,
			String use_etime,String lastday,String totalcount,String maxreceive);
	
	Coupon selectByCouponid(String couponid);
	
	int editCouponData(String couponid,String picId,String coupon_condition,String discount,String totalcount,String maxreceive);
}
