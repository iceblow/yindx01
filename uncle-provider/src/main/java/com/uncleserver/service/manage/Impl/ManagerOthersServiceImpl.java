package com.uncleserver.service.manage.Impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;




import com.uncleserver.common.CommonUtils;
import com.uncleserver.dao.RatioMapper;
import com.uncleserver.model.Area;
import com.uncleserver.model.AuntLevelSet;
import com.uncleserver.model.AuntSignSet;
import com.uncleserver.model.CategorySecond;
import com.uncleserver.model.Cities;
import com.uncleserver.model.Config;
import com.uncleserver.model.FeedBack;
import com.uncleserver.model.FeedBackAunt;
import com.uncleserver.model.FileInfo;
import com.uncleserver.model.LevelSet;
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
import com.uncleserver.service.api.Impl.BaseServiceImpl;
import com.uncleserver.service.manage.ManagerOthersService;

import cn.jiguang.common.utils.StringUtils;

@Service("managerOthersService")
public class ManagerOthersServiceImpl extends BaseServiceImpl implements ManagerOthersService{

	private static final int advicePageAmt = 10;
	
	@Resource
	private RatioMapper ratioMapper;
	
	@Override
	public  QueryResult<QueryFeedBack> getUserClient(PQuery pquery) {
		long count = feedBackMapper.getFeedCount();
		List<FeedBack> feeds = feedBackMapper.selectByPageAmt(pquery.getStartPage(),pquery.getRows());
		if(feeds==null){
			return new QueryResult<QueryFeedBack>( null,count,pquery.getPage(),pquery.getRows());
		}
		List<QueryFeedBack> feedBackss = new ArrayList<QueryFeedBack>();
		for(FeedBack feed:feeds){
			QueryFeedBack query = new QueryFeedBack();
			query.setAddtime(CommonUtils.getTimeFormat(feed.getAddtime(), "yyyy-MM-dd HH:mm:ss"));
			query.setContent(feed.getContent());
			query.setId(feed.getId());
			if(feed.getUserid()==0||feed.getUserid()==null){
				query.setUserid(0);
				query.setRealName("匿名用户");
			}else{
				query.setUserid(feed.getUserid());
				query.setRealName(feed.getRealName());
			}
			feedBackss.add(query);
		}
		
		QueryResult<QueryFeedBack> result = new QueryResult<QueryFeedBack>(feedBackss,count,pquery.getPage(),pquery.getRows());
		return result;
	}

	public QueryResult<QueryFeedBack> getAuntClent(PQuery pquery){
		long count = feedbackAuntMapper.getFeedCount();
		List<FeedBackAunt> feeds = feedbackAuntMapper.getFeedBackAuntByPage(pquery.getStartPage(),pquery.getRows());
		List<QueryFeedBack> feedBackss = new ArrayList<QueryFeedBack>();
		if(feeds==null){
			return new QueryResult<QueryFeedBack>( null,count,pquery.getPage(),pquery.getRows());
		}
		for(FeedBackAunt feed:feeds){
			
			QueryFeedBack query = new QueryFeedBack();
			query.setAddtime(CommonUtils.getTimeFormat(feed.getAddtime(), "yyyy-MM-dd HH:mm:ss"));
			query.setContent(feed.getContent());
			query.setId(feed.getId());
			if(feed.getUserType()==0){
				query.setUserType("阿姨");
			}else{
				query.setUserType("公司");
			}
			
			if(feed.getUserid()==0||feed.getUserid()==null){
				query.setUserid(0);
				query.setRealName("匿名用户");
			}else{
				query.setUserid(feed.getUserid());
				query.setRealName(feed.getRealName());
			}
			feedBackss.add(query);
		}
		
		QueryResult<QueryFeedBack> result = new QueryResult<QueryFeedBack>(feedBackss,count,pquery.getPage(),pquery.getRows());
		return result;
	}
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int delAdvice(String id){
		try {
			int i = feedBackMapper.deleteByPrimaryKey(CommonUtils.parseInt(id, 0));
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int delAuntAdvice(String id){
		try {
			int i = feedbackAuntMapper.deleteByPrimaryKey(CommonUtils.parseInt(id, 0));
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public QueryResult<QuerySignSet> getUserSignSet(PQuery pquery,String dayCount,String pointCount){
		dayCount = StringUtils.isNotEmpty(dayCount)?dayCount:null;
		pointCount = StringUtils.isNotEmpty(pointCount)?pointCount:null;
		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(dayCount)){
			map.put("dayCount", dayCount);
		}
		if(!StringUtils.isEmpty(pointCount)){
			map.put("point", pointCount);
		}
		long total =  signSetMapper.getCount(map);
		map.put("start", pquery.getStartPage());	
		map.put("row", pquery.getRows());
		List<SignSet> set = signSetMapper.getSignSetQuery(map);
		List<QuerySignSet> querys = new ArrayList<QuerySignSet>();
		if(set==null){
			return new QueryResult<QuerySignSet>( null,total,pquery.getPage(),pquery.getRows());
		}
		for(SignSet s:set){
			QuerySignSet query = new QuerySignSet();
			query.setAddtime(CommonUtils.getTimeFormat(s.getAddtime(), "yyyy-MM-dd HH:mm:ss"));
			query.setDaycount(s.getDaycount());
			query.setId(s.getId());
			query.setPoint(s.getPoint());
			querys.add(query);
		}
	
		QueryResult<QuerySignSet> result = new QueryResult<QuerySignSet>( querys,total,pquery.getPage(),pquery.getRows());
		return result;
	}
	
	public QueryResult<QuerySignSet> getAuentSignSet(PQuery pquery,String dayCount,String pointCount){
		dayCount = StringUtils.isNotEmpty(dayCount)?dayCount:null;
		pointCount = StringUtils.isNotEmpty(pointCount)?pointCount:null;
		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(dayCount)){
			map.put("dayCount", dayCount);
		}
		if(!StringUtils.isEmpty(pointCount)){
			map.put("point", pointCount);
		}
		long total =  auntSignSetMapper.getCount(map);
		map.put("start", pquery.getStartPage());
		map.put("row", pquery.getRows());
		List<AuntSignSet> set =  auntSignSetMapper.getSignSetQuery(map);
		List<QuerySignSet> querys = new ArrayList<QuerySignSet>();
		if(set==null){
			return new QueryResult<QuerySignSet>( null,total,pquery.getPage(),pquery.getRows());
		}
		for(AuntSignSet s:set){
			QuerySignSet query = new QuerySignSet();
			query.setAddtime(CommonUtils.getTimeFormat(s.getAddtime(), "yyyy-MM-dd HH:mm:ss"));
			query.setDaycount(s.getDaycount());
			query.setId(s.getId());
			query.setPoint(s.getPoint());
			querys.add(query);
		}
		QueryResult<QuerySignSet> result = new QueryResult<QuerySignSet>( querys,total,pquery.getPage(),pquery.getRows());
		return result;
	}
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int saveSignSet(String dayCount,String pointCount ){
		SignSet set = new SignSet();
		set.setAddtime(new Date());
		set.setDaycount((byte)CommonUtils.parseInt(dayCount, 0));
		set.setPoint(CommonUtils.parseInt(pointCount, 0));
		try {
			signSetMapper.insertSelective(set);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int saveAuntSignSet(String dayCount,String pointCount ){
		AuntSignSet set = new AuntSignSet();
		set.setAddtime(new Date());
		set.setDaycount((byte)CommonUtils.parseInt(dayCount, 0));
		set.setPoint(CommonUtils.parseInt(pointCount, 0));
		try {
			auntSignSetMapper.insertSelective(set);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	//通过id 获取 用户端签到配置
	public SignSet getSignSetById(String id){
		SignSet set = signSetMapper.selectByPrimaryKey(CommonUtils.parseInt(id, 0));
		return set;
	}
	
	public AuntSignSet getAuntSignSetById(String id){
		AuntSignSet set = auntSignSetMapper.selectByPrimaryKey(CommonUtils.parseInt(id, 0));
		return set;
	}
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int delSignSet(String id){
		int i = 0;
		try {
			i = signSetMapper.deleteByPrimaryKey(CommonUtils.parseInt(id, 0));
			return i;
		} catch (Exception e) {
			
			e.printStackTrace();
			return 0;
		}
	}
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int delAuntSignSet(String id){
		int i = 0;
		try {
			i = auntSignSetMapper.deleteByPrimaryKey(CommonUtils.parseInt(id, 0));
			return i;
		} catch (Exception e) {
			
			e.printStackTrace();
			return 0;
		}
	}
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int editSignSet(SignSet set){
		int i = 0;
		try {
			i = signSetMapper.updateByPrimaryKeySelective(set);
			return i;
		} catch (Exception e) {			
			e.printStackTrace();
			return 0;
		}
	}
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int editAuntSignSet(AuntSignSet set){
		int i = 0;
		try {
			i = auntSignSetMapper.updateByPrimaryKeySelective(set);
			return i;
		} catch (Exception e) {			
			e.printStackTrace();
			return 0;
		}
	}
	
	public QueryResult<Config> getConfig(){
		List<Config> list = configMapper.getConfigs();
		long count = configMapper.getCount(); 
		QueryResult<Config> result = new QueryResult<Config>(list,count,1,20);
		
		return result;
	}
	
	public Config getConfigById(String id){
		Config c = configMapper.selectByPrimaryKey(CommonUtils.parseInt(id, 0));
		return c;
	}
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int editConfig(Config c){
		try {
			int i = configMapper.updateByPrimaryKeySelective(c);
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public QueryResult<QueryLevel> getUserLevelSet(){
		long count = levelSetMapper.getCount();
		List<LevelSet> list = levelSetMapper.getAllLevelSet();
		if(count==0){
			return new QueryResult<QueryLevel>(null,count,1,0);
		}
		List<QueryLevel> querys = new ArrayList<QueryLevel>();
		for(LevelSet s:list){
			QueryLevel query = new QueryLevel();
			query.setAddtime(CommonUtils.getTimeFormat(s.getAddtime(), "yyyy-MM-dd HH:mm:ss"));
			query.setId(s.getId());
			query.setLevel(s.getLevel());
			query.setPoint(s.getPoint());
			query.setTitle(s.getTitle());
			querys.add(query);
		}
		QueryResult<QueryLevel> result = new QueryResult<QueryLevel>(querys,count,1,10);
		return result;
	}
	
	public QueryResult<QueryLevel> getAuntLevelSet(){
		long count = auntLevelSetMapper.getCount();
		List<AuntLevelSet> list = auntLevelSetMapper.getAllLevelSet();
		if(count==0){
			return new QueryResult<QueryLevel>(null,count,1,0);
		}
		List<QueryLevel> querys = new ArrayList<QueryLevel>();
		for(AuntLevelSet s:list){
			QueryLevel query = new QueryLevel();
			query.setAddtime(CommonUtils.getTimeFormat(s.getAddtime(), "yyyy-MM-dd HH:mm:ss"));
			query.setId(s.getId());
			query.setLevel((short) s.getLevel());
			query.setPoint(s.getPoint());
			query.setTitle(s.getTitle());
			querys.add(query);
		}
		QueryResult<QueryLevel> result = new QueryResult<QueryLevel>(querys,count,1,10);
		return result;
	}
	
	public QueryLevel getLevelSetByClient(String client,String id){
		QueryLevel level = new QueryLevel();
		if("user".equals(client)){
			LevelSet set =levelSetMapper.selectByPrimaryKey(CommonUtils.parseInt(id, 0));
			level.setId(set.getId());
			level.setLevel(set.getLevel());
			level.setPoint(set.getPoint());
			level.setTitle(set.getTitle());
			level.setAddtime(CommonUtils.getTimeFormat(set.getAddtime(), "yyyy-MM-dd HH:mm:ss"));
		}else{
			AuntLevelSet set = auntLevelSetMapper.selectByPrimaryKey(CommonUtils.parseInt(id, 0));
			level.setId(set.getId());
			level.setLevel((short)set.getLevel());
			level.setPoint(set.getPoint());
			level.setTitle(set.getTitle());
			level.setAddtime(CommonUtils.getTimeFormat(set.getAddtime(), "yyyy-MM-dd HH:mm:ss"));
		}
		return level;
	} 
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int editLevel(Integer id, String level,String point,String title,String client){
		int i = 0;
		try {
			if("user".equals(client)){
				LevelSet set = new LevelSet();
				set.setId(id);
				set.setLevel(CommonUtils.parseShort(level, (short)0));
				set.setPoint(CommonUtils.parseInt(point, 0));
				set.setTitle(title);
				i = levelSetMapper.updateByPrimaryKeySelective(set);
			}else{
				AuntLevelSet set = new AuntLevelSet();
				set.setId(id);
				set.setLevel((byte)CommonUtils.parseInt(level, (short)0));
				set.setPoint(CommonUtils.parseInt(point, 0));
				set.setTitle(title);
				i = auntLevelSetMapper.updateByPrimaryKeySelective(set);
			}
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public QueryResult<QueryVersion> getVersion(PQuery pquery,String platformtype,String apptype,String poststate,String versiontype){
		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(platformtype)){
			map.put("platformtype", platformtype);
		}
		if(!StringUtils.isEmpty(apptype)){
			map.put("apptype", apptype);
		}
		if(!StringUtils.isEmpty(poststate)){
			map.put("poststate",poststate);
		}
		if(!StringUtils.isEmpty(versiontype)){
			map.put("versiontype", versiontype);
		}
		long count = versionMapper.getCount(map);
		if(count==0){
			return new QueryResult<QueryVersion>(null,count,pquery.getPage(),pquery.getRows());
		}
		map.put("start",pquery.getStartPage());
		map.put("row", pquery.getRows());
		List<Version> list = versionMapper.getVsersion(map);
		List<QueryVersion> querys = new ArrayList<QueryVersion>();
		for(Version v:list){
			QueryVersion query = new QueryVersion(v);
			querys.add(query);
		}
		QueryResult<QueryVersion> result = new QueryResult<QueryVersion>(querys,count,pquery.getPage(),pquery.getRows());
		return result;
	}
	//发布版本
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int editVersion(Integer id){
		Version v = versionMapper.selectByPrimaryKey(id);
		v.setPoststate((byte)1);
		v.setPosttime(new Date());
		try {
			int i = versionMapper.updateByPrimaryKey(v);
			return i;
		} catch (Exception e) {
			return 0;
		}
	}
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int delVersion(Integer id){
		try {
			int i = versionMapper.deleteByPrimaryKey(id);
			return i;
		} catch (Exception e) {
			return 0;
		}
	}
	
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int saveVersion(Version v){
		try {
			int  i = versionMapper.insertSelective(v);
			return i;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int saveFile(FileInfo info){
		try {
			int i = fileInfoMapper.insertSelective(info);
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public QueryResult<QueryRatio> getRatio(PQuery pquery,String city ,String categoryid,String server_type,String ratio_type){
		Map<String,Object> map = new HashMap<String,Object>();
		if(StringUtils.isNotEmpty(city)){
			map.put("city", city);
		}
		if(StringUtils.isNotEmpty(categoryid)){
			map.put("categoryid", categoryid);
		}
		if(StringUtils.isNotEmpty(server_type)){
			map.put("server_type", server_type);
		}
		if(StringUtils.isNotEmpty(ratio_type)){
			map.put("ratio_type", ratio_type);
		}
		long count = ratioMapper.getCount(map);
		map.put("start", pquery.getStartPage());
		map.put("row", pquery.getRows());
		List<QueryRatio> ratios = ratioMapper.getAllRatios(map);
		
		return new QueryResult<QueryRatio>(ratios,count,pquery.getPage(),pquery.getRows());
	}
	
	public QueryResult<Ratio> getRatios(PQuery pquery,String categoryid,String server_type,String city,String area){
		List<Ratio> ratios = new ArrayList<Ratio>();
		if(CommonUtils.isEmptyString(categoryid)&&CommonUtils.isEmptyString(server_type)){
			if(CommonUtils.isEmptyString(area)){
				List<Area> list = new ArrayList<Area>();
				if(CommonUtils.isEmptyString(city)){
					list = areaMapper.selectByState((short)0);
				}else{
					list = areaMapper.selectByCityid(Integer.parseInt(city));
				}
				for(Area a:list){
					for(int i=1;i<19;i++){
						Ratio ratio = new Ratio();
						ratio.setCity(a.getName());
						ratio.setServerType((short)1);
						ratio.setCategoryid(i);
						ratio.setStrServerType("阿姨");
						ratio.setStrCategoryid(categorySecondMapper.selectByPrimaryKey(i).getName());
						Ratio r = ratioMapper.selectByCity(ratio.getCity(),i,1);
						if(r!=null){
							ratio.setRatio(r.getRatio());
						}
						ratios.add(ratio);
						Ratio ratio1 = new Ratio();
						ratio1.setCity(a.getName());
						ratio1.setServerType((short)2);
						ratio1.setCategoryid(i);
						ratio1.setStrServerType("公司");
						ratio1.setStrCategoryid(categorySecondMapper.selectByPrimaryKey(i).getName());
						Ratio r1 = ratioMapper.selectByCity(ratio1.getCity(),i,2);
						if(r1!=null){
							ratio1.setRatio(r1.getRatio());
						}
						ratios.add(ratio1);
					}
				}
			}
			if(!CommonUtils.isEmptyString(area)){
				for(int i=1;i<19;i++){
					Ratio ratio = new Ratio();
					ratio.setCity(area);
					ratio.setServerType((short)1);
					ratio.setCategoryid(i);
					ratio.setStrServerType("阿姨");
					ratio.setStrCategoryid(categorySecondMapper.selectByPrimaryKey(i).getName());
					Ratio r = ratioMapper.selectByCity(ratio.getCity(),i,1);
					if(r!=null){
						ratio.setRatio(r.getRatio());
					}
					ratios.add(ratio);
				}
				for(int i=1;i<19;i++){
					Ratio ratio = new Ratio();
					ratio.setCity(area);
					ratio.setServerType((short)2);
					ratio.setCategoryid(i);
					ratio.setStrServerType("公司");
					ratio.setStrCategoryid(categorySecondMapper.selectByPrimaryKey(i).getName());
					Ratio r = ratioMapper.selectByCity(ratio.getCity(),i,1);
					if(r!=null){
						ratio.setRatio(r.getRatio());
					}
					ratios.add(ratio);
				}
			}
		}
		if(StringUtils.isEmpty(server_type)&&StringUtils.isNotEmpty(categoryid)){
			if(StringUtils.isEmpty(area)){
				List<Area> list = new ArrayList<Area>();
				if(CommonUtils.isEmptyString(city)){
					list = areaMapper.selectByState((short)0);
				}else{
					list = areaMapper.selectByCityid(Integer.parseInt(city));
				}
				for(Area a:list){
					Ratio ratio = new Ratio();
					ratio.setCity(a.getName());
					ratio.setServerType((short)1);
					ratio.setStrServerType("阿姨");
					ratio.setCategoryid(Integer.parseInt(categoryid));
					ratio.setStrCategoryid(categorySecondMapper.selectByPrimaryKey(Integer.parseInt(categoryid)).getName());
					Ratio r = ratioMapper.selectByCity(ratio.getCity(),Integer.parseInt(categoryid),1);
					if(r!=null){
						ratio.setRatio(r.getRatio());
					}
					ratios.add(ratio);
					Ratio ratio1 = new Ratio();
					ratio1.setCity(a.getName());
					ratio1.setServerType((short)2);
					ratio1.setStrServerType("公司");
					ratio1.setCategoryid(Integer.parseInt(categoryid));
					ratio1.setStrCategoryid(categorySecondMapper.selectByPrimaryKey(Integer.parseInt(categoryid)).getName());
					Ratio r1 = ratioMapper.selectByCity(ratio1.getCity(),Integer.parseInt(categoryid),2);
					if(r1!=null){
						ratio1.setRatio(r1.getRatio());
					}
					ratios.add(ratio1);
				}
			}else{
				Ratio ratio = new Ratio();
				ratio.setCity(area);
				ratio.setServerType((short)1);
				ratio.setStrServerType("阿姨");
				ratio.setCategoryid(Integer.parseInt(categoryid));
				ratio.setStrCategoryid(categorySecondMapper.selectByPrimaryKey(Integer.parseInt(categoryid)).getName());
				Ratio r = ratioMapper.selectByCity(ratio.getCity(),Integer.parseInt(categoryid),1);
				if(r!=null){
					ratio.setRatio(r.getRatio());
				}
				ratios.add(ratio);
				Ratio ratio1 = new Ratio();
				ratio1.setCity(area);
				ratio1.setServerType((short)2);
				ratio1.setStrServerType("公司");
				ratio1.setCategoryid(Integer.parseInt(categoryid));
				ratio1.setStrCategoryid(categorySecondMapper.selectByPrimaryKey(Integer.parseInt(categoryid)).getName());
				Ratio r1 = ratioMapper.selectByCity(ratio1.getCity(),Integer.parseInt(categoryid),2);
				if(r1!=null){
					ratio1.setRatio(r1.getRatio());
				}
				ratios.add(ratio1);
			}
		}
		if(StringUtils.isNotEmpty(server_type)&&StringUtils.isEmpty(categoryid)){
			if(StringUtils.isEmpty(area)){
				List<Area> list = new ArrayList<Area>();
				if(CommonUtils.isEmptyString(city)){
					list = areaMapper.selectByState((short)0);
				}else{
					list = areaMapper.selectByCityid(Integer.parseInt(city));
				}
				for(Area a:list){
					for(int i=1;i<19;i++){
						Ratio ratio = new Ratio();
						ratio.setCity(a.getName());
						ratio.setServerType((short)Integer.parseInt(server_type));
						if(Integer.parseInt(server_type)==1){
							ratio.setStrServerType("阿姨");
						}else{
							ratio.setStrServerType("公司");
						}
						ratio.setCategoryid(i);
						ratio.setStrCategoryid(categorySecondMapper.selectByPrimaryKey(i).getName());
						Ratio r = ratioMapper.selectByCity(ratio.getCity(),i,1);
						if(r!=null){
							ratio.setRatio(r.getRatio());
						}
						ratios.add(ratio);
					}
				}
			}else{
				for(int i=1;i<19;i++){
					Ratio ratio = new Ratio();
					ratio.setCity(area);
					ratio.setServerType((short)Integer.parseInt(server_type));
					if(Integer.parseInt(server_type)==1){
						ratio.setStrServerType("阿姨");
					}else{
						ratio.setStrServerType("公司");
					}
					ratio.setCategoryid(i);
					ratio.setStrCategoryid(categorySecondMapper.selectByPrimaryKey(i).getName());
					Ratio r = ratioMapper.selectByCity(ratio.getCity(),i,1);
					if(r!=null){
						ratio.setRatio(r.getRatio());
					}
					ratios.add(ratio);
				}
			}
		}
		if(!CommonUtils.isEmptyString(categoryid)&&!CommonUtils.isEmptyString(server_type)&&!CommonUtils.isEmptyString(area)){
			Ratio ratio = new Ratio();
			ratio.setCity(area);
			ratio.setCategoryid(Integer.parseInt(categoryid));
			ratio.setStrCategoryid(categorySecondMapper.selectByPrimaryKey(Integer.parseInt(categoryid)).getName());
			ratio.setServerType((short)Integer.parseInt(server_type));
			if(Integer.parseInt(server_type)==1){
				ratio.setStrServerType("阿姨");
			}else{
				ratio.setStrServerType("公司");
			}
			Ratio r = ratioMapper.selectByCity(area, Integer.parseInt(categoryid), Integer.parseInt(server_type));
			if(r!=null){
				ratio.setRatio(r.getRatio());
			}
			ratios.add(ratio);
		}
		if(!CommonUtils.isEmptyString(categoryid)&&!CommonUtils.isEmptyString(server_type)&&CommonUtils.isEmptyString(area)){
			List<Area> list = new ArrayList<Area>();
			if(CommonUtils.isEmptyString(city)){
				list = areaMapper.selectByState((short)0);
			}else{
				list = areaMapper.selectByCityid(Integer.parseInt(city));
			}
			for(Area a:list){
				Ratio ratio = new Ratio();
				ratio.setCity(a.getName());
				ratio.setCategoryid(Integer.parseInt(categoryid));
				ratio.setStrCategoryid(categorySecondMapper.selectByPrimaryKey(Integer.parseInt(categoryid)).getName());
				ratio.setServerType((short)Integer.parseInt(server_type));
				if(Integer.parseInt(server_type)==1){
					ratio.setStrServerType("阿姨");
				}else{
					ratio.setStrServerType("公司");
				}
				Ratio r = ratioMapper.selectByCity(a.getName(), Integer.parseInt(categoryid), Integer.parseInt(server_type));
				if(r!=null){
					ratio.setRatio(r.getRatio());
				}
				ratios.add(ratio);
			}
		}
		List<Ratio> list = new ArrayList<Ratio>();
		int i = 1;
		for(Ratio ratio:ratios){
			if(i>=pquery.getStartPage()&&i<=(pquery.getStartPage()+pquery.getRows())){
				list.add(ratio);
			}
			i++;
		}
		return new QueryResult<Ratio>(list,(long)ratios.size(),pquery.getPage(),pquery.getRows());
	}
	//获取所有城市
	public List<Cities> getAllCities(){
		List<Cities> list = citiesMapper.selectAll();
		return list;
	}
	//获取所有服务内容
	public List<CategorySecond> getAllCategorySecond(){
		return categorySecondMapper.selectAllCategorySecond();
	}
	
	public Ratio getRatioById(String id){
		return ratioMapper.selectByPrimaryKey(CommonUtils.parseInt(id, 0));
	}
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int delRatio(String id){
		try {
			int i = ratioMapper.deleteByPrimaryKey(CommonUtils.parseInt(id, 0));
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int editRatio(Ratio r){
		try {
			int i = ratioMapper.updateByPrimaryKeySelective(r);
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int addRatio(Ratio r){
		try {
			int i = ratioMapper.insert(r);
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int setRatio(String city,String categoryid,String serverType,String ratio){
		Ratio ratio1 = ratioMapper.selectByCity(city, Integer.parseInt(categoryid), Integer.parseInt(serverType));
		if(ratio1!=null){
			return 0;
		}else{
			Ratio r = new Ratio();
			r.setCity(city);
			r.setCategoryid(Integer.parseInt(categoryid));
			r.setServerType((short)Integer.parseInt(serverType));
			r.setRatio((float)Integer.parseInt(ratio));
			try {
				int i = ratioMapper.insert(r);
				return i;
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		}
	}
}
