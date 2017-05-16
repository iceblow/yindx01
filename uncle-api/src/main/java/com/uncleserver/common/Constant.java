package com.uncleserver.common;

public class Constant {

	// 用户端安卓请求接口的appid和appkey
	public static String APPID_ANDROID = "zhika_android";
	public static String APPKEY_ANDROID = "2caa9a9da78db6e60eb02704f0052caa";

	// 用户端IOS请求接口的appid和appkey
	public static String APPID_IOS = "zhika_ios";
	public static String APPKEY_IOS = "03cad22b837854e8813277519cfb0752";

	// 阿姨端安卓请求接口的appid和appkey
	public static String APPID_AUNT_ANDROID = "zhikaaunt_android";
	public static String APPKEY_AUNT_ANDROID = "c011264d16069e819271fc9160dc60db";

	// 阿姨端IOS请求接口的appid和appkey
	public static String APPID_AUNT_IOS = "zhikaaunt_ios";
	public static String APPKEY_AUNT_IOS = "92a35d8b542d4b11a6430f4db05c7e8d";
	
	// 用户端微信端请求接口的appid和appkey
	public static String APPID_WECHAT = "zhika_wechat";
	public static String APPKEY_WECHAT = "24a35d8b552d3b11a7230f4db05c7e8e";

	// 用户端安卓的推送信息个推
	public static String PUSH_APP_KEY = "";
	public static String PUSH_APP_ID = "";
	public static String PUSH_MASTERSECRET = "";
	// 骑手端安卓的推送信息个推
	public static String PUSH_APP_KEY_RIDER = "";
	public static String PUSH_APP_ID_RIDER = "";
	public static String PUSH_MASTERSECRET_RIDER = "";
	// 苹果推送环境,false为开发者环境,true为正式环境
	public static boolean IOS_PUSH_TYPE = true;
	//极光推送
	public static String JIGUANG_PUSH_KEY = "6c2ba1f2fa61b62864d6c375";
	public static String JIGUANG_PUSH_SECRET  = "d936566134352aa6f5973827";
	public static String JIGUANG_DEV_KEY = "946fd17de891bf01e2a44ee2";
	public static String JIGUANG_DEV_SECRET = "c6a3b68e1f7e3a6ba24b9d96";
	
	public static String JIGUANG_PUSH_USER_KEY = "1f0b0a26cf8da207d5f46e56";
	public static String JIGUANG_PUSH_USER_SECRET = "d936566134352aa6f5973827";
	public static String JIGUAN_PUSH_AUNT_KEY = "6c2ba1f2fa61b62864d6c375";
	public static String JIGUNAG_PUSH_AUNT_SECRET = "c6a3b68e1f7e3a6ba24b9d96";
	
	// 容联云通信短信平台相关信息
	public static String SMS_ACCOUNT_SID = "8aaf070857514dd70157699bb6cc0c16";
	public static String SMS_ACCOUNT_TOKEN = "c1854283a46d47ef9de649ab9f974e0c";
	public static String SMS_APPID = "8aaf070857514dd70157699bb8290c1d";
	public static int CUSTOMER_PERMISSION_ID = 2;
	public static String SMS_TEMPLATE = "128814";

	//用户端APP的微信ID
	public static String WXCHAT_APPID = "wx5be474eb4e770f62";
	public static String WXCHAT_APPSECRET = "afb099e9403930d5cfa6830b7a5328e3";
	public static String WXCHAT_PARTNER = "1412554002";
	public static String WXCHAT_PARTNERKEY = "NNAxLiegfsz5jjGqS33c1wdm6hc8EpVG";
	
	//阿姨端的微信ID
	public static String WXCHAT_APPID_AY = "wx798d35ae04763c93";
	public static String WXCHAT_APPSECRET_AY = "7c86df350a643261357c8d201adfda01";
	public static String WXCHAT_PARTNER_AY = "1408552902";
	public static String WXCHAT_PARTNERKEY_AY = "NNAxLiegfsz5jjGqS33c1wdm6hc8EpVG";
	
	//Config表中的KEY值定义
	public static String DAY_MAX_SMS = "daymaxsms";
	public static String REGISTER_POINT_USER = "register_point_user";
	public static String REGISTER_POINT_AUNT = "register_point_aunt";
	public static String VIDEO_POINT_AUNT = "video_point_aunt";
	public static String SHARE_APP_POINT_USER = "share_app_point_user";
	
	//红包中的KEY值
	public static String RED_PACKET_SHARE = "red_packet_share";
	public static String RED_PACKET_ORDER_MONTH = "red_packet_order_month";
	public static String RED_PACKET_NEW_ORDER = "red_packet_new_order";
	public static String RED_PACKET_AUNT_COMPLETE_ORDER = "red_packet_aunt_complete_order";
	public static String RED_PACKET_AUNT_WEEK = "red_packet_aunt_week";
	
	
	//七牛云
	public static String ACCESS_KEY = "OazYdSs5VG_Cjr3GEhOSyfgz0_OcrDizWeerf26d";
	public static String SECRET_KEY = "GAKNvh2Yc8tNtSCQmaaFgcZKJRhSjaFB918FjYuu";
	public static String BUCKET = "uncle";
	public static String DOMAINOFBUCKET = "http://ofc6sbq4f.bkt.clouddn.com"; 
	
	public static String DEFAULT_REGION = "杭州市 ";
    public static double EARTH_RADIUS = 6378.137; 
    public static int VIEW_LENGTH = 300;
    public static int DISCONNECT_TIME = 1*60*1000;
    public static int DELAY_TIME = 2*60*60;
    
    public static String MUST_INFO_RATIO = "12";
    public static String OPTIONALINFO = "12";
	   
	public static final String SESSION_WXOPENID = "wxopenid";
	public static final String SESSION_WXUNIONID = "wxunionid";
	public static final String SESSION_WXUSERID = "wxuserid";
	public static final String SESSION_WXNICKNAME = "wxnickname";
	public static final String SESSION_WXHEADIMGURL = "wxheadimgurl";
	public static final String SESSION_ACCESSTOKEN = "wxaccesstoken";
	
}
