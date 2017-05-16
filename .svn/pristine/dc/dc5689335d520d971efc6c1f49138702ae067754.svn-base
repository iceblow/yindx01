package com.uncleserver.controller.wechat;

import java.io.File;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.uncleserver.common.CommonUtils;
import com.uncleserver.common.SignUtil;
import com.uncleserver.common.WxUserInfo;
import com.uncleserver.common.wxpay.AccessToken;

public class WeiXinEntity {

	// 获取access_token的接口地址（GET） 限200（次/天）
	public final String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	public final String message_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";

	// 获取jsapi_ticket_url的接口地址（GET） 限200（次/天）
	public final String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

	// 自定义菜单创建接口
	public final String create_menu_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	// 获取用户基本信息
	public final String get_user_info_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	// 下载多媒体素材的地址
	private static final String DOWNLOAD_MEDIA = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";

	public static AccessToken accessToken;

	public WxUserInfo getWxUserInfo(String openid) {
		WxUserInfo userinfo = null;
		AccessToken token = getAccessToken();
		if (accessToken != null && accessToken.isExpires()) {
			// 拼装url
			String url = get_user_info_url.replace("ACCESS_TOKEN", token.getAccess_token()).replace("OPENID", openid);

			JsonObject jsonObject = CommonUtils.httpRequest(url, "GET", "");
			userinfo = new Gson().fromJson(jsonObject, WxUserInfo.class);
		}
		return userinfo;
	}

	// 自定义菜单创建接口
	public int createMenu(String jsonMenu) {
		int result = -1;
		AccessToken token = getAccessToken();
		if (accessToken != null && accessToken.isExpires()) {
			// 拼装url
			String url = create_menu_url.replace("ACCESS_TOKEN", token.getAccess_token());
			// 调用接口创建菜单
			JsonObject jsonObject = CommonUtils.httpRequest(url, "POST", jsonMenu);
			// 请求成功
			if (null != jsonObject) {
				result = jsonObject.get("errcode").getAsInt();
			}
		}
		return result;
	}

	public int deleteMenu() {
		int result = -1;
		AccessToken token = getAccessToken();
		if (accessToken != null && accessToken.isExpires()) {
			// 拼装url
			String url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN"
					.replace("ACCESS_TOKEN", token.getAccess_token());
			// 调用接口创建菜单
			JsonObject jsonObject = CommonUtils.httpRequest(url, "POST", "");
			// 请求成功
			if (null != jsonObject) {
				result = jsonObject.get("errcode").getAsInt();
			}
		}
		return result;
	}

	/**
	 * 取得微信access_token
	 * 
	 * @return
	 */
	public AccessToken getAccessToken() {
		if (accessToken != null && accessToken.isExpires()) {
			return accessToken;
		}
		String requestUrl = access_token_url.replace("APPID", SignUtil.AppId).replace("APPSECRET", SignUtil.AppSecret);
		JsonObject jsonObject = CommonUtils.httpRequest(requestUrl, "GET", null);
		// 请求成功
		if (null != jsonObject) {
			try {
				String access_token = jsonObject.get("access_token").getAsString();
				String jsapi_ticketurl = jsapi_ticket_url.replace("ACCESS_TOKEN", access_token);
				JsonObject tickObject = CommonUtils.httpRequest(jsapi_ticketurl, "GET", null);
				String jsapi_ticke = tickObject.get("ticket").getAsString();
				accessToken = new AccessToken(jsonObject.get("access_token").getAsString(),
						jsonObject.get("expires_in").getAsInt(), jsapi_ticke);
			} catch (Exception e) {
				accessToken = null;
			}
		}
		return accessToken;
	}

	public String getJsTicket() {
		AccessToken token = getAccessToken();
		if (accessToken != null && accessToken.isExpires()) {
			return token.getJsapi_ticket();
		}
		return null;
	}

	// 发送文本信息给用户
	public int sendTextMessage(String touser, String content) {
		int result = 0;
		AccessToken token = getAccessToken();
		if (accessToken != null && accessToken.isExpires()) {
			// 拼装url
			String url = message_url.replace("ACCESS_TOKEN", token.getAccess_token());
			// 将菜单对象转换成json字符串
			String jsonMsg = "{\"touser\":\"" + touser + "\",\"msgtype\":\"text\",\"text\":{\"content\":\"" + content
					+ "\"}}";
			// 调用接口创建菜单
			JsonObject jsonObject = CommonUtils.httpRequest(url, "POST", jsonMsg);
			// 请求成功
			if (null != jsonObject) {
				if (0 != jsonObject.get("errcode").getAsInt()) {
					result = jsonObject.get("errcode").getAsInt();
				}
			}
		}
		return result;
	}

	/**
	 * 下载多媒体文件
	 * 
	 * @param mediaID
	 * @param type
	 *            type只支持四种类型素材(video/image/voice/thumb)
	 */
	public File downloadMedia(String mediaID, String type) {
		File file = null;
		AccessToken token = getAccessToken();
		if (accessToken != null && accessToken.isExpires()) {
			String url = DOWNLOAD_MEDIA.replace("ACCESS_TOKEN", token.getAccess_token()).replace("MEDIA_ID", mediaID);
			String path = CommonUtils.getLocationPath() + File.separator + "WeChat" + File.separator + type;
			path += File.separator + CommonUtils.getTimeFormat(new Date(), "yyyyMMdd") + File.separator;
			CommonUtils.checkPath(path);
			String filenameorg = CommonUtils.getTimeFormat(new Date(), "yyyyMMddhhmmssSSS") + "_"
					+ (int) (Math.random() * 100);
			String fileName = path + filenameorg;
			file = CommonUtils.downloadMedia(fileName, url);
		}
		return file;
	}

	public String downloadMediaPath(String mediaID, String type) {
		String filepath = "";
		AccessToken token = getAccessToken();
		if (accessToken != null && accessToken.isExpires()) {
			String url = DOWNLOAD_MEDIA.replace("ACCESS_TOKEN", token.getAccess_token()).replace("MEDIA_ID", mediaID);
			String path = CommonUtils.getLocationPath() + File.separator + "WeChat" + File.separator + type;
			path += File.separator + CommonUtils.getTimeFormat(new Date(), "yyyyMMdd") + File.separator;
			CommonUtils.checkPath(path);
			String filenameorg = CommonUtils.getTimeFormat(new Date(), "yyyyMMddhhmmssSSS") + "_"
					+ (int) (Math.random() * 100);
			String fileName = path + filenameorg;
			filepath = CommonUtils.downloadMediaPath(fileName, url);
		}
		return filepath;
	}
}
