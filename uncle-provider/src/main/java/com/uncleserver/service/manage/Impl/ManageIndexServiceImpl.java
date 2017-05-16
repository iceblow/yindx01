package com.uncleserver.service.manage.Impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.uncleserver.common.CommonUtils;
import com.uncleserver.dao.AuntBannerMapper;
import com.uncleserver.dao.AuntPushMapper;
import com.uncleserver.dao.HomeAdMapper;
import com.uncleserver.dao.HomeBannerMapper;
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
import com.uncleserver.service.api.Impl.BaseServiceImpl;
import com.uncleserver.service.manage.ManageIndexService;

@Service("manageIndexService")
public class ManageIndexServiceImpl extends BaseServiceImpl implements ManageIndexService {
	
	@Resource
	private HomeBannerMapper homeBannerMapper;
	
	@Resource
	private AuntBannerMapper auntBannerMapper;
	
	@Resource
	private HomeAdMapper homeAdMapper;
	
	@Resource
	private AuntPushMapper auntPushMapper;
	
	

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public String getPath(Integer id) {
		// TODO Auto-generated method stub
		return getFilePathById(id);
	}

	@Override
	public QueryResult<HomeBanner> pageHomeBanner(PQuery pquery,String cityName,String type,String contentid) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(StringUtils.isNotEmpty(cityName)){
			map.put("city", cityName);
		}
		if(StringUtils.isNotEmpty(type)){
			map.put("type", type);
		}
		if(StringUtils.isNotEmpty(contentid)){
			map.put("contentid", contentid);
		}
		map.put("start", pquery.getStartPage());
		map.put("row", pquery.getRows());
		List<HomeBanner> list = homeBannerMapper.getHomeBannerByCity(map);
		long total = list == null ? 0 : homeBannerMapper.getCount(map);
		return new QueryResult<HomeBanner>(list, total, pquery.getPage(), pquery.getRows());
	}

	@Override
	public QueryResult<AuntBanner> pageAuntBanner(PQuery pquery,String type,String contentid) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(StringUtils.isNotEmpty(type)){
			map.put("type", type);
		}
		if(StringUtils.isNotEmpty(contentid)){
			map.put("contentid", contentid);
		}
		map.put("start", pquery.getStartPage());
		map.put("row", pquery.getRows());
		List<AuntBanner> list = auntBannerMapper.getAuntBannerByTypeAndCon(map);
		long total = list == null ? 0 : auntBannerMapper.getCount(map);
		return new QueryResult<AuntBanner>(list, total, pquery.getPage(), pquery.getRows());
	}
	
	@Override
	public QueryResult<HomeAd> pageHomeAd(PQuery pquery) {
		List<HomeAd> list = homeAdMapper.getWithLimit((pquery.getPage() - 1) * pquery.getRows(), pquery.getRows());
		long total = list == null ? 0 : homeAdMapper.getCount();
		return new QueryResult<HomeAd>(list, total, pquery.getPage(), pquery.getRows());
	}

	@Override
	public QueryResult<AuntPush> pageAuntPush(PQuery pquery) {
		List<AuntPush> list = auntPushMapper.getWithLimit((pquery.getPage() - 1) * pquery.getRows(), pquery.getRows());
		long total = list == null ? 0 : auntPushMapper.getCount();
		return new QueryResult<AuntPush>(list, total, pquery.getPage(), pquery.getRows());
	}

	@Override
	public Admin selectAdminByAccount(String account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAdmin(Admin admin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Role> getSelectRole() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Proviences> getAllProviences(){
		return proviencesMapper.selectAll();
	}
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int delappbanner(String id){
		try {
			int i = homeBannerMapper.deleteByPrimaryKey(CommonUtils.parseInt(id, 0));
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int upappbanner(String id){
		HomeBanner banner = homeBannerMapper.selectByPrimaryKey(CommonUtils.parseInt(id, 0));
		if(banner==null){
			return 0;
		}
		int maxSort = homeBannerMapper.getMaxBanner();
		long size = homeBannerMapper.selectCounMaxSort(maxSort);
		if(maxSort==banner.getSort()){
			if(size>=2){	
				banner.setSort(banner.getSort()+1);
				int i = homeBannerMapper.updateByPrimaryKeySelective(banner);
				return i;
			}
			return 2;
		}else{
			banner.setSort(banner.getSort()+1);
			int i = homeBannerMapper.updateByPrimaryKeySelective(banner);
			return i;
		}					
	}
	
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int downsort(String id){
		HomeBanner banner = homeBannerMapper.selectByPrimaryKey(CommonUtils.parseInt(id, 0));
		if(banner==null){
			return 0;
		}
		if(1==banner.getSort()){//sort 最小为1
			return 2;
		}else{
			banner.setSort(banner.getSort()-1);
			int i = homeBannerMapper.updateByPrimaryKeySelective(banner);
			return i;
		}
		
	}
	
	public Cities getgetCitiesByCityName(String cityName){
		return citysMapper.selectCityByCityNane(cityName);
	}
	
	public Cities getgetAreaByCityName(String name){
		Cities result =citysMapper.selectAreaByChildName(name);
		Cities cities =citysMapper.selectCityByChildId(result.getCityid());
		int Provienceid=cities.getProvienceid();
		String cityName=cities.getName();
		result.setProvienceid(Provienceid);
		result.setCityName(cityName);
		return result;
	}
	
	public FileInfo getFileInfoById(int id){
		return fileInfoMapper.selectByPrimaryKey(id);
	}
	
	public List<Cities> getCitiesByPro(String id){
		return citysMapper.selectAll();
	}
	
	public HomeBanner getHomeBannerById(String id){
		return homeBannerMapper.selectByPrimaryKey(CommonUtils.parseInt(id, 0));
	}
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int saveAppBanner(HomeBanner banner){
		try {
			int maxSort = homeBannerMapper.getMaxBanner();
			banner.setSort(maxSort+1);
			int i = homeBannerMapper.insertSelective(banner);
			return i;
		} catch (Exception e) {
			return 0;
		}
	}
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int editAppBanner(HomeBanner banner){
		try {
			int i = homeBannerMapper.updateByPrimaryKeySelective(banner);
			return i;
		} catch (Exception e) {
			return 0;
		}
	}
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public Integer saveFile(String key){
		FileInfo info = new FileInfo();
		info.setAddtime(new Date());
		info.setFilePath(key);
		info.setFiletype((byte)0);
		info.setState((byte)0);
		fileInfoMapper.insertSelective(info);
		return info.getFileid();
	}
	
	
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int delauntbanner(String id){
		try {
			int i = auntBannerMapper.deleteByPrimaryKey(CommonUtils.parseInt(id, 0));
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int upauntbanner(String id){
		AuntBanner banner =auntBannerMapper.selectByPrimaryKey(CommonUtils.parseInt(id, 0));
		if(banner==null){
			return 0;
		}
		int maxSort = auntBannerMapper.getMaxBanner();
		long size = auntBannerMapper.selectCounMaxSort(maxSort);
		if(maxSort==banner.getSort()){
			if(size>=2){	
				banner.setSort(banner.getSort()+1);
				int i = auntBannerMapper.updateByPrimaryKeySelective(banner);
				return i;
			}
			return 2;
		}else{
			banner.setSort(banner.getSort()+1);
			int i = auntBannerMapper.updateByPrimaryKeySelective(banner);
			return i;
		}	
		
		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int downauntbannersort(String id){
		AuntBanner banner = auntBannerMapper.selectByPrimaryKey(CommonUtils.parseInt(id, 0));
		if(banner==null){
			return 0;
		}
		if(1==banner.getSort()){//sort 最小为1
			return 2;
		}else{
			banner.setSort(banner.getSort()-1);
			int i = auntBannerMapper.updateByPrimaryKeySelective(banner);
			return i;
		}
		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int saveAuntBanner(AuntBanner banner){
		try {
			int maxSort = auntBannerMapper.getMaxBanner();
			banner.setSort(maxSort+1);
			int i = auntBannerMapper.insertSelective(banner);
			return i;
		} catch (Exception e) {
			return 0;
		}
	}
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int editAuntBanner(AuntBanner banner){
		try {
			int i = auntBannerMapper.updateByPrimaryKeySelective(banner);
			return i;
		} catch (Exception e) {
			return 0;
		}
	}
	
	public AuntBanner getAuntBannerById(String id){
		return auntBannerMapper.selectByPrimaryKey(CommonUtils.parseInt(id, 0));
	}
	
	public List<Company> getAllCompany(){
		return companyMapper.getAllCompany();
	}
	
	public List<Aunt> getAllAunt(){
		return auntMapper.selectAllAunt();
	}

	@Override
	public List<Area> selectArea(int id) {
		List<Area> list = areaMapper.selectByCityid(id);
		return list;
	}
}
