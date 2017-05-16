package com.uncleserver.service.api;

import com.uncleserver.model.Aunt;
import com.uncleserver.model.AuntExtra;
import com.uncleserver.model.Company;
import com.uncleserver.model.CompanyExtra;
import com.uncleserver.model.User;
import com.uncleserver.modelVo.ApiResult;

public interface AuntUserService extends BaseService {
	ApiResult sendVcode(String phone, int typeInt, String user_type);

	ApiResult checkVcode(String phone, String vcode, int typeInt, int user_type);

	ApiResult userRegister(String phone, String password, String vcode, String sessionid);// 用户注册

	ApiResult login(String phone, String password, String sessionid);// 登录

	ApiResult autoLogin(String accesstoken);// 自动登录

	ApiResult thirdLogin(String openid, String thirdtype, String sessionid, String nickname, String avatarurl);// 第三方登录

	ApiResult bindPhone(Aunt aunt, String phone, String vcode, String sessionid);// 绑定手机号码

	ApiResult changePassword(String phone, String vcode, String newpsw, String user_type);// 忘记密码-修改密码

	ApiResult companyLogin(String phone, String password, String sessionid);// 登录

	ApiResult companyAutoLogin(String accesstoken);// 登录

	ApiResult heartBeat(String accesstoken, int user_type, String longitude, String latitude);// 信条链接

	ApiResult feedBack(String content, int userid, int user_type);// 信条链接

	ApiResult sign(Aunt aunt, AuntExtra auntExtra);// 用户签到

	ApiResult refreshMessage(Aunt aunt, Company company);// 用户签到

	ApiResult messageList(Aunt aunt, Company company);// 用户签到

	ApiResult setReaded(Aunt aunt, Company company, String mids);// 用户签到

	ApiResult delMessage(Aunt aunt, Company company, String mids);// 用户签到

	ApiResult messageDetail(Aunt aunt, Company company, String messageid, int page);// 删除消息

	ApiResult getSignMonth(Aunt aunt, String time);// 获取本月签到记录

	ApiResult updateMustInfo(Aunt aunt, String real_name, String sex, String origin_place, String idcard_num,
			String nation, String work_year, String server_ids, String train_state, String language, String character,
			String now_address, String sessionid,String idcard_picids);// 获取本月签到记录

	ApiResult getAuntInfoSet();// 获取本月签到记录

	ApiResult updateOptionalInfo(Aunt aunt, String culture, String home_address, String religion, String political,
			String height, String weight, String marriage, String blood_type, String display, String work_his,
			String self_comment, String hobby);

	ApiResult refreshUserInfo(Aunt aunt, AuntExtra extra);// 更新用户信息(余额、优惠券、积分)

	ApiResult updateSet(AuntExtra extra, String voice_statem, String text_size);// 更新用户信息(余额、优惠券、积分)

	ApiResult updateSet(CompanyExtra extra, String voice_statem, String text_size);

	ApiResult getCommentList(String userid, String accesstoken, String user_type, int page);// 更新用户信息(余额、优惠券、积分)

	ApiResult changeInfo(Aunt aunt, String key, String value);// 更新用户信息(余额、优惠券、积分)

	ApiResult getBalanceRecord(int page, AuntExtra extra);// 获取用户余额变动记录

	ApiResult getBalanceRecord(int page, CompanyExtra extra);// 获取用户余额变动记录

	ApiResult getPointRecord(Aunt aunt, int page);// 获取用户余额变动记录

	ApiResult setAliInfo(String userid, String user_type, String account, String name);// 获取用户余额变动记录

	ApiResult cash(String userid, String user_type, String type, String count);// 获取用户余额变动记录

	ApiResult refreshInfo(Aunt aunt, AuntExtra extra);// 刷新阿姨信息

	ApiResult getTutorialList(int page);// 刷新阿姨信息

	ApiResult uoloadViewingTime(String userid, String tutorialid);// 刷新阿姨信息

	ApiResult logout(AuntExtra extra);

	ApiResult logout(CompanyExtra extra);
	
	ApiResult invite(Aunt aunt,String intive_code);// 删除消息
	
	ApiResult receiveRedPacket(Aunt aunt, Company company, String packetid);// 用户签到
	
	ApiResult redPacketList(Aunt aunt, Company company, String page);// 用户签到

	ApiResult setPassword(Aunt aunt, String password);

	ApiResult setCode();
}