package com.uncleserver.controller.api;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.uncleserver.service.api.AuntUserService;

@Controller
@RequestMapping("/auntapi/user")
public class AuntUserController extends BaseController {

	@Autowired
	private AuntUserService auntUserService;

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
			String user_type = request.getParameter("user_type");
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
				result = auntUserService.sendVcode(phone, typeInt, user_type);
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
			String user_type = request.getParameter("user_type");
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
			int userTypeI = CommonUtils.parseInt(user_type, 0);
			if (typeInt == 0 || typeInt == 1 || typeInt == 2 || typeInt == 4) {
				result = auntUserService.checkVcode(phone, vcode, typeInt, userTypeI);
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

			result = auntUserService.userRegister(phone, password, vcode, request.getSession().getId().toLowerCase());

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

			result = auntUserService.login(phone, password, request.getSession().getId().toLowerCase());

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
			AuntExtra extra = auntUserService.selectAuntExtraByToken(accesstoken);

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
			result = auntUserService.autoLogin(accesstoken);
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

			result = auntUserService.thirdLogin(openid, thirdtype, request.getSession().getId().toLowerCase(), nickname,
					avatarurl);
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
//			String password = request.getParameter("password");

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
			Aunt aunt = auntUserService.selectAuntByAuntID(CommonUtils.parseInt(userid, 0));
			if (aunt == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}
			AuntExtra extra = auntUserService.selectAuntExtraByAuntID(aunt.getAuntid());
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

			result = auntUserService.bindPhone(aunt, phone, vcode,
					request.getSession().getId().toLowerCase());

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
			String user_type = request.getParameter("user_type");

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
					|| CommonUtils.isEmptyString(vcode) || CommonUtils.isEmptyString(user_type)) {

				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			result = auntUserService.changePassword(phone, vcode, newpsw, user_type);
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
	@RequestMapping("/companyLogin")
	@ResponseBody
	public ApiResult companyLogin(HttpServletRequest request) {
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

			result = auntUserService.companyLogin(phone, password, request.getSession().getId().toLowerCase());

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
	@RequestMapping("/companyAutoLogin")
	@ResponseBody
	public ApiResult companyAutoLogin(HttpServletRequest request) {
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
			CompanyExtra extra = auntUserService.selectCompanyByToken(accesstoken);

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
			result = auntUserService.companyAutoLogin(accesstoken);
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
	@RequestMapping("/heartBeat")
	@ResponseBody
	public ApiResult heartBeat(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String user_type = request.getParameter("user_type");
			String longitude = request.getParameter("longitude");
			String latitude = request.getParameter("latitude");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(user_type)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
			String accesstoken = request.getParameter("accesstoken");

			result = auntUserService.heartBeat(accesstoken, CommonUtils.parseInt(user_type, 0), longitude, latitude);
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
	@RequestMapping("/feedBack")
	@ResponseBody
	public ApiResult feedBack(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String content = request.getParameter("content");
			String userid = request.getParameter("userid");
			String user_type = request.getParameter("user_type");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(content)
					|| CommonUtils.isEmptyString(user_type)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
			String accesstoken = request.getParameter("accesstoken");
			result = auntUserService.feedBack(content, CommonUtils.parseInt(userid, 0),
					CommonUtils.parseInt(user_type, 0));
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
			Aunt aunt = auntUserService.selectAuntByAuntId(CommonUtils.parseInt(userid, 0));
			if (aunt == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}
			AuntExtra extra = auntUserService.selectAuntExtraByAuntId(CommonUtils.parseInt(userid, 0));
			String accesstoken = request.getParameter("accesstoken");
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
			result = auntUserService.sign(aunt, extra);
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
	@RequestMapping("/refreshMessage")
	@ResponseBody
	public ApiResult refreshMessage(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String user_type = request.getParameter("user_type");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(user_type)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			if (CommonUtils.parseInt(user_type, 0) == 0) {
				Aunt aunt = auntUserService.selectAuntByAuntId(CommonUtils.parseInt(userid, 0));
				if (aunt == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}
				AuntExtra extra = auntUserService.selectAuntExtraByAuntId(CommonUtils.parseInt(userid, 0));
				String accesstoken = request.getParameter("accesstoken");
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
				result = auntUserService.refreshMessage(aunt, null);
			} else if (CommonUtils.parseInt(user_type, 0) == 1) {
				Company company = auntUserService.selectCompanyByCid(CommonUtils.parseInt(userid, 0));
				if (company == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}
				CompanyExtra extra = auntUserService.selectCompanyExtraByCid(CommonUtils.parseInt(userid, 0));
				String accesstoken = request.getParameter("accesstoken");
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
				result = auntUserService.refreshMessage(null, company);

			} else {

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
	 * 第三方登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/messageList")
	@ResponseBody
	public ApiResult messageList(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String user_type = request.getParameter("user_type");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(user_type)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			if (CommonUtils.parseInt(user_type, 0) == 0) {
				Aunt aunt = auntUserService.selectAuntByAuntId(CommonUtils.parseInt(userid, 0));
				if (aunt == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}
				AuntExtra extra = auntUserService.selectAuntExtraByAuntId(CommonUtils.parseInt(userid, 0));
				String accesstoken = request.getParameter("accesstoken");
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
				result = auntUserService.messageList(aunt, null);
			} else if (CommonUtils.parseInt(user_type, 0) == 1) {
				Company company = auntUserService.selectCompanyByCid(CommonUtils.parseInt(userid, 0));
				if (company == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}
				CompanyExtra extra = auntUserService.selectCompanyExtraByCid(CommonUtils.parseInt(userid, 0));
				String accesstoken = request.getParameter("accesstoken");
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
				result = auntUserService.messageList(null, company);

			} else {

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
	 * 第三方登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/setReaded")
	@ResponseBody
	public ApiResult setReaded(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String user_type = request.getParameter("user_type");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(user_type)
					|| CommonUtils.isEmptyString(mids)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			if (CommonUtils.parseInt(user_type, 0) == 0) {
				Aunt aunt = auntUserService.selectAuntByAuntId(CommonUtils.parseInt(userid, 0));
				if (aunt == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}
				AuntExtra extra = auntUserService.selectAuntExtraByAuntId(CommonUtils.parseInt(userid, 0));
				String accesstoken = request.getParameter("accesstoken");
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
				result = auntUserService.setReaded(aunt, null, mids);
			} else if (CommonUtils.parseInt(user_type, 0) == 1) {
				Company company = auntUserService.selectCompanyByCid(CommonUtils.parseInt(userid, 0));
				if (company == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}
				CompanyExtra extra = auntUserService.selectCompanyExtraByCid(CommonUtils.parseInt(userid, 0));
				String accesstoken = request.getParameter("accesstoken");
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
				result = auntUserService.setReaded(null, company, mids);

			} else {

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
	 * 第三方登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/delMessage")
	@ResponseBody
	public ApiResult delMessage(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String user_type = request.getParameter("user_type");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(user_type)
					|| CommonUtils.isEmptyString(mids)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			if (CommonUtils.parseInt(user_type, 0) == 0) {
				Aunt aunt = auntUserService.selectAuntByAuntId(CommonUtils.parseInt(userid, 0));
				if (aunt == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}
				AuntExtra extra = auntUserService.selectAuntExtraByAuntId(CommonUtils.parseInt(userid, 0));
				String accesstoken = request.getParameter("accesstoken");
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
				result = auntUserService.delMessage(aunt, null, mids);
			} else if (CommonUtils.parseInt(user_type, 0) == 1) {
				Company company = auntUserService.selectCompanyByCid(CommonUtils.parseInt(userid, 0));
				if (company == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}
				CompanyExtra extra = auntUserService.selectCompanyExtraByCid(CommonUtils.parseInt(userid, 0));
				String accesstoken = request.getParameter("accesstoken");
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
				result = auntUserService.delMessage(null, company, mids);

			} else {

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
	 * 第三方登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/messageDetail")
	@ResponseBody
	public ApiResult messageDetail(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String user_type = request.getParameter("user_type");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(user_type)
					|| CommonUtils.isEmptyString(messageid) || CommonUtils.isEmptyString(page)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			if (CommonUtils.parseInt(user_type, 0) == 0) {
				Aunt aunt = auntUserService.selectAuntByAuntId(CommonUtils.parseInt(userid, 0));
				if (aunt == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}
				AuntExtra extra = auntUserService.selectAuntExtraByAuntId(CommonUtils.parseInt(userid, 0));
				String accesstoken = request.getParameter("accesstoken");
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
				result = auntUserService.messageDetail(aunt, null, messageid, CommonUtils.parseInt(page, 0));
			} else if (CommonUtils.parseInt(user_type, 0) == 1) {
				Company company = auntUserService.selectCompanyByCid(CommonUtils.parseInt(userid, 0));
				if (company == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}
				CompanyExtra extra = auntUserService.selectCompanyExtraByCid(CommonUtils.parseInt(userid, 0));
				String accesstoken = request.getParameter("accesstoken");
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
				result = auntUserService.messageDetail(null, company, messageid, CommonUtils.parseInt(page, 0));

			} else {

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
	 * 第三方登录
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

			Aunt aunt = auntUserService.selectAuntByAuntId(CommonUtils.parseInt(userid, 0));
			if (aunt == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}
			AuntExtra extra = auntUserService.selectAuntExtraByAuntId(CommonUtils.parseInt(userid, 0));
			String accesstoken = request.getParameter("accesstoken");
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
			result = auntUserService.getSignMonth(aunt, time);
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
	@RequestMapping("/updateMustInfo")
	@ResponseBody
	public ApiResult updateMustInfo(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String real_name = request.getParameter("real_name");
			String sex = request.getParameter("sex");
			String origin_place = request.getParameter("origin_place");
			String idcard_num = request.getParameter("idcard_num");
			String nation = request.getParameter("nation");
			String work_year = request.getParameter("work_year");
			String server_ids = request.getParameter("server_ids");
			String train_state = request.getParameter("train_state");
			String language = request.getParameter("language");
			String character = request.getParameter("character");
			String now_address = request.getParameter("now_address");
			String idcard_picids = request.getParameter("idcard_picids");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(real_name)
					|| CommonUtils.isEmptyString(sex) || CommonUtils.isEmptyString(origin_place)
					|| CommonUtils.isEmptyString(idcard_num) || CommonUtils.isEmptyString(nation)
					|| CommonUtils.isEmptyString(work_year) || CommonUtils.isEmptyString(server_ids)
					|| CommonUtils.isEmptyString(train_state) || CommonUtils.isEmptyString(language)
					|| CommonUtils.isEmptyString(character) || CommonUtils.isEmptyString(now_address)
					|| CommonUtils.isEmptyString(idcard_picids)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			Aunt aunt = auntUserService.selectAuntByAuntId(CommonUtils.parseInt(userid, 0));
			if (aunt == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}
			AuntExtra extra = auntUserService.selectAuntExtraByAuntId(CommonUtils.parseInt(userid, 0));
			String accesstoken = request.getParameter("accesstoken");
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
			result = auntUserService.updateMustInfo(aunt, real_name, sex, origin_place, idcard_num, nation, work_year,
					server_ids, train_state, language, character, now_address,
					request.getSession().getId().toLowerCase(),idcard_picids);
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
	@RequestMapping("/getAuntInfoSet")
	@ResponseBody
	public ApiResult getAuntInfoSet(HttpServletRequest request) {
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

			result = auntUserService.getAuntInfoSet();
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
	@RequestMapping("/updateOptionalInfo")
	@ResponseBody
	public ApiResult updateOptionalInfo(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String culture = request.getParameter("culture");
			String home_address = request.getParameter("home_address");
			String religion = request.getParameter("religion");
			String political = request.getParameter("political");
			String height = request.getParameter("height");
			String weight = request.getParameter("weight");
			String marriage = request.getParameter("marriage");
			String blood_type = request.getParameter("blood_type");
			String display = request.getParameter("display");
			String work_his = request.getParameter("work_his");
			String self_comment = request.getParameter("self_comment");
			String hobby = request.getParameter("hobby");
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

			Aunt aunt = auntUserService.selectAuntByAuntId(CommonUtils.parseInt(userid, 0));
			if (aunt == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}
			AuntExtra extra = auntUserService.selectAuntExtraByAuntId(CommonUtils.parseInt(userid, 0));
			String accesstoken = request.getParameter("accesstoken");
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
			result = auntUserService.updateOptionalInfo(aunt, culture, home_address, religion, political, height,
					weight, marriage, blood_type, display, work_his, self_comment, hobby);
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

			Aunt aunt = auntUserService.selectAuntByAuntId(CommonUtils.parseInt(userid, 0));
			if (aunt == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}
			AuntExtra extra = auntUserService.selectAuntExtraByAuntId(CommonUtils.parseInt(userid, 0));
			String accesstoken = request.getParameter("accesstoken");
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
			result = auntUserService.refreshUserInfo(aunt, extra);
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
	@RequestMapping("/updateSet")
	@ResponseBody
	public ApiResult updateSet(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String voice_state = request.getParameter("voice_state");
			String text_size = request.getParameter("text_size");
			String user_type = request.getParameter("user_type");
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

			if (CommonUtils.parseInt(user_type, 0) == 0) {
				Aunt aunt = auntUserService.selectAuntByAuntId(CommonUtils.parseInt(userid, 0));
				if (aunt == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}
				AuntExtra extra = auntUserService.selectAuntExtraByAuntId(CommonUtils.parseInt(userid, 0));
				String accesstoken = request.getParameter("accesstoken");
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
				result = auntUserService.updateSet(extra, voice_state, text_size);
			} else if (CommonUtils.parseInt(user_type, 0) == 1) {
				Company company = auntUserService.selectCompanyByCid(CommonUtils.parseInt(userid, 0));
				if (company == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}
				CompanyExtra extra = auntUserService.selectCompanyExtraByCid(CommonUtils.parseInt(userid, 0));
				String accesstoken = request.getParameter("accesstoken");
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
				result = auntUserService.updateSet(extra, voice_state, text_size);
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
	 * 第三方登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getCommentList")
	@ResponseBody
	public ApiResult getCommentList(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String user_type = request.getParameter("user_type");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(user_type)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
			if (CommonUtils.parseInt(user_type, 0) == 0) {
				AuntExtra extra = auntUserService.selectAuntExtraByAuntId(CommonUtils.parseInt(userid, 0));
				result = auntUserService.getCommentList(userid, extra.getAccesstoken(), user_type,
						CommonUtils.parseInt(page, 1));
			} else if (CommonUtils.parseInt(user_type, 0) == 1) {
				CompanyExtra extra = auntUserService.selectCompanyExtraByCid(CommonUtils.parseInt(userid, 0));
				result = auntUserService.getCommentList(userid, extra.getAccesstoken(), user_type,
						CommonUtils.parseInt(page, 1));
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
	 * 第三方登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/changeInfo")
	@ResponseBody
	public ApiResult changeInfo(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String key = request.getParameter("key");
			String value = request.getParameter("value");
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
			Aunt aunt = auntUserService.selectAuntByAuntId(CommonUtils.parseInt(userid, 0));
			if (aunt == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}
			AuntExtra extra = auntUserService.selectAuntExtraByAuntId(CommonUtils.parseInt(userid, 0));
			String accesstoken = request.getParameter("accesstoken");
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
			result = auntUserService.changeInfo(aunt, key, value);
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
	@RequestMapping("/getBalanceRecord")
	@ResponseBody
	public ApiResult getBalanceRecord(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String user_type = request.getParameter("user_type");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(user_type)
					|| CommonUtils.isEmptyString(page)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			int userTypeInt = CommonUtils.parseInt(user_type, 0);
			if (userTypeInt == 0) {// 阿姨
				Aunt aunt = auntUserService.selectAuntByAuntId(CommonUtils.parseInt(userid, 0));
				if (aunt == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}

				AuntExtra extra = auntUserService.selectAuntExtraByAuntId(aunt.getAuntid());

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
				result = auntUserService.getBalanceRecord(CommonUtils.parseInt(page, 0), extra);
			} else {// 公司
				Company company = auntUserService.selectCompanyByCid(CommonUtils.parseInt(userid, 0));
				if (company == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}

				CompanyExtra extra = auntUserService.selectCompanyExtraByCid(company.getCompanyid());

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
				result = auntUserService.getBalanceRecord(CommonUtils.parseInt(page, 1), extra);

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
	 * 第三方登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getPointRecord")
	@ResponseBody
	public ApiResult getPointRecord(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(page)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
			Aunt aunt = auntUserService.selectAuntByAuntId(CommonUtils.parseInt(userid, 0));
			if (aunt == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}
			AuntExtra extra = auntUserService.selectAuntExtraByAuntId(CommonUtils.parseInt(userid, 0));
			String accesstoken = request.getParameter("accesstoken");
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
			result = auntUserService.getPointRecord(aunt, CommonUtils.parseInt(page, 0));
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
	@RequestMapping("/setAliInfo")
	@ResponseBody
	public ApiResult setAliInfo(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String user_type = request.getParameter("user_type");
			String account = request.getParameter("account");
			String name = request.getParameter("name");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(user_type)
					|| CommonUtils.isEmptyString(account) || CommonUtils.isEmptyString(name)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
			if (CommonUtils.parseInt(user_type, 0) == 0) {
				Aunt aunt = auntUserService.selectAuntByAuntId(CommonUtils.parseInt(userid, 0));
				if (aunt == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}
				AuntExtra extra = auntUserService.selectAuntExtraByAuntId(CommonUtils.parseInt(userid, 0));
				String accesstoken = request.getParameter("accesstoken");
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
				result = auntUserService.setAliInfo(userid, user_type, account, name);
			} else if (CommonUtils.parseInt(user_type, 0) == 1) {
				Company company = auntUserService.selectCompanyByCid(CommonUtils.parseInt(userid, 0));
				if (company == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}
				CompanyExtra extra = auntUserService.selectCompanyExtraByCid(CommonUtils.parseInt(userid, 0));
				String accesstoken = request.getParameter("accesstoken");
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
				result = auntUserService.setAliInfo(userid, user_type, account, name);
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
	 * 提现
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/cash")
	@ResponseBody
	public ApiResult cash(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String user_type = request.getParameter("user_type");
			String type = request.getParameter("type");
			String count = request.getParameter("count");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(user_type)
					|| CommonUtils.isEmptyString(type) || CommonUtils.isEmptyString(count)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
			if (CommonUtils.parseInt(user_type, 0) == 0) {
				Aunt aunt = auntUserService.selectAuntByAuntId(CommonUtils.parseInt(userid, 0));
				if (aunt == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}
				AuntExtra extra = auntUserService.selectAuntExtraByAuntId(CommonUtils.parseInt(userid, 0));
				String accesstoken = request.getParameter("accesstoken");
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
				result = auntUserService.cash(userid, user_type, type, count);
			} else if (CommonUtils.parseInt(user_type, 0) == 1) {
				Company company = auntUserService.selectCompanyByCid(CommonUtils.parseInt(userid, 0));
				if (company == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}
				CompanyExtra extra = auntUserService.selectCompanyExtraByCid(CommonUtils.parseInt(userid, 0));
				String accesstoken = request.getParameter("accesstoken");
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
				result = auntUserService.cash(userid, user_type, type, count);
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
	 * 刷新阿姨信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/refreshInfo")
	@ResponseBody
	public ApiResult refreshInfo(HttpServletRequest request) {
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
			Aunt aunt = auntUserService.selectAuntByAuntId(CommonUtils.parseInt(userid, 0));
			if (aunt == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}
			AuntExtra extra = auntUserService.selectAuntExtraByAuntId(CommonUtils.parseInt(userid, 0));
			String accesstoken = request.getParameter("accesstoken");
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
			result = auntUserService.refreshInfo(aunt, extra);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 刷新阿姨信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getTutorialList")
	@ResponseBody
	public ApiResult getTutorialList(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

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

			if (CommonUtils.isEmptyString(page)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			result = auntUserService.getTutorialList(CommonUtils.parseInt(page, 0));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 刷新阿姨信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/uploadViewingTime")
	@ResponseBody
	public ApiResult uploadViewingTime(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String tutorialid = request.getParameter("tutorialid");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(tutorialid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
			Aunt aunt = auntUserService.selectAuntByAuntId(CommonUtils.parseInt(userid, 0));
			if (aunt == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}
			AuntExtra extra = auntUserService.selectAuntExtraByAuntId(CommonUtils.parseInt(userid, 0));
			String accesstoken = request.getParameter("accesstoken");
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
			result = auntUserService.uoloadViewingTime(userid, tutorialid);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	@RequestMapping("/logout")
	@ResponseBody
	public ApiResult logout(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String user_type = request.getParameter("user_type");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(user_type)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			if (CommonUtils.parseInt(user_type, 0) == 0) {
				Aunt aunt = auntUserService.selectAuntByAuntId(CommonUtils.parseInt(userid, 0));
				if (aunt == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}
				AuntExtra extra = auntUserService.selectAuntExtraByAuntId(CommonUtils.parseInt(userid, 0));
				String accesstoken = request.getParameter("accesstoken");
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
				result = auntUserService.logout(extra);
			} else if (CommonUtils.parseInt(user_type, 0) == 1) {
				Company company = auntUserService.selectCompanyByCid(CommonUtils.parseInt(userid, 0));
				if (company == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}
				CompanyExtra extra = auntUserService.selectCompanyExtraByCid(CommonUtils.parseInt(userid, 0));
				String accesstoken = request.getParameter("accesstoken");
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
				result = auntUserService.logout(extra);
			}

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	@RequestMapping("/hasPassword")
	@ResponseBody
	public ApiResult hasPassword(HttpServletRequest request) {
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

			Aunt aunt = auntUserService.selectAuntByAuntId(CommonUtils.parseInt(userid, 0));
			if (aunt == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}
			AuntExtra extra = auntUserService.selectAuntExtraByAuntId(CommonUtils.parseInt(userid, 0));
			String accesstoken = request.getParameter("accesstoken");
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

			result.setCode("1");
			result.setMessage("请求成功");
			HashMap<String, Object> map = new HashMap<>();
			if (CommonUtils.isEmptyString(aunt.getPassword())) {
				map.put("haspassword", 0);
			} else {
				map.put("haspassword", 1);
			}
			result.setResult(map);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	@RequestMapping("/setPassword")
	@ResponseBody
	public ApiResult setPassword(HttpServletRequest request) {
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

			Aunt aunt = auntUserService.selectAuntByAuntId(CommonUtils.parseInt(userid, 0));
			if (aunt == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}
			AuntExtra extra = auntUserService.selectAuntExtraByAuntId(CommonUtils.parseInt(userid, 0));
			String accesstoken = request.getParameter("accesstoken");
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

			result = auntUserService.setPassword(aunt, password);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}
	
	
	@RequestMapping("/invite")
	@ResponseBody
	public ApiResult invite(HttpServletRequest request) {
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

				Aunt aunt = auntUserService.selectAuntByAuntId(CommonUtils.parseInt(userid, 0));
				if (aunt == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}
				AuntExtra extra = auntUserService.selectAuntExtraByAuntId(CommonUtils.parseInt(userid, 0));
				String accesstoken = request.getParameter("accesstoken");
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
				result = auntUserService.invite(aunt, intive_code);
			    return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}
	
	@RequestMapping("/receiveRedPacket")
	@ResponseBody
	public ApiResult receiveRedPacket(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String user_type = request.getParameter("userType");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(user_type)||CommonUtils.isEmptyString(packetid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			if (CommonUtils.parseInt(user_type, 0) == 0) {
				Aunt aunt = auntUserService.selectAuntByAuntId(CommonUtils.parseInt(userid, 0));
				if (aunt == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}
				AuntExtra extra = auntUserService.selectAuntExtraByAuntId(CommonUtils.parseInt(userid, 0));
				String accesstoken = request.getParameter("accesstoken");
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
				result = auntUserService.receiveRedPacket(aunt, null, packetid);
			} else if (CommonUtils.parseInt(user_type, 0) == 1) {
				Company company = auntUserService.selectCompanyByCid(CommonUtils.parseInt(userid, 0));
				if (company == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}
				CompanyExtra extra = auntUserService.selectCompanyExtraByCid(CommonUtils.parseInt(userid, 0));
				String accesstoken = request.getParameter("accesstoken");
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
				result = auntUserService.receiveRedPacket(null, company, packetid);
			}

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}
	
	@RequestMapping("/redPacketList")
	@ResponseBody
	public ApiResult redPacketList(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
			String user_type = request.getParameter("user_type");
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

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(user_type)||CommonUtils.isEmptyString(page)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			if (CommonUtils.parseInt(user_type, 0) == 0) {
				Aunt aunt = auntUserService.selectAuntByAuntId(CommonUtils.parseInt(userid, 0));
				if (aunt == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}
				AuntExtra extra = auntUserService.selectAuntExtraByAuntId(CommonUtils.parseInt(userid, 0));
				String accesstoken = request.getParameter("accesstoken");
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
				result = auntUserService.redPacketList(aunt, null, page);
			} else if (CommonUtils.parseInt(user_type, 0) == 1) {
				Company company = auntUserService.selectCompanyByCid(CommonUtils.parseInt(userid, 0));
				if (company == null) {
					result.setCode("106");
					result.setMessage("用户不存在");
					return result;
				}
				CompanyExtra extra = auntUserService.selectCompanyExtraByCid(CommonUtils.parseInt(userid, 0));
				String accesstoken = request.getParameter("accesstoken");
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
				result = auntUserService.redPacketList(null, company, page);
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
			result = auntUserService.setCode();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}
	

}
