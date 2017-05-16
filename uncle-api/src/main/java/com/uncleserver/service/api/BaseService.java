package com.uncleserver.service.api;

import com.uncleserver.common.wxpay.AccessToken;
import com.uncleserver.model.Aunt;
import com.uncleserver.model.AuntExtra;
import com.uncleserver.model.AuntMessageDetail;
import com.uncleserver.model.Company;
import com.uncleserver.model.CompanyExtra;
import com.uncleserver.model.MessageDetail;
import com.uncleserver.model.User;
import com.uncleserver.model.UserExtra;
import com.uncleserver.modelVo.UserModel;

public interface BaseService {

	String getFilePathById(Integer fileid);

	boolean checkCanSendVcode(String phone);// 检查手机号码是否还可以获取验证码

	UserModel createUserModel(User user, UserExtra extra);

	User selectUserByUserId(int userid);

	UserExtra selectUserExtraByUserID(int userid);

	Aunt selectAuntByAuntID(int anutid);

	UserExtra selectUserExtraByToken(String accesstoken);

	AuntExtra selectAuntExtraByAuntID(int anutid);

	AuntExtra selectAuntExtraByToken(String accesstoken);

	CompanyExtra selectCompanyByToken(String accesstoken);

	// 微信支付AccessToken
	AccessToken getAccessToken();

	String createOrderNum(int type, Long nowcount);

	Aunt selectAuntByAuntId(int auntid);

	Company selectCompanyByCid(int companyId);

	AuntExtra selectAuntExtraByAuntId(int auntid);

	CompanyExtra selectCompanyExtraByCid(int companyId);

	void sendSystemMessageToUser(int userid, int usertype, MessageDetail detail, AuntMessageDetail auntDetail);
}
