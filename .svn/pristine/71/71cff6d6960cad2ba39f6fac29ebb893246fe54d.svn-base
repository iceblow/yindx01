package com.uncleserver.controller.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.controller.BaseController;
import com.uncleserver.model.Company;
import com.uncleserver.model.CompanyExtra;
import com.uncleserver.modelVo.ApiResult;
import com.uncleserver.service.api.AuntCompanyService;

@Controller
@RequestMapping("/auntapi/company")
public class AuntCompanyController extends BaseController {

	@Autowired
	private AuntCompanyService auntCompanyService;

	/**
	 * 获取员工列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getStaffList")
	@ResponseBody
	public ApiResult getStaffList(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}

			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}

			String userid = request.getParameter("userid");
			String page = request.getParameter("page");

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(page)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			Company company = auntCompanyService.selectCompanyByCid(CommonUtils.parseInt(userid, 0));
			if (company == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}

			CompanyExtra extra = auntCompanyService.selectCompanyExtraByCid(company.getCompanyid());

			if (!checkSession(accesstoken, extra)) {
				result.setCode("105");
				result.setMessage("您的账号已经在别处登录,请重新登录");
				return result;
			}

			if (checkTokenTime(extra.getTokenTime())) {
				result.setCode("107");
				result.setMessage("登录信息过期,请重新登录");
				return result;
			}

			result = auntCompanyService.getStaffList(userid, page);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 搜索员工
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/searchStaff")
	@ResponseBody
	public ApiResult searchStaff(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}

			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}

			String userid = request.getParameter("userid");
			String keywrod = request.getParameter("keywrod");
			String page = request.getParameter("page");

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(keywrod)
					|| CommonUtils.isEmptyString(page)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			Company company = auntCompanyService.selectCompanyByCid(CommonUtils.parseInt(userid, 0));
			if (company == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}

			CompanyExtra extra = auntCompanyService.selectCompanyExtraByCid(company.getCompanyid());

			if (!checkSession(accesstoken, extra)) {
				result.setCode("105");
				result.setMessage("您的账号已经在别处登录,请重新登录");
				return result;
			}

			if (checkTokenTime(extra.getTokenTime())) {
				result.setCode("107");
				result.setMessage("登录信息过期,请重新登录");
				return result;
			}

			result = auntCompanyService.searchStaff(userid, page, keywrod);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 添加员工
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addStaff")
	@ResponseBody
	public ApiResult addStaff(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}

			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}

			String userid = request.getParameter("userid");
			String auntid = request.getParameter("auntid");

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(auntid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			Company company = auntCompanyService.selectCompanyByCid(CommonUtils.parseInt(userid, 0));
			if (company == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}

			CompanyExtra extra = auntCompanyService.selectCompanyExtraByCid(company.getCompanyid());

			if (!checkSession(accesstoken, extra)) {
				result.setCode("105");
				result.setMessage("您的账号已经在别处登录,请重新登录");
				return result;
			}

			if (checkTokenTime(extra.getTokenTime())) {
				result.setCode("107");
				result.setMessage("登录信息过期,请重新登录");
				return result;
			}

			result = auntCompanyService.addStaff(userid, auntid);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 删除员工
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/delStaff")
	@ResponseBody
	public ApiResult delStaff(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}

			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}

			String userid = request.getParameter("userid");
			String auntid = request.getParameter("auntids");

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(auntid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			Company company = auntCompanyService.selectCompanyByCid(CommonUtils.parseInt(userid, 0));
			if (company == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}

			CompanyExtra extra = auntCompanyService.selectCompanyExtraByCid(company.getCompanyid());

			if (!checkSession(accesstoken, extra)) {
				result.setCode("105");
				result.setMessage("您的账号已经在别处登录,请重新登录");
				return result;
			}

			if (checkTokenTime(extra.getTokenTime())) {
				result.setCode("107");
				result.setMessage("登录信息过期,请重新登录");
				return result;
			}

			result = auntCompanyService.delStaff(userid, auntid);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 刷新公司信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/refreshInfo")
	@ResponseBody
	public ApiResult refreshInfo(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}

			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}

			String userid = request.getParameter("userid");

			if (CommonUtils.isEmptyString(userid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			Company company = auntCompanyService.selectCompanyByCid(CommonUtils.parseInt(userid, 0));
			if (company == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}

			CompanyExtra extra = auntCompanyService.selectCompanyExtraByCid(company.getCompanyid());

			if (!checkSession(accesstoken, extra)) {
				result.setCode("105");
				result.setMessage("您的账号已经在别处登录,请重新登录");
				return result;
			}

			if (checkTokenTime(extra.getTokenTime())) {
				result.setCode("107");
				result.setMessage("登录信息过期,请重新登录");
				return result;
			}

			result = auntCompanyService.refreshInfo(company, extra);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 获取公司所有信息
	 * 
	 * @param request
	 * @return
	 */
	/*
	 * @RequestMapping("/getAllInfo")
	 * 
	 * @ResponseBody public ApiResult getAllInfo(HttpServletRequest request) {
	 * ApiResult result = getApiResult();
	 * 
	 * try {
	 * 
	 * if (!checkMethod(request.getMethod())) { result.setCode("103");
	 * result.setMessage("非法请求方式,仅支持POST"); return result; }
	 * 
	 * if (!checkPermission(request)) { result.setCode("104");
	 * result.setMessage("验证失败,请求被拒绝"); return result; }
	 * 
	 * String userid = request.getParameter("userid");
	 * 
	 * if (CommonUtils.isEmptyString(userid)) { result.setCode("101");
	 * result.setMessage("缺少接口参数"); return result; }
	 * 
	 * String accesstoken = request.getParameter("accesstoken"); Company company
	 * = auntCompanyService.selectCompanyByCid(CommonUtils.parseInt(userid, 0));
	 * if (company == null) { result.setCode("106"); result.setMessage("用户不存在");
	 * return result; }
	 * 
	 * CompanyExtra extra =
	 * auntCompanyService.selectCompanyExtraByCid(company.getCompanyid());
	 * 
	 * if (!checkSession(accesstoken, extra)) { result.setCode("105");
	 * result.setMessage("您的账号已经在别处登录,请重新登录"); return result; }
	 * 
	 * if (checkTokenTime(extra.getTokenTime())) { result.setCode("107");
	 * result.setMessage("登录信息过期,请重新登录"); return result; }
	 * 
	 * result = auntCompanyService.getAllInfo(company, extra);
	 * 
	 * return result; } catch (Exception e) { e.printStackTrace();
	 * result.setCode("102"); result.setMessage("服务器端异常"); return result; } }
	 */

	/**
	 * 添加服务地址
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addAddress")
	@ResponseBody
	public ApiResult addAddress(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String provience = request.getParameter("provience");
			String city = request.getParameter("city");
			String area = request.getParameter("area");
			String longitude = request.getParameter("longitude");
			String latitude = request.getParameter("latitude");
			String phone = request.getParameter("phone");
			String rname = request.getParameter("rname");
			String sex = request.getParameter("sex");
			String addressname = request.getParameter("addressname");
			String addressdetail = request.getParameter("addressdetail");
			String isdefault = request.getParameter("isdefault");

			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}

			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(city) || CommonUtils.isEmptyString(area)
					|| CommonUtils.isEmptyString(longitude) || CommonUtils.isEmptyString(latitude)
					|| CommonUtils.isEmptyString(phone) || CommonUtils.isEmptyString(rname)
					|| CommonUtils.isEmptyString(sex) || CommonUtils.isEmptyString(addressname)
					|| CommonUtils.isEmptyString(addressdetail) || CommonUtils.isEmptyString(isdefault)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			Company company = auntCompanyService.selectCompanyByCid(CommonUtils.parseInt(userid, 0));
			if (company == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}

			CompanyExtra extra = auntCompanyService.selectCompanyExtraByCid(company.getCompanyid());

			if (!checkSession(accesstoken, extra)) {
				result.setCode("105");
				result.setMessage("您的账号已经在别处登录,请重新登录");
				return result;
			}

			if (checkTokenTime(extra.getTokenTime())) {
				result.setCode("107");
				result.setMessage("登录信息过期,请重新登录");
				return result;
			}

			result = auntCompanyService.addAddress(userid, provience, city, area, longitude, latitude, phone, rname,
					sex, addressname, addressdetail, isdefault);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 修改服务地址
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/editAddress")
	@ResponseBody
	public ApiResult editAddress(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String addressid = request.getParameter("addressid");
			String provience = request.getParameter("provience");
			String city = request.getParameter("city");
			String area = request.getParameter("area");
			String longitude = request.getParameter("longitude");
			String latitude = request.getParameter("latitude");
			String phone = request.getParameter("phone");
			String rname = request.getParameter("rname");
			String sex = request.getParameter("sex");
			String addressname = request.getParameter("addressname");
			String addressdetail = request.getParameter("addressdetail");
			String isdefault = request.getParameter("isdefault");

			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}

			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(addressid)
					|| CommonUtils.isEmptyString(city) || CommonUtils.isEmptyString(area)
					|| CommonUtils.isEmptyString(longitude) || CommonUtils.isEmptyString(latitude)
					|| CommonUtils.isEmptyString(phone) || CommonUtils.isEmptyString(rname)
					|| CommonUtils.isEmptyString(sex) || CommonUtils.isEmptyString(addressname)
					|| CommonUtils.isEmptyString(addressdetail) || CommonUtils.isEmptyString(isdefault)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			Company company = auntCompanyService.selectCompanyByCid(CommonUtils.parseInt(userid, 0));
			if (company == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}

			CompanyExtra extra = auntCompanyService.selectCompanyExtraByCid(company.getCompanyid());

			if (!checkSession(accesstoken, extra)) {
				result.setCode("105");
				result.setMessage("您的账号已经在别处登录,请重新登录");
				return result;
			}

			if (checkTokenTime(extra.getTokenTime())) {
				result.setCode("107");
				result.setMessage("登录信息过期,请重新登录");
				return result;
			}

			result = auntCompanyService.editAddress(userid, addressid, provience, city, area, longitude, latitude,
					phone, rname, sex, addressname, addressdetail, isdefault);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 删除服务地址
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/delAddress")
	@ResponseBody
	public ApiResult delAddress(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String addressid = request.getParameter("addressid");

			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}

			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(addressid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			Company company = auntCompanyService.selectCompanyByCid(CommonUtils.parseInt(userid, 0));
			if (company == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}

			CompanyExtra extra = auntCompanyService.selectCompanyExtraByCid(company.getCompanyid());

			if (!checkSession(accesstoken, extra)) {
				result.setCode("105");
				result.setMessage("您的账号已经在别处登录,请重新登录");
				return result;
			}

			if (checkTokenTime(extra.getTokenTime())) {
				result.setCode("107");
				result.setMessage("登录信息过期,请重新登录");
				return result;
			}

			result = auntCompanyService.delAddress(userid, addressid);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 获取用户服务地址列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAddressList")
	@ResponseBody
	public ApiResult getAddressList(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String categoryid = request.getParameter("categoryid");

			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}

			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}

			if (CommonUtils.isEmptyString(userid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			Company company = auntCompanyService.selectCompanyByCid(CommonUtils.parseInt(userid, 0));
			if (company == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}

			CompanyExtra extra = auntCompanyService.selectCompanyExtraByCid(company.getCompanyid());

			if (!checkSession(accesstoken, extra)) {
				result.setCode("105");
				result.setMessage("您的账号已经在别处登录,请重新登录");
				return result;
			}

			if (checkTokenTime(extra.getTokenTime())) {
				result.setCode("107");
				result.setMessage("登录信息过期,请重新登录");
				return result;
			}

			result = auntCompanyService.getAddressList(company.getCompanyid(), CommonUtils.parseInt(categoryid, 0));

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 领取优惠券
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/companyDetail")
	@ResponseBody
	public ApiResult companyDetail(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String companyid = request.getParameter("companyid");

			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}

			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}

			if (CommonUtils.isEmptyString(companyid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			Company company = auntCompanyService.selectCompanyByCid(CommonUtils.parseInt(companyid, 0));
			if (company == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}

			CompanyExtra extra = auntCompanyService.selectCompanyExtraByCid(company.getCompanyid());

			if (!checkSession(accesstoken, extra)) {
				result.setCode("105");
				result.setMessage("您的账号已经在别处登录,请重新登录");
				return result;
			}

			if (checkTokenTime(extra.getTokenTime())) {
				result.setCode("107");
				result.setMessage("登录信息过期,请重新登录");
				return result;
			}

			result = auntCompanyService.companyDetail(companyid);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

}
