package com.uncleserver.service.api.Impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.common.Constant;
import com.uncleserver.common.alipay.AlipayConfig;
import com.uncleserver.common.alipay.util.OrderInfoUtil2_0;
import com.uncleserver.common.sms.RLYSmsUtils;
import com.uncleserver.model.Area;
import com.uncleserver.model.BalanceRecord;
import com.uncleserver.model.CategorySecond;
import com.uncleserver.model.Company;
import com.uncleserver.model.Config;
import com.uncleserver.model.Coupon;
import com.uncleserver.model.CouponUser;
import com.uncleserver.model.FeedBack;
import com.uncleserver.model.Invitation;
import com.uncleserver.model.LevelSet;
import com.uncleserver.model.Message;
import com.uncleserver.model.MessageDetail;
import com.uncleserver.model.Order;
import com.uncleserver.model.OrderPay;
import com.uncleserver.model.OrderPool;
import com.uncleserver.model.OrderRefund;
import com.uncleserver.model.PickAddress;
import com.uncleserver.model.PointProgress;
import com.uncleserver.model.PointRecord;
import com.uncleserver.model.RechargeOrder;
import com.uncleserver.model.RedPacketRecord;
import com.uncleserver.model.SignRecord;
import com.uncleserver.model.SignSet;
import com.uncleserver.model.User;
import com.uncleserver.model.UserExtra;
import com.uncleserver.model.VCode;
import com.uncleserver.modelVo.ApiResult;
import com.uncleserver.modelVo.BalanceValue;
import com.uncleserver.modelVo.CouponValue;
import com.uncleserver.modelVo.DayValue;
import com.uncleserver.modelVo.MessageDetailValue;
import com.uncleserver.modelVo.MessageValue;
import com.uncleserver.modelVo.PayValue;
import com.uncleserver.modelVo.PointValue;
import com.uncleserver.modelVo.UserModel;
import com.uncleserver.service.api.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(8);

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult sendVcode(String phone, int type) {
		ApiResult result = new ApiResult();
		boolean canSend = checkCanSendVcode(phone);
		if (!canSend) {
			result.setCode("2");
			result.setMessage("发送失败,该号码今天请求验证码次数超过上限");
			return result;
		}

		Long count = userMapper.selectUserCountByPhone(phone);

		if (type == 0 || type == 3) {
			if (count != null && count > 0) {
				result.setCode("3");
				result.setMessage("发送失败,该手机号码已经注册");
				return result;
			}
		} else if (type == 1) {
			if (count == null || count == 0) {
				result.setCode("3");
				result.setMessage("发送失败,该手机号码未注册");
				return result;
			}
		}

		String vcode = CommonUtils.getRandomVcode();
		// 调用第三方发送验证码

		VCode vcodeModel = new VCode();
		vcodeModel.setAddtime(new Date());
		vcodeModel.setPhone(phone);
		vcodeModel.setType((short) type);
		vcodeModel.setVcode(vcode);
		vCodeMapper.insertSelective(vcodeModel);

		result.setCode("1");
		result.setMessage("验证码发送成功");
		HashMap<String, Object> map = new HashMap<>();
		map.put("vcode", vcode);
		RLYSmsUtils.SendSms(phone, Constant.SMS_TEMPLATE, new String[] { vcode, "五分钟" });
		result.setResult(map);
		return result;

	}

	@Override
	public ApiResult checkVcode(String phone, String vcode, int typeInt) {
		ApiResult result = new ApiResult();

		VCode vcodeModel = vCodeMapper.selectLastVcodeByPhoneAndType(phone, typeInt);
		if (vcodeModel == null || !vcode.equals(vcodeModel.getVcode())) {
			result.setCode("2");
			result.setMessage("验证码错误,请重新输入");
			return result;
		}

		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, -30);

		Calendar sendTime = Calendar.getInstance();
		sendTime.setTime(vcodeModel.getAddtime());

		if (now.after(sendTime)) {
			result.setCode("3");
			result.setMessage("验证码已过期,请重新获取");
			return result;
		}

		result.setCode("1");
		result.setMessage("验证成功");

		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult registerUser(String phone, String password, String vcode, String sessionid) {
		ApiResult result = new ApiResult();

		Long count = userMapper.selectUserCountByPhone(phone);

		if (count != null && count > 0) {
			result.setCode("2");
			result.setMessage("注册失败,该手机号码已经注册");
			return result;
		}

		VCode vcodeModel = vCodeMapper.selectLastVcodeByPhoneAndType(phone, 0);
		if (vcodeModel == null || !vcode.equals(vcodeModel.getVcode())) {
			result.setCode("2");
			result.setMessage("验证码错误,请重新输入");
			return result;
		} else {
			Calendar now = Calendar.getInstance();
			now.add(Calendar.MINUTE, -30);

			Calendar sendTime = Calendar.getInstance();
			sendTime.setTime(vcodeModel.getAddtime());
			if (now.after(sendTime)) {
				result.setCode("2");
				result.setCode("验证码已过期,请重新获取");
				return result;
			}
		}

		User user = new User();
		user.setAddtime(new Date());
		user.setPhone(phone);
		user.setTel(phone);
		user.setPassword(password);
		user.setLevel((short) 0);
		user.setNewcoupon((short) 0);
		
		userMapper.insert(user);
		
		user.setInvitation_count(0);
		user.setInvitation_code(CommonUtils.getUserInvite(user.getUserid()));
		user.setInvitationed_state((short)0);
		userMapper.updateByPrimaryKey(user);
		
		UserExtra userExtra = new UserExtra();
		userExtra.setUserid(user.getUserid());

		// 查询是否需要赠送积分
		Config config = configMapper.selectConfigByKey(Constant.REGISTER_POINT_USER);
		int sendPoint = 0;
		if (config != null && !CommonUtils.isEmptyString(config.getConfigvalue())) {
			sendPoint = CommonUtils.parseInt(config.getConfigvalue(), 0);
		}
		userExtra.setPoint(sendPoint);
		userExtra.setPoint_total(sendPoint);
		userExtra.setBalance(new BigDecimal("0.00"));
		userExtra.setUseTotal(new BigDecimal("0.00"));
		userExtra.setSignDay(0);
		String accesstoken = CommonUtils.MD5(sessionid + user.getUserid());
		userExtra.setAccesstoken(CommonUtils.MD5(accesstoken));
		userExtra.setTokenTime(new Date());
		userExtra.setUpdatetime(new Date());
		userExtraMapper.insertSelective(userExtra);

		if (sendPoint > 0) {
			PointRecord record = new PointRecord();
			record.setAddtime(new Date());
			record.setCount(sendPoint);
			record.setType((short) 1);
			record.setUserid(user.getUserid());
			pointRecordMapper.insertSelective(record);
		}

		result.setCode("1");
		result.setMessage("用户注册成功");
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult login(String phone, String password, String sessionid) {
		ApiResult result = new ApiResult();

		User user = userMapper.selectUserByPhone(phone);
		if (user == null) {
			result.setCode("2");
			result.setMessage("登录失败,用户不存在");
			return result;
		}

		if (!password.equals(user.getPassword())) {
			result.setCode("3");
			result.setMessage("登录失败,密码错误");
			return result;
		}

		// 更新用户信息
		UserExtra userExtra = userExtraMapper.selectByUserId(user.getUserid());
		if (userExtra == null) {
			userExtra = new UserExtra();
			userExtra.setUserid(user.getUserid());

			// 查询是否需要赠送积分
			Config config = configMapper.selectConfigByKey(Constant.REGISTER_POINT_USER);
			int sendPoint = 0;
			if (config != null && !CommonUtils.isEmptyString(config.getConfigvalue())) {
				sendPoint = CommonUtils.parseInt(config.getConfigvalue(), 0);
			}
			userExtra.setPoint(sendPoint);
			userExtra.setPoint_total(0);
			userExtra.setBalance(new BigDecimal("0.00"));
			userExtra.setUseTotal(new BigDecimal("0.00"));
			userExtra.setSignDay(0);
			String accesstoken = CommonUtils.MD5(sessionid + user.getUserid());
			userExtra.setAccesstoken(CommonUtils.MD5(accesstoken));
			userExtra.setTokenTime(new Date());
			userExtra.setUpdatetime(new Date());
			userExtra.setLastLogin(new Date());
			userExtraMapper.insertSelective(userExtra);
		} else {
			userExtra.setLastLogin(new Date());
			String accesstoken = CommonUtils.MD5(sessionid + user.getUserid());
			userExtra.setAccesstoken(CommonUtils.MD5(accesstoken));
			userExtra.setTokenTime(new Date());
			userExtraMapper.updateByPrimaryKey(userExtra);
		}

		UserModel model = createUserModel(user, userExtra);
		result.setCode("1");
		result.setMessage("登录成功");

		HashMap<String, Object> map = new HashMap<>();
		map.put("userinfo", model);
		result.setResult(map);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult thirdLogin(String openid, String unionid, String thirdtype, String sessionid, String nickname,
			String avatarurl) {
		ApiResult result = new ApiResult();
		Integer thirdTypeI = Integer.parseInt(thirdtype);
		User user = null;
		UserExtra userExtra = null;
		if (thirdTypeI == 1) {
			user = userMapper.selectUserByQQId(openid);
		} else if (thirdTypeI == 2) {
			user = userMapper.selectUserBySinaId(openid);
		} else if (thirdTypeI == 3) {
			user = userMapper.selectUserByWxId(openid);
		}

		if (null == user) {
			user = new User();
			user.setAddtime(new Date());
			user.setPhone("");
			user.setTel("");
			user.setPassword("");
			user.setLevel((short) 0);
			user.setRealName(CommonUtils.removeAllEmojis(nickname));
			user.setThirdAvatar(avatarurl);
			user.setNewcoupon((short) 0);

			if (thirdTypeI == 1) {
				user.setQqId(openid);
			} else if (thirdTypeI == 2) {
				user.setSinaId(openid);
			} else if (thirdTypeI == 3) {
				user.setWxId(openid);
				user.setWxUnionid(unionid);
			}
			userMapper.insert(user);
			
			user.setInvitation_count(0);
			user.setInvitation_code(CommonUtils.getUserInvite(user.getUserid()));
			user.setInvitationed_state((short)0);
			userMapper.updateByPrimaryKey(user);
			
			userExtra = new UserExtra();
			userExtra.setUserid(user.getUserid());
			// 查询是否需要赠送积分
			Config config = configMapper.selectConfigByKey(Constant.REGISTER_POINT_USER);
			int sendPoint = 0;
			if (config != null && !CommonUtils.isEmptyString(config.getConfigvalue())) {
				sendPoint = CommonUtils.parseInt(config.getConfigvalue(), 0);
			}
			userExtra.setPoint(sendPoint);
			userExtra.setPoint_total(sendPoint);
			userExtra.setBalance(new BigDecimal("0.00"));
			userExtra.setUseTotal(new BigDecimal("0.00"));
			userExtra.setSignDay(0);
			String accesstoken = CommonUtils.MD5(sessionid + user.getUserid());
			userExtra.setAccesstoken(CommonUtils.MD5(accesstoken));
			userExtra.setTokenTime(new Date());
			userExtra.setUpdatetime(new Date());
			userExtraMapper.insertSelective(userExtra);
			if (sendPoint > 0) {
				PointRecord record = new PointRecord();
				record.setAddtime(new Date());
				record.setCount(sendPoint);
				record.setType((short) 1);
				record.setUserid(user.getUserid());
				pointRecordMapper.insertSelective(record);
			}
		} else {
			if (CommonUtils.isEmptyString(user.getRealName())) {
				user.setRealName(CommonUtils.removeAllEmojis(nickname));
			}
			if (thirdTypeI == 1) {
				user.setQqId(openid);
			} else if (thirdTypeI == 2) {
				user.setSinaId(openid);
			} else if (thirdTypeI == 3) {
				user.setWxId(openid);
				user.setWxUnionid(unionid);
			}
			user.setThirdAvatar(avatarurl);
			userMapper.updateByPrimaryKey(user);
			// 更新用户信息
			userExtra = userExtraMapper.selectByUserId(user.getUserid());
			if (userExtra == null) {
				userExtra = new UserExtra();
				userExtra.setUserid(user.getUserid());

				// 查询是否需要赠送积分
				Config config = configMapper.selectConfigByKey(Constant.REGISTER_POINT_USER);
				int sendPoint = 0;
				if (config != null && !CommonUtils.isEmptyString(config.getConfigvalue())) {
					sendPoint = CommonUtils.parseInt(config.getConfigvalue(), 0);
				}
				userExtra.setPoint(sendPoint);
				userExtra.setPoint_total(0);
				userExtra.setBalance(new BigDecimal("0.00"));
				userExtra.setUseTotal(new BigDecimal("0.00"));
				userExtra.setSignDay(0);
				String accesstoken = CommonUtils.MD5(sessionid + user.getUserid());
				userExtra.setAccesstoken(CommonUtils.MD5(accesstoken));
				userExtra.setTokenTime(new Date());
				userExtra.setUpdatetime(new Date());
				userExtra.setLastLogin(new Date());
				userExtraMapper.insertSelective(userExtra);
				if (sendPoint > 0) {
					PointRecord record = new PointRecord();
					record.setAddtime(new Date());
					record.setCount(sendPoint);
					record.setType((short) 1);
					record.setUserid(user.getUserid());
					pointRecordMapper.insertSelective(record);
				}
			} else {
				userExtra.setLastLogin(new Date());
				String accesstoken = CommonUtils.MD5(sessionid + user.getUserid());
				userExtra.setAccesstoken(CommonUtils.MD5(accesstoken));
				userExtra.setTokenTime(new Date());
				userExtraMapper.updateByPrimaryKey(userExtra);
			}
		}
		// 用户等级
		LevelSet levelSet = levelSetMapper.setlectSetByPoint(userExtra.getPoint_total());
		if (levelSet != null && levelSet.getLevel() != user.getLevel()) {
			user.setLevel(levelSet.getLevel());
			userMapper.updateByPrimaryKey(user);
		}

		UserModel model = createUserModel(user, userExtra);
		result.setCode("1");
		result.setMessage("登录成功");

		HashMap<String, Object> map = new HashMap<>();
		map.put("userinfo", model);
		result.setResult(map);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult autoLogin(String accesstoken) {
		ApiResult result = new ApiResult();
		User user = null;
		UserExtra userExtra = null;
		userExtra = userExtraMapper.selectByAccesstoken(accesstoken);
		if (null == userExtra) {
			result.setCode("2");
			result.setMessage("登录失败,用户不存在");
			return result;
		}

		user = userMapper.selectByPrimaryKey(userExtra.getUserid());
		if (user == null) {
			result.setCode("2");
			result.setMessage("登录失败,用户不存在");
			return result;
		}

		if (!CommonUtils.checkSession(userExtra.getAccesstoken(), accesstoken)) {
			result.setCode("11");
			result.setMessage("您的登录信息已经过期,或者已经在其它终端登录,请重新登录");
			return result;
		}
		Date nowtime = new Date();
		Date dafter = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(userExtra.getTokenTime());
		calendar.add(Calendar.MONTH, 3);
		dafter = calendar.getTime();
		if (nowtime.getTime() > dafter.getTime()) {
			result.setCode("4");
			result.setMessage("自动登录失败,token过期.请重新登录");
			return result;
		}

		userExtra.setLastLogin(new Date());
		userExtra.setTokenTime(new Date());
		userExtraMapper.updateByPrimaryKey(userExtra);

		UserModel model = createUserModel(user, userExtra);
		result.setCode("1");
		result.setMessage("请求成功");

		HashMap<String, Object> map = new HashMap<>();
		/*
		 * map.put("real_name", model.getReal_name()); map.put("avatarurl",
		 * model.getAvatarurl()); map.put("level", model.getLevel());
		 * map.put("levelname", model.getLevelname()); map.put("point",
		 * model.getPoint()); map.put("balance", model.getBalance());
		 * map.put("sign_week", model.getSign_week()); map.put("today_sign",
		 * model.getToday_sign()); map.put("couponcount",
		 * model.getCouponcount());
		 */
		map.put("userinfo", model);
		result.setResult(map);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult refreshUserInfo(String userid, String accesstoken) {
		ApiResult result = new ApiResult();
		User user = null;
		UserExtra userExtra = null;
		user = userMapper.selectByPrimaryKey(Integer.parseInt(userid));
		if (user == null) {
			result.setCode("2");
			result.setMessage("登录失败,用户不存在");
			return result;
		}
		userExtra = userExtraMapper.selectByUserId(Integer.parseInt(userid));
		if (!CommonUtils.checkSession(userExtra.getAccesstoken(), accesstoken)) {
			result.setCode("11");
			result.setMessage("您的登录信息已经过期,或者已经在其它终端登录,请重新登录");
			return result;
		}
		userExtra.setUpdatetime(new Date());
		userExtraMapper.updateByPrimaryKey(userExtra);
		UserModel model = createUserModel(user, userExtra);

		HashMap<String, Object> map = new HashMap<>();
		map.put("real_name", model.getReal_name());
		map.put("avatarurl", model.getAvatarurl());
		map.put("level", model.getLevel());
		map.put("levelname", model.getLevelname());
		map.put("point", model.getPoint());
		map.put("balance", model.getBalance());
		map.put("sign_week", model.getSign_week());
		map.put("today_sign", model.getToday_sign());
		map.put("couponcount", model.getCouponcount());

		result.setCode("1");
		result.setMessage("请求成功");
		result.setResult(map);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult bindPhone(User user, String phone, String vcode, String sessionid) {
		ApiResult result = new ApiResult();

		VCode vcodeModel = vCodeMapper.selectLastVcodeByPhoneAndType(phone, 4);
		if (vcodeModel == null || !vcode.equals(vcodeModel.getVcode())) {
			result.setCode("2");
			result.setMessage("验证码错误,请重新输入");
			return result;
		}

		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, -30);

		Calendar sendTime = Calendar.getInstance();
		sendTime.setTime(vcodeModel.getAddtime());

		if (now.after(sendTime)) {
			result.setCode("3");
			result.setMessage("验证码已过期,请重新获取");
			return result;
		}

		User userPhone = userMapper.selectUserByPhone(phone);
		if (userPhone == null) {
			user.setPhone(phone);
			// user.setPassword(password);
			userMapper.updateByPrimaryKey(user);
			UserExtra extra = userExtraMapper.selectByUserId(user.getUserid());
			UserModel model = createUserModel(user, extra);
			HashMap<String, Object> map = new HashMap<>();
			map.put("userinfo", model);
			map.put("userid", model.getUserid());
			map.put("accesstoken", model.getAccesstoken());
			result.setCode("1");
			result.setMessage("修改成功");
			result.setResult(map);
			return result;
		} else {
			userPhone.setThirdAvatar(user.getThirdAvatar());
			userPhone.setWxId(user.getWxId());
			userPhone.setQqId(user.getQqId());
			userPhone.setSinaId(user.getSinaId());
			userPhone.setWxUnionid(user.getWxUnionid());
			if (CommonUtils.isEmptyString(userPhone.getRealName())) {
				userPhone.setRealName(user.getRealName());
			}

			userMapper.updateByPrimaryKey(userPhone);
			UserExtra extra = userExtraMapper.selectByUserId(user.getUserid());
			if (null != extra) {
				userExtraMapper.deleteByPrimaryKey(extra.getDataid());
			}
			
			userMapper.deleteByPrimaryKey(user.getUserid());
			UserExtra userExtra = userExtraMapper.selectByUserId(user.getUserid());
			if(userExtra != null){
				userExtraMapper.deleteByPrimaryKey(userExtra.getDataid());
			}
			
			UserExtra userPhoneExrea = userExtraMapper.selectByUserId(userPhone.getUserid());
			UserModel model = createUserModel(userPhone, userPhoneExrea);
			HashMap<String, Object> map = new HashMap<>();
			map.put("userinfo", model);
			map.put("userid", model.getUserid());
			map.put("accesstoken", model.getAccesstoken());
			result.setCode("1");
			result.setMessage("修改成功");
			result.setResult(map);
			return result;

		}

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult changePhone(User user, String phone, String vcode, String sessionid) {
		ApiResult result = new ApiResult();

		VCode vcodeModel = vCodeMapper.selectLastVcodeByPhoneAndType(phone, 3);
		if (vcodeModel == null || !vcode.equals(vcodeModel.getVcode())) {
			result.setCode("2");
			result.setMessage("验证码错误,请重新输入");
			return result;
		}

		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, -30);

		Calendar sendTime = Calendar.getInstance();
		sendTime.setTime(vcodeModel.getAddtime());

		if (now.after(sendTime)) {
			result.setCode("3");
			result.setMessage("验证码已过期,请重新获取");
			return result;
		}

		UserModel model = null;
		User userPhone = userMapper.selectUserByPhone(phone);
		user.setPhone(phone);
		userMapper.updateByPrimaryKey(user);
		if (userPhone != null) {
			UserExtra userPhoneExtra = userExtraMapper.selectByUserId(userPhone.getUserid());
			if (!CommonUtils.isEmptyString(user.getQqId()))
				userPhone.setQqId(user.getQqId());
			if (!CommonUtils.isEmptyString(user.getWxId()))
				userPhone.setWxId(user.getWxId());
			if (!CommonUtils.isEmptyString(user.getSinaId()))
				userPhone.setSinaId(user.getSinaId());
			userMapper.deleteByPrimaryKey(user.getUserid());
			model = createUserModel(userPhone, userPhoneExtra);
		} else {

			UserExtra userExtra = new UserExtra();
			userExtra.setUserid(user.getUserid());

			// 查询是否需要赠送积分
			Config config = configMapper.selectConfigByKey(Constant.REGISTER_POINT_USER);
			int sendPoint = 0;
			if (config != null && !CommonUtils.isEmptyString(config.getConfigvalue())) {
				sendPoint = CommonUtils.parseInt(config.getConfigvalue(), 0);
			}
			userExtra.setPoint(sendPoint);
			userExtra.setPoint_total(sendPoint);
			userExtra.setBalance(new BigDecimal("0.00"));
			userExtra.setUseTotal(new BigDecimal("0.00"));
			userExtra.setSignDay(0);
			String accesstoken = CommonUtils.MD5(sessionid + user.getUserid());
			userExtra.setAccesstoken(CommonUtils.MD5(accesstoken));
			userExtra.setTokenTime(new Date());
			userExtra.setUpdatetime(new Date());
			userExtra.setLastLogin(new Date());
			userExtraMapper.insertSelective(userExtra);

			model = createUserModel(user, userExtra);
		}

		HashMap<String, Object> map = new HashMap<>();
		map.put("userinfo", model);
		result.setCode("1");
		result.setMessage("手机号绑定成功");
		result.setResult(map);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult changeUserInfo(User user, String key, String value) {
		ApiResult result = new ApiResult();
		int keyI = Integer.parseInt(key);

		value = CommonUtils.removeAllEmojis(value);
		if (keyI == 1) {
			user.setAvatar(Integer.parseInt(value));
		} else if (keyI == 2) {
			user.setRealName(value);
		} else if (keyI == 3) {
			Date date = CommonUtils.getDateFormat(value, "yyyy-MM-dd");
			user.setBirthday(date);
		} else if (keyI == 4) {
			user.setSignature(value);
		}
		// 资料完整度
		Integer info_ratio = 0;
		if (user.getAvatar() != null) {
			info_ratio++;
		}
		if (user.getRealName() != null) {
			info_ratio++;
		}
		if (user.getBirthday() != null) {
			info_ratio++;
		}
		if (user.getSignature() != null) {
			info_ratio++;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		PointProgress pointProgress = pointProgressMapper.selectByUserid(user.getUserid());
		UserExtra userExtra = userExtraMapper.selectByUserId(user.getUserid());
		if (pointProgress == null) {
			if (userExtra != null) {
				// 用户信息
				userExtra.setPoint(userExtra.getPoint() + info_ratio * 25);
				userExtra.setPoint_total(userExtra.getPoint_total() + info_ratio * 25);
				userExtraMapper.updateByPrimaryKey(userExtra);
				// 积分变动
				PointRecord pointRecord = new PointRecord();
				pointRecord.setAddtime(new Date());
				pointRecord.setCount(info_ratio * 25);
				pointRecord.setType((short) 2);
				pointRecord.setUserid(user.getUserid());
				pointRecordMapper.insert(pointRecord);
				// 用户积分获取进度记录
				PointProgress pointProgressAdd = new PointProgress();
				pointProgressAdd.setAddtime(new Date());
				pointProgressAdd.setFirstOrder((byte) 0);
				pointProgressAdd.setUserInfo(info_ratio.byteValue());
				pointProgressAdd.setShareApp((byte) 0);
				pointProgressAdd.setUserid(user.getUserid());
				pointProgressMapper.insert(pointProgressAdd);
				map.put("point", info_ratio * 25);
			}
		} else if (pointProgress.getUserInfo() < info_ratio) {
			if (userExtra != null) {
				// 用户信息
				userExtra.setPoint(userExtra.getPoint() + (info_ratio - pointProgress.getUserInfo()) * 25);
				userExtra.setPoint_total(userExtra.getPoint_total() + (info_ratio - pointProgress.getUserInfo()) * 25);
				userExtraMapper.updateByPrimaryKey(userExtra);
				// 积分变动
				PointRecord pointRecord = new PointRecord();
				pointRecord.setAddtime(new Date());
				pointRecord.setCount((info_ratio - pointProgress.getUserInfo()) * 25);
				pointRecord.setType((short) 2);
				pointRecord.setUserid(user.getUserid());
				pointRecordMapper.insert(pointRecord);
				// 用户积分获取进度记录表更新
				pointProgress.setUserInfo(info_ratio.byteValue());
				pointProgressMapper.updateByPrimaryKey(pointProgress);
				map.put("point", (info_ratio - pointProgress.getUserInfo()) * 25);
			}
		} else {
			map.put("point", 0);
		}
		// 用户等级
		LevelSet levelSet = levelSetMapper.setlectSetByPoint(userExtra.getPoint_total());
		if (levelSet != null && levelSet.getLevel() != user.getLevel()) {
			user.setLevel(levelSet.getLevel());
		}
		userMapper.updateByPrimaryKey(user);
		UserExtra userPhoneExtra = userExtraMapper.selectByUserId(user.getUserid());
		UserModel model = createUserModel(user, userPhoneExtra);
		map.put("avatarurl", model.getAvatarurl());
		map.put("name", model.getReal_name());
		map.put("birthday", model.getBirthday());
		map.put("signature", model.getSignature());
		map.put("level", model.getLevel());
		map.put("level_name", model.getLevelname());
		map.put("info_ratio", info_ratio * 25 + "%");
		result.setCode("1");
		result.setMessage("修改成功");
		result.setResult(map);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult getSignMonth(User user, String time) {
		ApiResult result = new ApiResult();
		Date date = CommonUtils.getDateFormat(time, "yyyy-MM");
		UserExtra userPhoneExtra = userExtraMapper.selectByUserId(user.getUserid());
		int signdays = userPhoneExtra.getSignDay();
		HashMap<String, Object> map = new HashMap<>();
		map.put("signdays", signdays);
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		String firstDate = CommonUtils.getFirstDayOfMonth(year, month);
		String lastDate = CommonUtils.getLastDayOfMonth(year, month);
		List<SignRecord> signRecords = signRecordMapper.selectByMonth(firstDate, lastDate, user.getUserid());
		List<DayValue> dayList = new ArrayList<DayValue>();
		if (signRecords != null && signRecords.size() > 0) {
			for (SignRecord signRecord : signRecords) {
				String day = CommonUtils.getTimeFormat(signRecord.getAddtime(), "yyyy-MM-dd");
				DayValue dayValue = new DayValue();
				dayValue.setDay(day);
				dayList.add(dayValue);
			}
		}
		map.put("dayList", dayList);
		result.setCode("1");
		result.setMessage("请求成功");
		result.setResult(map);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult getBalanceList(User user, String page) {
		ApiResult result = new ApiResult();
		HashMap<String, Object> map = new HashMap<>();
		UserExtra userPhoneExtra = userExtraMapper.selectByUserId(user.getUserid());
		if (userPhoneExtra.getBalance() != null) {
			map.put("balance", userPhoneExtra.getBalance().doubleValue());
		} else {
			map.put("balance", "0.00");
		}
		List<BalanceRecord> record = balanceRecordMapper.selectByPage(Integer.parseInt(page) - 1, 10, user.getUserid());
		List<BalanceRecord> recordMore = balanceRecordMapper.selectByPage(Integer.parseInt(page), 10, user.getUserid());
		if (recordMore != null && recordMore.size() > 0) {
			map.put("havemore", 1);
		} else {
			map.put("havemore", 0);
		}
		List<BalanceValue> valueList = new ArrayList<>();
		for (BalanceRecord balanceRecord : record) {
			BalanceValue value = new BalanceValue();
			if(balanceRecord.getType() != null){
				value.setType(balanceRecord.getType());
			    if(balanceRecord.getType() == 1){
			    	value.setTitle("定金支付减少");
			    	
			    }else if(balanceRecord.getType() == 2){
			    	value.setTitle("订单支付减少");
			    	
			    }else if(balanceRecord.getType() == 3){
			    	value.setTitle("充值增加");
			    	
			    }else if(balanceRecord.getType() == 4){
			    	value.setTitle("小费打赏减少");
			    	
			    }else if(balanceRecord.getType() == 5){
			    	value.setTitle("红包增加");
			    }
			
			}else {
				value.setType(1);
				value.setTitle("定金支付减少");
			}
			
			
			value.setTime(CommonUtils.getTimeFormat(balanceRecord.getAddtime(), "yyyy-MM-dd HH:mm:ss"));
			value.setCount(balanceRecord.getCount().setScale(2, BigDecimal.ROUND_UP).floatValue());
			valueList.add(value);
		}
		map.put("balanceList", valueList);
		result.setCode("1");
		result.setMessage("请求成功");
		result.setResult(map);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult getPointList(User user, String page) {
		ApiResult result = new ApiResult();
		HashMap<String, Object> map = new HashMap<>();
		UserExtra userPhoneExtra = userExtraMapper.selectByUserId(user.getUserid());
		float point = userPhoneExtra.getPoint();
		List<PointRecord> record = pointRecordMapper.selectByPage(Integer.parseInt(page) - 1, 10, user.getUserid());
		List<PointRecord> recordMore = pointRecordMapper.selectByPage(Integer.parseInt(page), 10, user.getUserid());
		map.put("point", point);
		if (recordMore != null && recordMore.size() > 0) {
			map.put("havemore", 1);
		} else {
			map.put("havemore", 0);
		}
		List<PointValue> valueList = new ArrayList<>();
		for (PointRecord balanceRecord : record) {
			PointValue value = new PointValue();
			
			value.setTime(CommonUtils.getTimeFormat(balanceRecord.getAddtime(), "yyyy-MM-dd HH:mm:ss"));
			value.setCount(balanceRecord.getCount());
			if (balanceRecord.getType() == 0) {
				value.setTitle("签到增加");
				value.setType(0);
			} else if (balanceRecord.getType() == 1) {
				value.setTitle("注册赠送");
				value.setType(0);
			} else if (balanceRecord.getType() == 2) {
				value.setTitle("完善用户信息赠送");
				value.setType(0);
			} else if (balanceRecord.getType() == 3) {
				value.setTitle("分享APP获得");
				value.setType(0);
			} else if (balanceRecord.getType() == 4) {
				value.setTitle("评价订单获取");
				value.setType(0);
			} else if (balanceRecord.getType() == 5) {
				value.setTitle("生日赠送");
				value.setType(0);
			} else if (balanceRecord.getType() == 6) {
				value.setTitle("订单支付获取");
				value.setType(0);
			} else if (balanceRecord.getType() == 7) {
				value.setTitle("订单支付抵扣");
				value.setType(1);
			} else if (balanceRecord.getType() == 8) {
				value.setTitle("其它");
				value.setType(0);
			} else {

			}
			valueList.add(value);
		}
		map.put("pointList", valueList);
		result.setCode("1");
		result.setMessage("请求成功");
		result.setResult(map);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult changePassword(String phone, String vcode, String newpsw) {
		ApiResult result = new ApiResult();
		VCode vcodeModel = vCodeMapper.selectLastVcodeByPhoneAndType(phone, 1);
		if (vcodeModel == null || !vcode.equals(vcodeModel.getVcode())) {
			result.setCode("2");
			result.setMessage("验证码错误,请重新输入");
			return result;
		}

		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, -30);

		Calendar sendTime = Calendar.getInstance();
		sendTime.setTime(vcodeModel.getAddtime());

		if (now.after(sendTime)) {
			result.setCode("3");
			result.setMessage("验证码已过期,请重新获取");
			return result;
		}

		User user = userMapper.selectUserByPhone(phone);
		if (user == null) {
			result.setCode("4");
			result.setMessage("修改失败,该手机号未注册");
			return result;
		}
		user.setPassword(newpsw);

		userMapper.updateByPrimaryKey(user);

		result.setCode("1");
		result.setMessage("修改成功");
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult addAddress(String userid, String provience, String city, String area, String longitude,
			String latitude, String phone, String rname, String sex, String addressname, String addressdetail,
			String isdefault) {
		ApiResult result = new ApiResult();
		int isdefaultInt = CommonUtils.parseInt(isdefault, 0);
		PickAddress address = new PickAddress();
		address.setUserid(CommonUtils.parseInt(userid, 0));
		if (!CommonUtils.isEmptyString(provience))
			address.setProvience(provience);
		if (!CommonUtils.isEmptyString(area))
			address.setArea(area);
		address.setCity(city);
		address.setLongitude(CommonUtils.parseDouble(longitude, 0d));
		address.setLatitude(CommonUtils.parseDouble(latitude, 0d));
		address.setPhone(phone);
		address.setRname(rname);
		address.setSex(sex);
		address.setAddressname(addressname);
		address.setAddressdetail(CommonUtils.removeAllEmojis(addressdetail));
		address.setIsdefault((short) isdefaultInt);
		address.setAddtime(new Date());

		if (isdefaultInt == 1) {// 设置用户其它的地址不是默认地址
			pickAddressMapper.updateUserDefault(CommonUtils.parseInt(userid, 0));
		}
		pickAddressMapper.insert(address);

		result.setCode("1");
		result.setMessage("添加成功");
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult editAddress(String userid, String addressid, String provience, String city, String area,
			String longitude, String latitude, String phone, String rname, String sex, String addressname,
			String addressdetail, String isdefault) {
		ApiResult result = new ApiResult();

		PickAddress addressOld = pickAddressMapper.selectByPrimaryKey(CommonUtils.parseInt(addressid, 0));
		if (addressOld == null) {
			result.setCode("2");
			result.setMessage("编辑失败,服务地址不存在");
			return result;
		}
		int isdefaultInt = CommonUtils.parseInt(isdefault, 0);

		addressOld.setUserid(CommonUtils.parseInt(userid, 0));
		if (!CommonUtils.isEmptyString(provience))
			addressOld.setProvience(provience);
		if (!CommonUtils.isEmptyString(area))
			addressOld.setArea(area);
		addressOld.setCity(city);
		addressOld.setLongitude(CommonUtils.parseDouble(longitude, 0d));
		addressOld.setLatitude(CommonUtils.parseDouble(latitude, 0d));
		addressOld.setPhone(phone);
		addressOld.setRname(rname);
		addressOld.setSex(sex);
		addressOld.setAddressname(addressname);
		addressOld.setAddressdetail(CommonUtils.removeAllEmojis(addressdetail));
		if (addressOld.getIsdefault() != null && addressOld.getIsdefault() == 1) {// 本来就是默认
			addressOld.setIsdefault((short) isdefaultInt);
			pickAddressMapper.updateByPrimaryKey(addressOld);
		} else {
			if (isdefaultInt == 1) {// 设置用户其它的地址不是默认地址
				pickAddressMapper.updateUserDefault(CommonUtils.parseInt(userid, 0));
			}
			addressOld.setIsdefault((short) isdefaultInt);
			pickAddressMapper.updateByPrimaryKey(addressOld);
		}

		result.setCode("1");
		result.setMessage("编辑成功");
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult delAddress(String userid, String addressid) {
		ApiResult result = new ApiResult();

		PickAddress addressOld = pickAddressMapper.selectByPrimaryKey(CommonUtils.parseInt(addressid, 0));
		if (addressOld == null) {
			result.setCode("2");
			result.setMessage("删除失败,服务地址不存在");
			return result;
		}

		pickAddressMapper.deleteByPrimaryKey(CommonUtils.parseInt(addressid, 0));

		result.setCode("1");
		result.setMessage("删除成功");
		return result;
	}

	@Override
	public ApiResult getAddressList(int userid, int categoryid) {
		ApiResult result = new ApiResult();

		List<PickAddress> addressList = pickAddressMapper.getUserAllPickAddress(userid);
		if (categoryid > 0) {
			for (PickAddress pickAddress : addressList) {

				Long count = categoryCityMapper.selectAreaCidCount(pickAddress.getArea(), categoryid);
				if (count != null && count > 0) {
					pickAddress.setChoose_state(1);
					Area area = areaMapper.selectByName(pickAddress.getArea());
					if (area != null && area.getFastState() == 0) {
						pickAddress.setFast_state(0);
					} else {
						pickAddress.setFast_state(1);
					}
				} else {
					pickAddress.setChoose_state(0);
				}

			}
		}

		result.setCode("1");
		result.setMessage("请求成功");

		HashMap<String, Object> map = new HashMap<>();
		map.put("addressList", addressList);
		result.setResult(map);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult feedBack(String userid, String content) {
		ApiResult result = new ApiResult();

		User user = userMapper.selectByPrimaryKey(CommonUtils.parseInt(userid, 0));

		FeedBack feedBack = new FeedBack();
		feedBack.setAddtime(new Date());
		feedBack.setContent(CommonUtils.removeAllEmojis(content));
		if (user != null)
			feedBack.setUserid(user.getUserid());
		else
			feedBack.setUserid(0);

		feedBackMapper.insertSelective(feedBack);
		result.setCode("1");
		result.setMessage("提交成功,感谢您的宝贵意见");
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult sign(User user, UserExtra extra) {
		ApiResult result = new ApiResult();
		Calendar now = Calendar.getInstance();
		if (extra.getLastSign() != null) {
			String dateStr1 = CommonUtils.getTimeFormat(extra.getLastSign(), "yyyy-MM-dd");
			String dateStr2 = CommonUtils.getTimeFormat(now.getTime(), "yyyy-MM-dd");
			if (dateStr1.equals(dateStr2)) {
				result.setCode("2");
				result.setMessage("签到失败,您今天已经签过到了");
				return result;
			}
		}

		int continuityDay = 1;// 连续签到天数
		if (extra.getLastSign() != null) {
			Calendar lastSignday = Calendar.getInstance();
			lastSignday.setTime(extra.getLastSign());
			lastSignday.add(Calendar.DAY_OF_MONTH, 1);
			if (lastSignday.get(Calendar.YEAR) == now.get(Calendar.YEAR)
					&& lastSignday.get(Calendar.MONTH) == now.get(Calendar.MONTH)
					&& lastSignday.get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH)) {
				if (extra.getSignDay() != null) {
					continuityDay = extra.getSignDay() + 1;
				}
			}
		}
		// 设置好用户信息
		extra.setLastSign(now.getTime());
		extra.setSignDay(continuityDay);

		// 查询应该增加多少积分
		SignSet set = signSetMapper.selectFitSet(continuityDay);
		int addPoint = 1;
		if (set != null && set.getPoint() != null) {
			addPoint = set.getPoint();
		}
		if (extra.getPoint() != null) {
			extra.setPoint(extra.getPoint() + addPoint);
		} else {
			extra.setPoint(addPoint);
		}
		if (extra.getPoint_total() != null) {
			extra.setPoint_total(extra.getPoint_total() + addPoint);
		} else {
			extra.setPoint_total(addPoint);
		}

		// 积分记录
		PointRecord pointRecord = new PointRecord();
		pointRecord.setAddtime(new Date());
		pointRecord.setCount(addPoint);
		pointRecord.setType((short) 0);
		pointRecord.setUserid(user.getUserid());
		pointRecordMapper.insert(pointRecord);
		// 签到记录
		SignRecord signRecord = new SignRecord();
		signRecord.setAddtime(new Date());
		signRecord.setUserid(user.getUserid());
		signRecordMapper.insert(signRecord);

		// 用户等级
		LevelSet levelSet = levelSetMapper.setlectSetByPoint(extra.getPoint_total());
		if (levelSet != null && levelSet.getLevel() != user.getLevel()) {
			user.setLevel(levelSet.getLevel());
			userMapper.updateByPrimaryKey(user);
		}
		userExtraMapper.updateByPrimaryKey(extra);
		result.setCode("1");
		result.setMessage("签到成功");

		HashMap<String, Object> map = new HashMap<>();
		map.put("days", continuityDay);
		map.put("point", addPoint);
		map.put("level", user.getLevel());
		if (levelSet != null) {
			map.put("level_name", levelSet.getTitle());
		}

		// 本周连续签到天数
		now.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		String dateStrMonday = CommonUtils.getTimeFormat(now.getTime(), "yyyy-MM-dd");
		now.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		String dateStrSunday = CommonUtils.getTimeFormat(now.getTime(), "yyyy-MM-dd");

		Long count = signRecordMapper.selectWeekSignCount(dateStrMonday, dateStrSunday, user.getUserid());
		if (count == null) {
			map.put("weekday", 0);
		} else {
			map.put("weekday", count.intValue());
		}
		result.setResult(map);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult recharge(User user, String count, String pay_type) {
		ApiResult result = new ApiResult();
		short paytypeShort = CommonUtils.parseShort(pay_type, (short) 1);
		// 生成充值订单
		RechargeOrder order = new RechargeOrder();
		order.setAddtime(new Date());
		order.setPayType((byte) paytypeShort);
		order.setUserid(user.getUserid());
		BigDecimal amount = new BigDecimal(count);
		order.setAmount(amount);
		order.setState((byte) 0);

		Long nowcount = rechargeOrderMapper.selectOrderNumCount();
		String thirdNumID = createOrderNum(1, nowcount);
		order.setOrdernum(thirdNumID);
		rechargeOrderMapper.insert(order);

		PayValue value = new PayValue();
		if (paytypeShort == 1) {// 支付宝
			Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(count, "用户充值", "用户充值", thirdNumID);
			String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
			String sign = OrderInfoUtil2_0.getSign(params, AlipayConfig.private_key);
			final String orderInfo = orderParam + "&" + sign;
			value.setBody(orderInfo);
		} else {
			getAccessToken();
		}
		HashMap<String, Object> resultMap = new HashMap<>();
		resultMap.put("pay_type", paytypeShort);
		resultMap.put("thirdNumID", thirdNumID);
		resultMap.put("pay", value);
		result.setResult(resultMap);
		result.setCode("1");
		result.setMessage("请求成功");
		return result;
	}

	/**
	 * 
	 * @param type
	 *            回调类型 1.支付宝 2.微信支付
	 * @param out_trade_no
	 *            订单标识号
	 * @param transaction_id
	 *            第三方支付的订单ID
	 * @param openid
	 *            第三方支付付款账户信息
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public void paySuccess(int type, String out_trade_no, String transaction_id, String openid) {
		if (!CommonUtils.isEmptyString(out_trade_no) && out_trade_no.length() > 1) {
			String table_info = out_trade_no.substring(0, 1);
			if ("R".equals(table_info)) {// 充值
				RechargeOrder rechargeOrder = rechargeOrderMapper.selectByOrderNum(out_trade_no);
				if (rechargeOrder != null && rechargeOrder.getState() == 0) {

					User user = userMapper.selectByPrimaryKey(rechargeOrder.getUserid());
					UserExtra userExtra = userExtraMapper.selectByUserId(user.getUserid());
					if (user != null) {

						BigDecimal addMoney = rechargeOrder.getAmount();
						if (userExtra.getBalance() == null) {
							userExtra.setBalance(addMoney);

						} else {
							userExtra.setBalance(userExtra.getBalance().add(addMoney));
						}
						userExtraMapper.updateByPrimaryKey(userExtra);

						BalanceRecord bRecord = new BalanceRecord();
						bRecord.setAddtime(new Date());
						bRecord.setCount(addMoney);
						bRecord.setType((short) 3);
						bRecord.setCount(addMoney);
						bRecord.setUserid(user.getUserid());
						balanceRecordMapper.insert(bRecord);

					}

					rechargeOrder.setState((byte) 1);
					rechargeOrder.setPayTime(new Date());
					rechargeOrder.setPayAccountInfo(openid);
					rechargeOrderMapper.updateByPrimaryKey(rechargeOrder);
				}
			} else if ("P".equals(table_info)) {// 订单相关
				List<OrderPay> payList = orderPayMapper.selectByOrderNum(out_trade_no);
				if (payList != null && payList.size() > 0) {
					for (OrderPay pay : payList) {
						if (null != pay) {
							if (pay.getState() == 0) {
								pay.setState((short) 1);
								pay.setPayTime(new Date());
								pay.setPayAccountInfo(openid);
								pay.setThirdOrderid(transaction_id);
								orderPayMapper.updateByPrimaryKey(pay);
								Order order = orderMapper.selectByPrimaryKey(pay.getOrderid());
								final Order orderQuote = order;
								if (order != null) {
									if (pay.getType() == 1 || pay.getType() == 5) {// 支付定金
										order.setState((short) 1);
										order.setPayTime2(new Date());
										orderMapper.updateByPrimaryKey(order);
										fixedThreadPool.execute(new Runnable() {
											@Override
											public void run() {

												List<OrderPool> orderPooList = orderPoolMapper
														.selectListByOrderUser(orderQuote.getOrderid(), 0);
												if (orderPooList != null && orderPooList.size() > 0) {
													for (OrderPool orderPool : orderPooList) {
														orderPool.setState((byte) 1);
														orderPoolMapper.updateByPrimaryKey(orderPool);
														sendOrderMessageToAunt(orderQuote, orderPool);
													}
												}
											}
										});
									} else if (pay.getType() == 2) {// 最后结算(或者支付月工资)
										int cid = order.getCategoryid();
										if (cid == 1 || cid == 2 || cid == 5 || cid == 6 || cid == 8) {// 普通类
											payHourlyWorker(order);
											
											fixedThreadPool.execute(new Runnable() {
												@Override
												public void run() {
							                        sendPayMessageToAunt(orderQuote);
												}
											});
										} else if (cid == 3 || cid == 4 || cid == 7 || cid == 9 || cid == 10
												|| cid == 11 || cid == 12 || cid == 13) {// 上门费用类
											multiplePayments(order);
											fixedThreadPool.execute(new Runnable() {
												@Override
												public void run() {
							                        sendPayMessageToAunt(orderQuote);
												}
											});
										} else if (cid == 14 || cid == 15 || cid == 16 || cid == 17 || cid == 18) {// 长期
											if(order.getState() == 5 || order.getState() == 6){
												fixedThreadPool.execute(new Runnable() {
													@Override
													public void run() {
								                        sendPayMessageToAunt(orderQuote);
													}
												});
											}
											payPermanentworker(order, pay);
										}

									} else if (pay.getType() == 3) {// 3.增加小费
										setTip(order, pay.getPirce());
									} else if (pay.getType() == 4) { // 维修费用第一次
										firstPayMaintenanceWorker(order);
									} else if (pay.getType() == 5) { // 维修费第二次
										// sencondPayMaintenanceWorker(order);
									} else if (pay.getType() == 6) { // 退单

									}
								}
							}
						}
					}
				}
			} else if ("F".equals(table_info)) {
				OrderRefund refund = orderRefundMapper.selectByOrderNum(out_trade_no);
				if (refund != null) {
					refund.setState((byte) 1);
					refund.setPayTime(new Date());
					refund.setPayAccountInfo(openid);
					refund.setThirdOrderid(transaction_id);
					orderRefundMapper.updateByPrimaryKey(refund);
					
				}
				
				
			}
		}
	}

	/**
	 * 获取我的优惠券列表
	 * 
	 * @param type
	 *            回调类型 1.支付宝 2.微信支付
	 * @param out_trade_no
	 *            订单标识号
	 * @param transaction_id
	 *            第三方支付的订单ID
	 * @param openid
	 *            第三方支付付款账户信息
	 */
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult getMyCoupon(User user) {
		ApiResult result = new ApiResult();
		Calendar calendarEnd = Calendar.getInstance();
		Date nowTime = new Date();
		calendarEnd.setTime(nowTime);
		calendarEnd.add(Calendar.DAY_OF_MONTH, -7);
		Calendar calendarStart = calendarEnd;
		String dateStr = CommonUtils.getTimeFormat(calendarStart.getTime(), "yyyy-MM-dd");
		String dateEnd = CommonUtils.getTimeFormat(nowTime, "yyyy-MM-dd");
		List<CouponUser> couponList = couponUserMapper.selectCouponById(user.getUserid(), dateStr, dateEnd);
		List<CouponValue> couponUserList = new ArrayList<CouponValue>();
		int count = 0;
		if (couponList != null && couponList.size() > 0) {
			for (CouponUser coupon : couponList) {
				CouponValue value = new CouponValue();
				value.setCouponid(coupon.getCouponid());
				if (coupon.getCompanyid() == 0) {
					value.setTitle("表叔云官方平台");
					value.setRangetype(0);

				} else {
					Company company = companyMapper.selectByPrimaryKey(coupon.getCompanyid());
					value.setTitle(company.getName());
					value.setRangetype(1);
					if (company.getLogoPicid() != null && company.getLogoPicid() > 0) {
						value.setLogourl(getFilePathById(company.getLogoPicid()));
					}

				}
				value.setDiscount(coupon.getDiscount().toPlainString());
				value.setCondition(coupon.getCouponCondition().toPlainString());
				CategorySecond secont = categorySecondMapper.selectByPrimaryKey(coupon.getCategoryid());
				if (secont != null) {
					value.setCategory(secont.getName());
				}
				value.setTimestr("有效期至" + CommonUtils.getTimeFormat(coupon.getUseEtime(), "yyyy.MM.dd"));
				calendarStart.setTime(new Date());
				Calendar calEnd = Calendar.getInstance();
				calEnd.setTime(coupon.getUseEtime());
				if (calendarStart.after(calEnd)) {
					value.setState(1);
				} else {
					value.setState(0);
				}
				couponUserList.add(value);
				count++;
			}
		}
		HashMap<String, Object> resultMap = new HashMap<>();
		resultMap.put("couponList", couponUserList);
		resultMap.put("count", count);
		result.setResult(resultMap);
		result.setCode("1");
		result.setMessage("请求成功");
		return result;

	}

	/**
	 * 获取是否有未读消息
	 * 
	 * @param type
	 *            回调类型 1.支付宝 2.微信支付
	 * 
	 */
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult refreshMessage(User user) {
		ApiResult result = new ApiResult();
		Long count = messageMapper.selectUnRead(user.getUserid());
		long countL = (long) count;
		HashMap<String, Object> resultMap = new HashMap<>();
		resultMap.put("messagecount", countL);
		result.setResult(resultMap);
		result.setCode("1");
		result.setMessage("请求成功");
		return result;

	}

	/**
	 * 获取消息列表
	 * 
	 * @param type
	 *            回调类型 1.支付宝 2.微信支付
	 * 
	 */
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult messageList(User user) {
		ApiResult result = new ApiResult();
		List<Message> messages = messageMapper.selectByPage(user.getUserid());
		List<MessageValue> messageList = new ArrayList<>();
		if (messages != null && messages.size() > 0) {
			for (Message message : messages) {
				MessageValue value = new MessageValue();
				value.setId(message.getId());
				value.setTitle(message.getTitle());
				byte typeB = message.getType();
				value.setType(typeB);
				value.setRead_state(message.getReadState());
				String timeStr = CommonUtils.getTimeDiff(message.getLastMsgTime());
				value.setTimestr(timeStr);
				MessageDetail detail = messageDetailMapper.selectLastByMid(message.getId());
				if (detail != null) {
					value.setLastmessage(detail.getDetail());
				} else {
					value.setLastmessage("");
				}
				messageList.add(value);
			}

		}

		HashMap<String, Object> resultMap = new HashMap<>();
		resultMap.put("messagelist", messageList);
		result.setResult(resultMap);
		result.setCode("1");
		result.setMessage("请求成功");
		return result;

	}

	/**
	 * 获取消息列表
	 * 
	 * @param type
	 *            回调类型 1.支付宝 2.微信支付
	 * 
	 */
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult setReaded(User user, String mids) {
		ApiResult result = new ApiResult();
		String[] ids = mids.split(",");
		if (null != ids) {
			for (int i = 0; i < ids.length; i++) {
				if (!CommonUtils.isEmptyString(ids[i])) {
					Message message = messageMapper.selectByPrimaryKey(Integer.parseInt(ids[i]));
					message.setReadState((short) 1);
					message.setUnreadCount(0);
					messageMapper.updateByPrimaryKey(message);
				}
			}

		}
		result.setCode("1");
		result.setMessage("请求成功");
		return result;

	}

	/**
	 * 获取消息列表
	 * 
	 * @param type
	 *            回调类型 1.支付宝 2.微信支付
	 * 
	 */
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult delMessage(User user, String mids) {
		ApiResult result = new ApiResult();
		String[] ids = mids.split(",");
		if (null != ids) {
			for (int i = 0; i < ids.length; i++) {
				if (!CommonUtils.isEmptyString(ids[i])) {
					Message message = messageMapper.selectByPrimaryKey(Integer.parseInt(ids[i]));
					message.setReadState((short) 1);
					message.setUnreadCount(0);
					messageMapper.updateByPrimaryKey(message);
					messageDetailMapper.deleteByMessageid(message.getId());
				}
			}

		}
		result.setCode("1");
		result.setMessage("请求成功");
		return result;

	}

	/**
	 * 获取消息列表
	 * 
	 * @param type
	 *            回调类型 1.支付宝 2.微信支付
	 * 
	 */
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult messageDetail(User user, String messageid, int page) {
		ApiResult result = new ApiResult();

		Message message = messageMapper.selectByPrimaryKey(CommonUtils.parseInt(messageid, 0));
		if (message != null) {
			message.setReadState((short) 1);
			message.setUnreadCount(0);
			messageMapper.updateByPrimaryKey(message);

			List<MessageDetail> detailList = messageDetailMapper.selectByPage(message.getId(), (page - 1) * 10, 10);
			List<MessageDetailValue> valueList = new ArrayList<>();
			HashMap<String, Object> resultMap = new HashMap<>();
			if (detailList != null && detailList.size() > 0) {
				for (MessageDetail messageDetail : detailList) {
					MessageDetailValue value = new MessageDetailValue();
					value.setId(messageDetail.getId());
					value.setTitle(messageDetail.getTitle());
					value.setDetail(messageDetail.getDetail());
					value.setLink_content(messageDetail.getLinkContent());
					value.setLink_title(messageDetail.getLinkTitle());
					if (messageDetail.getLinkType() != null) {
						value.setLink_type(messageDetail.getLinkType());
					} else {
						value.setLink_type((short) -1);
					}
					String timeStr = CommonUtils.getTimeDiff(messageDetail.getAddtime());
					value.setTimestr(timeStr);
					valueList.add(value);
				}

				Long count = messageDetailMapper.selectPageCount(message.getId());
				if (count == null) {
					resultMap.put("havemore", 0);
				} else {
					if (count > page * 10) {
						resultMap.put("havemore", 1);
					} else {
						resultMap.put("havemore", 0);
					}
				}

				resultMap.put("messagelist", valueList);
			} else {
				resultMap.put("havemore", 0);
			}

			result.setResult(resultMap);
			result.setCode("1");
			result.setMessage("请求成功");
		} else {
			result.setCode("2");
			result.setMessage("请求失败,消息不存在");
		}

		return result;

	}

	/**
	 * 获取消息列表
	 * 
	 * @param type
	 *            回调类型 1.支付宝 2.微信支付
	 * 
	 */
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult receiveCoupon(User user, String couponid) {
		ApiResult result = new ApiResult();
		Coupon coupon = couponMapper.selectByPrimaryKeyOrNum(couponid);
		if (coupon == null) {
			result.setCode("2");
			result.setMessage("优惠 已领完或过期");
			return result;
		}
		Date now = new Date();
		Calendar nowCal = Calendar.getInstance();
		nowCal.setTime(now);
		Date startTime = coupon.getStime();
		Date endTime = coupon.getEtime();
		Calendar startCal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();
		startCal.setTime(startTime);
		endCal.setTime(endTime);
		if (startCal.after(nowCal)) {
			result.setCode("2");
			result.setMessage("优惠券还未开始领取");
			return result;
		}
		if (nowCal.after(endCal)) {
			result.setCode("2");
			result.setMessage("优惠券领取结束");
			return result;
		}
		if (coupon.getMaxreceive() == 0) {

		} else {
			Long count = couponUserMapper.selectByUserid(Integer.parseInt(couponid), user.getUserid());
			long countL = count;
			if (countL >= coupon.getMaxreceive()) {
				result.setCode("2");
				result.setMessage("达到优惠券领取上限");
				return result;
			}

		}
		int receiveCount = coupon.getGetcount();
		int totalCount = coupon.getTotalcount();
		if (receiveCount + 1 > totalCount) {
			result.setCode("2");
			result.setMessage("优惠券已领完");
			return result;
		} else {
			receiveCount++;
			coupon.setGetcount(receiveCount);
			couponMapper.updateByPrimaryKey(coupon);
		}
		CouponUser couponUser = new CouponUser();
		couponUser.setCouponid(coupon.getCouponid());
		couponUser.setUserid(user.getUserid());
		couponUser.setCouponCondition(coupon.getCouponCondition());
		couponUser.setDiscount(coupon.getDiscount());
		couponUser.setRangetype(coupon.getRangetype());
		couponUser.setCompanyid(coupon.getCompanyid());
		couponUser.setCategoryid(coupon.getCategoryid());
		couponUser.setUseStime(coupon.getUseStime());
		couponUser.setUseEtime(coupon.getUseEtime());
		couponUser.setState((byte) 0);
		couponUser.setAddtime(new Date());
		couponUserMapper.insert(couponUser);

		result.setCode("1");
		result.setMessage("请求成功");
		return result;

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult setPassword(User user, String password) {
		ApiResult result = new ApiResult();
		result.setCode("1");
		result.setMessage("请求成功");
		HashMap<String, Object> map = new HashMap<>();
		user.setPassword(password);
		userMapper.updateByPrimaryKey(user);
		result.setResult(map);
		return result;
	}

	@Override
	public ApiResult hasPassword(User user) {
		ApiResult result = new ApiResult();
		result.setCode("1");
		result.setMessage("请求成功");
		HashMap<String, Object> map = new HashMap<>();
		if (!CommonUtils.isEmptyString(user.getPassword())) {
			map.put("haspassword", 1);
		} else {
			map.put("haspassword", 0);
		}
		result.setResult(map);
		return result;
	}

	@Override
	public User userByPhone(String phone) {
		User user = userMapper.selectUserByPhone(phone);
		return user;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult invite(User user, String intive_code) {
		ApiResult result = new ApiResult();
		if(user.getInvitationed_state() == 1){
			result.setCode("2");
			result.setMessage("用户已被邀请");
			return result;
		}
		User inViteUser = userMapper.selectUserByCode(intive_code);
		if(inViteUser == null){
			result.setCode("3");
			result.setMessage("邀请码错误");
			return result;
		}
		Integer count = inViteUser.getInvitation_count();
		if(count == null){
			count = 0;
		}
		count ++;
		inViteUser.setInvitation_count(count);
		userMapper.updateByPrimaryKey(inViteUser);
		user.setInvitationed_state((short)1);
		userMapper.updateByPrimaryKey(user);
		Invitation invitation = new Invitation();
		invitation.setUserid(inViteUser.getUserid());
		invitation.setInviteduserid(user.getUserid());
		invitation.setAddtime(new Date());
		invitationMapper.insert(invitation);
		result.setCode("1");
		result.setMessage("请求成功");
		
		PointProgress pointProgressToday = pointProgressMapper.selectByUseridToday(inViteUser.getUserid());
		if(null != pointProgressToday){
		    if(pointProgressToday.getShareApp() == 5){
		    	
		    }else {
		    	Byte shareApp = pointProgressToday.getShareApp();
		    	shareApp++;
		    	pointProgressToday.setShareApp(shareApp);
		    	
		    	PointRecord pointRecord = new PointRecord();
		    	Config config = configMapper.selectConfigByKey(Constant.SHARE_APP_POINT_USER);
		    	if(config != null){
		    		pointRecord.setAddtime(new Date());
		    		pointRecord.setUserid(inViteUser.getUserid());
		    		pointRecord.setType((short)3);
		    		pointRecord.setCount(CommonUtils.parseInt(config.getConfigvalue(), 0));
		    		pointRecordMapper.insert(pointRecord);
		    		
		    		UserExtra extra = userExtraMapper.selectByUserId(inViteUser.getUserid());
		    		int point = extra.getPoint();
		    		point += CommonUtils.parseInt(config.getConfigvalue(), 0);
			    	extra.setPoint(point);
			    	
			    	int pointTotal = extra.getPoint_total();
			    	pointTotal += CommonUtils.parseInt(config.getConfigvalue(), 0);
			    	extra.setPoint_total(pointTotal);
			    	userExtraMapper.updateByPrimaryKey(extra);
		    	}
		    	
		    }
			
		}else {
			PointProgress pointProgress = pointProgressMapper.selectByUserid(inViteUser.getUserid());
			if(pointProgress == null){
				
			}else {
				pointProgress.setUpdatetime(new Date());
				pointProgress.setShareApp((byte)1);
				pointProgressMapper.updateByPrimaryKey(pointProgress);
				PointRecord pointRecord = new PointRecord();
		    	Config config = configMapper.selectConfigByKey(Constant.SHARE_APP_POINT_USER);
		    	if(config != null){
		    		pointRecord.setAddtime(new Date());
		    		pointRecord.setUserid(inViteUser.getUserid());
		    		pointRecord.setType((short)3);
		    		pointRecord.setCount(CommonUtils.parseInt(config.getConfigvalue(), 0));
		    		pointRecordMapper.insert(pointRecord);
		    		
		    		UserExtra extra = userExtraMapper.selectByUserId(inViteUser.getUserid());
		    		int point = extra.getPoint();
		    		point += CommonUtils.parseInt(config.getConfigvalue(), 0);
			    	extra.setPoint(point);
			    	
			    	int pointTotal = extra.getPoint_total();
			    	pointTotal += CommonUtils.parseInt(config.getConfigvalue(), 0);
			    	extra.setPoint_total(pointTotal);
			    	userExtraMapper.updateByPrimaryKey(extra);
		    	}
				
			}
		}
		
		return result;
	}

	@Override
	public ApiResult receiveRedPacket(User user, String packetid) {
		ApiResult result = new ApiResult();
		RedPacketRecord redPackRecord = redPacketRecordMapper.selectByPrimaryKey(CommonUtils.parseInt(packetid, 0));
		if(redPackRecord != null){
			if(redPackRecord.getState() == 1){
				result.setCode("3");
				result.setMessage("红包已领取");
				return result;
			}
			redPackRecord.setUserid(user.getUserid());
			redPackRecord.setState((byte)1);
			redPacketRecordMapper.updateByPrimaryKey(redPackRecord);
			
			UserExtra extra = userExtraMapper.selectByUserId(user.getUserid());
			BigDecimal balance = extra.getBalance();
			balance = balance.add(redPackRecord.getCount());
			extra.setBalance(balance);
			
			balance = extra.getUseTotal();
			balance = balance.add(redPackRecord.getCount());
			extra.setUseTotal(balance);
			userExtraMapper.updateByPrimaryKey(extra);
			
			BalanceRecord balaceRecord = new BalanceRecord();
			balaceRecord.setCount(redPackRecord.getCount());
			balaceRecord.setAddtime(new Date());
			balaceRecord.setUserid(user.getUserid());
			balaceRecord.setType((short)5);
			balanceRecordMapper.insert(balaceRecord);
			
			
		}else {
			result.setCode("2");
			result.setMessage("红包不存在");
			return result;
			
			
			
		}
		result.setCode("1");
		result.setMessage("请求成功");
		return result;
	}

	@Override
	public ApiResult setCode() {
		ApiResult result = new ApiResult();
		List<User> userList = userMapper.selectAllUser();
		if(userList != null && userList.size()>0){
			for(User user:userList){
				user.setInvitation_count(0);
				user.setInvitation_code(CommonUtils.getUserInvite(user.getUserid()));
				user.setInvitationed_state((short)0);
				userMapper.updateByPrimaryKey(user);
			}
		}
		result.setCode("1");
		result.setMessage("请求成功");
		return result;
	}
	
	
	
}
