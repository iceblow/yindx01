package com.uncleserver.controller.api;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.uncleserver.common.CommonUtils;
import com.uncleserver.common.Sha1Util;
import com.uncleserver.common.SignUtil;
import com.uncleserver.controller.BaseController;
import com.uncleserver.controller.wechat.WeiXinEntity;
import com.uncleserver.model.User;
import com.uncleserver.model.UserExtra;
import com.uncleserver.modelVo.ApiResult;
import com.uncleserver.service.api.SystemService;

@Controller
@RequestMapping("/api/system")
public class SystemController extends BaseController {

	@Autowired
	private SystemService systemService;

	/**
	 * 检查新版本
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/checkversion")
	@ResponseBody
	public ApiResult checkVersion(HttpServletRequest request) {
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

			String appid = request.getParameter("appid");
			if (CommonUtils.isEmptyString(appid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
			result = systemService.sendVcode(appid);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 获取城市列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/cityList")
	@ResponseBody
	public ApiResult cityList(HttpServletRequest request) {
		ApiResult result = getApiResult();
		try {
			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}
			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}
			result = systemService.getCityList();

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 注册用户端推送信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/registerPush")
	@ResponseBody
	public ApiResult registerPush(HttpServletRequest request) {
		ApiResult result = getApiResult();
		try {
			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}
			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}

			String userid = request.getParameter("userid");
			String pushkey = request.getParameter("pushkey");
			String devicetype = request.getParameter("devicetype");

			if (CommonUtils.isEmptyString(pushkey) || CommonUtils.isEmptyString(devicetype)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			result = systemService.registerPush(userid, pushkey, devicetype);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 注册阿姨端推送信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/registerAuntPush")
	@ResponseBody
	public ApiResult registerAuntPush(HttpServletRequest request) {
		ApiResult result = getApiResult();
		try {
			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}
			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}

			String userid = request.getParameter("userid");
			String user_type = request.getParameter("user_type");
			String pushkey = request.getParameter("pushkey");
			String devicetype = request.getParameter("devicetype");

			if (CommonUtils.isEmptyString(pushkey) || CommonUtils.isEmptyString(devicetype)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
			int useridInt = CommonUtils.parseInt(userid, 0);
			if (useridInt > 0 && CommonUtils.isEmptyString(user_type)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			result = systemService.registerAuntPush(useridInt, user_type, pushkey, devicetype);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}
	
	/**
	 * 获取服务协议,服务细则信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getWebHtml")
	@ResponseBody
	public ApiResult getWebHtml(HttpServletRequest request) {
		ApiResult result = getApiResult();
		try {
			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}
			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}

			String type = request.getParameter("type");
			String relation_id = request.getParameter("relation_id");

			if (CommonUtils.isEmptyString(type) || ("1".equals(type) && CommonUtils.isEmptyString(relation_id))) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
			

			result = systemService.getWebHtml(type, relation_id);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}
	
	/**
	 * 获取评论、退单理由信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getReasonUser")
	@ResponseBody
	public ApiResult getReasonUser(HttpServletRequest request) {
		ApiResult result = getApiResult();
		try {
			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}
			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}
			
			String type = request.getParameter("type");
			if (CommonUtils.isEmptyString(type)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
			
			result = systemService.getReasonUser(type);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}
	
	/**
	 * 获取评论、退单理由信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getReasonAunt")
	@ResponseBody
	public ApiResult getReasonAunt(HttpServletRequest request) {
		ApiResult result = getApiResult();
		try {
			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}
			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}

			String type = request.getParameter("type");
			if (CommonUtils.isEmptyString(type)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
			
			result = systemService.getReasonAunt(type);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}
	
	/**
	 * 获取关于画面地址
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAbout")
	@ResponseBody
	public ApiResult getAbout(HttpServletRequest request) {
		ApiResult result = getApiResult();
		try {
			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}
			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}

			String type = request.getParameter("type");
			if (CommonUtils.isEmptyString(type)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
			
			result = systemService.getAbout(type);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 设置用户的区县
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/setArea")
	@ResponseBody
	public ApiResult setArea(HttpServletRequest request) {
		ApiResult result = getApiResult();
		try {
			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}
			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}
			
			String userid = request.getParameter("userid");
			String area = request.getParameter("area");
			
			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(area)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
			
			String accesstoken = request.getParameter("accesstoken");
			User user = systemService.selectUserByUserId(CommonUtils.parseInt(userid, 0));
			if (user == null) {
				result.setCode("106");
				result.setMessage("用户不存在");
				return result;
			}

			UserExtra extra = systemService.selectUserExtraByUserID(user.getUserid());

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
			
			result = systemService.setArea(extra,area);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}
	
	/**
	 * 获取设备的推送设置(用户端)
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getPushSet")
	@ResponseBody
	public ApiResult getPushSet(HttpServletRequest request) {
		ApiResult result = getApiResult();
		try {
			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}
			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}

			String push_key = request.getParameter("push_key");
			String devicetype = request.getParameter("devicetype");
			
			if (CommonUtils.isEmptyString(push_key) || CommonUtils.isEmptyString(devicetype)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
			
			result = systemService.getPushSet(push_key,devicetype);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}
	
	/**
	 * 设置设备的推送信息(用户端)
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/setPush")
	@ResponseBody
	public ApiResult setPush(HttpServletRequest request) {
		ApiResult result = getApiResult();
		try {
			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}
			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}
			
			String push_key = request.getParameter("push_key");
			String devicetype = request.getParameter("devicetype");
			String isaccept = request.getParameter("isaccept");
			
			if (CommonUtils.isEmptyString(push_key) || CommonUtils.isEmptyString(devicetype) || CommonUtils.isEmptyString(isaccept)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
			
			result = systemService.setPush(push_key,devicetype,isaccept);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}
	
	/**
	 * 获取设备的推送设置(阿姨端)
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAuntPushSet")
	@ResponseBody
	public ApiResult getAuntPushSet(HttpServletRequest request) {
		ApiResult result = getApiResult();
		try {
			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}
			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}
			
			String push_key = request.getParameter("push_key");
			String devicetype = request.getParameter("devicetype");
			
			if (CommonUtils.isEmptyString(push_key) || CommonUtils.isEmptyString(devicetype)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
			
			result = systemService.getAuntPushSet(push_key,devicetype);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}
	
	/**
	 * 设置设备的推送信息(阿姨端)
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/setAuntPush")
	@ResponseBody
	public ApiResult setAuntPush(HttpServletRequest request) {
		ApiResult result = getApiResult();
		try {
			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}
			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}
			
			String push_key = request.getParameter("push_key");
			String devicetype = request.getParameter("devicetype");
			String isaccept = request.getParameter("isaccept");
			
			if (CommonUtils.isEmptyString(push_key) || CommonUtils.isEmptyString(devicetype) || CommonUtils.isEmptyString(isaccept)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
			
			result = systemService.setAuntPush(push_key,devicetype,isaccept);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getWxPreperSign")
	public ApiResult getWxPreperSign(String sign_backurl, HttpServletResponse response) {
		ApiResult result = getApiResult();
		result.setCode("1");
		result.setMessage("");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		String currTime = CommonUtils.getCurrTime();
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = CommonUtils.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		String nonce_str = strTime + strRandom;
		String timestamp = Sha1Util.getTimeStamp();
		String jsapi_ticket = new WeiXinEntity().getJsTicket();
		String appid = SignUtil.AppId;
		String signstr = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url="
				+ sign_backurl;
		String sign = Sha1Util.getSha1(signstr);
		map.put("nonce_str", nonce_str);
		map.put("timestamp", timestamp);
		map.put("jsapi_ticket", jsapi_ticket);
		map.put("appid", appid);
		map.put("sign", sign);
		result.setResult(map);
		return result;
	}
}
