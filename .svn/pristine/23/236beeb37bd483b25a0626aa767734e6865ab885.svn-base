package com.uncleserver.service.api;

import com.uncleserver.model.UserExtra;
import com.uncleserver.modelVo.ApiResult;

public interface SystemService extends BaseService {
	ApiResult sendVcode(String appid);

	ApiResult getCityList();

	ApiResult registerPush(String userid, String pushkey, String devicetype);

	ApiResult registerAuntPush(int useridInt, String user_type, String pushkey, String devicetype);

	ApiResult getWebHtml(String type, String relation_id);

	ApiResult getReasonUser(String type);

	ApiResult getReasonAunt(String type);

	ApiResult getAbout(String type);

	ApiResult setArea(UserExtra extra, String area);

	ApiResult getPushSet(String push_key, String devicetype);

	ApiResult setPush(String push_key, String devicetype, String isaccept);

	ApiResult getAuntPushSet(String push_key, String devicetype);

	ApiResult setAuntPush(String push_key, String devicetype, String isaccept);
	
	String getWXPayBody(String ordernum);
}
