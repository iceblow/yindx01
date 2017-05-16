/**
 * 
 */
package com.uncleserver.common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.uncleserver.common.wxpay.MyX509TrustManager;
import com.vdurmont.emoji.EmojiParser;

/**
 * @author qiuch
 * 
 */
public class CommonUtils {

	/**
	 * 读取配置文件
	 */
	public static Properties properties = new Properties();

	static {
		try {
			String path = "/config/config.properties";
			InputStream inStream = CommonUtils.class.getResourceAsStream(path);
			if (inStream == null) {
				inStream = CommonUtils.class.getClassLoader().getResourceAsStream("/" + path);
			}
			properties.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 检查路径是否存在，不存在则创建路径
	 * 
	 * @param path
	 */
	public static void checkPath(String path) {
		String[] paths = null;
		if (path.contains("/")) {
			paths = path.split(File.separator);
		} else {
			paths = path.split(File.separator + File.separator);
		}
		if (paths == null || paths.length == 0) {
			return;
		}
		String pathdir = "";
		for (String string : paths) {
			pathdir = pathdir + string + File.separator;
			File file = new File(pathdir);
			if (!file.exists()) {
				file.mkdir();
			}
		}
	}

	/**
	 * 判断String是否为空
	 * 
	 */
	public static boolean isEmptyString(String value) {
		if (value == null || value.length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 只判断多个String是否为空(无论有没全角或半角的空格) 若非空则返回true,否则返回false
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isEmptyAllString(String... str) {
		if (null == str)
			return true;
		for (String s : str) {
			if (isEmptyString(s)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * String -> int
	 * 
	 * @param String
	 * @return int
	 */
	public static int parseInt(String string, int def) {
		if (isEmptyString(string))
			return def;
		int num = def;
		try {
			num = Integer.parseInt(string);
		} catch (Exception e) {
			num = def;
		}
		return num;
	}

	/**
	 * String -> short
	 * 
	 * @param String
	 * @return int
	 */
	public static short parseShort(String string, short def) {
		if (isEmptyString(string))
			return def;
		short num = def;
		try {
			num = Short.parseShort(string);
		} catch (Exception e) {
			num = def;
		}
		return num;
	}

	/**
	 * String -> long
	 * 
	 * @param String
	 * @return long
	 */
	public static long parseLong(String string, long def) {
		if (isEmptyString(string))
			return def;
		long num;
		try {
			num = Long.parseLong(string);
		} catch (Exception e) {
			num = def;
		}
		return num;
	}

	/**
	 * String -> double
	 * 
	 * @param String
	 * @return long
	 */
	public static double parseDouble(String string, double def) {
		if (isEmptyString(string))
			return def;
		double num;
		try {
			num = Double.parseDouble(string);
		} catch (Exception e) {
			num = def;
		}
		return num;
	}

	/**
	 * String -> float
	 * 
	 * @param String
	 * @return long
	 */
	public static float parseFloat(String string, float def) {
		if (isEmptyString(string))
			return def;
		float num;
		try {
			num = Float.parseFloat(string);
		} catch (Exception e) {
			num = def;
		}
		return num;
	}

	/**
	 * String -> float
	 * 
	 * @param String
	 * @return long
	 */
	public static float parseFloat(String string, float def, int digit) {
		if (isEmptyString(string))
			return def;
		float num;
		try {
			num = Float.parseFloat(string);
		} catch (Exception e) {
			num = def;
		}
		if (digit > 0 && num != def) {
			BigDecimal bigDecimal = new BigDecimal(num);
			float twoDecimalP = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
			return twoDecimalP;
		} else {
			return num;
		}
	}

	/**
	 * @param date
	 * @param string
	 * @return
	 */
	public static String getTimeFormat(Date date, String string) {
		SimpleDateFormat sdFormat;
		if (isEmptyString(string)) {
			sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else {
			sdFormat = new SimpleDateFormat(string);
		}
		try {
			return sdFormat.format(date);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 格式化时间 String转换Date "yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param 需要格式化的时间和格式
	 * @return 格式化之后的时间
	 */
	public static Date getDateFormat(String date, String format) {
		if (isEmptyString(date))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static int hasNext(List<?> a) {
		if (a != null && a.size() > 0) {
			return 1;
		}
		return 0;
	}

	/**
	 * MD5加密
	 * 
	 * @param 要加密的String
	 * @return 加密后String(大写)
	 */
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			// 使用MD5创建MessageDigest对象
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				str[k++] = hexDigits[b >> 4 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			return new String(str).toLowerCase();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 过滤字符串中的可能存在XSS攻击的非法字符
	 * 
	 * @param value
	 * @return
	 */
	public static String cleanXSS(String value) {
		value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
		value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
		value = value.replaceAll("'", "& #39;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		value = value.replaceAll("script", "");
		value = value.replaceAll(" ", "");
		return value;
	}

	/**
	 * 获取随机6位验证码
	 * 
	 * @return
	 */
	public static String getRandomVcode() {
		Random random = new Random();
		String result = "";
		for (int i = 0; i < 6; i++) {
			result += random.nextInt(10);
		}
		return result;
	}

	/**
	 * 计算地球上任意两点(经纬度)距离
	 * 
	 * @param long1
	 *            第一点经度
	 * @param lat1
	 *            第一点纬度
	 * @param long2
	 *            第二点经度
	 * @param lat2
	 *            第二点纬度
	 * @return 返回距离 单位：米
	 */
	public static double Distance(double long1, double lat1, double long2, double lat2) {
		double a, b, R;
		R = 6378137; // 地球半径
		lat1 = lat1 * Math.PI / 180.0;
		lat2 = lat2 * Math.PI / 180.0;
		a = lat1 - lat2;
		b = (long1 - long2) * Math.PI / 180.0;
		double d;
		double sa2, sb2;
		sa2 = Math.sin(a / 2.0);
		sb2 = Math.sin(b / 2.0);
		d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
		return d;
	}

	// 上传文件的目录
	public static String getLocationPath() {
		return properties.getProperty("uploadFilePath");
	}

	// 工程访问路径
	public static String getWebRootUrl() {
		return properties.getProperty("webRootUrl");
	}

	// 苹果推送证书密码
	public static String getIosPushPsw() {
		return properties.getProperty("ios_push_file_psw");
	}

	// 苹果推送证书密码
	public static String getIosPushPath() {
		return properties.getProperty("ios_push_file_path");
	}

	// 苹果推送证书密码
	public static String getIosAuntPushPath() {
		return properties.getProperty("ios_aunt_push_file_path");
	}

	// 容联云短信平台
	public static String getSMSBaseUrl() {
		return properties.getProperty("sms_base_url");
	}

	public static String getSMSServerPort() {
		return properties.getProperty("sms_server_port");
	}

	/**
	 * 过滤字符串中的表情符号
	 * 
	 * @param emojisString
	 * @return
	 */
	public static String removeAllEmojis(String emojisString) {
		if (!CommonUtils.isEmptyString(emojisString)) {
			return EmojiParser.removeAllEmojis(emojisString);
		}
		return null;
	}

	/**
	 * 将传入的时间转换成 **小时前 ***几天前的格式
	 * 
	 * @param date
	 * @return
	 */
	public static final String getTimeDiff(Date date) {
		Calendar currentDate = Calendar.getInstance();// 获取当前时间
		long diff = currentDate.getTimeInMillis() - date.getTime();
		if (diff <= 0) {
			return 1 + "秒钟前";
		} else if (diff < 60000) {
			int second = (int) (diff / 1000);
			if (second == 0) {
				return 1 + "秒钟前";
			} else {
				return second + "秒钟前";
			}

		} else if (diff < 3600000) {
			int minute = (int) (diff / 60000);
			if (minute == 0) {
				return 1 + "分钟前";
			} else {
				return minute + "分钟前";
			}

		} else if (diff < 86400000) {
			int hour = (int) (diff / 3600000);
			if (hour == 0)
				hour = 1;
			return hour + "小时前";
		} else if (diff < 864000000) {
			int day = (int) (diff / 86400000);
			if (day == 0)
				day = 1;
			return day + "天前";
		} // 十天以内

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdFormat.format(date);
	}

	public static boolean checkSession(String accesstoken, String userToken) {
		return !CommonUtils.isEmptyString(accesstoken) && accesstoken.equals(userToken);
	}

	public static int getAge(Date startDate, Date endDate) {
		if (startDate == null)
			return 0;
		Calendar calendarStart = Calendar.getInstance();
		Calendar calendarEnd = Calendar.getInstance();
		calendarStart.setTime(startDate);
		calendarEnd.setTime(endDate);
		int endYear = calendarEnd.get(Calendar.YEAR);
		int startYear = calendarStart.get(Calendar.YEAR);
		return endYear - startYear;

	}

	/**
	 * 获得该月第一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getFirstDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		// 设置年份
		cal.set(Calendar.YEAR, year);
		// 设置月份
		cal.set(Calendar.MONTH, month);
		// 获取某月最小天数
		int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		// 设置日历中月份的最小天数
		cal.set(Calendar.DAY_OF_MONTH, firstDay);
		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String firstDayOfMonth = sdf.format(cal.getTime());
		return firstDayOfMonth;
	}

	/**
	 * 获得该月最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		// 设置年份
		cal.set(Calendar.YEAR, year);
		// 设置月份
		cal.set(Calendar.MONTH, month);
		// 获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastDayOfMonth = sdf.format(cal.getTime());
		return lastDayOfMonth;
	}

	public static String httpRequestString(String requestUrl, String requestMethod, String outputStr) {
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象
			TrustManager[] tm = { (TrustManager) new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			JsonParser jp = new JsonParser();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	/**
	 * 发起https请求
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JsonObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JsonObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象
			TrustManager[] tm = { (TrustManager) new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			JsonParser jp = new JsonParser();
			jsonObject = (JsonObject) jp.parse(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * 获取用户当前请求的IP地址
	 * 
	 * @param request
	 * @return
	 */

	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
															// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	public Date parse(String strDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(strDate);
	}

	// 由出生日期获得年龄
	public static int getAge(Date birthDay) throws Exception {
		if (birthDay == null)
			return 0;

		Calendar cal = Calendar.getInstance();

		if (cal.before(birthDay)) {
			throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);

		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth)
					age--;
			} else {
				age--;
			}
		}
		return age;
	}

	public static String getTimeDiff(String startTime, String endTime) {
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date begin;
		java.util.Date end;
		String str = "";
		try {
			begin = dfs.parse(startTime);
			end = dfs.parse(endTime);
			long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
			long day1 = between / (24 * 3600);
			long hour1 = between % (24 * 3600) / 3600;
			long minute1 = between % 3600 / 60;
			long second1 = between % 60 / 60;
			str += day1 + "天" + hour1 + "小时" + minute1 + "分" + second1 + "秒";
		} catch (ParseException e) {

			e.printStackTrace();

		}
		return str;
	}

	public static void setMaptoJson(HttpServletResponse response, Map<String, Object> map) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping().create();
		String json = gson.toJson(map);
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");
		try {
			out = response.getWriter();
			out.print(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 发起https请求
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static com.alibaba.fastjson.JSONObject httpRequestali(String requestUrl, String requestMethod,
			String outputStr) {
		com.alibaba.fastjson.JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象
			TrustManager[] tm = { (TrustManager) new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();

			jsonObject = com.alibaba.fastjson.JSONObject.parseObject(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	public static String getFullURLWithParam(HttpServletRequest request) {
		String requestUrl = request.getRequestURL().toString();
		Map<String, String[]> map = request.getParameterMap();
		Set<Entry<String, String[]>> keSet = map.entrySet();
		if (keSet.iterator().hasNext()) {
			requestUrl = requestUrl + "?";
		}
		for (Iterator<Entry<String, String[]>> itr = keSet.iterator(); itr.hasNext();) {
			Map.Entry<String, String[]> me = itr.next();
			String key = me.getKey(); // 获取参数名
			String value = me.getValue()[0]; // 获取参数值
			if (itr.hasNext()) {
				requestUrl = requestUrl + key + "=" + value + "&";
			} else {
				requestUrl = requestUrl + key + "=" + value;
			}

		}
		try {
			requestUrl = URLEncoder.encode(requestUrl, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return requestUrl;
	}

	public static String downloadMediaPath(String fileName, String url) {
		File file = null;
		String fileRealPath = "";
		// 拼接请求地址
		try {
			URL urlrequest = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) urlrequest.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");

			// 根据内容类型获取扩展名
			String fileExt = getFileEndWitsh(conn.getHeaderField("Content-Type"));
			fileName = fileName + fileExt;
			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
			file = new File(fileName);
			FileOutputStream fos = new FileOutputStream(file);
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
			fos.close();
			bis.close();

			conn.disconnect();
			fileRealPath = fileName;
			// String info = String.format("下载媒体文件成功，filePath=" + filePath);
			// System.out.println(info);
		} catch (Exception e) {
			// filePath = null;
			String error = String.format("下载媒体文件失败：%s", e);
			System.out.println(error);
		}
		return fileRealPath;
	}

	/**
	 * 根据内容类型判断文件扩展名
	 * 
	 * @param contentType
	 *            内容类型
	 * @return
	 */
	public static String getFileEndWitsh(String contentType) {
		String fileEndWitsh = "";
		if ("image/jpeg".equals(contentType))
			fileEndWitsh = ".jpg";
		else if ("audio/mpeg".equals(contentType))
			fileEndWitsh = ".mp3";
		else if ("audio/amr".equals(contentType))
			fileEndWitsh = ".amr";
		else if ("video/mp4".equals(contentType))
			fileEndWitsh = ".mp4";
		else if ("video/mpeg4".equals(contentType))
			fileEndWitsh = ".mp4";
		return fileEndWitsh;
	}

	/**
	 * 获取媒体文件
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param media_id
	 *            媒体文件id
	 * @param savePath
	 *            文件在服务器上的存储路径
	 */
	public static File downloadMedia(String fileName, String url) {
		File file = null;
		// 拼接请求地址
		try {
			URL urlrequest = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) urlrequest.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");

			// 根据内容类型获取扩展名
			String fileExt = getFileEndWitsh(conn.getHeaderField("Content-Type"));
			fileName = fileName + fileExt;
			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
			file = new File(fileName);
			FileOutputStream fos = new FileOutputStream(file);
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
			fos.close();
			bis.close();

			conn.disconnect();
			String info = String.format("下载媒体文件成功，filePath=" + file.getAbsolutePath());
			System.out.println(info);
		} catch (Exception e) {
			// filePath = null;
			String error = String.format("下载媒体文件失败：%s", e);
			System.out.println(error);
		}
		return file;
	}

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 根据两个位置的经纬度，来计算两地的距离（单位为KM） 参数为String类型
	 * 
	 * @param lat1
	 *            用户经度
	 * @param lng1
	 *            用户纬度
	 * @param lat2
	 *            商家经度
	 * @param lng2
	 *            商家纬度
	 * @return
	 */
	public static String getDistance(String lat1Str, String lng1Str, String lat2Str, String lng2Str) {
		Double lat1 = Double.parseDouble(lat1Str);
		Double lng1 = Double.parseDouble(lng1Str);
		Double lat2 = Double.parseDouble(lat2Str);
		Double lng2 = Double.parseDouble(lng2Str);

		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double difference = radLat1 - radLat2;
		double mdifference = rad(lng1) - rad(lng2);
		double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(mdifference / 2), 2)));
		distance = distance * Constant.EARTH_RADIUS;
		distance = Math.round(distance * 10000) / 10000;
		String distanceStr = distance + "";
		distanceStr = distanceStr.substring(0, distanceStr.indexOf("."));

		return distanceStr;
	}

	/**
	 * 获取当前时间 yyyyMMddHHmmss
	 * 
	 * @return String
	 */
	public static String getCurrTime() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		return s;
	}

	/**
	 * 取出一个指定长度大小的随机正整数.
	 * 
	 * @param length
	 *            int 设定所取出随机数的长度。length小于11
	 * @return int 返回生成的随机数。
	 */
	public static int buildRandom(int length) {
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		return (int) ((random * num));
	}

	// 获取邀请码
	public static String getUserInvite(Integer num) {
		String index = Long.toString(num, 36);
		String nums = "";
		if (index.length() != 6) {
			for (int i = 0; i < (6 - index.length()); i++) {
				nums += "0";
			}
		}
		return "u"+nums + index;
	}

	// 获取邀请码
	public static String getAuntInvite(Integer num) {
		String index = Long.toString(num, 36);
		String nums = "";
		if (index.length() != 6) {
			for (int i = 0; i < (6 - index.length()); i++) {
				nums += "0";
			}
		}
		return "a"+nums + index;
	}

}
