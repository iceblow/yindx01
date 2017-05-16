package com.uncleserver.controller.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.controller.BaseController;
import com.uncleserver.model.CategorySecond;
import com.uncleserver.model.Company;
import com.uncleserver.model.Coupon;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;
import com.uncleserver.service.manage.ManageCouponService;

@Controller
@RequestMapping("/manage/coupon")
public class ManageCouponController extends BaseController {
	@Resource
	private ManageCouponService manageCouponService;

	@RequestMapping("/couponlist")
	public ModelAndView couponlist(HttpSession session, String page) {
		ModelAndView mav = new ModelAndView();
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		return mav;
	}

	@RequestMapping("/getCouponList")
	@ResponseBody
	QueryResult<Coupon> getCouponList(HttpSession session, PQuery pquery, String rangetype, String granttype,
			String delstate) {
		session.setAttribute("page", pquery.getPage());
		QueryResult<Coupon> result = null;
		try {
			result = manageCouponService.getCouponList(pquery, rangetype, granttype, delstate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/getCompanyName")
	@ResponseBody
	public String getCompanyName(String companyid) {
		String result;
		result = manageCouponService.getCompanyName(companyid);
		return result;
	}

	@RequestMapping("/getCompanyList")
	public void getCompanyList(HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Company> list = manageCouponService.getCompanyList();
		map.put("list", list);
		CommonUtils.setMaptoJson(response, map);
	}

	@RequestMapping("/getcategorysecond")
	public void getcategorysecond(HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<CategorySecond> list = manageCouponService.getcategorysecond();
		map.put("list", list);
		CommonUtils.setMaptoJson(response, map);
	}

	@RequestMapping("/delcoupon")
	@ResponseBody
	public int delcoupon(Integer couponid) {
		int result;
		result = manageCouponService.delcoupon(couponid);
		return result;
	}

	@RequestMapping("/addcoupon")
	public ModelAndView addcoupon(HttpServletRequest req, HttpSession session, String page) {
		ModelAndView mav = new ModelAndView();
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		return mav;
	}

	@RequestMapping("/addCouponData")
	@ResponseBody
	public void addCouponData(String picId, String coupon_condition, String discount, String rangetype,
			String companyid, String categoryid, String granttype, String stime, String etime, String use_stime,
			String use_etime, String lastday, String totalcount, String maxreceive) {
		manageCouponService.addCouponData(picId, coupon_condition, discount, rangetype, companyid, categoryid,
				granttype, stime, etime, use_stime, use_etime, lastday, totalcount, maxreceive);
	}

	@RequestMapping("/editCoupon")
	@ResponseBody
	public ModelAndView editCoupon(String couponid) {
		ModelAndView modelAndView = new ModelAndView();
		Coupon coupon = manageCouponService.selectByCouponid(couponid);
		modelAndView.addObject("coupon", coupon);
		modelAndView.setViewName("manage/coupon/editCoupon");
		return modelAndView;
	}

	@RequestMapping("/editCouponData")
	@ResponseBody
	public void editCouponData(String couponid, String picId, String coupon_condition, String discount,
			String totalcount, String maxreceive) {
		manageCouponService.editCouponData(couponid, picId, coupon_condition, discount, totalcount, maxreceive);
	}
}
