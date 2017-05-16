package com.uncleserver.web.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JSPFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// System.out.println("JSPFilter--doFilter");
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String url = httpRequest.getRequestURL().toString();
		if (url.contains("/uncle-web/homepage.jsp") || url.contains("/uncle-web/makeorder.jsp")
				|| url.contains("/uncle-web/AboutUs.jsp") || url.contains("/uncle-web/appaboutaunt.jsp")
				|| url.contains("/uncle-web/appaboutuser.jsp") || url.contains("/uncle-web/CollusionDevelopment.jsp")
				|| url.contains("/uncle-web/CommonProblem.jsp") || url.contains("/uncle-web/qr_wap.jsp")
				|| url.contains("/uncle-web/qr.jsp") || url.contains("/uncle-web/company/coupon/coupon1.jsp")
				|| url.contains("/uncle-web/company/coupon/coupon2.jsp")
				|| url.contains("/uncle-web/serverIntroduce.jsp")
				|| url.contains("/uncle-web/serverTeam.jsp")) {
			chain.doFilter(httpRequest, httpResponse);
		} else {
			httpResponse.sendRedirect("http://" + httpRequest.getHeader("Host") + "/uncle-web/homepage.jsp");
		}

	}

	@Override
	public void destroy() {

	}

}
