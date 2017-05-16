package com.uncleserver.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.common.Constant;
import com.uncleserver.model.AuntExtra;
import com.uncleserver.model.CompanyExtra;
import com.uncleserver.model.UserExtra;
import com.uncleserver.modelVo.ApiResult;

@Controller
@RequestMapping("/")
public class BaseController {

	@SuppressWarnings("unchecked")
	protected void keepPara(HttpServletRequest request, ModelAndView mv) {
		Map<String, String[]> map = request.getParameterMap();
		if (map != null)
			for (Entry<String, String[]> e : map.entrySet()) {
				String[] values = e.getValue();
				try {
					if (values.length == 1)
						mv.addObject(e.getKey(), URLDecoder.decode(values[0], "UTF-8"));
					else {
						String[] newValues = new String[values.length];
						for (int i = 0; i < values.length; i++) {
							newValues[i] = URLDecoder.decode(values[i], "UTF-8");
						}
						mv.addObject(e.getKey(), newValues);
					}
				} catch (UnsupportedEncodingException e1) {
					throw new RuntimeException("decode异常：" + values + e1.getMessage());
				}
			}
	}

	/**
	 * 验证 post 还是get
	 * 
	 * @param method
	 * @return
	 */
	protected boolean checkMethod(String method) {
		if ("GET".equals(method) || "POST".equals(method)) {
			return true;
		}
		return false;
	}

	protected boolean checkSession(String accesstoken, UserExtra extra) {
		return !CommonUtils.isEmptyString(accesstoken) && extra != null && accesstoken.equals(extra.getAccesstoken());
	}

	protected boolean checkSession(String accesstoken, AuntExtra extra) {
		return !CommonUtils.isEmptyString(accesstoken) && extra != null && accesstoken.equals(extra.getAccesstoken());
	}

	protected boolean checkSession(String accesstoken, CompanyExtra extra) {
		return !CommonUtils.isEmptyString(accesstoken) && extra != null && accesstoken.equals(extra.getAccesstoken());
	}

	@SuppressWarnings("unchecked")
	protected boolean checkPermission(HttpServletRequest request) {
		Map<String, String[]> param = request.getParameterMap();
		String appid = request.getParameter("appid");
		String accesstime = request.getParameter("accesstime");
		String accessKey = request.getParameter("accesskey");

		if (CommonUtils.isEmptyString(appid) || CommonUtils.isEmptyString(accesstime)
				|| CommonUtils.isEmptyString(accessKey)) {
			return false;
		}

		// 去除appid中的版本信息
		int index = appid.lastIndexOf("_");
		String appidNoVersion = appid.substring(0, index);
		String appKey = null;
		if (Constant.APPID_ANDROID.equals(appidNoVersion)) {
			appKey = Constant.APPKEY_ANDROID;
		} else if (Constant.APPID_IOS.equals(appidNoVersion)) {
			appKey = Constant.APPKEY_IOS;
		} else if (Constant.APPID_AUNT_ANDROID.equals(appidNoVersion)) {
			appKey = Constant.APPKEY_AUNT_ANDROID;
		} else if (Constant.APPID_AUNT_IOS.equals(appidNoVersion)) {
			appKey = Constant.APPKEY_AUNT_IOS;
		} else if (Constant.APPID_WECHAT.equals(appidNoVersion)) {
			appKey = Constant.APPKEY_WECHAT;
		} else {
			return false;
		}
		Iterator<String> ite = param.keySet().iterator();
		List<String> params = new ArrayList<String>();
		while (ite.hasNext()) {
			String t = ite.next();
			if (!"appid".equals(t) && !"accesstime".equals(t) && !"accesskey".equals(t) && !"accesstoken".equals(t)) {
				params.add(t);
			}
		}
		Collections.sort(params, new Comparator<String>() {

			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		String checkString = accesstime + appid + appKey;

		for (String string : params) {
			checkString += string;
		}

		// System.out.println(checkString);
		// System.out.println(CommonUtils.MD5(checkString));
		// System.out.println(accessKey);
		if (accessKey.equals(CommonUtils.MD5(checkString).toLowerCase())) {
			return true;
		}
		return false;
	}

	/**
	 * 检查访问令牌是否过期
	 * 
	 * @param tokentime
	 * @return true 过期
	 */
	protected boolean checkTokenTime(Date tokentime) {
		if (tokentime == null) {
			return true;
		}

		Calendar now = Calendar.getInstance();
		Calendar token = Calendar.getInstance();
		token.setTime(tokentime);
		token.add(Calendar.DAY_OF_MONTH, 30);
		if (token.before(now)) {
			return true;
		}

		return false;
	}

	protected ApiResult getApiResult() {
		return new ApiResult();
	}

	protected String getClientVersion(HttpServletRequest request) {
		String appid = request.getParameter("appid");
		int index = appid.lastIndexOf("_");
		String version = appid.substring(index + 1, appid.length());
		return version;
	}
}
