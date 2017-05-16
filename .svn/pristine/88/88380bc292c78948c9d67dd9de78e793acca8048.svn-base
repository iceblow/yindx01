package com.uncleserver.service.manage;

import java.util.List;

import com.uncleserver.model.Admin;
import com.uncleserver.model.Area;
import com.uncleserver.model.Aunt;
import com.uncleserver.model.AuntBanner;
import com.uncleserver.model.AuntPush;
import com.uncleserver.model.Cities;
import com.uncleserver.model.Company;
import com.uncleserver.model.FileInfo;
import com.uncleserver.model.HomeAd;
import com.uncleserver.model.HomeBanner;
import com.uncleserver.model.Permission;
import com.uncleserver.model.Proviences;
import com.uncleserver.model.Role;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;
import com.uncleserver.modelVo.HomeBannerVO;

public interface ManageIndexService {
	
	QueryResult<HomeBanner> pageHomeBanner(PQuery pquery,String cityName,String type,String contentid);
	
	HomeBanner getHomeBannerById(String id);//通过id获取主页轮播图片
	
	Integer saveFile(String key);//保存图片
	
	int saveAppBanner(HomeBanner banner);//保存主页轮播图片
	
	int editAppBanner(HomeBanner banner);//修改图片
	
	int delappbanner(String id);//删除主页轮播图片
	
	int upappbanner(String id);//升序
	
	int downsort(String id);//降序
	
	List<Proviences> getAllProviences();//获取所有省
	
	List<Cities> getCitiesByPro(String id);//通过省获取城市
	
	List<Area> selectArea(int id);
	
	Cities getgetCitiesByCityName(String cityName);//通过城市名获取其城市
	
	Cities getgetAreaByCityName(String cityName);//通过区名获取其城市
	
	FileInfo getFileInfoById(int id);
	
	QueryResult<AuntBanner> pageAuntBanner(PQuery pquery,String type,String contentid);//通过条件获取图片
	
	int downauntbannersort(String id);//阿姨端主页图片降序
	
	int upauntbanner(String id);//阿姨端图片升序
	
	int delauntbanner(String id);//阿姨端主页图片删除
	
	int saveAuntBanner(AuntBanner banner);//新增阿姨端图片
	
	int editAuntBanner(AuntBanner banner);//修改阿姨端图片
	
	List<Company> getAllCompany();//获取所有公司
	
	List<Aunt> getAllAunt();//获取所有阿姨
	
	AuntBanner getAuntBannerById(String id);
	
	QueryResult<HomeAd> pageHomeAd(PQuery pquery);
	
	QueryResult<AuntPush> pageAuntPush(PQuery pquery);
	
	Admin selectAdminByAccount(String account);
	
	void addAdmin(Admin admin);
	
	List<Role> getSelectRole();
	
	String getPath(Integer id);
	
	
}
