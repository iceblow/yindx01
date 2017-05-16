package com.uncleserver.service.api;

import com.uncleserver.model.User;
import com.uncleserver.modelVo.ApiResult;

public interface HomeService extends BaseService {
	
	ApiResult homeUser(String city);
	
	ApiResult auntList(String screentype,String longitude,String latitude,
			String distance_from,String distance_to,String name_letter,
			String comment_type,String servertype,String agetype,String yeartype,String page);
	
	ApiResult auntDetail(User user,String auntid);
	
	ApiResult companyList(String screentype,String companytype,String longitude,String latitude,
			String distance_from,String distance_to,String name_letter,
			String comment_type,String servertype,String titletype,String startype,String page);
	
	ApiResult companyDetail(String companyid);
	
	ApiResult companyDetailMore(String companyid,String page);
	
	ApiResult search(String keyword,String type,String page);
	
	ApiResult likeAunt(String auntid,String userid);
	
}
