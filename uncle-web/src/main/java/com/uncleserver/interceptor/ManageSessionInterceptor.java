package com.uncleserver.interceptor;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 运营管理后台拦截器
 * 
 * @author guok
 *
 */
public class ManageSessionInterceptor extends HandlerInterceptorAdapter {
	public String[] allowUrls;

	public void setAllowUrls(String[] allowUrls) {
		this.allowUrls = allowUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

		String url = request.getRequestURL().toString();
		// System.out.println("Manage::" + url);
		// 判断是否微信请求
		Map<String, String[]> map = request.getParameterMap();
		Set<Entry<String, String[]>> keSet = map.entrySet();

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
