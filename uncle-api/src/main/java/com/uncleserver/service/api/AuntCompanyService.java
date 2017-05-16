package com.uncleserver.service.api;

import com.uncleserver.model.Company;
import com.uncleserver.model.CompanyExtra;
import com.uncleserver.modelVo.ApiResult;

public interface AuntCompanyService extends BaseService {

	ApiResult getStaffList(String userid, String page);

	ApiResult searchStaff(String userid, String page, String keywrod) throws Exception;

	ApiResult addStaff(String userid, String auntid);

	ApiResult delStaff(String userid, String auntid);

	ApiResult refreshInfo(Company company, CompanyExtra extra);

	ApiResult addAddress(String userid, String provience, String city, String area, String longitude, String latitude,
			String phone, String rname, String sex, String addressname, String addressdetail, String isdefault);// 新增服务地址

	ApiResult editAddress(String userid, String addressid, String provience, String city, String area, String longitude,
			String latitude, String phone, String rname, String sex, String addressname, String addressdetail,
			String isdefault);// 编辑服务地址

	ApiResult delAddress(String userid, String addressid);// 删除服务地址

	ApiResult getAddressList(int userid, int categoryid);// 获取用户的服务地址列表
	
	ApiResult companyDetail(String companyid);
}
