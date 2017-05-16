package com.uncleserver.controller.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.controller.BaseController;
import com.uncleserver.model.Aunt;
import com.uncleserver.model.AuntExtra;
import com.uncleserver.model.Company;
import com.uncleserver.model.CompanyExtra;
import com.uncleserver.modelVo.ApiResult;
import com.uncleserver.service.api.AuntHomeService;

@Controller
@RequestMapping("/auntapi/home")
public class AuntHomeController extends BaseController {

	@Autowired
	private AuntHomeService auntHomeService;

	/**
	 * 获取首页轮播图
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getBanner")
	@ResponseBody
	public ApiResult getBanner(HttpServletRequest request) {
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

			result = auntHomeService.getBanner();

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 设置当前状态
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/setState")
	@ResponseBody
	public ApiResult setState(HttpServletRequest request) {
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
			String user_type = request.getParameter("user_type");
			String state = request.getParameter("state");

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(user_type)
					|| CommonUtils.isEmptyString(state)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			int userTypeInt = CommonUtils.parseInt(user_type, 0);
			if (userTypeInt == 0) {// 阿姨
				Aunt aunt = auntHomeService.selectAuntByAuntId(CommonUtils.parseInt(userid, 0));
				if (aunt == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}

				AuntExtra extra = auntHomeService.selectAuntExtraByAuntId(aunt.getAuntid());

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
				result = auntHomeService.setState(state, extra);
			} else {// 公司
				Company company = auntHomeService.selectCompanyByCid(CommonUtils.parseInt(userid, 0));
				if (company == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}

				CompanyExtra extra = auntHomeService.selectCompanyExtraByCid(company.getCompanyid());

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
				result = auntHomeService.setState(state, extra);
			}

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 获取待接单或者待抢单的订单列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/orderList")
	@ResponseBody
	public ApiResult orderList(HttpServletRequest request) {
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
			String user_type = request.getParameter("user_type");
			String longitude = request.getParameter("longitude");
			String latitude = request.getParameter("latitude");

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(user_type)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			int userTypeInt = CommonUtils.parseInt(user_type, 0);
			if (userTypeInt == 0) {// 阿姨
				Aunt aunt = auntHomeService.selectAuntByAuntId(CommonUtils.parseInt(userid, 0));
				if (aunt == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}

				AuntExtra extra = auntHomeService.selectAuntExtraByAuntId(aunt.getAuntid());

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
			} else {// 公司
				Company company = auntHomeService.selectCompanyByCid(CommonUtils.parseInt(userid, 0));
				if (company == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}

				CompanyExtra extra = auntHomeService.selectCompanyExtraByCid(company.getCompanyid());

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

			}
			result = auntHomeService.getOrderList(CommonUtils.parseInt(userid, 0), CommonUtils.parseInt(user_type, 0),longitude,latitude);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 获取待接单或者待抢单的订单列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/acceptOrder")
	@ResponseBody
	public ApiResult acceptOrder(HttpServletRequest request) {
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
			String user_type = request.getParameter("user_type");
			String orderid = request.getParameter("orderid");
			String orderid_user = request.getParameter("orderid_user");
			String aunt_m_count = request.getParameter("aunt_m_count");
			String aunt_w_count = request.getParameter("aunt_w_count");

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(user_type)
					|| CommonUtils.isEmptyString(orderid) || CommonUtils.isEmptyString(orderid_user)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			int userTypeInt = CommonUtils.parseInt(user_type, 0);
			if (userTypeInt == 0) {// 阿姨
				Aunt aunt = auntHomeService.selectAuntByAuntId(CommonUtils.parseInt(userid, 0));
				if (aunt == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}

				AuntExtra extra = auntHomeService.selectAuntExtraByAuntId(aunt.getAuntid());

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
			} else {// 公司
				Company company = auntHomeService.selectCompanyByCid(CommonUtils.parseInt(userid, 0));
				if (company == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}

				CompanyExtra extra = auntHomeService.selectCompanyExtraByCid(company.getCompanyid());

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

			}
			result = auntHomeService.acceptOrder(CommonUtils.parseInt(userid, 0), CommonUtils.parseInt(user_type, 0),
					CommonUtils.parseInt(orderid, 0), CommonUtils.parseInt(orderid_user, 0),
					CommonUtils.parseInt(aunt_m_count, 0),CommonUtils.parseInt(aunt_w_count, 0));

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 拒单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/refuseOrder")
	@ResponseBody
	public ApiResult refuseOrder(HttpServletRequest request) {
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
			String user_type = request.getParameter("user_type");
			String orderid = request.getParameter("orderid");
			String orderid_user = request.getParameter("orderid_user");
			String reason = request.getParameter("reason");
			String content = request.getParameter("content");

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(user_type)
					|| CommonUtils.isEmptyString(orderid) || CommonUtils.isEmptyString(reason)
					|| CommonUtils.isEmptyString(orderid_user)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			int userTypeInt = CommonUtils.parseInt(user_type, 0);
			if (userTypeInt == 0) {// 阿姨
				Aunt aunt = auntHomeService.selectAuntByAuntId(CommonUtils.parseInt(userid, 0));
				if (aunt == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}

				AuntExtra extra = auntHomeService.selectAuntExtraByAuntId(aunt.getAuntid());

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
			} else {// 公司
				Company company = auntHomeService.selectCompanyByCid(CommonUtils.parseInt(userid, 0));
				if (company == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}

				CompanyExtra extra = auntHomeService.selectCompanyExtraByCid(company.getCompanyid());

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

			}
			result = auntHomeService.refuseOrder(userid, user_type, orderid, orderid_user, reason, content);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

}
