package com.uncleserver.service.api;

import com.uncleserver.model.AuntExtra;
import com.uncleserver.model.CompanyExtra;
import com.uncleserver.modelVo.ApiResult;

public interface AuntHomeService extends BaseService {

	ApiResult getBanner();

	ApiResult setState(String state, CompanyExtra extra);

	ApiResult setState(String state, AuntExtra extra);

	ApiResult getOrderList(int userid, int usertype, String longitude, String latitude);

	ApiResult acceptOrder(int userid, int usertype, int orderid, int orderid_user,int auntMCount,int auntWCount);

	ApiResult refuseOrder(String userid, String user_type, String orderid, String orderid_user, String reason,
			String content);
}
