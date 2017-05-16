package com.uncleserver.service.company.Impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.common.Constant;
import com.uncleserver.common.sms.RLYSmsUtils;
import com.uncleserver.dao.CompanyExtraMapper;
import com.uncleserver.dao.CompanyMapper;
import com.uncleserver.dao.CompanyRangeMapper;
import com.uncleserver.dao.ConfigMapper;
import com.uncleserver.dao.PointRecordMapper;
import com.uncleserver.dao.UserExtraMapper;
import com.uncleserver.dao.VCodeAuntMapper;
import com.uncleserver.model.CompanyExtra;
import com.uncleserver.model.CompanyWithBLOBs;
import com.uncleserver.model.Config;
import com.uncleserver.model.VCodeAunt;
import com.uncleserver.modelVo.ApiResult;
import com.uncleserver.service.company.RegistService;

@Service("registService")
public class RegistServiceImpl  implements RegistService{

	
	@Resource 
	private ConfigMapper configMapper;
	@Resource
	private UserExtraMapper userExtraMapper;
	@Resource
	private PointRecordMapper pointRecordMapper;
	@Resource
	private CompanyMapper companyMapper;
	@Resource
	private CompanyExtraMapper companyExtraMapper;
	@Resource
	private CompanyRangeMapper companyRangeMapper;
	@Resource 
	private VCodeAuntMapper vCodeAuntMapper;
	
	
	/**
	 * 检查是否短信超过今天最大次数
	 * @param phone
	 * @return
	 */
	public boolean checkCanSendVcode(String phone) {
		Config config = configMapper.selectConfigByKey(Constant.DAY_MAX_SMS);
		if (config != null) {
			int count = CommonUtils.parseInt(config.getConfigvalue(), 0);
			if (count > 0) {
				Long sendedcount = vCodeAuntMapper.setectTodaySendCount(phone);
				if (sendedcount != null && sendedcount >= count) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 获取短信
	 */
	public ApiResult getVCode(String phone,int type){
		ApiResult result = new ApiResult();
		boolean canSend = checkCanSendVcode(phone);
		if (!canSend) {
			result.setCode("2");
			result.setMessage("发送失败,该号码今天请求验证码次数超过上限");
			return result;
		}
		Long count = companyMapper.selectUserCountByPhone(phone);

		
		if (type == 0 || type == 3) {
			if (count != null && count > 0) {
				result.setCode("3");
				result.setMessage("发送失败,该手机号码已经注册");
				return result;
			}
		} 
		String vcode = CommonUtils.getRandomVcode();
		// 调用第三方发送验证码

		VCodeAunt vcodeModel = new VCodeAunt();
		vcodeModel.setAddtime(new Date());
		vcodeModel.setPhone(phone);
		vcodeModel.setType((byte) type);
		vcodeModel.setVcode(vcode);
		vcodeModel.setUserType((byte)1);
		vCodeAuntMapper.insertSelective(vcodeModel);

		result.setCode("1");
		result.setMessage("验证码发送成功");
		HashMap<String, Object> map = new HashMap<>();
		map.put("vcode", vcode);
		RLYSmsUtils.SendSms(phone, Constant.SMS_TEMPLATE, new String[] { vcode, "五分钟" });
		//result.setResult(map);
		return result;
	}
	
	public ApiResult registerUser(String phone, String password, String vcode, String companytype,String companyName,String companyAds,String sessionId){
		ApiResult result = new ApiResult();
		Long count = companyMapper.selectUserCountByPhone(phone);

		if (count != null && count > 0) {
			result.setCode("2");
			result.setMessage("注册失败,该手机号码已经注册");
			return result;
		}

		VCodeAunt vcodeModel = vCodeAuntMapper.selectLastVcodeByPhoneAndType(phone, 0,1);
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
		//CompanyWithBLOBs c =  companyMapper.
		CompanyWithBLOBs company = new CompanyWithBLOBs();
		company.setCompanyid(companyMapper.getMaxId()+1);
		company.setAddress(companyAds);
		company.setAddtime(new Date());
		company.setCity("无");
		company.setCompanyDetail("无");
		//company.setLoginAccount(phone);
		company.setLatitude(0.0d);
		company.setListpic(0);
		company.setLogoPicid(0);
		company.setLongitude(0.0d);
		company.setMainBusiness("无");
		company.setName(companyName);
		company.setName_letter("无");
		for(int i = 0;i<3;i++){
			password = CommonUtils.MD5(password);
		}
		company.setPassword(password);
		company.setPeopleCount(1);
		company.setPhone(phone);
		company.setPiclist("无");
		company.setProfile("无");
		company.setRelationPeople("无");
		company.setRelationPhone(phone);
		company.setStar((byte)1);
		company.setStateDel((byte)0);
		company.setTimeTrain((byte)0);
		company.setTitle((byte)1);
		company.setType((byte)CommonUtils.parseInt(companytype, 0));
		company.setYearCreate(new Date());
		companyMapper.insert(company);
		
		 
		CompanyExtra companyExtra = new CompanyExtra();
		String accessToken = CommonUtils.MD5(sessionId+company.getCompanyid());
		companyExtra.setDataid(companyExtraMapper.getMaxId()+1);
		companyExtra.setAccesstoken(CommonUtils.MD5(accessToken));
		companyExtra.setAddtime(new Date());
		companyExtra.setBalance(new BigDecimal("0.00"));
		companyExtra.setCompanyid(company.getCompanyid());
		companyExtra.setHeartbeatTime(null);
		companyExtra.setLastBuy(null);
		companyExtra.setLastDevicetype((short)1);
		companyExtra.setLastLogin(null);
		companyExtra.setScore(5.0f);
		companyExtra.setState((short)1);
		companyExtra.setTokenTime(new Date());
		companyExtra.setUseTotal(new BigDecimal("0.00"));
		companyExtra.setFontSet((short)2);
		companyExtra.setVoiceSet((short)0);
		
		companyExtraMapper.insertSelective(companyExtra);
		
		result.setCode("1");
		result.setMessage("用户注册成功");
		
		return result;
	}
}
