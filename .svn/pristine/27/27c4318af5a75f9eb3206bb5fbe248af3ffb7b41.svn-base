package com.uncleserver.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 请求校验工具类
 * 
 * @author wangcl
 * @date 2014-02-14
 */

public class SignUtil {

	
	//公众号的APPID
	public static String AppId = "wxc507f4e1b5340215";
	public static String AppSecret = "75ac0ab3850c22a18c037f9a10ce1752";
	public static String Partner = "1408949402";
	public static String PartnerKey = "NNAxLiegfsz5jjGqS33c1wdm6hc8EpVG";
	
	private static final String token = "biaoshuyunUser";
	private static final String EventKey = "";// 微信公众号入口菜单的KYE值

	/**
	 * 验证签名
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return boolean
	 */
	public static boolean checkSignature(String signature, String timestamp, String nonce) {
		if (signature == null || timestamp == null || nonce == null) {
			return false;
		}
		String[] arr = new String[] { token, timestamp, nonce };
		Arrays.sort(arr);
		StringBuffer content = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);

		}
		MessageDigest md = null;
		String tmpStr = null;

		try {
			md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);

		} catch (NoSuchAlgorithmException e) {
			// TODO: handle exception
		}

		content = null;

		return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 * 
	 * @param byteArray
	 * @return
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转换为十六进制字符串
	 * 
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];

		String s = new String(tempArr);
		return s;
	}
}
