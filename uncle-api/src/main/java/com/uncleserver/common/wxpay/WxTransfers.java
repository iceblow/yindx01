package com.uncleserver.common.wxpay;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.common.Constant;
import com.uncleserver.common.wxpay.GetWxOrderno;
import com.uncleserver.common.wxpay.MD5Util;
import com.uncleserver.common.wxpay.TenpayUtil;

public class WxTransfers {
	public final static void main(String[] args) throws Exception {

	}

	@SuppressWarnings("deprecation")
	public static boolean doTransfers(String partner_trade_no, float transfer_money, String openid,
			String ip) {
		String parentkey = Constant.WXCHAT_PARTNERKEY;
		String parent = Constant.WXCHAT_PARTNER;
		String appid = Constant.WXCHAT_APPID;
		String finalmoney = String.format("%.2f", transfer_money);
		String total_fee = Integer.valueOf(finalmoney.replace(".", "")).toString();

		SortedMap<String, String> parameters = new TreeMap<String, String>();
		parameters.put("mch_appid", appid);// appid
		parameters.put("mch_id", parent);// 商户号
		String currTime = TenpayUtil.getCurrTime();
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		String nonce_str = strTime + strRandom;
		parameters.put("nonce_str", nonce_str);

		parameters.put("partner_trade_no", partner_trade_no);// 订单好
		parameters.put("openid", openid);// 我们自己设定的退款申请号，约束为UK
		parameters.put("check_name", "NO_CHECK");

		parameters.put("amount", total_fee);// 单位为分
		parameters.put("desc", "提现");// 单位为分
		parameters.put("spbill_create_ip", ip);// 操作人员,默认为商户账号
		String sign = createSign(parameters, parentkey);
		parameters.put("sign", sign);

		String reuqestXml = getRequestXml(parameters);

		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			FileInputStream instream = new FileInputStream(new File(CommonUtils.properties.getProperty("WXAPPP12")));
			try {
				keyStore.load(instream, Constant.WXCHAT_PARTNER.toCharArray());
			} finally {
				instream.close();
			}
			// Trust own CA and all self-signed certs
			SSLContext sslcontext = SSLContexts.custom()
					.loadKeyMaterial(keyStore, Constant.WXCHAT_PARTNER.toCharArray()).build();
			// Allow TLSv1 protocol only
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
					null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			try {

				HttpPost httppost = new HttpPost("https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers");

				System.out.println("executing request" + httppost.getRequestLine());
				StringEntity reqEntity = new StringEntity(reuqestXml);
				httppost.setEntity(reqEntity);
				CloseableHttpResponse response = httpclient.execute(httppost);
				try {
					String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
					Map<String, Object> dataMap = new HashMap<String, Object>();
					System.out.println("json是:" + jsonStr);
					if (jsonStr.indexOf("FAIL") != -1) {
						return false;
					}
					Map map = GetWxOrderno.doXMLParse(jsonStr);
					String return_code = (String) map.get("return_code");
					if ("SUCCESS".equals(return_code)) {
						String result_code = (String) map.get("result_code");
						if ("SUCCESS".equals(result_code)) {
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} finally {
					response.close();
				}
			} finally {
				httpclient.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static String createSign(SortedMap<String, String> packageParams, String parentkey) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + parentkey);
		System.out.println("md5 sb:" + sb + "key=" + parentkey);
		String sign = MD5Util.MD5Encode(sb.toString(), "utf-8").toUpperCase();
		System.out.println("packge签名:" + sign);
		return sign;

	}

	public static String getRequestXml(SortedMap<String, String> parameters) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
				sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
			} else {
				sb.append("<" + k + ">" + v + "</" + k + ">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}
}
