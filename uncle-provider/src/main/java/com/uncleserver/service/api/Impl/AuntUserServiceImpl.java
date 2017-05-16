package com.uncleserver.service.api.Impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uncleserver.common.CommonUtils;
import com.uncleserver.common.Constant;
import com.uncleserver.common.IdcardValidator;
import com.uncleserver.common.sms.RLYSmsUtils;
import com.uncleserver.model.Aunt;
import com.uncleserver.model.AuntBalanceRecord;
import com.uncleserver.model.AuntCard;
import com.uncleserver.model.AuntCash;
import com.uncleserver.model.AuntDisplay;
import com.uncleserver.model.AuntExtra;
import com.uncleserver.model.AuntInfoSet;
import com.uncleserver.model.AuntLevelSet;
import com.uncleserver.model.AuntMessage;
import com.uncleserver.model.AuntMessageDetail;
import com.uncleserver.model.AuntPointProgress;
import com.uncleserver.model.AuntPointRecord;
import com.uncleserver.model.AuntSignSet;
import com.uncleserver.model.AuntSkill;
import com.uncleserver.model.BalanceRecord;
import com.uncleserver.model.CategorySecond;
import com.uncleserver.model.CategoryThird;
import com.uncleserver.model.Company;
import com.uncleserver.model.CompanyExtra;
import com.uncleserver.model.CompanyWithBLOBs;
import com.uncleserver.model.Config;
import com.uncleserver.model.FeedBackAunt;
import com.uncleserver.model.Invitation;
import com.uncleserver.model.MessageDetail;
import com.uncleserver.model.Order;
import com.uncleserver.model.OrderComment;
import com.uncleserver.model.PointProgress;
import com.uncleserver.model.PointRecord;
import com.uncleserver.model.RedPacketRecord;
import com.uncleserver.model.SignRecordAunt;
import com.uncleserver.model.Tutorials;
import com.uncleserver.model.User;
import com.uncleserver.model.UserExtra;
import com.uncleserver.model.VCodeAunt;
import com.uncleserver.modelVo.AliInfo;
import com.uncleserver.modelVo.ApiResult;
import com.uncleserver.modelVo.AuntBalanceModel;
import com.uncleserver.modelVo.AuntDisplayModel;
import com.uncleserver.modelVo.AuntResultModel;
import com.uncleserver.modelVo.AuntSetValue;
import com.uncleserver.modelVo.AuntSkillModel;
import com.uncleserver.modelVo.BalanceValue;
import com.uncleserver.modelVo.CommnetValue;
import com.uncleserver.modelVo.CompanyResultModel;
import com.uncleserver.modelVo.DayValue;
import com.uncleserver.modelVo.DisplayModel;
import com.uncleserver.modelVo.MessageDetailValue;
import com.uncleserver.modelVo.MessageValue;
import com.uncleserver.modelVo.TutorialValue;
import com.uncleserver.service.api.AuntUserService;

@Service("auntUserService")
public class AuntUserServiceImpl extends BaseServiceImpl implements AuntUserService {

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult sendVcode(String phone, int typeInt, String user_type) {
		ApiResult result = new ApiResult();
		boolean canSend = checkCanSendVcode(phone);
		if (!canSend) {
			result.setCode("2");
			result.setMessage("发送失败,该号码今天请求验证码次数超过上限");
			return result;
		}

		int userType = Integer.parseInt(user_type);

		Long count = null;
		if (userType == 0) {// 阿姨
			count = auntMapper.selectUserCountByPhone(phone);
		} else {// 公司
			count = companyMapper.selectUserCountByPhone(phone);
		}

		if (typeInt == 0 || typeInt == 3) {
			if (count != null && count > 0) {
				result.setCode("3");
				result.setMessage("发送失败,该手机号码已经注册");
				return result;
			}
		} else if (typeInt == 1) {
			if (count == null || count == 0) {
				result.setCode("3");
				result.setMessage("发送失败,该手机号码未注册");
				return result;
			}
		}

		String vcode = CommonUtils.getRandomVcode();
		// 调用第三方发送验证码
		VCodeAunt vCodeAunt = new VCodeAunt();
		vCodeAunt.setAddtime(new Date());
		vCodeAunt.setPhone(phone);
		vCodeAunt.setType((byte) typeInt);
		vCodeAunt.setVcode(vcode);

		vCodeAunt.setUserType((byte) userType);
		vCodeAuntMapper.insert(vCodeAunt);
		result.setCode("1");
		result.setMessage("验证码发送成功");
		HashMap<String, Object> map = new HashMap<>();
		map.put("vcode", vcode);
		RLYSmsUtils.SendSms(phone, Constant.SMS_TEMPLATE, new String[] { vcode, "五分钟" });
		result.setResult(map);
		return result;
	}

	@Override
	public ApiResult checkVcode(String phone, String vcode, int typeInt, int user_type) {
		ApiResult result = new ApiResult();

		VCodeAunt vcodeModel = vCodeAuntMapper.selectLastVcodeByPhoneAndType(phone, typeInt, user_type);
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
	public ApiResult userRegister(String phone, String password, String vcode, String sessionid) {
		ApiResult result = new ApiResult();
		Long count = auntMapper.selectUserCountByPhone(phone);
		if (count != null && count > 0) {
			result.setCode("2");
			result.setMessage("注册失败,该手机号码已经注册");
			return result;
		}
		VCodeAunt vcodeModel = vCodeAuntMapper.selectLastVcodeByPhoneAndType(phone, 0, 0);
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
		Aunt aunt = new Aunt();
		aunt.setAddtime(new Date());
		aunt.setPhone(phone);
		aunt.setPassword(password);
		aunt.setLevel((byte) 0);
		aunt.setWorkYear((byte) 0);
		aunt.setTrainState((byte) 0);
		aunt.setLanguage("");
		aunt.setCharacters("");
		aunt.setCulture("");
		aunt.setReligion("");
		aunt.setPolitical("");
		aunt.setMarriage("");
		aunt.setWorkHis("");
		aunt.setSelfComment("");
		aunt.setHobby("");
		aunt.setState((byte) 0);
		aunt.setConstellation("");
		aunt.setZodiac("");
		aunt.setCompanyid(0);
		aunt.setInfoState((short) 0);
		aunt.setKingState((short) 0);
		auntMapper.insert(aunt);
		
		aunt.setInvitation_count(0);
		aunt.setInvitation_code(CommonUtils.getAuntInvite(aunt.getAuntid()));
		aunt.setInvitationed_state((short)0);
		auntMapper.updateByPrimaryKey(aunt);
		
		AuntExtra auntExtra = new AuntExtra();
		auntExtra.setAuntid(aunt.getAuntid());
		// 查询是否需要赠送积分
		Config config = configMapper.selectConfigByKey(Constant.REGISTER_POINT_AUNT);
		int sendPoint = 0;
		if (config != null && !CommonUtils.isEmptyString(config.getConfigvalue())) {
			sendPoint = CommonUtils.parseInt(config.getConfigvalue(), 0);
		}
		auntExtra.setPoint(sendPoint);
		auntExtra.setPointTotal(sendPoint);
		auntExtra.setBalance(new BigDecimal("0.00"));
		auntExtra.setUseTotal(new BigDecimal("0.00"));
		String accesstoken = CommonUtils.MD5(sessionid + aunt.getAuntid());
		auntExtra.setAccesstoken(CommonUtils.MD5(accesstoken));
		auntExtra.setTokenTime(new Date());
		auntExtra.setLongitude(0d);
		auntExtra.setLatitude(0d);
		auntExtra.setState((short) 0);
		auntExtra.setFontSet((short) 2);
		auntExtra.setVoiceSet((short) 0);
		auntExtra.setScore(0f);
		auntExtramapper.insert(auntExtra);
		if (sendPoint > 0) {
			AuntPointRecord record = new AuntPointRecord();
			record.setAddtime(new Date());
			record.setCount(sendPoint);
			record.setType((byte) 1);
			record.setAuntid(aunt.getAuntid());
			auntPointRecordMapper.insert(record);
		}

		result.setCode("1");
		result.setMessage("用户注册成功");

		HashMap<String, Object> map = new HashMap<>();
		map.put("userid", aunt.getAuntid());
		map.put("accesstoken", auntExtra.getAccesstoken());
		result.setResult(map);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult login(String phone, String password, String sessionid) {
		ApiResult result = new ApiResult();

		Aunt aunt = auntMapper.selectUserByPhone(phone);
		if (aunt == null) {
			result.setCode("2");
			result.setMessage("登录失败,用户不存在");
			return result;
		}
		if (!password.equals(aunt.getPassword())) {
			result.setCode("3");
			result.setMessage("登录失败,密码错误");
			return result;
		}
		
		
		// 更新用户信息
		AuntExtra auntExtra = auntExtramapper.selectByAuntId(aunt.getAuntid());
		if (auntExtra == null) {
			auntExtra = new AuntExtra();
			auntExtra.setAuntid(aunt.getAuntid());
			// 查询是否需要赠送积分
			Config config = configMapper.selectConfigByKey(Constant.REGISTER_POINT_AUNT);
			int sendPoint = 0;
			if (config != null && !CommonUtils.isEmptyString(config.getConfigvalue())) {
				sendPoint = CommonUtils.parseInt(config.getConfigvalue(), 0);
			}
			auntExtra.setPoint(sendPoint);
			auntExtra.setPointTotal(sendPoint);
			auntExtra.setBalance(new BigDecimal("0.00"));
			auntExtra.setUseTotal(new BigDecimal("0.00"));
			String accesstoken = CommonUtils.MD5(sessionid + aunt.getAuntid());
			auntExtra.setAccesstoken(CommonUtils.MD5(accesstoken));
			auntExtra.setTokenTime(new Date());
			auntExtra.setLongitude(0d);
			auntExtra.setLatitude(0d);
			auntExtra.setState((short) 0);
			auntExtra.setFontSet((short) 2);
			auntExtra.setVoiceSet((short) 0);
			auntExtra.setScore(0f);
			auntExtramapper.insert(auntExtra);
			if (sendPoint > 0) {
				AuntPointRecord record = new AuntPointRecord();
				record.setAddtime(new Date());
				record.setCount(sendPoint);
				record.setType((byte) 1);
				record.setAuntid(aunt.getAuntid());
				auntPointRecordMapper.insert(record);
			}
		} else {
			auntExtra.setLastLogin(new Date());
			String accesstoken = CommonUtils.MD5(sessionid + aunt.getAuntid());
			auntExtra.setAccesstoken(CommonUtils.MD5(accesstoken));
			auntExtra.setTokenTime(new Date());
			auntExtramapper.updateByPrimaryKey(auntExtra);
		}

		AuntResultModel model = createAuntModel(aunt, auntExtra);
		result.setCode("1");
		result.setMessage("登录成功");
		HashMap<String, Object> map = new HashMap<>();
		map.put("userinfo", model);
		result.setResult(map);
		return result;
	}

	// userid work_year weight height train_state companyid level point balance
	// sign_week today_sign score live_state
	public AuntResultModel createAuntModel(Aunt aunt, AuntExtra extra) {
		AuntResultModel model = new AuntResultModel();
		model.setAccesstoken(extra.getAccesstoken());
		if (aunt.getAvatar() != null && aunt.getAvatar() > 0) {
			String filePath = getFilePathById(aunt.getAvatar());
			model.setAvatarurl(filePath);
		} else {
			model.setAvatarurl(aunt.getThirdAvatar());
		}

		if (extra.getBalance() != null) {
			model.setBalance(extra.getBalance().floatValue());
		} else {
			model.setBalance(0f);
		}

		if (aunt.getBirthday() != null) {
			model.setBirthday(CommonUtils.getTimeFormat(aunt.getBirthday(), "yyyy-MM-dd"));
		} else {
			model.setBirthday("未设置");
		}
		model.setLevel(aunt.getLevel());
		model.setCharacter(aunt.getCharacters());

		// 服务分类
		List<AuntSkill> slist = auntSkillMapper.selectByAuntIdTo(aunt.getAuntid());
		List<AuntSkillModel> skillList = new ArrayList<>();
		if (skillList != null) {
			for (AuntSkill skill : slist) {
				AuntSkillModel modelskill = new AuntSkillModel();
				modelskill.setCategoryid(skill.getCategoryid());
				modelskill.setThirdid(skill.getThirdid() != null ? skill.getThirdid() : 0);

				CategorySecond cs = categorySecondMapper.selectByPrimaryKey(modelskill.getCategoryid());
				if (cs != null) {
					modelskill.setCname(cs.getName());
				}
				if (modelskill.getThirdid() > 0) {
					CategoryThird ct = categoryThirdMapper.selectByPrimaryKey(modelskill.getThirdid());
					if (ct != null) {
						modelskill.setTname(ct.getName());
					}
				}
				skillList.add(modelskill);
			}
		}
		model.setSkillList(skillList);
		// 技能展示
		List<AuntDisplay> dlist = auntDisplayMapper.selectByAuntId(aunt.getAuntid());
		List<AuntDisplayModel> displayList = new ArrayList<>();

		if (dlist != null) {
			for (AuntDisplay auntDisplay : dlist) {
				AuntDisplayModel modeld = new AuntDisplayModel();
				modeld.setCertificate_name(auntDisplay.getCertificateName());
				modeld.setPicurl(getFilePathById(auntDisplay.getPicid()));
				modeld.setSkill_name(auntDisplay.getSkillName());
				displayList.add(modeld);
			}
		}
		model.setDisplayList(displayList);

		AuntLevelSet set = auntLevelSetMapper.setlectSetByLevel(aunt.getLevel());
		if (set != null) {
			model.setLevelname(set.getTitle());
		}
		model.setUserid(aunt.getAuntid());
		model.setPhone(aunt.getPhone());
		model.setPoint(extra.getPoint());
		model.setReal_name(aunt.getRealName());
		if (aunt.getSex() != null) {
			model.setSex(aunt.getSex());
		} else {
			model.setSex("未设置");
		}

		Calendar now = Calendar.getInstance();
		if (extra.getLastSign() != null) {
			String dateStr1 = CommonUtils.getTimeFormat(extra.getLastSign(), "yyyy-MM-dd");
			String dateStr2 = CommonUtils.getTimeFormat(now.getTime(), "yyyy-MM-dd");
			if (dateStr1.equals(dateStr2)) {
				model.setToday_sign(1);
			} else {
				model.setToday_sign(0);
			}
		} else {
			model.setToday_sign(0);
		}

		// 本周连续签到天数
		now.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		String dateStrMonday = CommonUtils.getTimeFormat(now.getTime(), "yyyy-MM-dd");
		now.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		now.add(Calendar.DAY_OF_YEAR, 1);
		String dateStrSunday = CommonUtils.getTimeFormat(now.getTime(), "yyyy-MM-dd");

		Long count = signRecordAuntMapper.selectWeekSignCount(dateStrMonday, dateStrSunday, aunt.getAuntid());
		if (count == null) {
			model.setSign_week(0);
		} else {
			model.setSign_week(count.intValue());
		}
		model.setSex(aunt.getSex());
		model.setSignature(aunt.getSignature());
		model.setIdcard_num(aunt.getIdcardNum());
		model.setOrigin_place(aunt.getOriginPlace());
		if (aunt.getWorkYear() != null) {
			model.setWork_year(aunt.getWorkYear());
		}
		model.setHome_address(aunt.getHomeAddress());
		model.setNow_address(aunt.getNowAddress());
		model.setNation(aunt.getNation());

		if (aunt.getWeight() != null) {
			model.setWeight(aunt.getWeight());
		}else{
			model.setWeight(0f);
		}

		if (aunt.getHeight() != null) {
			model.setHeight(aunt.getHeight());
		} else {
			model.setHeight(0f);
		}

		model.setBlood_type(aunt.getBloodType());
		if (aunt.getTrainState() != null) {
			model.setTrain_state(aunt.getTrainState());
		}

		model.setLanguage(aunt.getLanguage());
		model.setCulture(aunt.getCulture());
		model.setReligion(aunt.getReligion());
		model.setPolitical(aunt.getPolitical());
		model.setMarriage(aunt.getMarriage());
		model.setWork_his(aunt.getWorkHis());
		model.setSelf_comment(aunt.getSelfComment());
		model.setHobby(aunt.getHobby());
		model.setState("" + aunt.getState());
		model.setConstellation(aunt.getConstellation());
		model.setZodiac(aunt.getZodiac());
		if (aunt.getCompanyid() != null) {
			model.setCompanyid(aunt.getCompanyid());
		}
		if (extra.getScore() != null) {
			model.setScore(extra.getScore());
		}
		if (extra.getState() != null) {
			model.setLive_state(extra.getState());
		}
		if (aunt.getLevel() != null) {
			model.setLevel(aunt.getLevel());
		}

		if (aunt.getSignature() != null) {
			model.setSignature(aunt.getSignature());
		} else {
			model.setSignature("未设置");
		}

		// TODO: 设置第三方ID 设置信息完成度 设置提现账号信息
		model.setWx_id(aunt.getWxId());
		model.setSina_id(aunt.getSinaId());
		model.setQq_id(aunt.getQqId());
		if (extra.getPoint() != null) {
			model.setPoint(extra.getPoint());
		}
		if (extra.getBalance() != null) {
			model.setBalance(extra.getBalance().setScale(2, BigDecimal.ROUND_UP).floatValue());
		}

		if (extra.getScore() != null) {
			model.setScore(extra.getScore());
		}
		if (extra.getState() != null) {
			model.setLive_state(extra.getState());
		}
		model.setAccesstoken(extra.getAccesstoken());
		model.setRatio1("" + getRatio1(aunt));
		model.setRatio2("" + getRatio2(aunt));
		AuntCard auntCard = auntCardMapper.selectByAuntidAndType(aunt.getAuntid(), 0);
		if (null != auntCard) {
			AliInfo aliInfo = new AliInfo();
			aliInfo.setAccount(auntCard.getAccount());
			aliInfo.setName(auntCard.getName());
			model.setAliInfo(aliInfo);
		}

		if (aunt.getInfoState() == null) {
			model.setInfo_state(0);
		} else {
			model.setInfo_state(aunt.getInfoState());
		}

		if (extra.getFontSet() != null) {
			model.setText_size(extra.getFontSet());
		} else {
			model.setText_size(1);
		}
		if (extra.getVoiceSet() != null) {
			model.setVoice_state(extra.getVoiceSet());
		} else {
			model.setVoice_state(0);
		}

		if (aunt.getKingState() != null) {
			model.setKing_state(aunt.getKingState());
		} else {
			model.setKing_state(0);
		}
		
		List<String> piclist = new ArrayList<>();
		if(!CommonUtils.isEmptyString(aunt.getIdcard_picids())){
		   String[] ids = aunt.getIdcard_picids().split(",");
		   if(ids != null){
			   for(int i=0;i<ids.length;i++){
				   if(!CommonUtils.isEmptyString(ids[i])){
					   String picurl = getFilePathById(CommonUtils.parseInt(ids[i], 0));
					   if(picurl != null){
						   piclist.add(picurl);
					   }
					   
				   }
			   }
		   }
		}
		model.setPiclist(piclist);
		return model;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult autoLogin(String accesstoken) {
		ApiResult result = new ApiResult();
		Aunt aunt = null;
		AuntExtra auntExtra = null;
		auntExtra = auntExtramapper.selectByAccesstoken(accesstoken);
		if (null == auntExtra) {
			result.setCode("2");
			result.setMessage("登录失败,用户不存在");
			return result;
		}
		aunt = auntMapper.selectByPrimaryKey(auntExtra.getAuntid());
		if (aunt == null) {
			result.setCode("2");
			result.setMessage("登录失败,用户不存在");
			return result;
		}
		if (!CommonUtils.checkSession(auntExtra.getAccesstoken(), accesstoken)) {
			result.setCode("11");
			result.setMessage("您的登录信息已经过期,或者已经在其它终端登录,请重新登录");
			return result;
		}
		Date nowtime = new Date();
		Date dafter = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(auntExtra.getTokenTime());
		calendar.add(Calendar.MONTH, 3);
		dafter = calendar.getTime();
		if (nowtime.getTime() > dafter.getTime()) {
			result.setCode("4");
			result.setMessage("自动登录失败,token过期.请重新登录");
			return result;
		}
		auntExtra.setLastLogin(new Date());
		auntExtra.setTokenTime(new Date());
		auntExtramapper.updateByPrimaryKey(auntExtra);

		AuntResultModel model = createAuntModel(aunt, auntExtra);
		result.setCode("1");
		result.setMessage("请求成功");
		HashMap<String, Object> map = new HashMap<>();
		map.put("userinfo", model);
		result.setResult(map);
		return result;
	}

	@Override
	public ApiResult thirdLogin(String openid, String thirdtype, String sessionid, String nickname, String avatarurl) {
		ApiResult result = new ApiResult();
		Integer thirdTypeI = Integer.parseInt(thirdtype);
		Aunt aunt = null;
		AuntExtra auntExtra = null;
		if (thirdTypeI == 1) {
			aunt = auntMapper.selectUserByQQId(openid);
		} else if (thirdTypeI == 2) {
			aunt = auntMapper.selectUserBySinaId(openid);
		} else if (thirdTypeI == 3) {
			aunt = auntMapper.selectUserByWxId(openid);
		}

		if (null == aunt) {
			aunt = new Aunt();
			aunt.setAddtime(new Date());
			aunt.setPhone("");
			aunt.setPassword("");
			aunt.setLevel((byte) 0);
			aunt.setWorkYear((byte) 0);
			aunt.setTrainState((byte) 0);
			aunt.setLanguage("");
			aunt.setCharacters("");
			aunt.setCulture("");
			aunt.setReligion("");
			aunt.setPolitical("");
			aunt.setMarriage("");
			aunt.setWorkHis("");
			aunt.setSelfComment("");
			aunt.setHobby("");
			aunt.setState((byte) 0);
			aunt.setConstellation("");
			aunt.setZodiac("");
			aunt.setCompanyid(0);
			if (thirdTypeI == 1) {
				aunt.setQqId(openid);
			} else if (thirdTypeI == 2) {
				aunt.setSinaId(openid);
			} else if (thirdTypeI == 3) {
				aunt.setWxId(openid);
			}
			aunt.setInfoState((short) 0);
			aunt.setKingState((short) 0);
			auntMapper.insert(aunt);
			
			aunt.setInvitation_count(0);
			aunt.setInvitation_code(CommonUtils.getAuntInvite(aunt.getAuntid()));
			aunt.setInvitationed_state((short)0);
			auntMapper.updateByPrimaryKey(aunt);
			
			auntExtra = new AuntExtra();
			auntExtra.setAuntid(aunt.getAuntid());
			// 查询是否需要赠送积分
			Config config = configMapper.selectConfigByKey(Constant.REGISTER_POINT_AUNT);
			int sendPoint = 0;
			if (config != null && !CommonUtils.isEmptyString(config.getConfigvalue())) {
				sendPoint = CommonUtils.parseInt(config.getConfigvalue(), 0);
			}
			auntExtra.setPoint(sendPoint);
			auntExtra.setPointTotal(sendPoint);
			auntExtra.setBalance(new BigDecimal("0.00"));
			auntExtra.setUseTotal(new BigDecimal("0.00"));
			String accesstoken = CommonUtils.MD5(sessionid + aunt.getAuntid());
			auntExtra.setAccesstoken(CommonUtils.MD5(accesstoken));
			auntExtra.setTokenTime(new Date());
			auntExtra.setLongitude(0d);
			auntExtra.setLatitude(0d);
			auntExtra.setState((short) 0);
			auntExtra.setFontSet((short) 2);
			auntExtra.setVoiceSet((short) 0);
			auntExtra.setScore(0f);
			auntExtramapper.insert(auntExtra);
			if (sendPoint > 0) {
				AuntPointRecord record = new AuntPointRecord();
				record.setAddtime(new Date());
				record.setCount(sendPoint);
				record.setType((byte) 1);
				record.setAuntid(aunt.getAuntid());
				auntPointRecordMapper.insert(record);
			}

		} else {
			aunt.setRealName(nickname);
			aunt.setThirdAvatar(avatarurl);
			auntMapper.updateByPrimaryKey(aunt);
			// 更新用户信息
			auntExtra = auntExtramapper.selectByAuntId(aunt.getAuntid());
			if (auntExtra == null) {
				auntExtra = new AuntExtra();
				auntExtra.setAuntid(aunt.getAuntid());

				// 查询是否需要赠送积分
				Config config = configMapper.selectConfigByKey(Constant.REGISTER_POINT_USER);
				int sendPoint = 0;
				if (config != null && !CommonUtils.isEmptyString(config.getConfigvalue())) {
					sendPoint = CommonUtils.parseInt(config.getConfigvalue(), 0);
				}
				auntExtra.setPoint(sendPoint);
				auntExtra.setPointTotal(sendPoint);
				auntExtra.setBalance(new BigDecimal("0.00"));
				auntExtra.setUseTotal(new BigDecimal("0.00"));
				String accesstoken = CommonUtils.MD5(sessionid + aunt.getAuntid());
				auntExtra.setAccesstoken(CommonUtils.MD5(accesstoken));
				auntExtra.setTokenTime(new Date());
				auntExtra.setLongitude(0d);
				auntExtra.setLatitude(0d);
				auntExtra.setState((short) 0);
				auntExtra.setFontSet((short) 2);
				auntExtra.setVoiceSet((short) 0);
				auntExtra.setScore(0f);
				auntExtramapper.insert(auntExtra);
			} else {
				auntExtra.setLastLogin(new Date());
				String accesstoken = CommonUtils.MD5(sessionid + aunt.getAuntid());
				auntExtra.setAccesstoken(CommonUtils.MD5(accesstoken));
				auntExtra.setTokenTime(new Date());
				auntExtramapper.updateByPrimaryKey(auntExtra);
				// 用户等级
				AuntLevelSet levelSet = auntLevelSetMapper.setlectSetByPoint(auntExtra.getPointTotal());
				if (levelSet != null && levelSet.getLevel() != aunt.getLevel()) {
					aunt.setLevel(levelSet.getLevel());
					auntMapper.updateByPrimaryKey(aunt);
				}
			}
		}

		AuntResultModel model = createAuntModel(aunt, auntExtra);
		result.setCode("1");
		result.setMessage("登录成功");

		HashMap<String, Object> map = new HashMap<>();
		map.put("userinfo", model);
		result.setResult(map);
		return result;
	}

	@Override
	public ApiResult bindPhone(Aunt aunt, String phone, String vcode, String sessionid) {
		ApiResult result = new ApiResult();
		VCodeAunt vcodeModel = vCodeAuntMapper.selectLastVcodeByPhoneAndType(phone, 4, 0);
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
		Aunt auntPhone = auntMapper.selectUserByPhone(phone);
		if (auntPhone == null) {
			aunt.setPhone(phone);
			auntMapper.updateByPrimaryKey(aunt);
			AuntExtra extra = auntExtramapper.selectByAuntId(aunt.getAuntid());
			AuntResultModel model = createAuntModel(aunt, extra);
			HashMap<String, Object> map = new HashMap<>();
			map.put("userinfo", model);
			result.setCode("1");
			result.setMessage("修改成功");
			result.setResult(map);
			return result;
		} else {
			auntPhone.setThirdAvatar(aunt.getThirdAvatar());

			auntPhone.setWxId(aunt.getWxId());
			auntPhone.setQqId(aunt.getQqId());
			auntPhone.setSinaId(aunt.getSinaId());
			auntPhone.setRealName(aunt.getRealName());
			auntMapper.updateByPrimaryKey(auntPhone);
			AuntExtra extra = auntExtramapper.selectByAuntId(aunt.getAuntid());
			if (null != extra) {
				auntExtramapper.deleteByPrimaryKey(extra.getDataid());
			}
			auntMapper.deleteByPrimaryKey(aunt.getAuntid());
			AuntExtra auntPhoneExrea = auntExtramapper.selectByAuntId(auntPhone.getAuntid());
			AuntResultModel model = createAuntModel(auntPhone, auntPhoneExrea);
			HashMap<String, Object> map = new HashMap<>();
			map.put("userinfo", model);
			result.setCode("1");
			result.setMessage("修改成功");
			result.setResult(map);
			return result;

		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult changePassword(String phone, String vcode, String newpsw, String user_type) {
		ApiResult result = new ApiResult();
		VCodeAunt vcodeModel = vCodeAuntMapper.selectLastVcodeByPhoneAndType(phone, 1,
				CommonUtils.parseInt(user_type, 0));

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

		short userType = CommonUtils.parseShort(user_type, (short) 0);
		if (userType == 0) {
			Aunt aunt = auntMapper.selectUserByPhone(phone);
			if (aunt == null) {
				result.setCode("4");
				result.setMessage("修改失败,该手机号未注册");
				return result;
			}
			aunt.setPassword(newpsw);
			auntMapper.updateByPrimaryKey(aunt);
			result.setCode("1");
			result.setMessage("修改成功");
		} else {
			CompanyWithBLOBs company = companyMapper.selectUserByPhone(phone);
			if (company == null) {
				result.setCode("4");
				result.setMessage("修改失败,该手机号未注册");
				return result;
			}
			company.setPassword(newpsw);
			companyMapper.updateByPrimaryKey(company);
			result.setCode("1");
			result.setMessage("修改成功");
		}

		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult companyLogin(String phone, String password, String sessionid) {
		ApiResult result = new ApiResult();
		CompanyWithBLOBs company = companyMapper.selectUserByPhone(phone);
		if (company == null) {
			result.setCode("2");
			result.setMessage("登录失败,用户不存在");
			return result;
		}

		if (!company.getPassword().equals(password)) {
			result.setCode("3");
			result.setMessage("登录失败,密码错误");
			return result;
		}

		// 更新用户信息
		CompanyExtra companyExtra = companyExtraMapper.selectByCompanyId(company.getCompanyid());
		if (companyExtra == null) {
			companyExtra = new CompanyExtra();
			companyExtra.setCompanyid(company.getCompanyid());
			companyExtra.setBalance(new BigDecimal("0.00"));
			companyExtra.setUseTotal(new BigDecimal("0.00"));
			String accesstoken = CommonUtils.MD5(sessionid + company.getCompanyid());
			companyExtra.setAccesstoken(CommonUtils.MD5(accesstoken));
			companyExtra.setTokenTime(new Date());
			companyExtra.setLastLogin(new Date());
			companyExtra.setState((short) 0);
			companyExtra.setLastDevicetype((short) 0);
			companyExtra.setAddtime(new Date());
			companyExtra.setScore(0f);
			companyExtra.setFontSet((short) 0);
			companyExtra.setVoiceSet((short) 0);
			companyExtraMapper.insert(companyExtra);
		} else {
			companyExtra.setLastLogin(new Date());
			String accesstoken = CommonUtils.MD5(sessionid + company.getCompanyid());
			companyExtra.setAccesstoken(CommonUtils.MD5(accesstoken));
			companyExtra.setTokenTime(new Date());
			companyExtraMapper.updateByPrimaryKey(companyExtra);
		}

		CompanyResultModel model = createCompanyModel(company, companyExtra);
		result.setCode("1");
		result.setMessage("登录成功");
		HashMap<String, Object> map = new HashMap<>();
		map.put("userinfo", model);
		result.setResult(map);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult companyAutoLogin(String accesstoken) {
		ApiResult result = new ApiResult();
		CompanyWithBLOBs company = null;
		CompanyExtra companyExtra = null;
		companyExtra = companyExtraMapper.selectByAccesstoken(accesstoken);
		if (null == companyExtra) {
			result.setCode("2");
			result.setMessage("登录失败,用户不存在");
			return result;
		}
		company = companyMapper.selectByPrimaryKey(companyExtra.getCompanyid());
		if (company == null) {
			result.setCode("2");
			result.setMessage("登录失败,用户不存在");
			return result;
		}
		if (!CommonUtils.checkSession(companyExtra.getAccesstoken(), accesstoken)) {
			result.setCode("11");
			result.setMessage("您的登录信息已经过期,或者已经在其它终端登录,请重新登录");
			return result;
		}
		Date nowtime = new Date();
		Date dafter = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(companyExtra.getTokenTime());
		calendar.add(Calendar.MONTH, 3);
		dafter = calendar.getTime();
		if (nowtime.getTime() > dafter.getTime()) {
			result.setCode("4");
			result.setMessage("自动登录失败,token过期.请重新登录");
			return result;
		}
		companyExtra.setLastLogin(new Date());
		companyExtra.setTokenTime(new Date());
		companyExtraMapper.updateByPrimaryKey(companyExtra);
		CompanyResultModel model = createCompanyModel(company, companyExtra);
		result.setCode("1");
		result.setMessage("请求成功");
		HashMap<String, Object> map = new HashMap<>();
		map.put("userinfo", model);
		result.setResult(map);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult heartBeat(String accesstoken, int user_type, String longitude, String latitude) {
		ApiResult result = new ApiResult();
		result.setCode("1");
		result.setMessage("请求成功");
		if (user_type == 0) {
			AuntExtra auntExtra = auntExtramapper.selectByAccesstoken(accesstoken);
			if (null != auntExtra) {
				auntExtra.setLongitude(CommonUtils.parseDouble(longitude, 0d));
				auntExtra.setLatitude(CommonUtils.parseDouble(latitude, 0d));
				auntExtra.setLocationtime(new Date());
				auntExtramapper.updateByPrimaryKey(auntExtra);
			}

		} else if (user_type == 1) {
			CompanyExtra extra = companyExtraMapper.selectByAccesstoken(accesstoken);
			if (null != extra) {
				extra.setHeartbeatTime(new Date());

				companyExtraMapper.updateByPrimaryKey(extra);
			}
		}
		return result;
	}

	@Override
	public ApiResult sign(Aunt aunt, AuntExtra auntExtra) {
		ApiResult result = new ApiResult();
		Calendar now = Calendar.getInstance();
		if (aunt != null) {
			if (auntExtra.getLastSign() != null) {
				String dateStr1 = CommonUtils.getTimeFormat(auntExtra.getLastSign(), "yyyy-MM-dd");
				String dateStr2 = CommonUtils.getTimeFormat(now.getTime(), "yyyy-MM-dd");
				if (dateStr1.equals(dateStr2)) {
					result.setCode("2");
					result.setMessage("签到失败,您今天已经签过到了");
					return result;
				}
			}

			int continuityDay = 1;// 连续签到天数
			if (auntExtra.getLastSign() != null) {
				Calendar lastSignday = Calendar.getInstance();
				lastSignday.setTime(auntExtra.getLastSign());
				lastSignday.add(Calendar.DAY_OF_MONTH, 1);
				if (lastSignday.get(Calendar.YEAR) == now.get(Calendar.YEAR)
						&& lastSignday.get(Calendar.MONTH) == now.get(Calendar.MONTH)
						&& lastSignday.get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH)) {
					if (auntExtra.getSignDay() != null) {
						continuityDay = auntExtra.getSignDay() + 1;
					}
				}
			}
			// 设置好用户信息
			auntExtra.setLastSign(now.getTime());
			auntExtra.setSignDay(continuityDay);
			// 查询应该增加多少积分
			AuntSignSet set = auntSignSetMapper.selectFitSet(continuityDay);
			int addPoint = 1;
			if (set != null && set.getPoint() != null) {
				addPoint = set.getPoint();
			}
			if (auntExtra.getPoint() != null) {
				auntExtra.setPoint(auntExtra.getPoint() + addPoint);
			} else {
				auntExtra.setPoint(addPoint);
			}
			if (auntExtra.getPointTotal() != null) {
				auntExtra.setPointTotal(auntExtra.getPointTotal() + addPoint);
			} else {
				auntExtra.setPointTotal(addPoint);
			}
			AuntPointRecord pointRecord = new AuntPointRecord();
			pointRecord.setAddtime(new Date());
			pointRecord.setCount(addPoint);
			pointRecord.setType((byte) 4);
			pointRecord.setAuntid(aunt.getAuntid());
			auntPointRecordMapper.insert(pointRecord);

			// 签到记录
			SignRecordAunt signRecord = new SignRecordAunt();
			signRecord.setAddtime(new Date());
			signRecord.setUserid(aunt.getAuntid());
			signRecordAuntMapper.insert(signRecord);

			// 用户等级
			AuntLevelSet levelSet = auntLevelSetMapper.setlectSetByPoint(auntExtra.getPointTotal());
			if (levelSet != null && levelSet.getLevel() != aunt.getLevel()) {
				aunt.setLevel(levelSet.getLevel());
				auntMapper.updateByPrimaryKey(aunt);
			}

			auntExtramapper.updateByPrimaryKey(auntExtra);

			result.setCode("1");
			result.setMessage("签到成功");

			HashMap<String, Object> map = new HashMap<>();
			map.put("days", continuityDay);
			map.put("point", addPoint);
			map.put("level", aunt.getLevel());
			if (levelSet != null) {
				map.put("level_name", levelSet.getTitle());
			}

			// 本周连续签到天数
			now.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			String dateStrMonday = CommonUtils.getTimeFormat(now.getTime(), "yyyy-MM-dd");
			now.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			String dateStrSunday = CommonUtils.getTimeFormat(now.getTime(), "yyyy-MM-dd");

			Long count = signRecordAuntMapper.selectWeekSignCount(dateStrMonday, dateStrSunday, aunt.getAuntid());
			if (count == null) {
				map.put("weekday", 0);
			} else {
				map.put("weekday", count.intValue());
			}
			result.setResult(map);

		}
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult feedBack(String content, int userid, int user_type) {

		ApiResult result = new ApiResult();
		result.setCode("1");
		result.setMessage("请求成功");
		FeedBackAunt feedBackAunt = new FeedBackAunt();
		feedBackAunt.setAddtime(new Date());
		feedBackAunt.setContent(content);
		feedBackAunt.setUserid(userid);
		feedBackAunt.setUserType((byte) user_type);
		feedbackAuntMapper.insert(feedBackAunt);
		return result;
	}

	private CompanyResultModel createCompanyModel(CompanyWithBLOBs company, CompanyExtra companyExtra) {
		CompanyResultModel model = new CompanyResultModel();
		model.setAccesstoken(companyExtra.getAccesstoken());
		model.setUserid(company.getCompanyid());
		model.setPhone(company.getPhone());
		model.setName(company.getName());
		if (company.getLogoPicid() != null && company.getLogoPicid() > 0) {
			String filePath = getFilePathById(company.getLogoPicid());
			model.setLogourl(filePath);
		} else {

		}
		if (company.getListpic() != null && company.getListpic() > 0) {
			String listpicurl = getFilePathById(company.getListpic());
			model.setListpicurl(listpicurl);
		}
		model.setProfile(company.getProfile());
		model.setCity(company.getCity());
		String picids = company.getPiclist();
		List<String> picurllist = new ArrayList<>();
		if (!CommonUtils.isEmptyString(picids)) {
			String[] ids = picids.split(",");
			if (ids != null) {
				for (int i = 0; i < ids.length; i++) {
					if (!CommonUtils.isEmptyString(ids[i])) {
						String filePath = getFilePathById(Integer.parseInt(ids[i]));
						picurllist.add(filePath);
					}
				}
			}
		}
		model.setPicurllist(picurllist);
		model.setYear_create(CommonUtils.getTimeFormat(company.getYearCreate(), "yyy-MM-dd"));
		if (company.getPeopleCount() != null) {
			model.setPeople_count(company.getPeopleCount());
		}
		model.setAddress(company.getAddress());
		model.setRelation_people(company.getRelationPeople());
		model.setRelation_phone(company.getRelationPhone());
		if (company.getStateDel() != null) {
			model.setState_del(company.getStateDel());
		}
		if (company.getTitle() != null) {
			model.setTitle(company.getTitle());
		}
		if (company.getStar() != null) {
			model.setStar(company.getStar());
		}
		if (companyExtra.getScore() != null) {
			model.setScore(companyExtra.getScore());
		}
		if (companyExtra.getBalance() != null) {   ;
			model.setBalance(CommonUtils.parseFloat(companyExtra.getBalance().setScale(2, BigDecimal.ROUND_UP).toPlainString(), 0));
		}
		if (companyExtra.getState() != null) {
			model.setLive_state(companyExtra.getState());
		}
		// model.setInfo_state(companyExtra.get);
		AuntCard auntCard = auntCardMapper.selectByAuntidAndType(company.getCompanyid(), 1);
		if (null != auntCard) {
			AliInfo aliInfo = new AliInfo();
			aliInfo.setAccount(auntCard.getAccount());
			aliInfo.setName(auntCard.getName());
			model.setAliInfo(aliInfo);
		}

		if (companyExtra.getFontSet() != null) {
			model.setText_size(companyExtra.getFontSet());
		} else {
			model.setText_size(1);
		}

		if (companyExtra.getVoiceSet() != null) {
			model.setVoice_state(companyExtra.getVoiceSet());
		} else {
			model.setVoice_state(0);
		}

		return model;

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult refreshMessage(Aunt aunt, Company company) {
		ApiResult result = new ApiResult();
		if (aunt != null) {
			Long count = auntMessageMapper.selectUnRead(aunt.getAuntid(), 0);
			long countL = (long) count;
			HashMap<String, Object> resultMap = new HashMap<>();
			resultMap.put("messagecount", countL);
			result.setResult(resultMap);
			result.setCode("1");
			result.setMessage("请求成功");
			return result;
		} else if (company != null) {
			Long count = auntMessageMapper.selectUnRead(company.getCompanyid(), 1);
			long countL = (long) count;
			HashMap<String, Object> resultMap = new HashMap<>();
			resultMap.put("messagecount", countL);
			result.setResult(resultMap);
			result.setCode("1");
			result.setMessage("请求成功");
			return result;
		} else {

		}
		return null;

	}

	/**
	 * 获取消息列表
	 * 
	 * @param type
	 *            回调类型 1.支付宝 2.微信支付
	 * 
	 */
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult messageList(Aunt aunt, Company company) {
		ApiResult result = new ApiResult();
		if (aunt != null) {
			List<AuntMessage> messages = auntMessageMapper.selectByPage(aunt.getAuntid(), 0);
			List<MessageValue> messageList = new ArrayList<>();
			if (messages != null && messages.size() > 0) {
				for (AuntMessage message : messages) {
					MessageValue value = new MessageValue();
					value.setId(message.getId());
					value.setTitle(message.getTitle());
					byte typeB = message.getType();
					value.setType(typeB);
					if (message.getReadState() == 1) {
						value.setRead_state(1);
					} else {
						value.setRead_state(0);
					}
					String timeStr = CommonUtils.getTimeDiff(message.getLastMsgTime());
					value.setTimestr(timeStr);
					AuntMessageDetail detail = auntMessageDetailMapper.selectLastByMid(message.getId());
					if (detail != null) {
						value.setLastmessage(detail.getDetail());
					}else{
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
		} else if (company != null) {
			List<AuntMessage> messages = auntMessageMapper.selectByPage(company.getCompanyid(), 1);
			List<MessageValue> messageList = new ArrayList<>();
			if (messages != null && messages.size() > 0) {
				for (AuntMessage message : messages) {
					MessageValue value = new MessageValue();
					value.setId(message.getId());
					value.setTitle(message.getTitle());
					byte typeB = message.getType();
					value.setType(typeB);
					if (message.getReadState() == 1) {
						value.setRead_state(1);
					} else {
						value.setRead_state(0);
					}
					String timeStr = CommonUtils.getTimeDiff(message.getLastMsgTime());
					value.setTimestr(timeStr);
					AuntMessageDetail detail = auntMessageDetailMapper.selectLastByMid(message.getId());
					if (detail != null) {
						value.setLastmessage(detail.getDetail());
					}else{
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
		} else {

		}

		return null;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult setReaded(Aunt aunt, Company company, String mids) {

		ApiResult result = new ApiResult();
		String[] ids = mids.split(",");
		if (null != ids) {
			for (int i = 0; i < ids.length; i++) {
				if (!CommonUtils.isEmptyString(ids[i])) {
					AuntMessage message = auntMessageMapper.selectByPrimaryKey(Integer.parseInt(ids[i]));
					message.setReadState((byte) 1);
					message.setUnreadCount(0);
					auntMessageMapper.updateByPrimaryKey(message);
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
	public ApiResult delMessage(Aunt aunt, Company company, String mids) {
		ApiResult result = new ApiResult();
		String[] ids = mids.split(",");
		if (null != ids) {
			for (int i = 0; i < ids.length; i++) {
				if (!CommonUtils.isEmptyString(ids[i])) {
					AuntMessage message = auntMessageMapper.selectByPrimaryKey(Integer.parseInt(ids[i]));
					message.setReadState((byte) 1);
					message.setUnreadCount(0);
					auntMessageMapper.updateByPrimaryKey(message);
					auntMessageDetailMapper.deleteByMessageid(message.getId());
				}
			}

		}
		result.setCode("1");
		result.setMessage("请求成功");
		return result;

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult messageDetail(Aunt aunt, Company company, String messageid, int page) {
		ApiResult result = new ApiResult();
		AuntMessage message = auntMessageMapper.selectByPrimaryKey(CommonUtils.parseInt(messageid, 0));
		if (message != null) {
			message.setReadState((byte) 1);
			message.setUnreadCount(0);
			auntMessageMapper.updateByPrimaryKey(message);

			List<AuntMessageDetail> detailList = auntMessageDetailMapper.selectByPage(message.getId(), (page - 1) * 10,
					10);
			List<MessageDetailValue> valueList = new ArrayList<>();
			HashMap<String, Object> resultMap = new HashMap<>();
			if (detailList != null && detailList.size() > 0) {
				for (AuntMessageDetail messageDetail : detailList) {
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

				Long count = auntMessageDetailMapper.selectPageCount(message.getId());
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

	@Override
	public ApiResult getSignMonth(Aunt aunt, String time) {

		ApiResult result = new ApiResult();
		Date date = CommonUtils.getDateFormat(time, "yyyy-MM");
		AuntExtra userPhoneExtra = auntExtramapper.selectByAuntId(aunt.getAuntid());
		HashMap<String, Object> map = new HashMap<>();
		if (userPhoneExtra.getSignDay() != null) {
			map.put("signdays", userPhoneExtra.getSignDay());
		} else {
			map.put("signdays", 0);
		}
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		String firstDate = CommonUtils.getFirstDayOfMonth(year, month);
		String lastDate = CommonUtils.getLastDayOfMonth(year, month);
		List<SignRecordAunt> signRecords = signRecordAuntMapper.selectByMonth(firstDate, lastDate,
				userPhoneExtra.getAuntid());
		List<DayValue> dayList = new ArrayList<DayValue>();
		if (signRecords != null && signRecords.size() > 0) {
			for (SignRecordAunt signRecord : signRecords) {
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
	public ApiResult updateMustInfo(Aunt aunt, String real_name, String sex, String origin_place, String idcard_num,
			String nation, String work_year, String server_ids, String train_state, String language, String character,
			String now_address, String sessionid,String idcard_picids) {
		ApiResult result = new ApiResult();
		aunt.setRealName(real_name);
		aunt.setSex(sex);
		aunt.setOriginPlace(origin_place);
		if(IdcardValidator.isValidatedAllIdcard(idcard_num)){
			
		}else {
			result.setCode("12");
			result.setMessage("请输入正确的身份证号");
			return result;
		}
		String birthDayStr = idcard_num.substring(6, 14);
		try {
			Date birthdate = new SimpleDateFormat("yyyyMMdd").parse(birthDayStr);
			aunt.setBirthday(birthdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		
		aunt.setIdcardNum(idcard_num);
		aunt.setNation(nation);
		aunt.setWorkYear(Byte.parseByte(work_year));
		String[] ids = server_ids.split(",");
		if (ids != null) {
			for (int i = 0; i < ids.length; i++) {
				if (!CommonUtils.isEmptyString(ids[i])) {
					int id = Integer.parseInt(ids[i]);
					AuntSkill skill = new AuntSkill();
					skill.setAddtime(new Date());
					skill.setAuntid(aunt.getAuntid());
					skill.setCategoryid(id);
					skill.setThirdid(0);
					auntSkillMapper.insert(skill);
				}
			}
		}
		aunt.setTrainState(Byte.parseByte(train_state));
		aunt.setLanguage(language);
		aunt.setCharacters(character);
		aunt.setNowAddress(now_address);
		aunt.setInfoState((short) 1);
		aunt.setIdcard_picids(idcard_picids);
		auntMapper.updateByPrimaryKey(aunt);

		// 更新用户信息
		AuntExtra auntExtra = auntExtramapper.selectByAuntId(aunt.getAuntid());
		if (auntExtra == null) {
			auntExtra = new AuntExtra();
			auntExtra.setAuntid(aunt.getAuntid());
			// 查询是否需要赠送积分
			Config config = configMapper.selectConfigByKey(Constant.REGISTER_POINT_AUNT);
			int sendPoint = 0;
			if (config != null && !CommonUtils.isEmptyString(config.getConfigvalue())) {
				sendPoint = CommonUtils.parseInt(config.getConfigvalue(), 0);
			}
			auntExtra.setPoint(sendPoint);
			auntExtra.setPointTotal(sendPoint);
			auntExtra.setBalance(new BigDecimal("0.00"));
			auntExtra.setUseTotal(new BigDecimal("0.00"));
			String accesstoken = CommonUtils.MD5(sessionid + aunt.getAuntid());
			auntExtra.setAccesstoken(CommonUtils.MD5(accesstoken));
			auntExtra.setTokenTime(new Date());
			auntExtra.setLongitude(0d);
			auntExtra.setLatitude(0d);
			auntExtra.setState((short) 0);
			auntExtra.setFontSet((short) 2);
			auntExtra.setVoiceSet((short) 0);
			auntExtra.setScore(0f);
			auntExtramapper.insert(auntExtra);
			if (sendPoint > 0) {
				AuntPointRecord record = new AuntPointRecord();
				record.setAddtime(new Date());
				record.setCount(sendPoint);
				record.setType((byte) 2);
				record.setAuntid(aunt.getAuntid());
				auntPointRecordMapper.insert(record);
			}
		} else {
			auntExtra.setLastLogin(new Date());
			String accesstoken = CommonUtils.MD5(sessionid + aunt.getAuntid());
			auntExtra.setAccesstoken(CommonUtils.MD5(accesstoken));
			auntExtra.setTokenTime(new Date());
			auntExtramapper.updateByPrimaryKey(auntExtra);
		}

		AuntResultModel model = createAuntModel(aunt, auntExtra);
		HashMap<String, Object> map = new HashMap<>();
		result.setCode("1");
		result.setMessage("请求成功");
		map.put("userinfo", model);
		result.setResult(map);

		return result;
	}

	@Override
	public ApiResult getAuntInfoSet() {
		ApiResult result = new ApiResult();
		List<AuntInfoSet> auntInfoSet0 = auntInfoSetMapper.selectByType(0);
		List<AuntSetValue> list0 = new ArrayList<>();
		if (auntInfoSet0 != null && auntInfoSet0.size() > 0) {
			for (AuntInfoSet auntInfoSet : auntInfoSet0) {
				AuntSetValue value = new AuntSetValue();
				value.setName(auntInfoSet.getTitle());
				list0.add(value);
			}
		}

		List<AuntInfoSet> auntInfoSet1 = auntInfoSetMapper.selectByType(1);
		List<AuntSetValue> list1 = new ArrayList<>();
		if (auntInfoSet1 != null && auntInfoSet1.size() > 0) {
			for (AuntInfoSet auntInfoSet : auntInfoSet1) {
				AuntSetValue value = new AuntSetValue();
				value.setName(auntInfoSet.getTitle());
				list1.add(value);
			}
		}

		List<AuntInfoSet> auntInfoSet2 = auntInfoSetMapper.selectByType(2);
		List<AuntSetValue> list2 = new ArrayList<>();
		if (auntInfoSet2 != null && auntInfoSet2.size() > 0) {
			for (AuntInfoSet auntInfoSet : auntInfoSet2) {
				AuntSetValue value = new AuntSetValue();
				value.setName(auntInfoSet.getTitle());
				list2.add(value);
			}
		}

		List<AuntInfoSet> auntInfoSet3 = auntInfoSetMapper.selectByType(3);
		List<AuntSetValue> list3 = new ArrayList<>();
		if (auntInfoSet3 != null && auntInfoSet3.size() > 0) {
			for (AuntInfoSet auntInfoSet : auntInfoSet3) {
				AuntSetValue value = new AuntSetValue();
				value.setName(auntInfoSet.getTitle());
				list3.add(value);
			}
		}

		List<AuntInfoSet> auntInfoSet4 = auntInfoSetMapper.selectByType(4);
		List<AuntSetValue> list4 = new ArrayList<>();
		if (auntInfoSet4 != null && auntInfoSet4.size() > 0) {
			for (AuntInfoSet auntInfoSet : auntInfoSet4) {
				AuntSetValue value = new AuntSetValue();
				value.setName(auntInfoSet.getTitle());
				list4.add(value);
			}
		}

		List<AuntInfoSet> auntInfoSet5 = auntInfoSetMapper.selectByType(5);
		List<AuntSetValue> list5 = new ArrayList<>();
		if (auntInfoSet5 != null && auntInfoSet5.size() > 0) {
			for (AuntInfoSet auntInfoSet : auntInfoSet5) {
				AuntSetValue value = new AuntSetValue();
				value.setName(auntInfoSet.getTitle());
				list5.add(value);
			}
		}

		List<AuntInfoSet> auntInfoSet6 = auntInfoSetMapper.selectByType(6);
		List<AuntSetValue> list6 = new ArrayList<>();
		if (auntInfoSet6 != null && auntInfoSet6.size() > 0) {
			for (AuntInfoSet auntInfoSet : auntInfoSet6) {
				AuntSetValue value = new AuntSetValue();
				value.setName(auntInfoSet.getTitle());
				list6.add(value);
			}
		}

		List<AuntInfoSet> auntInfoSet7 = auntInfoSetMapper.selectByType(7);
		List<AuntSetValue> list7 = new ArrayList<>();
		if (auntInfoSet7 != null && auntInfoSet7.size() > 0) {
			for (AuntInfoSet auntInfoSet : auntInfoSet7) {
				AuntSetValue value = new AuntSetValue();
				value.setName(auntInfoSet.getTitle());
				list7.add(value);
			}
		}

		List<AuntInfoSet> auntInfoSet8 = auntInfoSetMapper.selectByType(8);
		List<AuntSetValue> list8 = new ArrayList<>();
		if (auntInfoSet8 != null && auntInfoSet8.size() > 0) {
			for (AuntInfoSet auntInfoSet : auntInfoSet8) {
				AuntSetValue value = new AuntSetValue();
				value.setName(auntInfoSet.getTitle());
				list8.add(value);
			}
		}

		HashMap<String, Object> map = new HashMap<>();
		map.put("list0", list0);
		map.put("list1", list1);
		map.put("list2", list2);
		map.put("list3", list3);
		map.put("list4", list4);
		map.put("list5", list5);
		map.put("list6", list6);
		map.put("list7", list7);
		map.put("list8", list8);
		result.setResult(map);
		result.setCode("1");
		result.setMessage("请求成功");
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult updateOptionalInfo(Aunt aunt, String culture, String home_address, String religion,
			String political, String height, String weight, String marriage, String blood_type, String display,
			String work_his, String self_comment, String hobby) {
		ApiResult result = new ApiResult();
		aunt.setCulture(culture);
		aunt.setHomeAddress(home_address);
		aunt.setReligion(religion);
		aunt.setPolitical(political);
		if (height != null) {
			aunt.setHeight(Float.parseFloat(height));
		}
		if (weight != null) {
			aunt.setWeight(Float.parseFloat(weight));
		}

		aunt.setMarriage(marriage);
		aunt.setBloodType(blood_type);
		aunt.setWorkHis(work_his);
		aunt.setSelfComment(self_comment);
		aunt.setHobby(hobby);
		auntMapper.updateByPrimaryKey(aunt);

		List<DisplayModel> displays = new Gson().fromJson(display, new TypeToken<List<DisplayModel>>() {
		}.getType());
		if (displays != null && displays.size() > 0) {
			for (DisplayModel model : displays) {
				AuntDisplay auntDisplay = new AuntDisplay();
				auntDisplay.setAuntid(aunt.getAuntid());
				auntDisplay.setCertificateName(model.getCertificate_name());
				auntDisplay.setSkillName(model.getSkill_name());
				auntDisplay.setPicid(model.getPicid());
				auntDisplayMapper.insert(auntDisplay);
			}
		}
		HashMap<String, Object> map = new HashMap<>();
		map.put("ratio1", getRatio1(aunt));
		map.put("ratio2", getRatio2(aunt));
		result.setCode("1");
		result.setMessage("请求成功");
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult refreshUserInfo(Aunt aunt, AuntExtra extra) {
		ApiResult result = new ApiResult();

		AuntResultModel model = createAuntModel(aunt, extra);

		result.setCode("1");
		result.setMessage("请求成功");

		HashMap<String, Object> map = new HashMap<>();
		// map.put("userinfo", model);
		map.put("real_name", model.getReal_name());
		map.put("avatarurl", model.getAvatarurl());
		map.put("level", model.getLevel());
		map.put("levelname", model.getLevelname());
		map.put("point", model.getPoint());
		map.put("balance", model.getBalance());
		map.put("sign_week", model.getSign_week());
		map.put("today_sign", model.getToday_sign());
		map.put("work_year", "" + model.getWork_year());
		result.setResult(map);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult updateSet(AuntExtra extra, String voice_statem, String text_size) {
		ApiResult result = new ApiResult();
		if (!CommonUtils.isEmptyString(voice_statem)) {
			extra.setVoiceSet(CommonUtils.parseShort(voice_statem, (short) 0));
		}
		if (!CommonUtils.isEmptyString(text_size)) {
			extra.setFontSet(CommonUtils.parseShort(text_size, (short) 1));
		}

		auntExtramapper.updateByPrimaryKey(extra);

		HashMap<String, Object> map = new HashMap<>();
		if (extra.getVoiceSet() != null) {
			map.put("voice_state", extra.getVoiceSet());
		} else {
			map.put("voice_state", 0);
		}

		if (extra.getFontSet() != null) {
			map.put("text_size", extra.getFontSet());
		} else {
			map.put("voice_state", 1);
		}

		result.setCode("1");
		result.setMessage("请求成功");
		result.setResult(map);
		return result;
	}

	@Override
	public ApiResult getCommentList(String userid, String accesstoken, String user_type, int page) {
		ApiResult result = new ApiResult();
		int userType = Integer.parseInt(user_type);
		if (userType == 0) {
			Aunt aunt = auntMapper.selectByPrimaryKey(Integer.parseInt(userid));
			if (aunt == null) {
				result.setCode("2");
				result.setMessage("登录失败,用户不存在");
				return result;
			}
			AuntExtra extra = auntExtramapper.selectByAuntId(Integer.parseInt(userid));
			extra = auntExtramapper.selectByAuntId(Integer.parseInt(userid));
			if (!CommonUtils.checkSession(extra.getAccesstoken(), accesstoken)) {
				result.setCode("11");
				result.setMessage("您的登录信息已经过期,或者已经在其它终端登录,请重新登录");
				return result;
			}
			HashMap<String, Object> map = new HashMap<>();
			if (aunt.getAvatar() != null && aunt.getAvatar() > 0) {
				map.put("picurl", getFilePathById(aunt.getAvatar()));
			} else {
				map.put("picurl", aunt.getThirdAvatar());
			}
			map.put("score", extra.getScore());
			List<OrderComment> commnetList = orderCommentMapper.selectByAuntIdAndPage(Integer.parseInt(userid),
					(page - 1) * 10, 10);
			List<OrderComment> commnetListMore = orderCommentMapper.selectByAuntIdAndPage(Integer.parseInt(userid),
					(page) * 10, 10);
			if (commnetListMore != null && commnetListMore.size() > 0) {
				map.put("havemore", 1);
			} else {
				map.put("havemore", 0);
			}
			List<CommnetValue> valueList = new ArrayList<>();
			for (OrderComment orderComment : commnetList) {
				CommnetValue value = new CommnetValue();
				User user = userMapper.selectByPrimaryKey(orderComment.getUserid());
				if (user.getAvatar() != null && user.getAvatar() > 0) {
					value.setAvatarurl(getFilePathById(user.getAvatar()));
				} else {
					value.setAvatarurl(user.getThirdAvatar());
				}
				value.setOrderid(orderComment.getOrderid());
				value.setName(user.getRealName());
				value.setStae(orderComment.getCommentType());
				value.setTime(CommonUtils.getTimeFormat(orderComment.getAddtime(), null));
				value.setContent(orderComment.getContent());
				Order order = orderMapper.selectByPrimaryKey(orderComment.getOrderid());
				CategorySecond second = categorySecondMapper.selectByPrimaryKey(order.getCategoryid());
				if (null != second) {
					value.setServer_type(second.getName());
				}
				valueList.add(value);
			}
			map.put("commentList", valueList);
			result.setCode("1");
			result.setMessage("请求成功");
			result.setResult(map);
			return result;
		} else if (userType == 1) {
			Company company = companyMapper.selectByPrimaryKey(Integer.parseInt(userid));
			if (company == null) {
				result.setCode("2");
				result.setMessage("请求失败,用户不存在");
				return result;
			}
			CompanyExtra extra = companyExtraMapper.selectByCompanyId(Integer.parseInt(userid));
			if (!CommonUtils.checkSession(extra.getAccesstoken(), accesstoken)) {
				result.setCode("11");
				result.setMessage("您的登录信息已经过期,或者已经在其它终端登录,请重新登录");
				return result;
			}
			HashMap<String, Object> map = new HashMap<>();
			if (company.getLogoPicid() != null && company.getLogoPicid() > 0) {
				map.put("picurl", getFilePathById(company.getLogoPicid()));
			} else {
			}
			map.put("score", extra.getScore());
			List<CommnetValue> valueList = new ArrayList<>();
			map.put("commentList", valueList);
			List<Aunt> auntList = auntMapper.selectAnutsByCompanyId(company.getCompanyid());
			if (auntList != null && auntList.size() > 0) {
				String str = "";
				int count = 0;
				for (Aunt aunt : auntList) {
					if (count == 0) {
						str += aunt.getAuntid() + "";
					} else {
						str += "," + aunt.getAuntid();
					}
					count++;
				}
				str = "(" + str + ")";
				List<OrderComment> commnetList = orderCommentMapper.selectByAuntIdsAndPage(str, (page - 1) * 10, 10);
				List<OrderComment> commnetListMore = orderCommentMapper.selectByAuntIdsAndPage(str, (page) * 10, 10);
				if (commnetListMore != null && commnetListMore.size() > 0) {
					map.put("havemore", 1);
				} else {
					map.put("havemore", 0);
				}
				for (OrderComment orderComment : commnetList) {
					CommnetValue value = new CommnetValue();
					User user = userMapper.selectByPrimaryKey(orderComment.getUserid());
					if (user.getAvatar() != null && user.getAvatar() > 0) {
						value.setAvatarurl(getFilePathById(user.getAvatar()));
					} else {
						value.setAvatarurl(user.getThirdAvatar());
					}
					value.setName(user.getRealName());
					value.setOrderid(orderComment.getOrderid());
					value.setStae(2);
					value.setTime(CommonUtils.getTimeFormat(orderComment.getAddtime(), null));
					value.setContent(orderComment.getContent());
					Order order = orderMapper.selectByPrimaryKey(orderComment.getOrderid());
					CategorySecond second = categorySecondMapper.selectByPrimaryKey(order.getCategoryid());
					if (null != second) {
						value.setServer_type(second.getName());
					}
					valueList.add(value);
				}
			}
			result.setCode("1");
			result.setMessage("请求成功");
			result.setResult(map);
			return result;
		}

		return null;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult changeInfo(Aunt aunt, String key, String value) {
		ApiResult result = new ApiResult();
		int keyI = Integer.parseInt(key);
		if (keyI == 1) {
			aunt.setRealName(value);
		} else if (keyI == 2) {
			aunt.setSex(value);
		} else if (keyI == 3) {
			aunt.setOriginPlace(value);
		} else if (keyI == 4) {
			aunt.setIdcardNum(value);
		} else if (keyI == 5) {
			aunt.setNation(value);
		} else if (keyI == 6) {
			aunt.setWorkYear(Byte.parseByte(value));
		} else if (keyI == 7) {
			String[] ids = value.split(",");
			if (ids != null) {
				auntSkillMapper.deleteByAuntid(aunt.getAuntid());
				for (int i = 0; i < ids.length; i++) {
					if (!CommonUtils.isEmptyString(ids[i])) {
						int id = Integer.parseInt(ids[i]);
						AuntSkill skill = new AuntSkill();
						skill.setAddtime(new Date());
						skill.setAuntid(aunt.getAuntid());
						skill.setCategoryid(id);
						skill.setThirdid(0);
						auntSkillMapper.insert(skill);
					}
				}
			}
		} else if (keyI == 8) {
			aunt.setTrainState(Byte.parseByte(value));
		} else if (keyI == 9) {
			aunt.setLanguage(value);
		} else if (keyI == 10) {
			aunt.setCharacters(value);
		} else if (keyI == 11) {
			aunt.setNowAddress(value);
		} else if (keyI == 12) {
			aunt.setCulture(value);
		} else if (keyI == 13) {
			aunt.setHomeAddress(value);
		} else if (keyI == 14) {
			aunt.setReligion(value);
		} else if (keyI == 15) {
			aunt.setPolitical(value);
		} else if (keyI == 16) {
			aunt.setHeight(Float.parseFloat(value));
		} else if (keyI == 17) {
			aunt.setWeight(Float.parseFloat(value));
		} else if (keyI == 18) {
			aunt.setMarriage(value);
		} else if (keyI == 19) {
			aunt.setBloodType(value);
		} else if (keyI == 20) {
			auntDisplayMapper.deleteByAuntid(aunt.getAuntid());
			List<DisplayModel> displays = new Gson().fromJson(value, new TypeToken<List<DisplayModel>>() {
			}.getType());
			if (displays != null && displays.size() > 0) {
				for (DisplayModel model : displays) {
					AuntDisplay auntDisplay = new AuntDisplay();
					auntDisplay.setAuntid(aunt.getAuntid());
					auntDisplay.setCertificateName(model.getCertificate_name());
					auntDisplay.setSkillName(model.getSkill_name());
					auntDisplay.setPicid(model.getPicid());
					auntDisplayMapper.insert(auntDisplay);
					
					String picUrl = getFilePathById(model.getPicid());
					if(!CommonUtils.isEmptyString(picUrl)){
						model.setPicurl(picUrl);
					}	
				}
				value = new Gson().toJson(displays);
			}
			
			
			
		} else if (keyI == 21) {
			aunt.setWorkHis(value);
		} else if (keyI == 22) {
			aunt.setSelfComment(value);
		} else if (keyI == 23) {
			aunt.setHobby(value);
		} else if (keyI == 24) {
			aunt.setAvatar(CommonUtils.parseInt(value, 0));
		} else if(keyI == 25){
			aunt.setIdcard_picids(value);
		}else {
			
		}
		auntMapper.updateByPrimaryKey(aunt);
		// 资料完整度
		Integer count = getHaveDone(aunt);
		AuntPointProgress auntPointProgress = auntPointProgressMapper.selectByAuntid(aunt.getAuntid());
		AuntExtra auntExtra = auntExtramapper.selectByAuntId(aunt.getAuntid());
		if (auntPointProgress == null) {
			AuntPointProgress add = new AuntPointProgress();
			add.setAddtime(new Date());
			add.setAuntid(aunt.getAuntid());
			add.setAuntInfo(count.byteValue());
			add.setVideoIds("");
			add.setVideoTimes((byte) 0);
			auntPointProgressMapper.insert(add);
			if (auntExtra != null) {
				AuntPointRecord auntPointRecord = new AuntPointRecord();
				auntPointRecord.setAddtime(new Date());
				auntPointRecord.setAuntid(aunt.getAuntid());
				auntPointRecord.setType((byte) 8);
				// 50% < 资料完整度 < 80%
				if ((new BigDecimal(String.valueOf(count)).divide(new BigDecimal("23"), 2, BigDecimal.ROUND_UP)
						.compareTo(new BigDecimal(50)) == 1
						&& new BigDecimal(String.valueOf(count)).divide(new BigDecimal("23"), 2, BigDecimal.ROUND_UP).compareTo(new BigDecimal(80)) == -1)) {
					// 用户信息
					auntExtra.setPoint(auntExtra.getPoint() + 50);
					auntExtra.setPointTotal(auntExtra.getPointTotal() + 50);
					auntExtramapper.updateByPrimaryKey(auntExtra);
					// 阿姨积分记录
					auntPointRecord.setCount(50);
					auntPointRecordMapper.updateByPrimaryKey(auntPointRecord);
				}
				// 80% < 资料完整度 < 100%
				if ((new BigDecimal(String.valueOf(count)).divide(new BigDecimal("23"), 2, BigDecimal.ROUND_UP).compareTo(new BigDecimal("80")) == 1
						&& new BigDecimal(String.valueOf(count)).divide(new BigDecimal("23"), 2, BigDecimal.ROUND_UP).compareTo(new BigDecimal("100")) == -1)) {
					// 用户信息
					auntExtra.setPoint(auntExtra.getPoint() + 80);
					auntExtra.setPointTotal(auntExtra.getPointTotal() + 80);
					auntExtramapper.updateByPrimaryKey(auntExtra);
					// 阿姨积分记录
					auntPointRecord.setCount(80);
					auntPointRecordMapper.updateByPrimaryKey(auntPointRecord);
				}
				// 资料完整度 = 100%
				if ((new BigDecimal(String.valueOf(count)).divide(new BigDecimal("23"), 2, BigDecimal.ROUND_UP).compareTo(new BigDecimal("100")) == 0)) {
					// 用户信息
					auntExtra.setPoint(auntExtra.getPoint() + 100);
					auntExtra.setPointTotal(auntExtra.getPointTotal() + 100);
					auntExtramapper.updateByPrimaryKey(auntExtra);
					// 阿姨积分记录
					auntPointRecord.setCount(100);
					auntPointRecordMapper.updateByPrimaryKey(auntPointRecord);
				}
				// 用户等级
				AuntLevelSet levelSet = auntLevelSetMapper.setlectSetByPoint(auntExtra.getPointTotal());
				if (levelSet != null && levelSet.getLevel() != aunt.getLevel()) {
					aunt.setLevel(levelSet.getLevel());
					auntMapper.updateByPrimaryKey(aunt);
				}
			}
		} else if (auntPointProgress.getAuntInfo() < count) {
			AuntPointRecord auntPointRecord = new AuntPointRecord();
			auntPointRecord.setAddtime(new Date());
			auntPointRecord.setAuntid(aunt.getAuntid());
			auntPointRecord.setType((byte) 8);
			// 已填过的少于50%，现在变成50-80之间的情况
			if (new BigDecimal(auntPointProgress.getAuntInfo()).divide(new BigDecimal(23), 2, BigDecimal.ROUND_UP)
					.compareTo(new BigDecimal(50)) == -1
					&& new BigDecimal(count).divide(new BigDecimal(23), 2, BigDecimal.ROUND_UP)
							.compareTo(new BigDecimal(80)) == -1
					&& new BigDecimal(count).divide(new BigDecimal(23), 2, BigDecimal.ROUND_UP)
							.compareTo(new BigDecimal(50)) == 1) {
				// 用户信息
				auntExtra.setPoint(auntExtra.getPoint() + 50);
				auntExtra.setPointTotal(auntExtra.getPointTotal() + 50);
				auntExtramapper.updateByPrimaryKey(auntExtra);
				// 阿姨积分记录
				auntPointRecord.setCount(50);
				auntPointRecordMapper.updateByPrimaryKey(auntPointRecord);
			}
			// 已填过的大于50%，现在变成80-100之间的情况
			if (new BigDecimal(auntPointProgress.getAuntInfo()).divide(new BigDecimal(23), 2, BigDecimal.ROUND_UP)
					.compareTo(new BigDecimal(50)) == 1
					&& new BigDecimal(count).divide(new BigDecimal(23), 2, BigDecimal.ROUND_UP)
							.compareTo(new BigDecimal(80)) == 1
					&& new BigDecimal(count).divide(new BigDecimal(23), 2, BigDecimal.ROUND_UP)
							.compareTo(new BigDecimal(100)) == -1) {
				// 用户信息
				auntExtra.setPoint(auntExtra.getPoint() + 30);
				auntExtra.setPointTotal(auntExtra.getPointTotal() + 30);
				auntExtramapper.updateByPrimaryKey(auntExtra);
				// 阿姨积分记录
				auntPointRecord.setCount(30);
				auntPointRecordMapper.updateByPrimaryKey(auntPointRecord);
			}
			// 已填过的大于80%，现在变成100%之间的情况
			if (new BigDecimal(auntPointProgress.getAuntInfo()).divide(new BigDecimal(23), 2, BigDecimal.ROUND_UP)
					.compareTo(new BigDecimal(80)) == 1
					&& new BigDecimal(count).divide(new BigDecimal(23), 2, BigDecimal.ROUND_UP)
							.compareTo(new BigDecimal(100)) == 0) {
				// 用户信息
				auntExtra.setPoint(auntExtra.getPoint() + 20);
				auntExtra.setPointTotal(auntExtra.getPointTotal() + 20);
				auntExtramapper.updateByPrimaryKey(auntExtra);
				// 阿姨积分记录
				auntPointRecord.setCount(20);
				auntPointRecordMapper.updateByPrimaryKey(auntPointRecord);
			}
			// 用户等级
			AuntLevelSet levelSet = auntLevelSetMapper.setlectSetByPoint(auntExtra.getPointTotal());
			if (levelSet != null && levelSet.getLevel() != aunt.getLevel()) {
				aunt.setLevel(levelSet.getLevel());
				auntMapper.updateByPrimaryKey(aunt);
			}
		}
		HashMap<String, Object> map = new HashMap<>();
		map.put("ratio1", getRatio1(aunt));
		map.put("ratio2", getRatio2(aunt));
		map.put("key", key);
		map.put("value", value);
		result.setCode("1");
		result.setMessage("请求成功");
		result.setResult(map);
		return result;
	}

	@Override
	public ApiResult getBalanceRecord(int page, AuntExtra extra) {
		ApiResult result = new ApiResult();
		HashMap<String, Object> map = new HashMap<>();
		float balance = CommonUtils.parseFloat(extra.getBalance().setScale(2, BigDecimal.ROUND_UP).toPlainString(), 0);
		map.put("balance", balance);

		List<AuntBalanceModel> balanceList = new ArrayList<>();
		List<AuntBalanceRecord> recordList = auntBalanceRecordMapper.selectRecordByPage(extra.getAuntid(), 0,
				(page - 1) * 10, 10);

		if (recordList != null && recordList.size() > 0) {
			for (AuntBalanceRecord auntBalanceRecord : recordList) {
				AuntBalanceModel model = new AuntBalanceModel();
				model.setCount(auntBalanceRecord.getCount().floatValue());
				model.setTime(CommonUtils.getTimeFormat(auntBalanceRecord.getAddtime(), "yyyy-MM-dd"));

				model.setType(auntBalanceRecord.getType());
				if (model.getType() == 1) {
					model.setTitle("提现");
				} else if (model.getType() == 2) {
					model.setTitle("收入");
				} else {
					model.setTitle("红包");
				}
				balanceList.add(model);
			}

			Long count = auntBalanceRecordMapper.selectRecordByPageCount(extra.getAuntid(), 0);
			if (count != null && count > page * 10) {
				map.put("havemore", 1);
			} else {
				map.put("havemore", 0);
			}
		} else {
			map.put("havemore", 0);
		}

		map.put("balanceList", balanceList);
		result.setCode("1");
		result.setMessage("请求成功");
		result.setResult(map);
		return result;
	}

	@Override
	public ApiResult getBalanceRecord(int page, CompanyExtra extra) {
		ApiResult result = new ApiResult();
		HashMap<String, Object> map = new HashMap<>();
		float balance = extra.getBalance().setScale(2, BigDecimal.ROUND_UP).floatValue();
		map.put("balance", balance);

		List<AuntBalanceModel> balanceList = new ArrayList<>();
		List<AuntBalanceRecord> recordList = auntBalanceRecordMapper.selectRecordByPage(extra.getCompanyid(), 1,
				(page - 1) * 10, 10);

		if (recordList != null && recordList.size() > 0) {
			for (AuntBalanceRecord auntBalanceRecord : recordList) {
				AuntBalanceModel model = new AuntBalanceModel();
				model.setCount(auntBalanceRecord.getCount().floatValue());
				model.setTime(CommonUtils.getTimeFormat(auntBalanceRecord.getAddtime(), "yyyy-MM-dd"));

				model.setType(auntBalanceRecord.getType());
				if (model.getType() == 1) {
					model.setTitle("提现");
				} else if (model.getType() == 2) {
					model.setTitle("收入");
				} else {
					model.setTitle("红包");
				}
				balanceList.add(model);
			}

			Long count = auntBalanceRecordMapper.selectRecordByPageCount(extra.getCompanyid(), 1);
			if (count != null && count > page * 10) {
				map.put("havemore", 1);
			} else {
				map.put("havemore", 0);
			}
		} else {
			map.put("havemore", 0);
		}

		map.put("balanceList", balanceList);
		result.setCode("1");
		result.setMessage("请求成功");
		result.setResult(map);
		return result;
	}

	@Override
	public ApiResult getPointRecord(Aunt aunt, int page) {
		ApiResult result = new ApiResult();
		AuntExtra extra = auntExtramapper.selectByAuntId(aunt.getAuntid());
		HashMap<String, Object> map = new HashMap<>();
		List<AuntPointRecord> records = auntPointRecordMapper.selectByPage((page - 1) * 10, 10, aunt.getAuntid());
		List<AuntPointRecord> recordsMore = auntPointRecordMapper.selectByPage((page) * 10, 10, aunt.getAuntid());
		if (recordsMore != null && recordsMore.size() > 0) {
			map.put("havemore", 1);
		} else {
			map.put("havemore", 0);
		}
		map.put("point", extra.getPoint());
		List<BalanceValue> valueList = new ArrayList<>();
		for (AuntPointRecord balanceRecord : records) {
			BalanceValue value = new BalanceValue();
			if (1 == balanceRecord.getType()) {
				value.setTitle("注册赠送");
				value.setType(balanceRecord.getType());
			} else if (2 == balanceRecord.getType()) {
				value.setTitle("完善用户信息赠送");
				value.setType(balanceRecord.getType());
			} else if (3 == balanceRecord.getType()) {
				value.setTitle("积分商城奖品抵扣");
				value.setType(balanceRecord.getType());
			} else if (4 == balanceRecord.getType()) {
				value.setTitle("签到增加");
				value.setType(balanceRecord.getType());
			}else if(5 == balanceRecord.getType()){
				value.setTitle("订单奖励");
				value.setType(balanceRecord.getType());
			}else if(8 == balanceRecord.getType()){
				value.setTitle("其它");
				value.setType(balanceRecord.getType());
			}else if(0 == balanceRecord.getType()){
				value.setTitle("其它");
				value.setType(balanceRecord.getType());
			}
			value.setTime(CommonUtils.getTimeFormat(balanceRecord.getAddtime(), "yyyy-MM-dd HH:mm:ss"));
			value.setCount(balanceRecord.getCount());
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
	public ApiResult setAliInfo(String userid, String user_type, String account, String name) {
		ApiResult result = new ApiResult();
		int userType = Integer.parseInt(user_type);
		if (userType == 0) {
			auntCardMapper.deleteByAuntid(Integer.parseInt(userid), 0);
			AuntCard auntCard = new AuntCard();
			auntCard.setAccount(account);
			auntCard.setName(name);
			auntCard.setUserType((byte) 0);
			auntCard.setAuntid(Integer.parseInt(userid));
			auntCard.setAddtime(new Date());
			auntCardMapper.insert(auntCard);
		} else if (userType == 1) {
			auntCardMapper.deleteByAuntid(Integer.parseInt(userid), 1);
			AuntCard auntCard = new AuntCard();
			auntCard.setAccount(account);
			auntCard.setName(name);
			auntCard.setUserType((byte) 1);
			auntCard.setAuntid(Integer.parseInt(userid));
			auntCard.setAddtime(new Date());
			auntCardMapper.insert(auntCard);

		} else {

		}
		HashMap<String, Object> map = new HashMap<>();
		AliInfo aliInfo = new AliInfo();
		aliInfo.setName(name);
		aliInfo.setAccount(account);
		map.put("aliInfo", aliInfo);
		result.setCode("1");
		result.setMessage("请求成功");
		result.setResult(map);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult cash(String userid, String user_type, String type, String count) {
		ApiResult result = new ApiResult();
		HashMap<String, Object> map = new HashMap<>();
		int userType = Integer.parseInt(user_type);
		if (userType == 0) {
			AuntExtra extra = auntExtramapper.selectByAuntId(Integer.parseInt(userid));
			BigDecimal balance = extra.getBalance();
			if (balance.compareTo(new BigDecimal(count)) == -1) {
				result.setCode("1");
				result.setMessage("余额不足无法提现");
				result.setResult(map);
			}
			BigDecimal decimal = balance.subtract(new BigDecimal(count));
			extra.setBalance(decimal);
			auntExtramapper.updateByPrimaryKey(extra);
			AuntCash cash = new AuntCash();
			AuntCard card = auntCardMapper.selectByAuntidAndType(Integer.parseInt(userid), 0);
			cash.setAccount(card.getAccount());
			cash.setAddtime(new Date());
			cash.setUserid(Integer.parseInt(userid));
			cash.setUserType((byte) 0);
			cash.setMoney(new BigDecimal(count));
			cash.setType(Byte.parseByte(type));
			cash.setName(card.getName());
			cash.setState(Byte.parseByte("0"));
			cash.setAdminid(0);
			auntCashMapper.insert(cash);
			map.put("balance", decimal.setScale(2, BigDecimal.ROUND_UP).floatValue());

		} else if (userType == 1) {

			CompanyExtra companyExtra = companyExtraMapper.selectByCompanyId(Integer.parseInt(userid));
			BigDecimal balance = companyExtra.getBalance();
			if (balance.compareTo(new BigDecimal(count)) == -1) {
				result.setCode("1");
				result.setMessage("余额不足无法提现");
				result.setResult(map);
			}
			BigDecimal decimal = balance.subtract(new BigDecimal(count));
			companyExtra.setBalance(decimal);
			companyExtraMapper.updateByPrimaryKey(companyExtra);

			AuntCash cash = new AuntCash();
			AuntCard card = auntCardMapper.selectByAuntidAndType(Integer.parseInt(userid), 1);
			cash.setAccount(card.getAccount());
			cash.setAddtime(new Date());
			cash.setUserid(Integer.parseInt(userid));
			cash.setUserType((byte) 0);
			cash.setMoney(new BigDecimal(count));
			cash.setType(Byte.parseByte(type));
			cash.setName(card.getName());
			cash.setState(Byte.parseByte("1"));
			cash.setAdminid(0);
			auntCashMapper.insert(cash);
			map.put("balance", decimal.setScale(2, BigDecimal.ROUND_UP).floatValue());

		}

		result.setCode("1");
		result.setMessage("请求成功");
		result.setResult(map);
		return result;
	}

	@Override
	public ApiResult refreshInfo(Aunt aunt, AuntExtra extra) {
		ApiResult result = new ApiResult();
		HashMap<String, Object> map = new HashMap<>();
		map.put("real_name", aunt.getRealName());

		String avatarurl = getFilePathById(aunt.getAvatar());
		if (!CommonUtils.isEmptyString(avatarurl)) {
			map.put("avatarurl", avatarurl);
		} else {
			if (!CommonUtils.isEmptyString(aunt.getThirdAvatar())) {
				map.put("avatarurl", aunt.getThirdAvatar());
			} else {
				map.put("avatarurl", "");
			}
		}
		map.put("level", aunt.getLevel());
		AuntLevelSet set = auntLevelSetMapper.setlectSetByLevel(aunt.getLevel());
		if (set != null) {
			map.put("levelname", set.getTitle());
		} else {
			map.put("levelname", "");
		}
		map.put("point", extra.getPoint());
		map.put("balance", extra.getBalance().floatValue());
		map.put("work_year", aunt.getWorkYear());

		Calendar now = Calendar.getInstance();
		if (extra.getLastSign() != null) {
			String dateStr1 = CommonUtils.getTimeFormat(extra.getLastSign(), "yyyy-MM-dd");
			String dateStr2 = CommonUtils.getTimeFormat(now.getTime(), "yyyy-MM-dd");
			if (dateStr1.equals(dateStr2)) {
				map.put("today_sign", 1);
			} else {
				map.put("today_sign", 0);
			}
		} else {
			map.put("today_sign", 0);
		}

		// 本周连续签到天数
		now.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		String dateStrMonday = CommonUtils.getTimeFormat(now.getTime(), "yyyy-MM-dd");
		now.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		String dateStrSunday = CommonUtils.getTimeFormat(now.getTime(), "yyyy-MM-dd");

		Long count = signRecordAuntMapper.selectWeekSignCount(dateStrMonday, dateStrSunday, aunt.getAuntid());
		if (count == null) {
			map.put("sign_week", 0);
		} else {
			map.put("sign_week", count.intValue());
		}

		result.setCode("1");
		result.setMessage("请求成功");
		result.setResult(map);
		return result;
	}

	@Override
	public ApiResult getTutorialList(int page) {
		ApiResult result = new ApiResult();
		HashMap<String, Object> map = new HashMap<>();
		ArrayList<TutorialValue> valueList = new ArrayList<>();
		List<Tutorials> tutorialList = tutorialsmapper.selectByPage((page - 1) * 10, 10);
		List<Tutorials> tutorialListMore = tutorialsmapper.selectByPage((page) * 10, 10);
		if (tutorialListMore != null && tutorialListMore.size() > 0) {
			map.put("havemore", 1);
		} else {
			map.put("havemore", 0);
		}
		if (tutorialList != null && tutorialList.size() > 0) {
			for (Tutorials tutorials : tutorialList) {
				TutorialValue value = new TutorialValue();
				value.setDataid(tutorials.getDataid());
				value.setTitle(tutorials.getTitle());
				if (tutorials.getPicid() != null && tutorials.getPicid() > 0) {
					value.setPicurl(getFilePathById(tutorials.getPicid()));
				}
				if (tutorials.getVideoid() != null && tutorials.getVideoid() > 0) {
					value.setVideourl(getFilePathById(tutorials.getVideoid()));
				}
				if (tutorials.getDuration() != null) {
					value.setDuration(tutorials.getDuration().intValue());
				}
				valueList.add(value);
			}

		}
		map.put("tutorialList", valueList);
		result.setCode("1");
		result.setMessage("请求成功");
		result.setResult(map);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult uoloadViewingTime(String userid, String tutorialid) {
		ApiResult result = new ApiResult();
		Tutorials tutorials = tutorialsmapper.selectByPrimaryKey(CommonUtils.parseInt(tutorialid, 0));
		if (tutorials == null) {
			result.setCode("2");
			result.setMessage("教程不存在");
			return result;
		}
		if (tutorials.getState() == 1) {
			result.setCode("2");
			result.setMessage("教程已过期");
			return result;
		}
		// 阿姨积分获取进度记录
		Calendar now = Calendar.getInstance();
		String nowDay = CommonUtils.getTimeFormat(now.getTime(), "yyyy-MM-dd");

		AuntPointProgress auntPointProgress = auntPointProgressMapper.selectByAuntid(CommonUtils.parseInt(userid, 0));
		if (auntPointProgress != null) {
			String lookDay = "";
			if (auntPointProgress.getUpdatetime() != null) {
				lookDay = CommonUtils.getTimeFormat(auntPointProgress.getUpdatetime(), "yyyy-MM-dd");
			}
			// 今日达到次数
			if (nowDay.equals(lookDay) && auntPointProgress.getVideoTimes() == 3) {
				result.setCode("2");
				result.setMessage("今日可看次数已达3次！");
				return result;
			}
			String[] ids = auntPointProgress.getVideoIds().split(",");
			List<String> list = Arrays.asList(ids);
			if (!list.contains(tutorialid)) {
				auntPointProgress.setVideoIds(auntPointProgress.getVideoIds() + "," + tutorialid);
			}
			// 今日已观看过
			if (nowDay.equals(lookDay)) {
				auntPointProgress.setVideoTimes((byte) (auntPointProgress.getVideoTimes() + 1));
			} else {// 今日没观看过
				auntPointProgress.setVideoTimes((byte) 1);
				auntPointProgress.setUpdatetime(new Date());
			}
			auntPointProgressMapper.updateByPrimaryKey(auntPointProgress);
		} else {
			AuntPointProgress add = new AuntPointProgress();
			add.setAddtime(new Date());
			add.setAuntid(CommonUtils.parseInt(userid, 0));
			add.setAuntInfo((byte) 0);
			add.setUpdatetime(new Date());
			add.setVideoIds(tutorialid);
			add.setVideoTimes((byte) 1);
			auntPointProgressMapper.insert(add);
		}

		result.setCode("1");
		result.setMessage("请求成功");
		return result;
	}

	@Override
	public ApiResult updateSet(CompanyExtra extra, String voice_statem, String text_size) {
		ApiResult result = new ApiResult();
		if (!CommonUtils.isEmptyString(voice_statem)) {
			extra.setVoiceSet(CommonUtils.parseShort(voice_statem, (short) 0));
		}
		if (!CommonUtils.isEmptyString(text_size)) {
			extra.setFontSet(CommonUtils.parseShort(text_size, (short) 1));
		}

		companyExtraMapper.updateByPrimaryKey(extra);

		HashMap<String, Object> map = new HashMap<>();
		if (extra.getVoiceSet() != null) {
			map.put("voice_state", extra.getVoiceSet());
		} else {
			map.put("voice_state", 0);
		}

		if (extra.getFontSet() != null) {
			map.put("text_size", extra.getFontSet());
		} else {
			map.put("voice_state", 1);
		}

		result.setCode("1");
		result.setMessage("请求成功");
		result.setResult(map);
		return result;
	}

	private int getRatio1(Aunt aunt) {
		BigDecimal decimal = new BigDecimal("100");
		decimal = decimal.divide(new BigDecimal(Constant.MUST_INFO_RATIO), 2, BigDecimal.ROUND_UP);
		int count = 0;
		if (!CommonUtils.isEmptyString(aunt.getRealName())) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getSex())) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getOriginPlace())) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getIdcardNum())) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getNation())) {
			count++;
		}
		if (aunt.getWorkYear() != null) {
			count++;
		}
		List<AuntSkill> skillList = auntSkillMapper.selectByAuntIdTo(aunt.getAuntid());
		if (skillList != null && skillList.size() > 0) {
			count++;
		}
		if (aunt.getTrainState() != null) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getLanguage())) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getCharacters())) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getNowAddress())) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getIdcard_picids())) {
			count++;
		}
		decimal = decimal.multiply(new BigDecimal("" + count));
		int ration = decimal.intValue();
		return ration;
	}

	private int getRatio2(Aunt aunt) {
		BigDecimal decimal = new BigDecimal("100");
		decimal = decimal.divide(new BigDecimal(Constant.OPTIONALINFO), 2, BigDecimal.ROUND_UP);
		int count = 0;
		if (!CommonUtils.isEmptyString(aunt.getCulture())) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getHomeAddress())) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getReligion())) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getPolitical())) {
			count++;
		}
		if (aunt.getHeight() != null) {
			count++;
		}
		if (aunt.getWeight() != null) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getMarriage())) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getBloodType())) {
			count++;
		}
		List<AuntDisplay> displayList = auntDisplayMapper.selectByAuntId(aunt.getAuntid());
		if (displayList != null && displayList.size() > 0) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getWorkHis())) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getSelfComment())) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getHobby())) {
			count++;
		}
		decimal = decimal.multiply(new BigDecimal("" + count));
		int ration = decimal.intValue();
		return ration;
	}

	private int getHaveDone(Aunt aunt) {
		int count = 0;
		if (!CommonUtils.isEmptyString(aunt.getRealName())) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getSex())) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getOriginPlace())) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getIdcardNum())) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getNation())) {
			count++;
		}
		if (aunt.getWorkYear() != null) {
			count++;
		}
		List<AuntSkill> skillList = auntSkillMapper.selectByAuntIdTo(aunt.getAuntid());
		if (skillList != null && skillList.size() > 0) {
			count++;
		}
		if (aunt.getTrainState() != null) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getLanguage())) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getCharacters())) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getNowAddress())) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getCulture())) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getHomeAddress())) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getReligion())) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getPolitical())) {
			count++;
		}
		if (aunt.getHeight() != null) {
			count++;
		}
		if (aunt.getWeight() != null) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getMarriage())) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getBloodType())) {
			count++;
		}
		List<AuntDisplay> displayList = auntDisplayMapper.selectByAuntId(aunt.getAuntid());
		if (displayList != null && displayList.size() > 0) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getWorkHis())) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getSelfComment())) {
			count++;
		}
		if (!CommonUtils.isEmptyString(aunt.getHobby())) {
			count++;
		}
		return count;
	}

	@Override
	public ApiResult logout(AuntExtra extra) {
		extra.setState((short) 1);
		auntExtramapper.updateByPrimaryKey(extra);
		ApiResult result = new ApiResult();
		result.setCode("1");
		return result;
	}

	@Override
	public ApiResult logout(CompanyExtra extra) {
		extra.setState((short) 1);
		companyExtraMapper.updateByPrimaryKey(extra);
		ApiResult result = new ApiResult();
		result.setCode("1");
		return result;
	}

	@Override
	public ApiResult setPassword(Aunt aunt, String password) {
		ApiResult result = new ApiResult();
		aunt.setPassword(password);
		auntMapper.updateByPrimaryKey(aunt);

		HashMap<String, Object> map = new HashMap<>();
		map.put("haspassword", 1);

		result.setCode("1");
		result.setMessage("设置成功");
		result.setResult(map);
		return result;
	}

	@Override
	public ApiResult invite(Aunt aunt, String intive_code) {
		ApiResult result = new ApiResult();
		if(aunt.getInvitationed_state() == 1){
			result.setCode("2");
			result.setMessage("用户已被邀请");
		}
		Aunt inViteAunt = auntMapper.selectUserByCode(intive_code);
		if(inViteAunt == null){
			result.setCode("3");
			result.setMessage("邀请码错误");
		}
		Integer count = inViteAunt.getInvitation_count();
		if(count == null){
			count = 0;
		}
		count ++;
		inViteAunt.setInvitation_count(count);
		auntMapper.updateByPrimaryKey(inViteAunt);
		aunt.setInvitationed_state((short)1);
		auntMapper.updateByPrimaryKey(aunt);
		
		Invitation invitation = new Invitation();
		invitation.setUserid(inViteAunt.getAuntid());
		invitation.setInviteduserid(aunt.getAuntid());
		invitation.setAddtime(new Date());
		invitationMapper.insert(invitation);
		result.setCode("1");
		result.setMessage("请求成功");
		//AuntPointProgress pointProgressToday = auntPointProgressMapper.selectByAuntidToday(inViteAunt.getAuntid());
		AuntPointRecord pointRecord = new AuntPointRecord();
		pointRecord.setAddtime(new Date());
		pointRecord.setAuntid(inViteAunt.getAuntid());
		pointRecord.setType((byte)8);
		Config config = configMapper.selectConfigByKey(Constant.SHARE_APP_POINT_USER);
		pointRecord.setCount(CommonUtils.parseInt(config.getConfigvalue(), 0));
		auntPointRecordMapper.insert(pointRecord);
		AuntExtra extra = auntExtramapper.selectByAuntId(inViteAunt.getAuntid());
		int point = extra.getPoint();
		point += CommonUtils.parseInt(config.getConfigvalue(), 0);
    	extra.setPoint(point);
    	int pointTotal = extra.getPointTotal();
    	pointTotal += CommonUtils.parseInt(config.getConfigvalue(), 0);
    	extra.setPointTotal(pointTotal);
    	auntExtramapper.updateByPrimaryKey(extra);
		
		/*if(null != pointProgressToday){
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
		}*/
		
		return result;
	}

	@Override
	public ApiResult receiveRedPacket(Aunt aunt, Company company, String packetid) {
		ApiResult result = new ApiResult();
		if(aunt != null){
			AuntExtra auntExtra = auntExtramapper.selectByAuntId(aunt.getAuntid());
			RedPacketRecord redPackRecord = redPacketRecordMapper.selectByPrimaryKey(CommonUtils.parseInt(packetid, 0));
			if(redPackRecord != null){
				if(redPackRecord.getState() == 1){
					result.setCode("3");
					result.setMessage("红包已领取");
					return result;
				}
				redPackRecord.setUserid(aunt.getAuntid());
				redPackRecord.setState((byte)1);
				redPacketRecordMapper.updateByPrimaryKey(redPackRecord);
				
				BigDecimal balance = auntExtra.getBalance();
				balance = balance.add(redPackRecord.getCount());
				auntExtra.setBalance(balance);
				
				balance = auntExtra.getUseTotal();
				balance = balance.add(redPackRecord.getCount());
				auntExtra.setUseTotal(balance);
				auntExtramapper.updateByPrimaryKey(auntExtra);
				AuntBalanceRecord auntBalanceRecord = new AuntBalanceRecord();
				auntBalanceRecord.setAddtime(new Date());
				auntBalanceRecord.setCount(redPackRecord.getCount());
				auntBalanceRecord.setUserid(aunt.getAuntid());
				auntBalanceRecord.setType((byte)5);
				auntBalanceRecord.setUserType((byte)0);
				auntBalanceRecordMapper.insert(auntBalanceRecord);
				
			}else {
				result.setCode("2");
				result.setMessage("红包不存在");
				return result;
				
			}
			
			
		}else if(company != null){
			CompanyExtra companyExtra = companyExtraMapper.selectByCompanyId(company.getCompanyid());
			RedPacketRecord redPackRecord = redPacketRecordMapper.selectByPrimaryKey(CommonUtils.parseInt(packetid, 0));
			if(redPackRecord != null){
				if(redPackRecord.getState() == 1){
					result.setCode("3");
					result.setMessage("红包已领取");
					return result;
				}
				redPackRecord.setUserid(company.getCompanyid());
				redPackRecord.setType((byte)1);
				redPacketRecordMapper.updateByPrimaryKey(redPackRecord);
				
				BigDecimal balance = companyExtra.getBalance();
				balance = balance.add(redPackRecord.getCount());
				companyExtra.setBalance(balance);
				
				balance = companyExtra.getUseTotal();
				balance = balance.add(redPackRecord.getCount());
				companyExtra.setUseTotal(balance);
				companyExtraMapper.updateByPrimaryKey(companyExtra);
				
				AuntBalanceRecord auntBalanceRecord = new AuntBalanceRecord();
				auntBalanceRecord.setAddtime(new Date());
				auntBalanceRecord.setCount(redPackRecord.getCount());
				auntBalanceRecord.setUserid(company.getCompanyid());
				auntBalanceRecord.setType((byte)5);
				auntBalanceRecord.setUserType((byte)1);
				auntBalanceRecordMapper.insert(auntBalanceRecord);
				
			}else {
				result.setCode("2");
				result.setMessage("红包不存在");
				return result;
				
			}
			
		}
		result.setCode("1");
		result.setMessage("请求成功");
		return result;
	}

	@Override
	public ApiResult redPacketList(Aunt aunt, Company company, String page) {
		ApiResult result = new ApiResult();
		//Integer pageInt = CommonUtils.parseInt(page, 0);
		if(aunt != null){
			List<RedPacketRecord> recordList = redPacketRecordMapper.selectByUserIdAndPage(aunt.getAuntid(), 1, CommonUtils.parseInt(page, 0) - 1, 10);
			List<RedPacketRecord> recordListMore = redPacketRecordMapper.selectByUserIdAndPage(aunt.getAuntid(), 1, CommonUtils.parseInt(page, 0) , 10);
			HashMap<String, Object> map = new HashMap<>();
			if(recordListMore != null && recordListMore.size()>0){
				map.put("haveMore", 1);
			}else {
				map.put("haveMore", 0);
			}
			map.put("packList", recordList);
			result.setResult(map);
			result.setCode("1");
			result.setMessage("请求成功");
			return result;
		}else if(company != null){
			List<RedPacketRecord> recordList = redPacketRecordMapper.selectByUserIdAndPage(company.getCompanyid(), 2, CommonUtils.parseInt(page, 0) - 1, 10);
			List<RedPacketRecord> recordListMore = redPacketRecordMapper.selectByUserIdAndPage(company.getCompanyid(), 2, CommonUtils.parseInt(page, 0), 10);
			HashMap<String, Object> map = new HashMap<>();
			if(recordListMore != null && recordListMore.size()>0){
				map.put("haveMore", 1);
			}else {
				map.put("haveMore", 0);
			}
			map.put("packList", recordList);
			result.setResult(map);
			result.setCode("1");
			result.setMessage("请求成功");
			return result;
			
		}
		result.setCode("1");
		result.setMessage("请求成功");
		return result;
	}

	@Override
	public ApiResult setCode() {
		ApiResult result = new ApiResult();
		List<Aunt> auntList = auntMapper.selectAllAunt();
		if(auntList != null && auntList.size()>0){
			for(Aunt aunt:auntList){
				aunt.setInvitation_count(0);
				aunt.setInvitation_code(CommonUtils.getAuntInvite(aunt.getAuntid()));
				aunt.setInvitationed_state((short)0);
				auntMapper.updateByPrimaryKey(aunt);
			}
		}
		result.setCode("1");
		result.setMessage("请求成功");
		return result;
	}
	
	

}
