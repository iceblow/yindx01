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
import com.uncleserver.common.Constant;
import com.uncleserver.controller.BaseController;
import com.uncleserver.model.User;
import com.uncleserver.model.UserExtra;
import com.uncleserver.modelVo.ApiResult;
import com.uncleserver.modelVo.PayValue;
import com.uncleserver.service.api.SystemService;
import com.uncleserver.service.api.UserService;
import com.uncleserver.web.common.WebUtils;

@Controller
@RequestMapping("/api/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	@Autowired
	private SystemService sysService;

	/**
	 * 获取验证码
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getVCode")
	@ResponseBody
	public ApiResult getVCode(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {
			String phone = request.getParameter("phone");
			String type = request.getParameter("type");

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

			if (CommonUtils.isEmptyString(phone) || CommonUtils.isEmptyString(type)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
			int typeInt = CommonUtils.parseInt(type, 0);
			if (typeInt == 0 || typeInt == 1 || typeInt == 2 || typeInt == 3 || typeInt == 4) {
				result = userService.sendVcode(phone, typeInt);
			} else {
				result.setCode("2");
				result.setMessage("不支持的验证码类型");
				return result;
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
	 * 验证验证码的有效性
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/checkVCode")
	@ResponseBody
	public ApiResult checkVCode(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {
			String phone = request.getParameter("phone");
			String type = request.getParameter("type");
			String vcode = request.getParameter("vcode");

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

			if (CommonUtils.isEmptyString(phone) || CommonUtils.isEmptyString(type)
					|| CommonUtils.isEmptyString(vcode)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
			int typeInt = CommonUtils.parseInt(type, 0);
			if (typeInt == 0 || typeInt == 1 || typeInt == 2 || typeInt == 4) {
				result = userService.checkVcode(phone, vcode, typeInt);
			} else {
				result.setCode("2");
				result.setMessage("不支持的验证类型");
				return result;
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
	 * 用户注册
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/userRegister")
	@ResponseBody
	public ApiResult userRegister(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {
			String phone = request.getParameter("phone");
			String password = request.getParameter("password");
			String vcode = request.getParameter("vcode");

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

			if (CommonUtils.isEmptyString(phone) || CommonUtils.isEmptyString(password)
					|| CommonUtils.isEmptyString(vcode)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			result = userService.registerUser(phone, password, vcode, request.getSession().getId().toLowerCase());

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public ApiResult login(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String phone = request.getParameter("phone");
			String password = request.getParameter("password");

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

			if (CommonUtils.isEmptyString(phone) || CommonUtils.isEmptyString(password)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			result = userService.login(phone, password, request.getSession().getId().toLowerCase());

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 第三方登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/thirdLogin")
	@ResponseBody
	public ApiResult thirdLogin(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String openid = request.getParameter("openid");
			String unionid = request.getParameter("unionid");
			String thirdtype = request.getParameter("thirdtype");
			String nickname = request.getParameter("nickname");
			String avatarurl = request.getParameter("avatarurl");

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

			if (CommonUtils.isEmptyString(openid) || CommonUtils.isEmptyString(thirdtype)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			if (CommonUtils.parseInt(thirdtype, 0) == 3 && CommonUtils.isEmptyString(unionid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			result = userService.thirdLogin(openid, unionid, thirdtype, request.getSession().getId().toLowerCase(),
					nickname, avatarurl);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 第三方登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/autoLogin")
	@ResponseBody
	public ApiResult autoLogin(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String accesstoken = request.getParameter("accesstoken");
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

			if (CommonUtils.isEmptyString(accesstoken)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
			UserExtra extra = userService.selectUserExtraByToken(accesstoken);

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
			result = userService.autoLogin(accesstoken);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 第三方登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/refreshUserInfo")
	@ResponseBody
	public ApiResult refreshUserInfo(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String accesstoken = request.getParameter("accesstoken");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(accesstoken)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			result = userService.refreshUserInfo(userid, accesstoken);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 修改手机号
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/changePhone")
	@ResponseBody
	public ApiResult changePhone(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String newphone = request.getParameter("newphone");
			String vcode = request.getParameter("vcode");
			String accesstoken = request.getParameter("accesstoken");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(vcode)
					|| CommonUtils.isEmptyString(newphone)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
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
			result = userService.changePhone(user, newphone, vcode, request.getSession().getId().toLowerCase());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 修改用户信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/changeUserInfo")
	@ResponseBody
	public ApiResult changeUserInfo(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String key = request.getParameter("key");
			String value = request.getParameter("value");
			String accesstoken = request.getParameter("accesstoken");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(key)
					|| CommonUtils.isEmptyString(value)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
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
			result = userService.changeUserInfo(user, key, value);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 修改手机号
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getSignMonth")
	@ResponseBody
	public ApiResult getSignMonth(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String time = request.getParameter("time");
			String accesstoken = request.getParameter("accesstoken");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(time)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
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
			result = userService.getSignMonth(user, time);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 修改手机号
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getBalanceList")
	@ResponseBody
	public ApiResult getBalanceList(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String page = request.getParameter("page");
			String accesstoken = request.getParameter("accesstoken");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(page)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
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
			result = userService.getBalanceList(user, page);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 修改手机号
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getPointList")
	@ResponseBody
	public ApiResult getPointList(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String page = request.getParameter("page");
			String accesstoken = request.getParameter("accesstoken");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(page)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
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
			result = userService.getPointList(user, page);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 绑定手机号码
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/bindPhone")
	@ResponseBody
	public ApiResult bindPhone(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String phone = request.getParameter("phone");
			String vcode = request.getParameter("vcode");
			// String password = request.getParameter("password");

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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(phone)
					|| CommonUtils.isEmptyString(vcode)) {
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

			result = userService.bindPhone(user, phone, vcode, request.getSession().getId().toLowerCase());

			String json = result.getR();
			HashMap<String, Object> map = new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>() {
			}.getType());

			request.getSession().setAttribute(Constant.SESSION_WXUSERID,
					(int) CommonUtils.parseDouble(String.valueOf(map.get("userid")), 0));
			request.getSession().setAttribute(Constant.SESSION_ACCESSTOKEN, String.valueOf(map.get("accesstoken")));

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 忘记密码.修改密码
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/changePassword")
	@ResponseBody
	public ApiResult changePassword(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String newpsw = request.getParameter("newpsw");
			String phone = request.getParameter("phone");
			String vcode = request.getParameter("vcode");

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

			if (CommonUtils.isEmptyString(newpsw) || CommonUtils.isEmptyString(phone)
					|| CommonUtils.isEmptyString(vcode)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			result = userService.changePassword(phone, vcode, newpsw);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

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

			result = userService.addAddress(userid, provience, city, area, longitude, latitude, phone, rname, sex,
					addressname, addressdetail, isdefault);

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

			result = userService.editAddress(userid, addressid, provience, city, area, longitude, latitude, phone,
					rname, sex, addressname, addressdetail, isdefault);

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

			result = userService.delAddress(userid, addressid);

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

			result = userService.getAddressList(user.getUserid(), CommonUtils.parseInt(categoryid, 0));

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 意见反馈
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/feedBack")
	@ResponseBody
	public ApiResult feedBack(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
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

			if (CommonUtils.isEmptyString(userid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			result = userService.feedBack(userid, content);

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
	@RequestMapping("/sign")
	@ResponseBody
	public ApiResult sign(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");

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

			result = userService.sign(user, extra);

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
	@RequestMapping("/recharge")
	@ResponseBody
	public ApiResult recharge(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(pay_type)
					|| CommonUtils.isEmptyString(count)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			if ("3".equals(pay_type) && CommonUtils.isEmptyString(openid)) {
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

			result = userService.recharge(user, count, pay_type);
			if (Integer.parseInt(pay_type) == 2) {
				String json = result.getR();
				HashMap<String, Object> map = new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>() {
				}.getType());
				String thirdNumID = (String) map.get("thirdNumID");
				PayValue value = WebUtils.createWxPayValue(request, response, thirdNumID,
						sysService.getWXPayBody(thirdNumID), CommonUtils.parseFloat(count, 0f));
				HashMap<String, Object> resultMap = new HashMap<>();
				resultMap.put("pay", value);
				resultMap.put("pay_type", pay_type);
				result.setResult(resultMap);

			} else if (Integer.parseInt(pay_type) == 3) {
				String json = result.getR();
				HashMap<String, Object> map = new Gson().fromJson(json, new TypeToken<HashMap<String, Object>>() {
				}.getType());
				String thirdNumID = (String) map.get("thirdNumID");
				PayValue value = WebUtils.createWxJSPayValue(request, response, thirdNumID, openid,
						sysService.getWXPayBody(thirdNumID), CommonUtils.parseFloat(count, 0f));
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
	 * 获取我的优惠券
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getMyCoupon")
	@ResponseBody
	public ApiResult getMyCoupon(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
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

			result = userService.getMyCoupon(user);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 获取是否有未读消息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/refreshMessage")
	@ResponseBody
	public ApiResult refreshMessage(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
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

			result = userService.refreshMessage(user);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 获取是否有未读消息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/messageList")
	@ResponseBody
	public ApiResult messageList(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
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

			result = userService.messageList(user);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 获取是否有未读消息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/setReaded")
	@ResponseBody
	public ApiResult setReaded(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String mids = request.getParameter("mids");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(mids)) {
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

			result = userService.setReaded(user, mids);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 获取是否有未读消息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/delMessage")
	@ResponseBody
	public ApiResult delMessage(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String mids = request.getParameter("mids");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(mids)) {
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

			result = userService.delMessage(user, mids);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 获取是否有未读消息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/messageDetail")
	@ResponseBody
	public ApiResult messageDetail(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String messageid = request.getParameter("messageid");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(messageid)
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

			result = userService.messageDetail(user, messageid, Integer.parseInt(page));
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
	@RequestMapping("/receiveCoupon")
	@ResponseBody
	public ApiResult receiveCoupon(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String couponid = request.getParameter("couponid");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(couponid)) {
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

			result = userService.receiveCoupon(user, couponid);
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
	@RequestMapping("/hasPassword")
	@ResponseBody
	public ApiResult hasPassword(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
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

			result = userService.hasPassword(user);
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
	@RequestMapping("/setPassword")
	@ResponseBody
	public ApiResult setPassword(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String password = request.getParameter("password");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(password)) {
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

			result = userService.setPassword(user, password);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}
	
	/**
	 * 邀请注册 
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/invite")
	@ResponseBody
	public ApiResult invite(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String intive_code = request.getParameter("intive_code");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(intive_code)) {
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

			result = userService.invite(user, intive_code);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}
	
	/**
	 * 邀请注册 
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/receiveRedPacket")
	@ResponseBody
	public ApiResult receiveRedPacket(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String packetid = request.getParameter("packetid");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(packetid)) {
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

			result = userService.receiveRedPacket(user, packetid);
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
	@RequestMapping("/setCode")
	@ResponseBody
	public ApiResult setCode(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			
			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}

			/*if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}*/
			result = userService.setCode();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}
	
	
	
	
	
	
	

}
