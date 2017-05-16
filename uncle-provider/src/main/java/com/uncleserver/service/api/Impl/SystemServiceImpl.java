package com.uncleserver.service.api.Impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.common.Constant;
import com.uncleserver.model.About;
import com.uncleserver.model.Agreement;
import com.uncleserver.model.Area;
import com.uncleserver.model.AuntPush;
import com.uncleserver.model.BackReason;
import com.uncleserver.model.Cities;
import com.uncleserver.model.OrderPay;
import com.uncleserver.model.UserExtra;
import com.uncleserver.model.UserPush;
import com.uncleserver.model.Version;
import com.uncleserver.modelVo.ApiResult;
import com.uncleserver.modelVo.CityLetterModel;
import com.uncleserver.modelVo.NameModel;
import com.uncleserver.modelVo.VersionModel;
import com.uncleserver.service.api.SystemService;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

@Service("systemService")
public class SystemServiceImpl extends BaseServiceImpl implements SystemService {

	@Override
	public ApiResult sendVcode(String appid) {
		ApiResult result = new ApiResult();

		int app_type = 0;
		int index = appid.lastIndexOf("_");
		String version = appid.substring(index + 1, appid.length());
		String appidNoVersion = appid.substring(0, index);

		if (appidNoVersion.equals(Constant.APPID_ANDROID)) {
			app_type = 1;// 安卓
		} else if (appidNoVersion.equals(Constant.APPID_AUNT_ANDROID)) {
			app_type = 2;// 阿姨端安卓
		} else if (appidNoVersion.equals(Constant.APPID_IOS)) {
			app_type = 3;// IOS
		} else if (appidNoVersion.equals(Constant.APPID_AUNT_IOS)) {
			app_type = 4;// 阿姨端IOS
		}
		if (app_type == 0) {
			result.setCode("2");
			result.setMessage("请求失败,错误的APPID");
			return result;
		}

		Version versionModel = null;
		if (app_type == 1) {
			versionModel = versionMapper.selectLastVersionByInfo(version, 0, 0);
		} else if (app_type == 2) {
			versionModel = versionMapper.selectLastVersionByInfo(version, 1, 1);
		} else if (app_type == 3) {
			versionModel = versionMapper.selectLastVersionByInfo(version, 0, 0);
		} else if (app_type == 4) {
			versionModel = versionMapper.selectLastVersionByInfo(version, 1, 1);
		}

		if (versionModel != null) {
			VersionModel model = new VersionModel();
			model.setDownloadurl(versionModel.getDownloadurl());
			model.setNewcontent(versionModel.getNewcontent());
			model.setPosttime(CommonUtils.getTimeFormat(versionModel.getPosttime(), "yyyy-MM-dd HH:mm:ss"));
			model.setVersiontype(versionModel.getVersiontype());
			HashMap<String, Object> map = new HashMap<>();
			map.put("versionInfo", model);
			result.setResult(map);
		}

		result.setCode("1");
		result.setMessage("请求成功");
		return result;
	}

	@Override
	public ApiResult getCityList() {
		ApiResult result = new ApiResult();

		HashMap<String, Object> map = new HashMap<>();
		List<Area> cityList = areaMapper.selectByState((short) 0);
		List<CityLetterModel> list = new ArrayList<>();
		if (cityList != null) {
			HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
			format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
			for (Area cities : cityList) {
				CityLetterModel model = new CityLetterModel();
				model.setId(cities.getId());
				model.setName(cities.getName());
				model.setFast_state(cities.getFastState());
				char first = cities.getName().charAt(0);
				try {
					String[] pingyin = PinyinHelper.toHanyuPinyinStringArray(first, format);
					if (pingyin != null && pingyin.length > 0) {
						model.setLetter(pingyin[0].substring(0, 1).toUpperCase());
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
				list.add(model);
			}
		}
		String[] letter = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
				"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

		for (CityLetterModel model : list) {
			for (int i = 0; i < 26; i++) {
				String let = letter[i];
				if (!let.equals(model.getLetter())) {
					continue;
				} else {

					List<CityLetterModel> cityListLetter = null;
					if (map.containsKey("cityList" + let)) {
						cityListLetter = (List<CityLetterModel>) map.get("cityList" + let);
					} else {
						cityListLetter = new ArrayList<>();
					}
					cityListLetter.add(model);
					map.put("cityList" + let, cityListLetter);
					break;
				}
			}
		}

		LinkedHashMap<String, Object> newmap = new LinkedHashMap<>();
		Object[] key_arr = map.keySet().toArray();
		Arrays.sort(key_arr);
		for (Object key : key_arr) {
			Object value = map.get(key);
			newmap.put(key.toString(), value);
		}
		result.setCode("1");
		result.setMessage("请求成功");
		result.setResult(newmap);

		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult registerPush(String userid, String pushkey, String devicetype) {
		ApiResult result = new ApiResult();

		int userInt = CommonUtils.parseInt(userid, 0);
		short devicetypeShort = CommonUtils.parseShort(devicetype, (short) 1);// 默认安卓
		UserPush pushUser = null;
		if (userInt > 0) {
			pushUser = userPushMapper.selectByUserId(userInt);
		}
		if (pushUser != null) {
			UserPush pushKey = userPushMapper.selectByKeyAndType(pushkey, devicetypeShort);
			if (pushKey != null && pushKey.getDataid().intValue() != pushUser.getDataid().intValue()) {
				userPushMapper.deleteByPrimaryKey(pushKey.getDataid());
			}
			pushUser.setPushKey(pushkey);
			pushUser.setDevicetype(devicetypeShort);
			userPushMapper.updateByPrimaryKeySelective(pushUser);
		} else {
			UserPush pushKey = userPushMapper.selectByKeyAndType(pushkey, devicetypeShort);
			if (pushKey != null) {
				pushKey.setUserid(userInt);
				userPushMapper.updateByPrimaryKeySelective(pushKey);
			} else {
				pushKey = new UserPush();
				pushKey.setUserid(userInt);
				pushKey.setPushKey(pushkey);
				pushKey.setDevicetype(devicetypeShort);
				pushKey.setIsaccept((short) 0);
				userPushMapper.insert(pushKey);
			}
		}
		result.setCode("1");
		result.setMessage("请求成功");
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult registerAuntPush(int useridInt, String user_type, String pushkey, String devicetype) {
		ApiResult result = new ApiResult();

		short usertypeShort = CommonUtils.parseShort(user_type, (short) 0);// 默认安卓
		short devicetypeShort = CommonUtils.parseShort(devicetype, (short) 1);// 默认安卓
		AuntPush pushAunt = null;
		if (useridInt > 0) {
			pushAunt = auntPushMapper.selectByUserId(useridInt, usertypeShort);
		}
		if (pushAunt != null) {
			AuntPush pushKey = auntPushMapper.selectByKeyAndType(pushkey, devicetypeShort);
			if (pushKey != null && pushKey.getDataid().intValue() != pushAunt.getDataid().intValue()) {
				auntPushMapper.deleteByPrimaryKey(pushKey.getDataid());
			}
			pushAunt.setPushKey(pushkey);
			pushAunt.setDevicetype(devicetypeShort);
			auntPushMapper.updateByPrimaryKeySelective(pushAunt);
		} else {
			AuntPush pushKey = auntPushMapper.selectByKeyAndType(pushkey, devicetypeShort);
			if (pushKey != null) {
				pushKey.setUserid(useridInt);
				pushKey.setUserType(usertypeShort);
				auntPushMapper.updateByPrimaryKeySelective(pushKey);
			} else {
				pushAunt = new AuntPush();
				pushAunt.setUserid(useridInt);
				pushAunt.setUserType(usertypeShort);
				pushAunt.setPushKey(pushkey);
				pushAunt.setDevicetype(devicetypeShort);
				pushAunt.setIsaccept((short) 0);
				auntPushMapper.insert(pushAunt);
			}
		}
		result.setCode("1");
		result.setMessage("请求成功");
		return result;
	}

	@Override
	public ApiResult getWebHtml(String type, String relation_id) {
		ApiResult result = new ApiResult();
		HashMap<String, Object> map = new HashMap<>();
		short typeShort = CommonUtils.parseShort(type, (short) 0);
		int relation_idInt = CommonUtils.parseInt(relation_id, 0);
		Agreement agreement = null;
		if ("1".equals(type)) {
			agreement = agreementMapper.selectByTypeAndRID(typeShort, relation_idInt);
		} else {
			agreement = agreementMapper.selectByType(typeShort);
		}
		if (agreement != null) {
			map.put("content", agreement.getContent());
		} else {
			map.put("content", "");
		}

		result.setCode("1");
		result.setMessage("请求成功");
		result.setResult(map);
		return result;
	}

	@Override
	public ApiResult getReasonUser(String type) {
		ApiResult result = new ApiResult();
		List<BackReason> reasonList = null;
		if ("1".equals(type)) {
			reasonList = backReasonMapper.selectListByType((short) 0);
		} else if ("2".equals(type)) {
			reasonList = backReasonMapper.selectListByType((short) 1);
		}

		List<NameModel> listReason = new ArrayList<>();
		if (reasonList != null && reasonList.size() > 0) {
			for (BackReason backReason : reasonList) {
				NameModel model = new NameModel();
				model.setName(backReason.getContent());
				listReason.add(model);
			}
		}

		HashMap<String, Object> map = new HashMap<>();
		result.setCode("1");
		result.setMessage("请求成功");
		map.put("listReason", listReason);
		result.setResult(map);

		return result;
	}

	@Override
	public ApiResult getReasonAunt(String type) {
		ApiResult result = new ApiResult();

		List<BackReason> reasonList = null;
		if ("1".equals(type)) {
			reasonList = backReasonMapper.selectListByType((short) 3);
		} else if ("2".equals(type)) {
			reasonList = backReasonMapper.selectListByType((short) 2);
		}

		List<NameModel> listReason = new ArrayList<>();
		if (reasonList != null && reasonList.size() > 0) {
			for (BackReason backReason : reasonList) {
				NameModel model = new NameModel();
				model.setName(backReason.getContent());
				listReason.add(model);
			}
		}

		HashMap<String, Object> map = new HashMap<>();
		result.setCode("1");
		result.setMessage("请求成功");
		map.put("listReason", listReason);
		result.setResult(map);

		return result;
	}

	@Override
	public ApiResult getAbout(String type) {
		ApiResult result = new ApiResult();
		About about = null;
		if ("1".equals(type)) {
			about = aboutMapper.selectByAppType((short) 1);
		} else {
			about = aboutMapper.selectByAppType((short) 2);
		}
		HashMap<String, Object> map = new HashMap<>();
		if (about != null)
			map.put("url", about.getUrl());
		else
			map.put("url", "");
		result.setCode("1");
		result.setMessage("请求成功");
		result.setResult(map);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult setArea(UserExtra extra, String area) {
		ApiResult result = new ApiResult();
		extra.setArea(area);

		Cities city = citysMapper.selectCityByChildName(area);
		if (city != null) {
			extra.setCity(city.getName());
		}

		userExtraMapper.updateByPrimaryKey(extra);

		result.setCode("1");
		result.setMessage("请求成功");
		return result;
	}

	@Override
	public ApiResult getPushSet(String push_key, String devicetype) {
		ApiResult result = new ApiResult();
		UserPush userPush = userPushMapper.selectByKeyAndType(push_key, CommonUtils.parseShort(devicetype, (short) 0));
		HashMap<String, Object> map = new HashMap<>();
		if (userPush == null) {
			map.put("isaccept", 0);
		} else {
			map.put("isaccept", userPush.getIsaccept());
		}
		result.setCode("1");
		result.setMessage("请求成功");
		result.setResult(map);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult setPush(String push_key, String devicetype, String isaccept) {
		ApiResult result = new ApiResult();
		UserPush userPush = userPushMapper.selectByKeyAndType(push_key, CommonUtils.parseShort(devicetype, (short) 0));
		if(userPush != null){
			userPush.setIsaccept(CommonUtils.parseShort(isaccept, (short) 0));
			userPushMapper.updateByPrimaryKey(userPush);
		}
		result.setCode("1");
		result.setMessage("请求成功");
		return result;
	}

	@Override
	public ApiResult getAuntPushSet(String push_key, String devicetype) {
		ApiResult result = new ApiResult();
		AuntPush auntPush = auntPushMapper.selectByKeyAndType(push_key, CommonUtils.parseShort(devicetype, (short) 0));
		HashMap<String, Object> map = new HashMap<>();
		if (auntPush == null) {
			map.put("isaccept", 0);
		} else {
			map.put("isaccept", auntPush.getIsaccept());
		}
		result.setCode("1");
		result.setMessage("请求成功");
		result.setResult(map);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult setAuntPush(String push_key, String devicetype, String isaccept) {
		ApiResult result = new ApiResult();
		AuntPush auntPush = auntPushMapper.selectByKeyAndType(push_key, CommonUtils.parseShort(devicetype, (short) 0));
		if(auntPush != null){
			auntPush.setIsaccept(CommonUtils.parseShort(isaccept, (short) 0));
			auntPushMapper.updateByPrimaryKey(auntPush);
		}
		result.setCode("1");
		result.setMessage("请求成功");
		return result;
	}

	@Override
	public String getWXPayBody(String ordernum) {
		String body = "";
		if(!CommonUtils.isEmptyString(ordernum)){
			String table_info = ordernum.substring(0, 1);
			
			if ("R".equals(table_info)) {
				body = "用户充值";
			}else if("P".equals(table_info)){
				List<OrderPay> payList = orderPayMapper.selectByOrderNum(ordernum);
				if(payList != null && payList.size()>0){
					for(OrderPay pay:payList){
						if(pay.getType() == 1){
							body = "支付定金";
						}else if(pay.getType() == 2){
							body = "支付服务费";
						}else if(pay.getType() == 3){
							body = "增加小费";
						}else if(pay.getType() == 4){
							body = "维修费用第一次";
						}else if(pay.getType() == 5){
							body = "维修费用第二次";
						}else if(pay.getType() == 6){
							body = "退单";
						}
						break;
					}
				}
			}else {
				
			}
		}
		
		return body;
	}
	
	
	
	
	
	
	
	
	
	
}
