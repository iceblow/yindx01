package com.uncleserver.controller.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.controller.BaseController;
import com.uncleserver.model.User;
import com.uncleserver.model.UserExtra;
import com.uncleserver.modelVo.ApiResult;
import com.uncleserver.service.api.HomeService;
import com.uncleserver.service.api.UserService;

@Controller
@RequestMapping("/api/home")
public class HomeController extends BaseController {

	@Autowired
	private HomeService homeService;
	@Autowired
	private UserService userService;

	/**
	 * 获取验证码
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/homeUser")
	@ResponseBody
	public ApiResult homeUser(HttpServletRequest request) {
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

			if (CommonUtils.isEmptyString(city)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
			result = homeService.homeUser(city);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 阿姨列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/auntList")
	@ResponseBody
	public ApiResult auntList(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String screentype = request.getParameter("screentype");
			String longitude = request.getParameter("longitude");
			String latitude = request.getParameter("latitude");
			String distance_from = request.getParameter("distance_from");
			String distance_to = request.getParameter("distance_to");
			String name_letter = request.getParameter("name_letter");
			String comment_type = request.getParameter("comment_type");
			String servertype = request.getParameter("servertype");
			String agetype = request.getParameter("agetype");
			String yeartype = request.getParameter("yeartype");
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

			if (CommonUtils.isEmptyString(screentype) || CommonUtils.isEmptyString(page)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			int screentypeI = Integer.parseInt(screentype);
			if (screentypeI == 0) {
				if (CommonUtils.isEmptyString(distance_from)) {
					result.setCode("101");
					result.setMessage("缺少接口参数");
					return result;
				}
			} else if (screentypeI == 1) {
				/*
				 * if (CommonUtils.isEmptyString(name_letter)) {
				 * result.setCode("101"); result.setMessage("缺少接口参数"); return
				 * result; }
				 */
			} else if (screentypeI == 2) {
				if (CommonUtils.isEmptyString(comment_type) || CommonUtils.isEmptyString(servertype)
						|| CommonUtils.isEmptyString(agetype) || CommonUtils.isEmptyString(yeartype)) {
					result.setCode("101");
					result.setMessage("缺少接口参数");
					return result;
				}
			}

			/*
			 * String accesstoken = request.getParameter("accesstoken"); User
			 * user =
			 * userService.selectUserByUserId(CommonUtils.parseInt(userid, 0));
			 * if (user == null) { result.setCode("106");
			 * result.setMessage("用户不存在"); return result; }
			 */

			/*
			 * UserExtra extra =
			 * userService.selectUserExtraByUserID(user.getUserid());
			 * 
			 * if (!checkSession(accesstoken, extra)) { result.setCode("105");
			 * result.setMessage("您的账号已经在别处登录,请重新登录"); return result; }
			 * 
			 * if (checkTokenTime(extra.getTokenTime())) {
			 * result.setCode("107"); result.setMessage("登录信息过期,请重新登录"); return
			 * result; }
			 */

			result = homeService.auntList(screentype, longitude, latitude, distance_from, distance_to, name_letter,
					comment_type, servertype, agetype, yeartype, page);
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
	@RequestMapping("/auntDetail")
	@ResponseBody
	public ApiResult auntDetail(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String userid = request.getParameter("userid");
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
			if (CommonUtils.isEmptyString(userid)) {
				result = homeService.auntDetail(null, auntid);
			} else {
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
				result = homeService.auntDetail(user, auntid);
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
	 * 阿姨列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/companyList")
	@ResponseBody
	public ApiResult companyList(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String screentype = request.getParameter("screentype");
			String companytype = request.getParameter("companytype");
			String longitude = request.getParameter("longitude");
			String latitude = request.getParameter("latitude");
			String distance_from = request.getParameter("distance_from");
			String distance_to = request.getParameter("distance_to");
			String name_letter = request.getParameter("name_letter");
			String comment_type = request.getParameter("comment_type");
			String servertype = request.getParameter("servertype");
			String titletype = request.getParameter("titletype");
			String startype = request.getParameter("startype");
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

			if (CommonUtils.isEmptyString(screentype) || CommonUtils.isEmptyString(page)
					|| CommonUtils.isEmptyString(companytype)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
			int screentypeI = Integer.parseInt(screentype);
			if (screentypeI == 0) {
				if (CommonUtils.isEmptyString(distance_from)) {
					result.setCode("101");
					result.setMessage("缺少接口参数");
					return result;
				}
			} else if (screentypeI == 1) {
				/*
				 * if (CommonUtils.isEmptyString(name_letter)) {
				 * result.setCode("101"); result.setMessage("缺少接口参数"); return
				 * result; }
				 */
			} else if (screentypeI == 2) {
				if (CommonUtils.isEmptyString(comment_type) || CommonUtils.isEmptyString(servertype)
						|| CommonUtils.isEmptyString(titletype) || CommonUtils.isEmptyString(startype)) {
					result.setCode("101");
					result.setMessage("缺少接口参数");
					return result;
				}
			}

			result = homeService.companyList(screentype, companytype, longitude, latitude, distance_from, distance_to,
					name_letter, comment_type, servertype, titletype, startype, page);
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

			result = homeService.companyDetail(companyid);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * APP首页搜索
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public ApiResult search(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String keyword = request.getParameter("keyword");
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

			if (CommonUtils.isEmptyString(keyword)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			result = homeService.search(keyword, type, page);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * APP首页搜索
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/companyDetailMore")
	@ResponseBody
	public ApiResult companyDetailMore(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String companyid = request.getParameter("companyid");
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

			if (CommonUtils.isEmptyString(companyid) || CommonUtils.isEmptyString(page)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			result = homeService.companyDetailMore(companyid, page);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * APP首页搜索
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/likeAunt")
	@ResponseBody
	public ApiResult likeAunt(HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = getApiResult();

		try {

			String auntid = request.getParameter("auntid");
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

			if (CommonUtils.isEmptyString(auntid) || CommonUtils.isEmptyString(userid)) {
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

			result = homeService.likeAunt(auntid, userid);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

}
