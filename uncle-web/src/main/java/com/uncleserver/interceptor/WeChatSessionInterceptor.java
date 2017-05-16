package com.uncleserver.interceptor;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.common.Constant;
import com.uncleserver.common.WxAuth;
import com.uncleserver.model.User;
import com.uncleserver.model.UserExtra;
import com.uncleserver.service.api.WechatService;

/**
 * 微信拦截器
 * 
 * @author guok
 *
 */
public class WeChatSessionInterceptor extends HandlerInterceptorAdapter {

	@Resource
	private WechatService wechatService;

	public String[] allowUrls;

	public void setAllowUrls(String[] allowUrls) {
		this.allowUrls = allowUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

		String url = request.getRequestURL().toString();
		// System.out.println("WeChat::" + url);
		// 判断是否微信请求
		Map<String, String[]> map = request.getParameterMap();
		Set<Entry<String, String[]>> keSet = map.entrySet();
		for (Iterator<Entry<String, String[]>> itr = keSet.iterator(); itr.hasNext();) {
			Map.Entry<String, String[]> me = itr.next();
			String key = me.getKey(); // 获取参数名
			if ("uri".equals(key)) {
				String value = me.getValue()[0]; // 获取参数值
				if (!CommonUtils.isEmptyString(value) && "/wechat/home/register".equals(value)) {
					return true;
				}
			}
		}

		boolean validation = false;
		String ua = request.getHeader("user-agent").toLowerCase();
		if (ua.indexOf("micromessenger") > 0) {// 是微信浏览器
			validation = true;
		}
		// 是否是微信登陆
		if (validation) {
			// 微信登陆跳转判断微信ID，不存在跳转微信授权
			String wxudid = String.valueOf(request.getSession().getAttribute(Constant.SESSION_WXOPENID));
			String wxunionid = String.valueOf(request.getSession().getAttribute(Constant.SESSION_WXUNIONID));
			String userid = String.valueOf(request.getSession().getAttribute(Constant.SESSION_WXUSERID));
			// String nickname =
			// String.valueOf(request.getSession().getAttribute(Constant.SESSION_WXNICKNAME));
			// String headimgurl =
			// String.valueOf(request.getSession().getAttribute(Constant.SESSION_WXHEADIMGURL));
			if (CommonUtils.isEmptyString(wxudid) || "null".equals(wxudid) || CommonUtils.isEmptyString(wxunionid)
					|| "null".equals(wxunionid) || CommonUtils.isEmptyString(userid) || "null".equals(userid)) {// 只要微信的openid为空
				User user = new WxAuth(request, response, wechatService, true).wxAuth();
				if (user == null) {
					return false;
				}
				if (CommonUtils.isEmptyString(user.getPhone())) {
					response.sendRedirect(CommonUtils.properties.getProperty("webRootUrl")
							+ "wechat/auth/go?uri=/wechat/home/register");
				} else {
					response.sendRedirect(
							CommonUtils.properties.getProperty("webRootUrl") + "wechat/auth/go?uri=/wechat/home/home");
				}
			} else {
				User user = wechatService.getUserByUserId(userid);
				if (user == null) {
					return false;
				}
				
				UserExtra extra = wechatService.getUserExtraByUserId(user.getUserid());
				if (extra != null) {
					request.getSession().setAttribute(Constant.SESSION_ACCESSTOKEN, extra.getAccesstoken());
				}

				if (CommonUtils.isEmptyString(user.getPhone())) {
					response.sendRedirect(CommonUtils.properties.getProperty("webRootUrl")
							+ "wechat/auth/go?uri=/wechat/home/register");
				}
			}
		}
		return true;
	}

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}
}
