package com.uncleserver.service.api;

import javax.servlet.http.HttpServletRequest;

import com.uncleserver.model.User;
import com.uncleserver.model.UserExtra;

public interface WechatService extends BaseService {

	User getUSerInfoByUnionId(String openid,String unioid, String nickname, String headimgurl,String sessionid);

	String processRequest(HttpServletRequest request);
	
	User getUserByUserId(String userid);
	
	UserExtra getUserExtraByUserId(int userid);
}
