package com.uncleserver.service.manage.Impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.model.CategorySecond;
import com.uncleserver.model.Company;
import com.uncleserver.model.Coupon;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;
import com.uncleserver.service.api.Impl.BaseServiceImpl;
import com.uncleserver.service.manage.ManageCouponService;

@Service("manageCouponService")
public class ManageCouponServiceImpl extends BaseServiceImpl implements ManageCouponService{

	@Override
	public QueryResult<Coupon> getCouponList(PQuery pquery, String rangetype,String granttype,String delstate) {
		if(rangetype==null||rangetype.equals("")){
			rangetype = "-1";
		}
		if(granttype==null||granttype.equals("")){
			granttype = "-1";
		}
		if(delstate==null||delstate.equals("")){
			delstate = "-1";
		}
		List<Coupon> coupon = couponMapper.selectCouponList(pquery.getStartPage(), pquery.getRows(),Integer.parseInt(rangetype),Integer.parseInt(granttype),Integer.parseInt(delstate));
		long count = couponMapper.selectCountCoupon(Integer.parseInt(rangetype),Integer.parseInt(granttype),Integer.parseInt(delstate));
		QueryResult<Coupon> result = new QueryResult<>(coupon,count,pquery.getPage(), pquery.getRows());
		return result;
	}
	
	public String getCompanyName(String companyid){
		String name = couponMapper.getCompanyName(Integer.parseInt(companyid));
		return name;
	}
	
	public List<Company> getCompanyList(){
		List<Company> list = couponMapper.getCompanyList();
		return list;
	}
	
	public List<CategorySecond> getcategorysecond(){
		List<CategorySecond> list = couponMapper.getcategorysecond();
		return list;
	}
	
	public int delcoupon(Integer couponid){
		int a = couponMapper.deleteByPrimaryKey(couponid);
		return a;
	}
	
	public int addCouponData(String picId,String coupon_condition,String discount,String rangetype,
			String companyid,String categoryid,String granttype,String stime,String etime,String use_stime,
			String use_etime,String lastday,String totalcount,String maxreceive){
		Coupon coupon = new Coupon();
		if(rangetype==null||rangetype.equals("")){
			rangetype = "-1";
		}
		if(granttype==null||granttype.equals("")){
			granttype = "-1";
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		coupon.setPicid(CommonUtils.parseInt(picId, 0));
		coupon.setCouponCondition(new BigDecimal(coupon_condition));
		coupon.setDiscount(new BigDecimal(discount));
		coupon.setRangetype(Short.valueOf(rangetype));
		if(companyid==null){
			companyid = "0";
		}
		coupon.setCompanyid(Integer.parseInt(companyid));
		coupon.setCategoryid(Integer.parseInt(categoryid));
		coupon.setGranttype(Byte.valueOf(granttype));
		if (!CommonUtils.isEmptyString(stime) || !CommonUtils.isEmptyString(etime)||!CommonUtils.isEmptyString(use_stime)||!CommonUtils.isEmptyString(use_etime)) {
			try {
				coupon.setStime(format.parse(stime));
				coupon.setEtime(format.parse(etime));
				coupon.setUseEtime(format.parse(use_etime));
				coupon.setUseStime(format.parse(use_stime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		coupon.setLastDay(Integer.parseInt(lastday));
		coupon.setTotalcount(Integer.parseInt(totalcount));
		coupon.setDiscountmoney(new BigDecimal("0"));
		coupon.setMaxreceive(Byte.valueOf(maxreceive));
		coupon.setGetcount(0);
		coupon.setUsecount(0);
		coupon.setLastmoney(new BigDecimal(discount));
		coupon.setAddtime(new Date());
		coupon.setDelstate(false);
		Calendar cal = Calendar.getInstance();
		String d = null;
		if(cal.get(Calendar.DAY_OF_YEAR)<10){
			d = "00"+cal.get(Calendar.DAY_OF_YEAR);
		}
		if(cal.get(Calendar.DAY_OF_YEAR)>9&&cal.get(Calendar.DAY_OF_YEAR)<100){
			d = "0"+cal.get(Calendar.DAY_OF_YEAR);
		}
		String h = null;
		if(cal.get(Calendar.HOUR_OF_DAY)>9&&cal.get(Calendar.HOUR_OF_DAY)<100){
			h = "0"+cal.get(Calendar.HOUR_OF_DAY);
		}
		if(cal.get(Calendar.HOUR_OF_DAY)<10){
			h = "00"+cal.get(Calendar.HOUR_OF_DAY);
		}
		long n = couponMapper.selectCountCoupon(-1,-1,-1);
		coupon.setCouponNum("c"+cal.get(Calendar.YEAR)+d+h+n);
		return couponMapper.insertSelective(coupon);
	}
	
	public Coupon selectByCouponid(String couponid){
		Coupon coupon = couponMapper.selectByPrimaryKey(Integer.parseInt(couponid));
		return coupon;
	}

	public int editCouponData(String couponid,String picId,String coupon_condition,String discount,String totalcount,String maxreceive){
		Coupon coupon = couponMapper.selectByPrimaryKey(Integer.parseInt(couponid));
		coupon.setPicid(CommonUtils.parseInt(picId, 0));
		coupon.setCouponCondition(new BigDecimal(coupon_condition));
		coupon.setDiscount(new BigDecimal(discount));
		coupon.setTotalcount(Integer.parseInt(totalcount));
		coupon.setMaxreceive(Byte.valueOf(maxreceive));
		return couponMapper.updateByPrimaryKeySelective(coupon);
	}
}
