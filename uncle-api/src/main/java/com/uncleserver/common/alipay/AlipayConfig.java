package com.uncleserver.common.alipay;

import com.uncleserver.common.CommonUtils;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */
public class AlipayConfig {

	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088421969255010";
	public static String seller_id = "zhejiangzhika@163.com";
	// 商户的私钥
	public static String private_key = "MIICeQIBADANBgkqhkiG9w0BAQEFAASCAmMwggJfAgEAAoGBANB9Uh/T38QLY/WmbGd+CTDORSR03DtnZz9bqM38cMd75nat1c3qBttqK4lBq64T6zCUwz937fOUBhrHGzDkI24fTn4EZ5ucLW4VOv0NzDDJZxE0j74+8O/lfaCi60bpJ/pYn6ZN/69S1ixaYvWV+X5AtdL+eGQ9ckmfuEUjAfWDAgMBAAECgYEAy4H4fs7Otks0CF2ppR+2kuhVP2++ZVHay1KKJH8MCvxbCm9GqpeMl/I7iuAKV4Jj6WjcaQCEyWUszEibtw6JsTl9rcks4zoRyFu2MM1OchRslGu9V33vmcw0drDduPnAg2pvCGC/TqYXu2mLOOTNxl+b1N2UU7SYdsW1Uyp+KWkCQQD2DLmUfGGSxZ/3g8vG4wKW7zESZOFb4XPEZtbuDdmLeoevwYxeMU05pmrpfRJXyNveODxewL3JraN8XqT4LxzXAkEA2Ou/S6G4RB/CP6w7xIv7DfL2wy9yr4t2TasNMBEu7HntiRhrpYqy7e3uiU1x4SQhxuQpNGVfLR2PTpr1suJLNQJBAMqKVwnACaIERFY/i7NAk5UjeMWnfUthHycBcNOtWvLR/nfTX3T6KJPYRhHF/x98HGGk8S0ehBxMb1OlpS8XuSkCQQCOPH8R+7fUy7JWh/wk09jJ+7+8mrcrzlnDcd7S3uGS5BxSYfXr9XeANhzMYUDD7SpMvT0bg0EWVySp2Ug0hNflAkEAsuph9xH65Sh2vFyQ490yN5Oao2jXycia23hz41mYTCWX7l2aJXj4nA42In2VjpOt4EJyxkv36SdOuleS+Wxqaw==";
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";

	public static String appid = "2016110302518174";

	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	// 签名方式 不需修改
	public static String sign_type = "RSA";
	
	//支付剩余金额回调地址
	public static String notify_url=CommonUtils.getWebRootUrl() + "api/alipay/notify";
	
	public static String OPEN_WAY = "https://openapi.alipay.com/gateway.do";

	// demo
	// PayToAppModel pay = new PayToAppModel();
	// Map<String, String> params =
	// com.alipay.util.OrderInfoUtil2_0.buildOrderParamMap(
	// String.valueOf(order.getPrice_detail().doubleValue()), order.getName(),
	// order.getDescrib(),
	// alipay_trade_no);
	// String orderParam =
	// com.alipay.util.OrderInfoUtil2_0.buildOrderParam(params);
	// String sign = com.alipay.util.OrderInfoUtil2_0.getSign(params,
	// AlipayConfig.private_key);
	// final String orderInfo = orderParam + "&" + sign;
	//
	// pay.setBody(orderInfo);
}
