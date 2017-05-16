package com.uncleserver.service.manage.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.model.Aunt;
import com.uncleserver.model.CategorySecond;
import com.uncleserver.model.Cities;
import com.uncleserver.model.Company;
import com.uncleserver.model.CompanyWithBLOBs;
import com.uncleserver.model.HomeAd;
import com.uncleserver.model.HomeIcon;
import com.uncleserver.model.HomeItem;
import com.uncleserver.model.Order;
import com.uncleserver.model.Proviences;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;
import com.uncleserver.modelVo.AddItemVo;
import com.uncleserver.modelVo.AuntModel;
import com.uncleserver.modelVo.HomeAdContentVo;
import com.uncleserver.modelVo.HomeAdVo;
import com.uncleserver.modelVo.HomeItemVo;
import com.uncleserver.service.api.Impl.BaseServiceImpl;
import com.uncleserver.service.manage.ManageHomeService;

public class ManageHomeServiceImpl extends BaseServiceImpl implements
		ManageHomeService {

	@Override
	public QueryResult<HomeItemVo> geHomeIconByCity(PQuery pquery, String city) {
		List<HomeIcon> homeIconList = null;
		long total=0;
		if(city==null){
			 homeIconList = homeIconMapper.selectByPage(pquery.getStartPage(),pquery.getRows());
				 total = homeIconMapper.selectByPageCount();
		}else{
		 homeIconList = homeIconMapper.selectByCityAndPage(city,pquery.getStartPage(),pquery.getRows());
		 total = homeIconMapper.managePageIconCount(city);
		}
		List<HomeItemVo> homeItemVoList = new ArrayList<HomeItemVo>();
		if(homeIconList != null && homeIconList.size()>0){
			for(HomeIcon homeIcon:homeIconList){
				HomeItemVo homeItemVo = new HomeItemVo();
				homeItemVo.setAddtime(CommonUtils.getTimeFormat(homeIcon.getAddtime(), null));
				homeItemVo.setCity(city);
				homeItemVo.setContent(Integer.parseInt(homeIcon.getContent()));
				homeItemVo.setDataid(homeIcon.getDataid());
				homeItemVo.setSort(homeIcon.getSort());
				if(Integer.parseInt(homeIcon.getContent()) > 0){
					CategorySecond second = categorySecondMapper.selectByPrimaryKey(Integer.parseInt(homeIcon.getContent()));
					if(null != second){
						homeItemVo.setTypeName(second.getName());
						if(second.getIconid() != null && second.getIconid()>0){
							String picurl = getFilePathById(second.getIconid());
							homeItemVo.setPicurl(picurl);
						}
					}
				}
				homeItemVoList.add(homeItemVo);
			}
		}
	    return new QueryResult<HomeItemVo>(homeItemVoList, total, pquery.getPage(), pquery.getRows());
	}

	@Override
	public List<Proviences> selectProviences() {
		List<Proviences> proviencesList = proviencesMapper.selectAll();
		return proviencesList;
	}

	@Override
	public List<Cities> selectCitiesByProvienceid(Integer provienceid) {
		List<Cities> cityList = citysMapper.selectAll();
		return cityList;
	}

	@Override
	public Cities selectCityByCityId(Integer cityid) {
		Cities city = citysMapper.selectByPrimaryKey(cityid);
		return city;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public Integer deleteicon(Integer iconid) {
		int result = homeIconMapper.deleteByPrimaryKey(iconid);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public Integer UpOrDowniconSort(Integer iconid, Integer type,String cityName) {
		int result = 1;
		if(type == 1){
			//排序上升
			HomeIcon homeicon = homeIconMapper.selectByPrimaryKey(iconid);
			int minsort = homeIconMapper.selectMinSort(cityName);
			if(minsort == homeicon.getSort()){
				return 102;
			}
			int sort = homeicon.getSort();
			HomeIcon beforIcon = homeIconMapper.selectBeforeSort(sort,cityName);
			homeicon.setSort(beforIcon.getSort());
			beforIcon.setSort(sort);
			result = homeIconMapper.updateByPrimaryKeySelective(homeicon);
			result = homeIconMapper.updateByPrimaryKeySelective(beforIcon);
			return result;
		}else{
			//排序下降
			HomeIcon homeicon = homeIconMapper.selectByPrimaryKey(iconid);
			int maxsort = homeIconMapper.selectMaxSort(cityName);
			if(maxsort == homeicon.getSort()){
				return 102;
			}
			int sort = homeicon.getSort();
			HomeIcon afterIcon = homeIconMapper.selectAfterSort(sort,cityName);
			homeicon.setSort(afterIcon.getSort());
			afterIcon.setSort(sort);
			result = homeIconMapper.updateByPrimaryKeySelective(homeicon);
			result = homeIconMapper.updateByPrimaryKeySelective(afterIcon);
			return result;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public Integer insertIcon(String area, Integer categoryid) {
		Integer result = 0;
	    List<HomeIcon> homeIcons = homeIconMapper.selectByCity(area);
	    if(homeIcons != null && homeIcons.size()>0){
	    	boolean flag = false;
	    	for(HomeIcon homeIcon:homeIcons){
	    		if(Integer.parseInt(homeIcon.getContent()) == categoryid){
	    			flag = true;
	    			break;
	    		}
	    	}
	    	if(flag){
	    		result = 102;
	    		return result;
	    	}
	    }
	    HomeIcon homeIcon = new HomeIcon();
	    homeIcon.setAddtime(new Date());
	    homeIcon.setCity(area);
	    homeIcon.setContent(""+categoryid);
	    Integer maxSort = homeIconMapper.selectMaxSort(area);
	    if(maxSort != null){
	        maxSort++;
	    }else {
	    	maxSort = 1;
	    }
	    homeIcon.setSort(maxSort);
	    homeIconMapper.insert(homeIcon);
	    result = 1;
		return result;
	}

	@Override
	public QueryResult<HomeItemVo> geHomeItemByCity(PQuery pquery,
			String cityName, Integer type) {
		List<HomeItem> homeItemList =null;
		long total =0;
		if(cityName==null){
			 homeItemList = homeItemMapper.selectByPage(pquery.getStartPage(),pquery.getRows());
			 total = homeItemMapper.selectByPageCount();
		}else {
			 homeItemList = homeItemMapper.selectByCityAndPage(cityName,type,pquery.getStartPage(),pquery.getRows());
			 total = homeItemMapper.managePageItemCount(cityName,type);
		}
		
		List<HomeItemVo> homeItemVoList = new ArrayList<HomeItemVo>();
		if(homeItemList != null && homeItemList.size()>0){
			for(HomeItem homeItem:homeItemList){
			   HomeItemVo homeItemVo = new HomeItemVo();
			   if(homeItem.getType() == 3){
				   Aunt aunt = auntMapper.selectByPrimaryKey(Integer.parseInt(homeItem.getContentid()));
				   homeItemVo.setAddtime(CommonUtils.getTimeFormat(homeItem.getAddtime(), null));
				   homeItemVo.setDataid(homeItem.getDataid());
				   homeItemVo.setName(aunt.getRealName());
				   homeItemVo.setSort(homeItem.getSort());
				   homeItemVo.setCity(homeItem.getCity());
				   homeItemVo.setContent(Integer.parseInt(homeItem.getContentid()));
				   if(aunt.getAvatar() != null && aunt.getAvatar()>0){
					   homeItemVo.setPicurl(getFilePathById(aunt.getAvatar()));
				   }else {
					   homeItemVo.setPicurl(aunt.getThirdAvatar());
				   }
				   
			   }else {
				   Company company = companyMapper.selectByPrimaryKey(Integer.parseInt(homeItem.getContentid()));
				   homeItemVo.setAddtime(CommonUtils.getTimeFormat(homeItem.getAddtime(), null));
				   homeItemVo.setDataid(homeItem.getDataid());
				   homeItemVo.setName(company.getName());
				   homeItemVo.setSort(homeItem.getSort());
				   homeItemVo.setCity(homeItem.getCity());
				   homeItemVo.setContent(Integer.parseInt(homeItem.getContentid()));
				   if(company.getLogoPicid() != null && company.getLogoPicid()>0){
					   homeItemVo.setPicurl(getFilePathById(company.getLogoPicid()));
				   }
			   }
			   homeItemVoList.add(homeItemVo);
			}
		}
		
		return new QueryResult<HomeItemVo>(homeItemVoList, total, pquery.getPage(), pquery.getRows());
	}

	@Override
	public QueryResult<AddItemVo> selectHomeItemlist(PQuery pquery,
			String cityName, Integer type) {
		List<AddItemVo> homeItemVoList = new ArrayList<AddItemVo>();
		long total = 0;
	    if(type == 3){
	    	List<Aunt> auntList = auntMapper.selectAuntByCompanyId(0, null, pquery.getStartPage(),pquery.getRows());
	    	total = auntMapper.countAuntByCompanyId(0, null);
	    	if(auntList != null && auntList.size()>0){
	    		for(Aunt aunt:auntList){
	    			AddItemVo addItemVo = new AddItemVo();
	    			addItemVo.setId(aunt.getAuntid());
	    			addItemVo.setName(aunt.getRealName());
	    			homeItemVoList.add(addItemVo);
	    		}
	    	}
	    	
	    	
	    }else {
	    	List<CompanyWithBLOBs> conpanyList = companyMapper.selectByCityAndType(cityName, type, pquery.getStartPage(),pquery.getRows());
	    	total = companyMapper.manageCountConpanyByCityAndType(cityName, type);
	    	if(conpanyList != null && conpanyList.size()>0){
	    		for(CompanyWithBLOBs company:conpanyList){
	    			AddItemVo addItemVo = new AddItemVo();
	    			addItemVo.setId(company.getCompanyid());
	    			addItemVo.setName(company.getName());
	    			homeItemVoList.add(addItemVo);
	    		}
	    	}
	    	
	    }
		return new QueryResult<AddItemVo>(homeItemVoList, total, pquery.getPage(), pquery.getRows());
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int insertcontent(String area, Integer type, String[] goodstips) {
		int result = 1;
		if(goodstips != null){
		   for(int i=0;i<goodstips.length;i++){
			   String[] ids = goodstips[i].split(",");
			   if(ids != null){
				   for(int j=0;j<ids.length;j++){
					   if(!CommonUtils.isEmptyString(ids[j])){
						   List<HomeItem> homeItems = homeItemMapper.selectByCityAndType(area, type);
						   boolean flag = false;
						   for(HomeItem homeItem:homeItems){
							   if(homeItem.getContentid().equals(ids[j])){
								   flag = true;
								   break;
							   }
						   }
						   if(flag){
							   result=102;
						   }else {
							   Integer maxSort = homeItemMapper.selectMaxSort(area, type);
							   if(maxSort == null){
								   maxSort = 1;
							   }else {
								   maxSort++;
							   }
							   HomeItem item = new HomeItem();
							   item.setAddtime(new Date());
							   item.setCity(area);
							   item.setContentid(ids[j]);
							   item.setSort(maxSort);
							   int typeI = type;
							   item.setType((byte)typeI);
							   homeItemMapper.insert(item);
						   }
					   }
				   }
				   
			   }
			   
		   }
		}
		return result;
	}

	@Override
	public HomeItem getHomeItem(Integer dataid) {
		HomeItem homeItem = homeItemMapper.selectByPrimaryKey(dataid);
		return homeItem;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int UpOrDownContentSort(Integer dataid, Byte type, String city,Integer companyType) {
		int result = 1;
		if(type == 1){
			//排序上升
			HomeItem homeItem = homeItemMapper.selectByPrimaryKey(dataid);
			int minsort = homeItemMapper.selectMinSort(city,companyType);
			if(minsort == homeItem.getSort()){
				return 102;
			}
			int sort = homeItem.getSort();
			HomeItem beforIcon = homeItemMapper.selectBeforeSort(sort, city, companyType);
			homeItem.setSort(beforIcon.getSort());
			beforIcon.setSort(sort);
			result = homeItemMapper.updateByPrimaryKeySelective(homeItem);
			result = homeItemMapper.updateByPrimaryKeySelective(beforIcon);
			return result;
		}else{
			//排序下降
			HomeItem homeicon = homeItemMapper.selectByPrimaryKey(dataid);
			int maxsort = homeItemMapper.selectMaxSort(city,companyType);
			if(maxsort == homeicon.getSort()){
				return 102;
			}
			int sort = homeicon.getSort();
			HomeItem afterIcon = homeItemMapper.selectAfterSort(sort, city, companyType);
			homeicon.setSort(afterIcon.getSort());
			afterIcon.setSort(sort);
			result = homeItemMapper.updateByPrimaryKeySelective(homeicon);
			result = homeItemMapper.updateByPrimaryKeySelective(afterIcon);
			return result;
		}
	}

	@Override
	public Integer deleteContent(Integer dataid) {
		int result = homeItemMapper.deleteByPrimaryKey(dataid);
		return result;
	}

	@Override
	public List<Cities> selectAllCitys() {
		List<Cities> cityes = citiesMapper.selectAll();
		return cityes;
	}

	@Override
	public QueryResult<HomeAdVo> getBanner(PQuery pQuery,String cityName) {
		List<HomeAd> homeAdList = homeAdMapper.selectByCityAndPage(cityName,pQuery.getStartPage(),pQuery.getRows());
		long total = homeAdMapper.managePageIconCount(cityName);
		List<HomeAdVo> homeAdVoList = new ArrayList<HomeAdVo>();
		if(homeAdList != null && homeAdList.size()>0){
		     for(HomeAd homeAd:homeAdList){
		    	 HomeAdVo homeAdVo = new HomeAdVo();
		    	 homeAdVo.setAddtime(CommonUtils.getTimeFormat(homeAd.getAddtime(), null));
		    	 homeAdVo.setCity(cityName);
		    	 homeAdVo.setDataid(homeAd.getDataid());
		    	 homeAdVo.setSort(homeAd.getSort());
		    	 if(homeAd.getType() == 0){
		    		 homeAdVo.setBannerTypeName("外链");
		    	 }else if(homeAd.getType() == 1){
		    		 homeAdVo.setBannerTypeName("公司");
		    	 }else if(homeAd.getType() == 2){
		    		 homeAdVo.setBannerTypeName("阿姨");
		    	 }
		    	 if(homeAd.getPicid() != null && homeAd.getPicid()>0){
		    		 homeAdVo.setPicurl(getFilePathById(homeAd.getPicid()));
		    	 }
		    	 homeAdVoList.add(homeAdVo);
		     }
		}
		 return new QueryResult<HomeAdVo>(homeAdVoList, total, pQuery.getPage(), pQuery.getRows());
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int deleteHomeAd(Integer dataid) {
		int result = homeAdMapper.deleteByPrimaryKey(dataid);
		return result;
	}

	@Override
	public List<HomeAdContentVo> getHomeAdContentList(Integer type,
			String cityName) {
		List<HomeAdContentVo> homeAdList = new ArrayList<>();
		if(type == 1){
			List<CompanyWithBLOBs> companyList = companyMapper.selectByCity(cityName);
			if(companyList != null && companyList.size()>0){
				for(CompanyWithBLOBs companyWithBLOBs:companyList){
					HomeAdContentVo homeAdContentVo = new HomeAdContentVo();
					homeAdContentVo.setId(companyWithBLOBs.getCompanyid());
					homeAdContentVo.setName(companyWithBLOBs.getName());
					homeAdContentVo.setType(type);
					homeAdList.add(homeAdContentVo);
				}
			}
		}else if(type == 2){
			List<Aunt> auntList = auntMapper.selectAnutsByCompanyId(0);
			if(auntList != null && auntList.size()>0){
			   for(Aunt aunt:auntList){
				   	HomeAdContentVo homeAdContentVo = new HomeAdContentVo();
					homeAdContentVo.setId(aunt.getAuntid());
					homeAdContentVo.setName(aunt.getRealName());
					homeAdContentVo.setType(type);
					homeAdList.add(homeAdContentVo);
			   }
			}
		}
		return homeAdList;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int addBannerData(String cityName, Integer type, String linkurl,
			Integer contentid, Integer fileid) {
		Integer result = 0;
		if(type == 0){
			HomeAd homeAd = new HomeAd();
			homeAd.setCity(cityName);
			homeAd.setContent(linkurl);
			homeAd.setPicid(fileid);
			int typeI = type;
			homeAd.setType((byte)typeI);
			Integer maxSort = homeAdMapper.selectMaxSort(cityName);
			if(maxSort == null){
				maxSort = 1;
			}else {
				maxSort++;
			}
			homeAd.setSort(maxSort);
			homeAdMapper.insert(homeAd);
		}else {
			HomeAd homeAd = new HomeAd();
			homeAd.setCity(cityName);
			homeAd.setContent(""+contentid);
			homeAd.setPicid(fileid);
			int typeI = type;
			homeAd.setType((byte)typeI);
			Integer maxSort = homeAdMapper.selectMaxSort(cityName);
			if(maxSort == null){
				maxSort = 1;
			}else {
				maxSort++;
			}
			homeAd.setSort(maxSort);
			homeAdMapper.insert(homeAd);
		}
		result = 1;
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int UpOrDownBannerSort(Integer dataid, int type,String cityName) {
		int result = 1;
		if(type == 1){
			//排序上升
			HomeAd homeItem = homeAdMapper.selectByPrimaryKey(dataid);
			int minsort = homeAdMapper.selectMinSort(cityName);
			if(minsort == homeItem.getSort()){
				return 102;
			}
			int sort = homeItem.getSort();
			HomeAd beforIcon = homeAdMapper.selectBeforeSort(sort, cityName);
			homeItem.setSort(beforIcon.getSort());
			beforIcon.setSort(sort);
			result = homeAdMapper.updateByPrimaryKeySelective(homeItem);
			result = homeAdMapper.updateByPrimaryKeySelective(beforIcon);
			return result;
		}else{
			//排序下降
			HomeAd homeicon = homeAdMapper.selectByPrimaryKey(dataid);
			int maxsort = homeAdMapper.selectMaxSort(cityName);
			if(maxsort == homeicon.getSort()){
				return 102;
			}
			int sort = homeicon.getSort();
			HomeAd afterIcon = homeAdMapper.selectAfterSort(sort, cityName);
			homeicon.setSort(afterIcon.getSort());
			afterIcon.setSort(sort);
			result = homeAdMapper.updateByPrimaryKeySelective(homeicon);
			result = homeAdMapper.updateByPrimaryKeySelective(afterIcon);
			return result;
		}
	}

	@Override
	public HomeAd getHomeAd(Integer dataid) {
		
		return homeAdMapper.selectByPrimaryKey(dataid);
	}

}
