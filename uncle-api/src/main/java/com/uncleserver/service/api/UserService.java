package com.uncleserver.service.api;

import javax.servlet.http.HttpServletRequest;

import com.uncleserver.model.User;
import com.uncleserver.model.UserExtra;
import com.uncleserver.modelVo.ApiResult;

public interface UserService extends BaseService {

	ApiResult sendVcode(String phone, int type);// 发送验证码-- 0:用户注册 1:忘记密码
												// 2:修改手机号--验证老手机号
												// 3:修改手机号--验证新手机号 //4:绑定手机号

	ApiResult checkVcode(String phone, String vcode, int typeInt);// 校验验证码--
																	// 0:用户注册
																	// 1:忘记密码
																	// 2:修改手机号--验证老手机号

	ApiResult registerUser(String phone, String password, String vcode, String sessionid);// 用户注册

	ApiResult login(String phone, String password, String sessionid);// 用户登录
	
	User userByPhone(String phone);

	ApiResult thirdLogin(String openid, String unionid, String thirdtype, String sessionid, String nickname,
			String avatarurl);// 第三方登录

	ApiResult autoLogin(String accesstoken);// 自动登录

	ApiResult refreshUserInfo(String userid, String accesstoken);// 更新用户信息(余额、优惠券、积分)

	ApiResult bindPhone(User user, String phone, String vcode, String sessionid);// 绑定手机号码

	ApiResult changePhone(User user, String phone, String vcode, String sessionid);// 修改手机号

	ApiResult changeUserInfo(User user, String key, String value);// 修改用户信息

	ApiResult getSignMonth(User user, String time);// 获取本月签到记录

	ApiResult getBalanceList(User user, String page);// 获取用户余额变动记录

	ApiResult getPointList(User user, String page);// 获取用户积分变动表

	ApiResult changePassword(String phone, String vcode, String newpsw);// 忘记密码-修改密码

	ApiResult addAddress(String userid, String provience, String city, String area, String longitude, String latitude,
			String phone, String rname, String sex, String addressname, String addressdetail, String isdefault);// 新增服务地址

	ApiResult editAddress(String userid, String addressid, String provience, String city, String area, String longitude,
			String latitude, String phone, String rname, String sex, String addressname, String addressdetail,
			String isdefault);// 编辑服务地址

	ApiResult delAddress(String userid, String addressid);// 删除服务地址

	ApiResult getAddressList(int userid, int categoryid);// 获取用户的服务地址列表

	ApiResult feedBack(String userid, String content);// 意见反馈

	ApiResult sign(User user, UserExtra extra);// 用户签到

	ApiResult recharge(User user, String count, String pay_type);// 用户签到

	ApiResult getMyCoupon(User user);// 用户签到

	ApiResult refreshMessage(User user);// 获取是否有未读消息

	ApiResult messageList(User user);// 获取是否有未读消息

	ApiResult setReaded(User user, String mids);// 获取是否有未读消息

	ApiResult delMessage(User user, String mids);// 删除消息

	ApiResult messageDetail(User user, String messageid, int page);// 删除消息

	ApiResult receiveCoupon(User user, String couponid);// 删除消息
	
	ApiResult hasPassword(User user);// 删除消息
	
	ApiResult invite(User user,String intive_code);// 删除消息
	
	ApiResult receiveRedPacket(User user,String packetid);// 删除消息
	
	ApiResult setPassword(User user,String password);// 删除消息

	void paySuccess(int type, String out_trade_no, String transaction_id, String openid);

	ApiResult setCode();
}
