package com.uncleserver.web.common;

import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.uncleserver.common.CommonUtils;
import com.uncleserver.common.Constant;
import com.uncleserver.common.SignUtil;
import com.uncleserver.common.wxpay.AccessToken;
import com.uncleserver.common.wxpay.GetWxOrderno;
import com.uncleserver.common.wxpay.RequestHandler;
import com.uncleserver.common.wxpay.Sha1Util;
import com.uncleserver.common.wxpay.TenpayUtil;
import com.uncleserver.modelVo.PayValue;

public class WebUtils {

	public static AccessToken accessToken;
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	public static PayValue createWxPayValue(HttpServletRequest request, HttpServletResponse response, String thirdNumID,
			String body, float money) {
		PayValue value = new PayValue();
		getAccessToken();
		if (accessToken != null && accessToken.isExpires()) {

			// 接收财付通通知的URL
			String notify_url = CommonUtils.getWebRootUrl() + "wx/notify";
			// 客户端IP
			String spbill_create_ip = CommonUtils.getIpAddr(request);

			String finalmoney = String.format("%.2f", money);
			String total_fee = Integer.valueOf(finalmoney.replace(".", "")).toString();
			// ---------------生成订单号 开始------------------------
			// 订单号，此处用时间加随机数生成，商户根据自己情况调整，只要保持全局唯一就行
			String out_trade_no = thirdNumID;

			// String body = systemService.getWXPayBody(thirdNumID);
			// ---------------生成订单号 结束------------------------
			// 商品描述

			// 商户号
			String mch_id = Constant.WXCHAT_PARTNER;
			// appid
			String appid = Constant.WXCHAT_APPID;
			String appsecret = Constant.WXCHAT_APPSECRET;
			String partnerkey = Constant.WXCHAT_PARTNERKEY;
			// 微信统一下单类型:
			String trade_type = "APP";
			// 微信签名随机字符串
			String currTime = TenpayUtil.getCurrTime();
			String strTime = currTime.substring(8, currTime.length());
			// 四位随机数
			String strRandom = TenpayUtil.buildRandom(4) + "";
			// 10位序列号,可以自行调整。
			String nonce_str = strTime + strRandom;
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
			packageParams.put("appid", appid);
			packageParams.put("mch_id", mch_id);
			packageParams.put("nonce_str", nonce_str);
			packageParams.put("body", body);
			packageParams.put("attach", out_trade_no);
			packageParams.put("out_trade_no", out_trade_no);
			packageParams.put("total_fee", "" + total_fee);
			packageParams.put("spbill_create_ip", spbill_create_ip);
			packageParams.put("notify_url", notify_url);
			packageParams.put("trade_type", trade_type);
			RequestHandler reqHandler = new RequestHandler(request, response);
			reqHandler.init(appid, appsecret, partnerkey);
			String sign = reqHandler.createSign(packageParams);
			String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>" + mch_id + "</mch_id>" + "<nonce_str>"
					+ nonce_str + "</nonce_str>" + "<sign>" + sign + "</sign>" + "<body>" + body + "</body>"
					+ "<attach>" + out_trade_no + "</attach>" + "<out_trade_no>" + out_trade_no + "</out_trade_no>" +
					// 金额，这里写的1 分到时修改
					"<total_fee>" + total_fee + "</total_fee>" + "<spbill_create_ip>" + spbill_create_ip
					+ "</spbill_create_ip>" + "<notify_url>" + notify_url + "</notify_url>" + "<trade_type>"
					+ trade_type + "</trade_type>" + "</xml>";
			String allParameters = "";
			try {
				allParameters = reqHandler.genPackage(packageParams);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
			String prepay_id = "";
			try {

				HashMap mapno = new GetWxOrderno().getPayNo(createOrderURL, xml);
				prepay_id = (String) mapno.get("prepay_id");
				// code_url = (String)mapno.get("code_url");
				// if(prepay_id.equals("")){
				// request.setAttribute("ErrorMsg", "统一支付接口获取预支付订单出错");
				// response.sendRedirect("error.jsp");
				// }
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			SortedMap<String, String> finalpackage = new TreeMap<String, String>();
			String timestamp = Sha1Util.getTimeStamp();
			finalpackage.put("appid", appid);
			finalpackage.put("partnerid", mch_id);
			finalpackage.put("prepayid", prepay_id);
			finalpackage.put("timestamp", timestamp);
			finalpackage.put("noncestr", nonce_str);
			finalpackage.put("package", "Sign=WXPay");
			String finalsign = reqHandler.createSign(finalpackage);

			value.setAppid(appid);
			value.setPartnerid(mch_id);
			value.setPrepayid(prepay_id);
			value.setPackagename("Sign=WXPay");
			value.setNoncestr(nonce_str);
			value.setTimeStamp(timestamp);
			value.setSign(finalsign);
		}
		return value;
	}

	public static PayValue createWxJSPayValue(HttpServletRequest request, HttpServletResponse response,
			String thirdNumID, String openid, String body, float money) {

		// 金额转化为分为单位
		String finalmoney = String.format("%.2f", money);
		finalmoney = finalmoney.replace(".", "");

		String appid = SignUtil.AppId;
		String appsecret = SignUtil.AppSecret;
		String partner = SignUtil.Partner;
		String partnerkey = SignUtil.PartnerKey;

		// 获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
		String currTime = TenpayUtil.getCurrTime();
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		String strReq = strTime + strRandom;

		// 商户号
		String mch_id = partner;
		// 子商户号 非必输
		// String sub_mch_id="";
		// 设备号 非必输
		String device_info = "";
		// 随机数
		String nonce_str = strReq;
		// 商品描述
		// String body = describe;

		/*
		 * // 商品描述根据情况修改 String body = systemService.getWXPayBody(thirdNumID);
		 */
		// 附加数据
		String attach = thirdNumID;
		// 商户订单号
		String out_trade_no = thirdNumID;

		int total_fee = Integer.parseInt(finalmoney);
		// 测试用价格
		// int total_fee = 1;
		// 订单生成的机器 IP
		String spbill_create_ip = request.getRemoteAddr();
		// 订 单 生 成 时 间 非必输
		// String time_start ="";
		// 订单失效时间 非必输
		// String time_expire = "";
		// 商品标记 非必输
		// String goods_tag = "";

		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		String notify_url = CommonUtils.getWebRootUrl() + "wxjs/notify";
		String trade_type = "JSAPI";
		// 非必输
		// String product_id = "";
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("attach", attach);
		packageParams.put("out_trade_no", out_trade_no);

		// 这里写的金额为1 分到时修改
		packageParams.put("total_fee", "" + total_fee);
		// packageParams.put("total_fee", "finalmoney");
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);

		packageParams.put("trade_type", trade_type);
		packageParams.put("openid", openid);

		RequestHandler reqHandler = new RequestHandler(request, response);
		reqHandler.init(appid, appsecret, partnerkey);

		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>" + mch_id + "</mch_id>" + "<nonce_str>"
				+ nonce_str + "</nonce_str>" + "<sign>" + sign + "</sign>" + "<body><![CDATA[" + body + "]]></body>"
				+ "<attach>" + attach + "</attach>" + "<out_trade_no>" + out_trade_no + "</out_trade_no>" +
				// 金额，这里写的1 分到时修改
				"<total_fee>" + total_fee + "</total_fee>" +
				// "<total_fee>"+finalmoney+"</total_fee>"+
				"<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>" + "<notify_url>" + notify_url
				+ "</notify_url>" + "<trade_type>" + trade_type + "</trade_type>" + "<openid>" + openid + "</openid>"
				+ "</xml>";
		// System.out.println(xml);
		String allParameters = "";
		try {
			allParameters = reqHandler.genPackage(packageParams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		String prepay_id = "";
		try {
			HashMap map = new GetWxOrderno().getPayNo(createOrderURL, xml);
			prepay_id = (String) map.get("prepay_id");
			// code_url = (String)map.get("code_url");
			if (prepay_id.equals("")) {
				request.setAttribute("ErrorMsg", "统一支付接口获取预支付订单出错");
				response.sendRedirect("error.jsp");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
		String appid2 = appid;
		String timestamp = Sha1Util.getTimeStamp();
		String nonceStr2 = nonce_str;
		String prepay_id2 = "prepay_id=" + prepay_id;
		String packages = prepay_id2;
		finalpackage.put("appId", appid2);
		finalpackage.put("timeStamp", timestamp);
		finalpackage.put("nonceStr", nonceStr2);
		finalpackage.put("package", packages);
		finalpackage.put("signType", "MD5");
		String finalsign = reqHandler.createSign(finalpackage);

		PayValue value = new PayValue();

		value.setAppid(appid2);
		value.setPartnerid(mch_id);
		value.setPrepayid(prepay_id);
		value.setPackagename(packages);
		value.setNoncestr(nonceStr2);
		value.setTimeStamp(timestamp);
		value.setSign(finalsign);

		return value;
	}

	public static AccessToken getAccessToken() {
		if (accessToken != null && accessToken.isExpires()) {
			return accessToken;
		}
		String requestUrl = access_token_url.replace("APPID", Constant.WXCHAT_APPID).replace("APPSECRET",
				Constant.WXCHAT_APPSECRET);
		JsonObject jsonObject = CommonUtils.httpRequest(requestUrl, "GET", null);
		// 请求成功
		if (null != jsonObject) {
			try {
				String access_token = jsonObject.get("access_token").getAsString();
				accessToken = new AccessToken(jsonObject.get("access_token").getAsString(),
						jsonObject.get("expires_in").getAsInt());
			} catch (Exception e) {
				accessToken = null;
			}
		}
		return accessToken;
	}
}
