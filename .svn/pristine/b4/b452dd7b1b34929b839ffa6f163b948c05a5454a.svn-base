/**
 * 
 */
package com.uncleserver.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.uncleserver.model.User;
import com.uncleserver.model.UserExtra;
import com.uncleserver.service.api.WechatService;
import com.vdurmont.emoji.EmojiParser;

/**
 * @author wangcl Session拦截微信授权类
 */
public class WxAuth {

	HttpServletRequest request;
	HttpServletResponse response;

	WechatService wechatService;

	String openId, accessToken, nickname, headimgurl;
	String unionId, code = null;

	boolean needuserinfo = false;// 是否需要去获取用户信息,默认不需要

	public WxAuth(HttpServletRequest request, HttpServletResponse response, WechatService wechatService) {
		this.request = request;
		this.response = response;
		this.wechatService = wechatService;
	}

	public WxAuth(HttpServletRequest request, HttpServletResponse response, WechatService wechatService,
			boolean needuserinfo) {
		this.request = request;
		this.response = response;
		this.wechatService = wechatService;
		this.needuserinfo = needuserinfo;
	}

	public User wxAuth() {
		code = request.getParameter("code");
		if (!CommonUtils.isEmptyString(code)) {
			if (getAccessToken(code)) {
				if (!CommonUtils.isEmptyString(unionId)) {
					request.getSession().setAttribute("wxauth", 0);
					return getUserByUnionId(openId, unionId, nickname, headimgurl);
				}
			}
		}
		reAuth();
		return null;
	}

	/**
	 * 根据code获取accesstoken
	 * 
	 * @param code
	 * @return
	 */
	private boolean getAccessToken(String code) {
		openId = null;
		accessToken = null;
		unionId = null; // 不管如何先清空数据
		String accessUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + SignUtil.AppId + "&secret="
				+ SignUtil.AppSecret + "&code=" + code + "&grant_type=authorization_code";
		JSONObject jsonObject = CommonUtils.httpRequestali(accessUrl, "GET", null);
		if (null != jsonObject) {
			try {
				openId = jsonObject.getString("openid");
				accessToken = jsonObject.getString("access_token");
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		if (!CommonUtils.isEmptyString(openId)) {
			if (!needuserinfo)
				return true;
			request.getSession().setAttribute(Constant.SESSION_WXOPENID, openId);

			String UnionURL = "https://api.weixin.qq.com/sns/userinfo?lang=zh_CN&openid=" + openId + "&access_token="
					+ accessToken;
			JSONObject jsonObject2 = CommonUtils.httpRequestali(UnionURL, "GET", null);
			if (null != jsonObject2) {
				try {
					nickname = jsonObject2.getString("nickname");
					nickname = EmojiParser.removeAllEmojis(nickname);
					request.getSession().setAttribute(Constant.SESSION_WXNICKNAME, nickname);
					headimgurl = jsonObject2.getString("headimgurl");
					request.getSession().setAttribute(Constant.SESSION_WXHEADIMGURL, headimgurl);

					unionId = jsonObject2.getString("unionid");
					request.getSession().setAttribute(Constant.SESSION_WXUNIONID, unionId);
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据openid查找用户
	 * 
	 * @param openid
	 * @return
	 */
	private User getUserByUnionId(String openid, String unionid, String nickname, String headimgurl) {
		User userInfo = null;
		try {
			userInfo = wechatService.getUSerInfoByUnionId(openid, unionid, nickname, headimgurl,
					request.getSession().getId().toLowerCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (userInfo != null) {
			request.getSession().setAttribute(Constant.SESSION_WXUSERID, userInfo.getUserid());
			request.getSession().setAttribute(Constant.SESSION_WXOPENID, openId);
			request.getSession().setAttribute(Constant.SESSION_WXUNIONID, unionId);
			UserExtra extra = wechatService.getUserExtraByUserId(userInfo.getUserid());
			if(extra != null){
				request.getSession().setAttribute(Constant.SESSION_ACCESSTOKEN, extra.getAccesstoken());
			}
			
			return userInfo;
		} else {
			return null;
		}
	}

	/**
	 * 跳转微信授权
	 */
	public void reAuth() {
		request.getSession().setAttribute(Constant.SESSION_WXOPENID, "");
		request.getSession().setAttribute(Constant.SESSION_WXUNIONID, "");
		request.getSession().setAttribute(Constant.SESSION_WXUSERID, "");
		request.getSession().setAttribute("accesstoken", "");
		request.getSession().setAttribute("wxauth", 1);
		try {
			if (needuserinfo) {
				response.sendRedirect(CommonUtils.properties.getProperty("webRootUrl") + "wechat/auth/wxsq?reurl="
						+ CommonUtils.getFullURLWithParam(request) + "&needuserinfo=1");// 返回到配置文件中定义的路径
			} else {
				response.sendRedirect(CommonUtils.properties.getProperty("webRootUrl") + "wechat/auth/wxsq?reurl="
						+ CommonUtils.getFullURLWithParam(request) + "&needuserinfo=0");// 返回到配置文件中定义的路径
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
