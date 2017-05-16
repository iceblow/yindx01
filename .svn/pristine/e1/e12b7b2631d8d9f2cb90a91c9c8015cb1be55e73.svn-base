package com.uncleserver.controller.wechat;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uncleserver.common.SignUtil;
import com.uncleserver.controller.BaseController;

@Controller
@RequestMapping("/wechat/auth")
public class WeChatAuthController extends BaseController {
	/**
	 * 微信授权重定向
	 * 
	 * @param session
	 * @param reurl
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/wxsq")
	public ModelAndView xqsq(HttpSession session, String reurl, int needuserinfo) throws UnsupportedEncodingException {
		if (reurl.contains("&code=")) {
			reurl = reurl.replaceAll("&code=", "&old_code=");
		}

		String URI = "";
		if (needuserinfo == 0) {
			URI = "https://open.weixin.qq.com/connect/oauth2/authorize?" + "appid=" + SignUtil.AppId + "&redirect_uri="
					+ URLEncoder.encode(reurl, "UTF-8")
					+ "&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		} else {
			URI = "https://open.weixin.qq.com/connect/oauth2/authorize?" + "appid=" + SignUtil.AppId + "&redirect_uri="
					+ URLEncoder.encode(reurl, "UTF-8")
					+ "&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
		}

		ModelAndView mov = new ModelAndView("redirect:" + URI);
		return mov;
	}

	@RequestMapping("/go")
	public ModelAndView go(HttpServletRequest request, String uri) {
		ModelAndView mv = new ModelAndView(uri);
		this.keepPara(request, mv);
		return mv;
	}
}
