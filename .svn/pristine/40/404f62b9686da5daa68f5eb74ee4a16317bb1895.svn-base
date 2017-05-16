package com.uncleserver.controller.api;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uncleserver.common.CommonUtils;
import com.uncleserver.controller.BaseController;
import com.uncleserver.model.User;
import com.uncleserver.model.UserExtra;
import com.uncleserver.modelVo.ApiResult;
import com.uncleserver.modelVo.PayValue;
import com.uncleserver.service.api.OrderService;
import com.uncleserver.service.api.SystemService;
import com.uncleserver.service.api.UserService;
import com.uncleserver.web.common.WebUtils;

@Controller
@RequestMapping("/api/order")
public class OrderController extends BaseController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@Autowired
	private SystemService sysService;

	/**
	 * 领取优惠券
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getServerCategory")
	@ResponseBody
	public ApiResult getServerCategory(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String city = request.getParameter("city");
			
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
			if(!CommonUtils.isEmptyString(city)){
				result = orderService.getServerCategory(city);
			}else {
				result = orderService.getServerCategory();
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
	 * 领取优惠券
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/canBookListMap")
	@ResponseBody
	public ApiResult canBookListMap(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String longitude = request.getParameter("longitude");
			String latitude = request.getParameter("latitude");
			String serverid = request.getParameter("serverid");
			String thirdids = request.getParameter("thirdids");
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

			if (CommonUtils.isEmptyString(longitude) || CommonUtils.isEmptyString(latitude)
					|| CommonUtils.isEmptyString(serverid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			result = orderService.canBookListMap(longitude, latitude, serverid, thirdids);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 获取可预约的公司列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/canBookCompanyList")
	@ResponseBody
	public ApiResult canBookCompanyList(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String longitude = request.getParameter("longitude");
			String latitude = request.getParameter("latitude");
			String serverid = request.getParameter("serverid");
			String thirdids = request.getParameter("thirdids");
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

			if (CommonUtils.isEmptyString(longitude) || CommonUtils.isEmptyString(latitude)
					|| CommonUtils.isEmptyString(serverid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			result = orderService.canBookCompanyList(longitude, latitude, serverid, thirdids);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 获取可预约的阿姨列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/canBookAuntList")
	@ResponseBody
	public ApiResult canBookAuntList(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String longitude = request.getParameter("longitude");
			String latitude = request.getParameter("latitude");
			String serverid = request.getParameter("serverid");
			String thirdids = request.getParameter("thirdids");
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

			if (CommonUtils.isEmptyString(longitude) || CommonUtils.isEmptyString(latitude)
					|| CommonUtils.isEmptyString(serverid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			result = orderService.canBookAuntList(longitude, latitude, serverid, thirdids);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 预约
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/booking")
	@ResponseBody
	public ApiResult booking(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String serverid = request.getParameter("serverid");
			String addressid = request.getParameter("addressid");
			String server_time = request.getParameter("server_time");
			String book_type = request.getParameter("book_type");
			String app_type = request.getParameter("app_type");
			String picids = request.getParameter("picids");
			String book = request.getParameter("book");
			String aunt_type = request.getParameter("aunt_type");
			String auntids = request.getParameter("auntids");
			String expect_time = request.getParameter("expect_time");
			String day_time = request.getParameter("day_time");
			String thing_count = request.getParameter("thing_count");
			String third_json = request.getParameter("third_json");
			String need_tools = request.getParameter("need_tools");
			String reason_mark = request.getParameter("reason_mark");
			String order_target_json = request.getParameter("order_target_json");
			String m_count = request.getParameter("m_count");
			String w_count = request.getParameter("w_count");
			String tip_price = request.getParameter("tip_price");
			String order_type = request.getParameter("order_type");
			String foodselect = request.getParameter("foodselect");
			String price = request.getParameter("price");

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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(serverid)
					|| CommonUtils.isEmptyString(addressid) || CommonUtils.isEmptyString(server_time)
					|| CommonUtils.isEmptyString(book_type) || CommonUtils.isEmptyString(app_type)) {

				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			User user = userService.selectUserByUserId(CommonUtils.parseInt(userid, 0));
			if (user == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}

			UserExtra extra = userService.selectUserExtraByUserID(user.getUserid());

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

			result = orderService.booking(user, serverid, addressid, server_time, book_type, app_type, picids, book,
					aunt_type, auntids, expect_time, day_time, thing_count, third_json, need_tools, reason_mark,
					order_target_json, m_count, w_count, tip_price, order_type, foodselect, price);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 签到
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/order")
	@ResponseBody
	public ApiResult order(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String tempid = request.getParameter("tempid");
			String pay_type = request.getParameter("pay_type");
			String openid = request.getParameter("openid");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(tempid)
					|| CommonUtils.isEmptyString(pay_type)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			if ("4".equals(pay_type) && CommonUtils.isEmptyString(openid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			User user = userService.selectUserByUserId(CommonUtils.parseInt(userid, 0));
			if (user == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}

			UserExtra extra = userService.selectUserExtraByUserID(user.getUserid());
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

			result = orderService.order(user, tempid, pay_type);
			if (Integer.parseInt(pay_type) == 2) {
				String json = result.getR();
				HashMap<String, Object> map = new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>() {
				}.getType());
				String thirdNumID = (String) map.get("thirdNumID");
				Double payMoney = (Double) map.get("pay_money");
				PayValue value = WebUtils.createWxPayValue(request, response, thirdNumID,
						sysService.getWXPayBody(thirdNumID), payMoney.floatValue());
				HashMap<String, Object> resultMap = new HashMap<>();
				resultMap.put("pay", value);
				resultMap.put("orderid", map.get("orderid"));
				resultMap.put("pay_type", pay_type);
				result.setResult(resultMap);

			} else if (Integer.parseInt(pay_type) == 4) {
				String json = result.getR();
				HashMap<String, Object> map = new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>() {
				}.getType());
				String thirdNumID = (String) map.get("thirdNumID");
				Double payMoney = (Double) map.get("pay_money");
				PayValue value = WebUtils.createWxJSPayValue(request, response, thirdNumID, openid,
						sysService.getWXPayBody(thirdNumID), payMoney.floatValue());
				HashMap<String, Object> resultMap = new HashMap<>();
				resultMap.put("pay", value);
				resultMap.put("pay_type", pay_type);
				resultMap.put("orderid", map.get("orderid"));
				result.setResult(resultMap);
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
	 * 获取阿姨提供的服务分类
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getAuntServer")
	@ResponseBody
	public ApiResult getAuntServer(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String auntid = request.getParameter("auntid");
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

			if (CommonUtils.isEmptyString(auntid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			result = orderService.getAuntServer(CommonUtils.parseInt(auntid, 0));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 获取公司提供的服务分类
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getCompanyServer")
	@ResponseBody
	public ApiResult getCompanyServer(HttpServletRequest request, HttpServletResponse response) {
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

			result = orderService.getCompanyServer(CommonUtils.parseInt(companyid, 0));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 获取可以选择的三级分类ID
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getThirdCategory")
	@ResponseBody
	public ApiResult getThirdCategory(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String serverid = request.getParameter("serverid");
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

			if (CommonUtils.isEmptyString(serverid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			result = orderService.getThirdCategory(CommonUtils.parseInt(serverid, 0));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 我的订单列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/orderList")
	@ResponseBody
	public ApiResult orderList(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String type = request.getParameter("type");
			String page = request.getParameter("page");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(type)
					|| CommonUtils.isEmptyString(page)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			User user = userService.selectUserByUserId(CommonUtils.parseInt(userid, 0));
			if (user == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}

			UserExtra extra = userService.selectUserExtraByUserID(user.getUserid());

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

			result = orderService.orderList(CommonUtils.parseInt(userid, 0), CommonUtils.parseInt(type, 0),
					CommonUtils.parseInt(page, 0));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 订单详情
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/orderDetail")
	@ResponseBody
	public ApiResult orderDetail(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String orderid = request.getParameter("orderid");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(orderid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			User user = userService.selectUserByUserId(CommonUtils.parseInt(userid, 0));
			if (user == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}

			UserExtra extra = userService.selectUserExtraByUserID(user.getUserid());

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

			result = orderService.orderDetail(CommonUtils.parseInt(userid, 0), CommonUtils.parseInt(orderid, 0));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	@RequestMapping("/payOrder")
	@ResponseBody
	public ApiResult payOrder(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();
		try {
			String userid = request.getParameter("userid");
			String orderid = request.getParameter("orderid");
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
			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(orderid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			User user = userService.selectUserByUserId(CommonUtils.parseInt(userid, 0));
			if (user == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}

			UserExtra extra = userService.selectUserExtraByUserID(user.getUserid());

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

			result = orderService.payOrder(userid, orderid);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 获取可用的优惠券列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getCanUseCoupon")
	@ResponseBody
	public ApiResult getCanUseCoupon(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();
		try {
			String userid = request.getParameter("userid");
			String orderid = request.getParameter("orderid");
			String month = request.getParameter("month");

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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(orderid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			User user = userService.selectUserByUserId(CommonUtils.parseInt(userid, 0));
			if (user == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}

			UserExtra extra = userService.selectUserExtraByUserID(user.getUserid());

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
			result = orderService.getCanUseCoupon(user, orderid, month);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 订单支付
	 */
	@RequestMapping("/pay")
	@ResponseBody
	public ApiResult pay(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();
		try {
			String userid = request.getParameter("userid");
			String orderid = request.getParameter("orderid");
			String couponid = request.getParameter("couponid");
			String pay_type = request.getParameter("pay_type");
			String openid = request.getParameter("openid");
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
			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(orderid)
					|| CommonUtils.isEmptyString(pay_type)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			if ("4".equals(pay_type) && CommonUtils.isEmptyString(openid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			User user = userService.selectUserByUserId(CommonUtils.parseInt(userid, 0));
			if (user == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}

			UserExtra extra = userService.selectUserExtraByUserID(user.getUserid());

			if (!checkSession(accesstoken, extra)) {
				result.setCode("105");
				result.setMessage("您的账号已经在别处登录,请重新登录");
				return result;
			}

			result = orderService.pay(user, extra, orderid, couponid, pay_type);
			if (Integer.parseInt(pay_type) == 2) {
				String json = result.getR();
				HashMap<String, Object> map = new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>() {
				}.getType());
				String thirdNumID = (String) map.get("thirdNumID");
				Double pay_money = (Double) map.get("pay_money");
				PayValue value = WebUtils.createWxPayValue(request, response, thirdNumID,
						sysService.getWXPayBody(thirdNumID), pay_money.floatValue());
				HashMap<String, Object> resultMap = new HashMap<>();
				resultMap.put("pay", value);
				result.setResult(resultMap);
			} else if (Integer.parseInt(pay_type) == 4) {
				String json = result.getR();
				HashMap<String, Object> map = new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>() {
				}.getType());
				String thirdNumID = (String) map.get("thirdNumID");
				Double pay_money = (Double) map.get("pay_money");
				PayValue value = WebUtils.createWxJSPayValue(request, response, thirdNumID, openid,
						sysService.getWXPayBody(thirdNumID), pay_money.floatValue());
				HashMap<String, Object> resultMap = new HashMap<>();
				resultMap.put("pay", value);
				resultMap.put("pay_type", pay_type);
				result.setResult(resultMap);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	@RequestMapping("payOrderMonth")
	@ResponseBody
	public ApiResult payOrderMonth(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();
		try {
			String userid = request.getParameter("userid");
			String orderid = request.getParameter("orderid");
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
			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(orderid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			User user = userService.selectUserByUserId(CommonUtils.parseInt(userid, 0));
			if (user == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}

			UserExtra extra = userService.selectUserExtraByUserID(user.getUserid());

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

			result = orderService.payOrderMonth(userid, orderid);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	@RequestMapping("/payMonth")
	@ResponseBody
	public ApiResult payMonth(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();
		try {
			String userid = request.getParameter("userid");
			String orderid = request.getParameter("orderid");
			String couponid = request.getParameter("couponid");
			String pay_type = request.getParameter("pay_type");
			String openid = request.getParameter("openid");
			String month = request.getParameter("month");

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
			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(orderid)
					 || CommonUtils.isEmptyString(pay_type)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			User user = userService.selectUserByUserId(CommonUtils.parseInt(userid, 0));
			if (user == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}

			UserExtra extra = userService.selectUserExtraByUserID(user.getUserid());

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

			if ("4".equals(pay_type) && CommonUtils.isEmptyString(openid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			result = orderService.payMonth(userid, orderid, couponid, pay_type, month);
			if (Integer.parseInt(pay_type) == 2) {
				String json = result.getR();
				HashMap<String, Object> map = new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>() {
				}.getType());
				String thirdNumID = (String) map.get("thirdNumID");
				Double pay_money = (Double) map.get("pay_money");
				PayValue value = WebUtils.createWxPayValue(request, response, thirdNumID,
						sysService.getWXPayBody(thirdNumID), pay_money.floatValue());
				HashMap<String, Object> resultMap = new HashMap<>();
				resultMap.put("pay", value);
				result.setResult(resultMap);

			} else if (Integer.parseInt(pay_type) == 4) {
				String json = result.getR();
				HashMap<String, Object> map = new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>() {
				}.getType());
				String thirdNumID = (String) map.get("thirdNumID");
				Double pay_money = (Double) map.get("pay_money");
				PayValue value = WebUtils.createWxJSPayValue(request, response, thirdNumID, openid,
						sysService.getWXPayBody(thirdNumID), pay_money.floatValue());
				HashMap<String, Object> resultMap = new HashMap<>();
				resultMap.put("pay", value);
				resultMap.put("pay_type", pay_type);
				result.setResult(resultMap);
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
	 * 投诉订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/complaintOrder")
	@ResponseBody
	public ApiResult complaintOrder(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();
		try {
			String userid = request.getParameter("userid");
			String orderid = request.getParameter("orderid");
			String content = request.getParameter("content");
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
			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(orderid)
					|| CommonUtils.isEmptyString(content)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			result = orderService.complaintOrder(userid, orderid, content);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 评论订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/commentOrder")
	@ResponseBody
	public ApiResult commentOrder(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();
		try {
			String userid = request.getParameter("userid");
			String orderid = request.getParameter("orderid");
			String content = request.getParameter("content");
			String reason = request.getParameter("reason");
			String score1 = request.getParameter("score1");
			String score2 = request.getParameter("score2");
			String score3 = request.getParameter("score3");
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
			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(orderid)
					|| CommonUtils.isEmptyString(reason) || CommonUtils.isEmptyString(score1)
					|| CommonUtils.isEmptyString(score2) || CommonUtils.isEmptyString(score3)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			User user = userService.selectUserByUserId(CommonUtils.parseInt(userid, 0));
			if (user == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}

			UserExtra extra = userService.selectUserExtraByUserID(user.getUserid());

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

			result = orderService.commentOrder(userid, orderid, content, reason, score1, score2, score3);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 退单确认
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/cancel")
	@ResponseBody
	public ApiResult cancel(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String orderid = request.getParameter("orderid");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(orderid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			User user = userService.selectUserByUserId(CommonUtils.parseInt(userid, 0));
			if (user == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}

			UserExtra extra = userService.selectUserExtraByUserID(user.getUserid());

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
			result = orderService.cancel(user, orderid);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 退单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/cancelOrder")
	@ResponseBody
	public ApiResult cancelOrder(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String orderid = request.getParameter("orderid");
			String reason = request.getParameter("reason");
			String content = request.getParameter("content");
			String pay_type = request.getParameter("pay_type");
			String openid = request.getParameter("openid");
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

			if (CommonUtils.isEmptyAllString(userid, orderid, pay_type)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			if ("4".equals(pay_type) && CommonUtils.isEmptyString(openid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			User user = userService.selectUserByUserId(CommonUtils.parseInt(userid, 0));
			if (user == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}

			UserExtra extra = userService.selectUserExtraByUserID(user.getUserid());

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
			result = orderService.cancelOrder(user, orderid, reason, content, pay_type);
			if (Integer.parseInt(pay_type) == 2) {
				String json = result.getR();
				HashMap<String, Object> map = new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>() {
				}.getType());
				String thirdNumID = (String) map.get("thirdNumID");
				PayValue value = WebUtils.createWxPayValue(request, response, thirdNumID,
						sysService.getWXPayBody(thirdNumID), 0.01f);
				HashMap<String, Object> resultMap = new HashMap<>();
				resultMap.put("pay", value);
				result.setResult(resultMap);

			} else if (Integer.parseInt(pay_type) == 4) {
				String json = result.getR();
				HashMap<String, Object> map = new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>() {
				}.getType());
				String thirdNumID = (String) map.get("thirdNumID");
				PayValue value = WebUtils.createWxJSPayValue(request, response, thirdNumID, openid,
						sysService.getWXPayBody(thirdNumID), 0.01f);
				HashMap<String, Object> resultMap = new HashMap<>();
				resultMap.put("pay", value);
				resultMap.put("pay_type", pay_type);
				result.setResult(resultMap);
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
	 * 支付定金
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/payDeposit")
	@ResponseBody
	public ApiResult payDeposit(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String orderid = request.getParameter("orderid");
			String pay_type = request.getParameter("pay_type");
			String openid = request.getParameter("openid");

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

			if (CommonUtils.isEmptyAllString(userid, orderid, pay_type)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			if ("4".equals(pay_type) && CommonUtils.isEmptyString(openid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			User user = userService.selectUserByUserId(CommonUtils.parseInt(userid, 0));
			if (user == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}

			UserExtra extra = userService.selectUserExtraByUserID(user.getUserid());

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
			result = orderService.payDeposit(user, orderid, pay_type);
			if (Integer.parseInt(pay_type) == 2) {
				String json = result.getR();
				HashMap<String, Object> map = new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>() {
				}.getType());
				String thirdNumID = (String) map.get("thirdNumID");
				Double payMoney = (Double) map.get("pay_money");
				PayValue value = WebUtils.createWxPayValue(request, response, thirdNumID,
						sysService.getWXPayBody(thirdNumID), payMoney.floatValue());
				HashMap<String, Object> resultMap = new HashMap<>();
				resultMap.put("pay", value);
				result.setResult(resultMap);

			} else if (Integer.parseInt(pay_type) == 4) {
				String json = result.getR();
				HashMap<String, Object> map = new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>() {
				}.getType());
				String thirdNumID = (String) map.get("thirdNumID");
				Double payMoney = (Double) map.get("pay_money");
				PayValue value = WebUtils.createWxJSPayValue(request, response, thirdNumID, openid,
						sysService.getWXPayBody(thirdNumID), payMoney.floatValue());
				HashMap<String, Object> resultMap = new HashMap<>();
				resultMap.put("pay", value);
				resultMap.put("pay_type", pay_type);
				result.setResult(resultMap);
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
	 * 增加小费
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addTip")
	@ResponseBody
	public ApiResult addTip(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String orderid = request.getParameter("orderid");
			String count = request.getParameter("count");
			String pay_type = request.getParameter("pay_type");
			String openid = request.getParameter("openid");

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

			if (CommonUtils.isEmptyAllString(userid, orderid, pay_type, count)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			if ("4".equals(pay_type) && CommonUtils.isEmptyString(openid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			User user = userService.selectUserByUserId(CommonUtils.parseInt(userid, 0));
			if (user == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}

			UserExtra extra = userService.selectUserExtraByUserID(user.getUserid());

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
			result = orderService.addTip(user, orderid, count, pay_type, openid);
			if (Integer.parseInt(pay_type) == 2) {
				String json = result.getR();
				HashMap<String, Object> map = new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>() {
				}.getType());
				String thirdNumID = (String) map.get("thirdNumID");
				Double payMoney = (Double) map.get("pay_money");
				PayValue value = WebUtils.createWxPayValue(request, response, thirdNumID,
						sysService.getWXPayBody(thirdNumID), payMoney.floatValue());
				HashMap<String, Object> resultMap = new HashMap<>();
				resultMap.put("pay", value);
				result.setResult(resultMap);

			} else if (Integer.parseInt(pay_type) == 4) {
				String json = result.getR();
				HashMap<String, Object> map = new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>() {
				}.getType());
				String thirdNumID = (String) map.get("thirdNumID");
				Double payMoney = (Double) map.get("pay_money");
				PayValue value = WebUtils.createWxJSPayValue(request, response, thirdNumID, openid,
						sysService.getWXPayBody(thirdNumID), payMoney.floatValue());
				HashMap<String, Object> resultMap = new HashMap<>();
				resultMap.put("pay", value);
				resultMap.put("pay_type", pay_type);
				result.setResult(resultMap);
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
	 * 增加小费
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/calcelBook")
	@ResponseBody
	public ApiResult calcelBook(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String orderid = request.getParameter("orderid");

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

			if (CommonUtils.isEmptyAllString(userid, orderid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			User user = userService.selectUserByUserId(CommonUtils.parseInt(userid, 0));
			if (user == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}

			UserExtra extra = userService.selectUserExtraByUserID(user.getUserid());

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
			result = orderService.calcelBook(user, orderid);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 增加小费
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/comfirmWages")
	@ResponseBody
	public ApiResult comfirmWages(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String orderid = request.getParameter("orderid");

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

			if (CommonUtils.isEmptyAllString(userid, orderid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			User user = userService.selectUserByUserId(CommonUtils.parseInt(userid, 0));
			if (user == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}

			UserExtra extra = userService.selectUserExtraByUserID(user.getUserid());

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
			result = orderService.comfirmWages(user, orderid);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 确认订单(维修类)
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/comfirmOrder")
	@ResponseBody
	public ApiResult comfirmOrder(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String orderid = request.getParameter("orderid");

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

			if (CommonUtils.isEmptyAllString(userid, orderid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			User user = userService.selectUserByUserId(CommonUtils.parseInt(userid, 0));
			if (user == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}

			UserExtra extra = userService.selectUserExtraByUserID(user.getUserid());

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
			result = orderService.comfirmOrder(user, orderid);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 确认订单(维修类)
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/comfirmMultiOrder")
	@ResponseBody
	public ApiResult comfirmMultiOrder(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String orderid = request.getParameter("orderid");

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

			if (CommonUtils.isEmptyAllString(userid, orderid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			User user = userService.selectUserByUserId(CommonUtils.parseInt(userid, 0));
			if (user == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}

			UserExtra extra = userService.selectUserExtraByUserID(user.getUserid());

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
			result = orderService.comfirmMultiOrder(user, orderid);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

}
