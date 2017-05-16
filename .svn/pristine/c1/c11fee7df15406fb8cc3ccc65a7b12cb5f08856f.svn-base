package com.uncleserver.service.manage;


import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.uncleserver.model.AuntSignSet;
import com.uncleserver.model.CategorySecond;
import com.uncleserver.model.Cities;
import com.uncleserver.model.Config;
import com.uncleserver.model.FileInfo;
import com.uncleserver.model.QueryFeedBack;
import com.uncleserver.model.QueryLevel;
import com.uncleserver.model.QueryRatio;
import com.uncleserver.model.QuerySignSet;
import com.uncleserver.model.QueryVersion;
import com.uncleserver.model.Ratio;
import com.uncleserver.model.SignSet;
import com.uncleserver.model.Version;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;

public interface ManagerOthersService {

	QueryResult<QueryFeedBack> getUserClient(PQuery pquery);//用户端反馈

	QueryResult<QueryFeedBack> getAuntClent(PQuery pquery);//阿姨端反馈
	
	int delAdvice(String id);
	
	int delAuntAdvice(String id);
	
	QueryResult<QuerySignSet> getUserSignSet(PQuery pquery,String dayCount,String pointCount);
	
	QueryResult<QuerySignSet> getAuentSignSet(PQuery pquery,String dayCount,String pointCount);
	
	int saveAuntSignSet(String dayCount,String pointCount );//保存阿姨端
	
	int saveSignSet(String dayCount,String pointCount );//保存客户端
	
	SignSet getSignSetById(String id);//通过id 查找签到配置
	
	AuntSignSet getAuntSignSetById(String id);
	
	int delSignSet(String id);//删除客户端签到配置
	
	int delAuntSignSet(String id);
	
	int editSignSet(SignSet set);//编辑客户端签到配置
	
	int editAuntSignSet(AuntSignSet set);
	
	QueryResult<Config> getConfig();//获得基本配置信息
	
	int editConfig(Config c);//修改基本配置
	
	Config getConfigById(String id);//
	
	QueryResult<QueryLevel> getUserLevelSet();//获得客户端等级配置
	
	QueryResult<QueryLevel> getAuntLevelSet();//获取阿姨端等级配置
	
	int editLevel(Integer id, String level,String point,String title,String client);
	
	QueryLevel getLevelSetByClient(String client,String id);//通过客户端和id获取level 配置信息
	
	QueryResult<QueryVersion> getVersion(PQuery pquery,String platformtype,String apptype,String poststate,String versiontype);//通过查询条件获取版本信息
	
	int delVersion(Integer id);//删除版本号
	
	int editVersion(Integer id);//发布版本号
	
	int saveVersion(Version v);
	
	int saveFile(FileInfo info);
	
	QueryResult<QueryRatio> getRatio(PQuery pquery,String city ,String categoryid,String server_type,String ratio_type);//获取平台佣金抽成
	
	QueryResult<Ratio> getRatios(PQuery pquery,String categoryid,String server_type,String city,String area);
	
	List<Cities> getAllCities();//获取所有城市
	
	List<CategorySecond> getAllCategorySecond();//获取所有服务内容
	
	Ratio getRatioById(String id);
	
	int delRatio(String id);
	
	int editRatio(Ratio r);
	
	int addRatio(Ratio r);
	
	int setRatio(String city,String categoryid,String serverType,String ratio);
	
}
