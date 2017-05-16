package com.uncleserver.service.api.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.model.Aunt;
import com.uncleserver.model.AuntExtra;
import com.uncleserver.model.AuntLike;
import com.uncleserver.model.CategorySecond;
import com.uncleserver.model.CompanyRange;
import com.uncleserver.model.CompanyWithBLOBs;
import com.uncleserver.model.HomeAd;
import com.uncleserver.model.HomeBanner;
import com.uncleserver.model.HomeIcon;
import com.uncleserver.model.HomeItem;
import com.uncleserver.model.User;
import com.uncleserver.modelVo.ApiResult;
import com.uncleserver.modelVo.AuntDetailValue;
import com.uncleserver.modelVo.AuntModel;
import com.uncleserver.modelVo.AuntValue;
import com.uncleserver.modelVo.CompanyDetail;
import com.uncleserver.modelVo.CompanyModel;
import com.uncleserver.modelVo.CompanyValue;
import com.uncleserver.modelVo.HomeAdValue;
import com.uncleserver.modelVo.HomeAuntValue;
import com.uncleserver.modelVo.HomeBannerValue;
import com.uncleserver.modelVo.HomeIconValue;
import com.uncleserver.modelVo.HomeItemValue;
import com.uncleserver.modelVo.SearchAuntModel;
import com.uncleserver.modelVo.SearchCompanyModel;
import com.uncleserver.modelVo.ServerValue;
import com.uncleserver.service.api.HomeService;

@Service("homeService")
public class HomeServiceImpl extends BaseServiceImpl implements HomeService {

	@Override
	public ApiResult homeUser(String city) {
		ApiResult result = new ApiResult();
		List<HomeBannerValue> bannerValues = new ArrayList<>();
		List<HomeIconValue> iconValues = new ArrayList<>();
		List<HomeAdValue> adValues = new ArrayList<>();
		List<HomeItemValue> homeValues = new ArrayList<>();
		List<HomeItemValue> repairValues = new ArrayList<>();
		List<HomeItemValue> mediumValues = new ArrayList<>();
		List<HomeAuntValue> auntValues = new ArrayList<>();

		List<HomeBanner> bannerList = homeBannerMapper.selectByCity(city);
		List<HomeIcon> iconLIst = homeIconMapper.selectByCity(city);
		List<HomeAd> adList = homeAdMapper.selectByCity(city);
		List<HomeItem> homeList = homeItemMapper.selectByCityAndType(city, 1);
		List<HomeItem> repairList = homeItemMapper.selectByCityAndType(city, 0);
		List<HomeItem> mediumList = homeItemMapper.selectByCityAndType(city, 2);
		List<HomeItem> auntList = homeItemMapper.selectByCityAndType(city, 3);
		if (bannerList != null && bannerList.size() > 0) {
			for (HomeBanner homeBanner : bannerList) {
				bannerValues.add(createHomeBannerValue(homeBanner));
			}
		}   
		if (iconLIst != null && iconLIst.size() > 0) {
			for (HomeIcon homeIcon : iconLIst) {
				iconValues.add(createHomeIconValue(homeIcon));
			}
		}
		if (adList != null && adList.size() > 0) {
			for (HomeAd homeAd : adList) {
				adValues.add(createHomeAdValue(homeAd));
			}
		}
		if (homeList != null && homeList.size() > 0) {
			for (HomeItem homeItem : homeList) {
				homeValues.add(createHomeItemValue(homeItem));
			}
		}
		if (repairList != null && repairList.size() > 0) {
			for (HomeItem homeItem : repairList) {
				repairValues.add(createHomeItemValue(homeItem));
			}
		}
		if (mediumList != null && mediumList.size() > 0) {
			for (HomeItem homeItem : mediumList) {
				mediumValues.add(createHomeItemValue(homeItem));
			}
		}
		if (auntList != null && auntList.size() > 0) {
			for (HomeItem homeItem : auntList) {
				auntValues.add(createAuntValue(homeItem));
			}
		}
		result.setCode("1");
		result.setMessage("请求成功");
		HashMap<String, Object> map = new HashMap<>();
		map.put("bannerList", bannerValues);
		map.put("iconList", iconValues);
		map.put("adList", adValues);
		map.put("homeList", homeValues);
		map.put("repairList", repairValues);
		map.put("mediumList", mediumValues);
		map.put("auntList", auntValues);
		result.setResult(map);
		return result;
	}

	private HomeBannerValue createHomeBannerValue(HomeBanner homeBanner) {
		HomeBannerValue homeBannerValue = new HomeBannerValue();
		homeBannerValue.setBannerid(homeBanner.getBannerid());
		homeBannerValue.setType(homeBanner.getType());
		homeBannerValue.setContentid(homeBanner.getContentid());
		if (homeBanner.getPicid() != null && homeBanner.getPicid() > 0) {
			// String filePath =
			// fileInfoMapper.selectByPrimaryKey(homeBanner.getPicid()).getFilePath();
			homeBannerValue.setPicurl(getFilePathById(homeBanner.getPicid()));
			// homeBannerValue.setPicurl(filePath);
		}
		return homeBannerValue;
	}

	private HomeIconValue createHomeIconValue(HomeIcon homeIcon) {
		HomeIconValue homeIconValue = new HomeIconValue();
		homeIconValue.setContent(Integer.parseInt(homeIcon.getContent()));

		/*
		 * if(homeIcon.get != null && homeIcon.getPicid()>0){
		 * 
		 * }
		 */

		if (Integer.parseInt(homeIcon.getContent()) == 0) {
			homeIconValue.setName("更多");
			homeIconValue.setNeedpic(0);
		} else {
			CategorySecond second = categorySecondMapper.selectByPrimaryKey(Integer.parseInt(homeIcon.getContent()));
			homeIconValue.setName(second.getName());
			homeIconValue.setNeedpic(second.getNeedpic());
			if (second.getIconid() != null && second.getIconid() > 0) {
				homeIconValue.setPicurl(getFilePathById(second.getIconid()));
			}
		}

		return homeIconValue;
	}

	private HomeAdValue createHomeAdValue(HomeAd homeAd) {
		HomeAdValue homeAdValue = new HomeAdValue();
		homeAdValue.setType(homeAd.getType());
		homeAdValue.setContentid(homeAd.getContent());
		if (homeAd.getPicid() != null && homeAd.getPicid() > 0) {
			// String filePath =
			// fileInfoMapper.selectByPrimaryKey(homeAd.getPicid()).getFilePath();
			homeAdValue.setPicurl(getFilePathById(homeAd.getPicid()));

		}
		return homeAdValue;
	}

	private HomeItemValue createHomeItemValue(HomeItem homeItem) {
		HomeItemValue homeItemValue = new HomeItemValue();
		homeItemValue.setCompanyid(Integer.parseInt(homeItem.getContentid()));
		CompanyWithBLOBs company = companyMapper.selectByPrimaryKey(Integer.parseInt(homeItem.getContentid()));
		homeItemValue.setName(company.getName());
		homeItemValue.setProfile(company.getProfile());
		if (company.getLogoPicid() != null && company.getLogoPicid() > 0) {
			// String filePath =
			// fileInfoMapper.selectByPrimaryKey(company.getLogoPicid()).getFilePath();
			if (company.getLogoPicid() != null && company.getLogoPicid() > 0) {
				homeItemValue.setPicurl(getFilePathById(company.getLogoPicid()));
			}

		}
		return homeItemValue;
	}

	private HomeAuntValue createAuntValue(HomeItem homeItem) {
		HomeAuntValue homeAuntValue = new HomeAuntValue();
		homeAuntValue.setAuntid(Integer.parseInt(homeItem.getContentid()));
		Aunt aunt = auntMapper.selectByPrimaryKey(Integer.parseInt(homeItem.getContentid()));
		if (aunt.getBirthday() != null) {
			int age = CommonUtils.getAge(aunt.getBirthday(), new Date());
			homeAuntValue.setAge("" + age);
		}

		homeAuntValue.setName(aunt.getRealName());
		homeAuntValue.setOrigin_place(aunt.getOriginPlace());
		AuntExtra auntExta = auntExtramapper.selectByPrimaryKey(Integer.parseInt(homeItem.getContentid()));
		homeAuntValue.setScore("" + auntExta.getScore().toString());
		if (aunt.getAvatar() != null && aunt.getAvatar() > 0) {
			// String filePath =
			// fileInfoMapper.selectByPrimaryKey(aunt.getAvatar()).getFilePath();
			if (aunt.getAvatar() != null && aunt.getAvatar() > 0) {
				homeAuntValue.setPicurl(getFilePathById(aunt.getAvatar()));
			}
			// homeAuntValue.setPicurl(filePath);
		} else if (CommonUtils.isEmptyString(aunt.getThirdAvatar())) {
			homeAuntValue.setPicurl(aunt.getThirdAvatar());
		}
		return homeAuntValue;
	}

	@Override
	public ApiResult auntList(String screentype, String longitude, String latitude, String distance_from,
			String distance_to, String name_letter, String comment_type, String servertype, String agetype,
			String yeartype, String page) {
		ApiResult result = new ApiResult();
		List<AuntModel> modelList = auntMapper.selectByCondition(CommonUtils.parseDouble(longitude, 0),
				CommonUtils.parseDouble(latitude, 0), CommonUtils.parseDouble(distance_from, 0),
				CommonUtils.parseDouble(distance_to, 0), name_letter, CommonUtils.parseInt(comment_type, 0),
				CommonUtils.parseInt(servertype, 0), CommonUtils.parseInt(agetype, 0),
				CommonUtils.parseInt(yeartype, 0), (CommonUtils.parseInt(page, 1) - 1) * 10, 10);
		List<AuntModel> modelListMore = auntMapper.selectByCondition(CommonUtils.parseDouble(longitude, 0),
				CommonUtils.parseDouble(latitude, 0), CommonUtils.parseDouble(distance_from, 0),
				CommonUtils.parseDouble(distance_to, 0), name_letter, CommonUtils.parseInt(comment_type, 0),
				CommonUtils.parseInt(servertype, 0), CommonUtils.parseInt(agetype, 0),
				CommonUtils.parseInt(yeartype, 0), (CommonUtils.parseInt(page, 1)) * 10, 10);
		result.setCode("1");
		result.setMessage("请求成功");
		HashMap<String, Object> map = new HashMap<>();
		if (modelListMore != null && modelListMore.size() > 0) {
			map.put("havemore", 1);
		} else {
			map.put("havemore", 0);
		}
		List<AuntValue> auntValues = new ArrayList<>();
		if (modelList != null && modelList.size() > 0) {
			for (AuntModel model : modelList) {
				AuntValue value = new AuntValue();
				value.setAuntid(model.getAuntid());
				if (model.getAvatar() != null && model.getAvatar() > 0) {
					String picurl = getFilePathById(model.getAvatar());
					value.setAvatarurl(picurl);
				} else {
					value.setAvatarurl(model.getThirdAvatar());
				}
				value.setName(model.getRealName());
				value.setScore(model.getScore());
				value.setAge("" + CommonUtils.getAge(model.getBirthday(), new Date()));
				value.setOrigin_place(model.getOriginPlace());
				value.setDistance("" + model.getDistance());
				auntValues.add(value);
			}
		}
		map.put("auntList", auntValues);
		result.setResult(map);
		return result;
	}

	@Override
	public ApiResult auntDetail(User user, String auntid) {
		ApiResult result = new ApiResult();
		Aunt aunt = auntMapper.selectByPrimaryKey(Integer.parseInt(auntid));
		AuntExtra extra = auntExtramapper.selectByAuntId(Integer.parseInt(auntid));
		if (aunt == null || extra == null) {
			result.setCode("2");
			result.setMessage("请求失败,阿姨不存在");
		}

		AuntDetailValue value = new AuntDetailValue();
		value.setAuntid(aunt.getAuntid());
		if (aunt.getAvatar() != null && aunt.getAvatar() > 0) {
			String picurl = getFilePathById(aunt.getAvatar());
			value.setAvatarurl(picurl);
		}
		value.setName(aunt.getRealName());
		value.setScore(extra.getScore());
		Long count = auntLikeMapper.selectLikeCount(CommonUtils.parseInt(auntid, 0));
		if(count != null){
			long countL = count;
			value.setLickcount((int)countL);
		}else {
			value.setLickcount(0);
		}
		if(user != null){
			AuntLike auntLike = auntLikeMapper.selectLikeData(user.getUserid(), CommonUtils.parseInt(auntid, 0));
			if(auntLike != null){
				value.setIslike(1);
			}else {
				value.setIslike(0);
			}
		}else {
			value.setIslike(0);
		}
		
		value.setShare_url("https://www.baidu.com/");
		value.setAge(CommonUtils.getAge(aunt.getBirthday(), new Date()));
		value.setOrigin_place(aunt.getOriginPlace());
		value.setNation(aunt.getNation());
		value.setMarriage(aunt.getMarriage());
		value.setPolitical(aunt.getPolitical());
		value.setCulture(aunt.getCulture());
		value.setReligion(aunt.getReligion());
		value.setLanguage(aunt.getLanguage());
		value.setWork_his(aunt.getWorkHis());
		value.setRegister(CommonUtils.getTimeFormat(aunt.getAddtime(), "yyyy-MM-dd HH:mm:ss"));
		value.setHeight("" + aunt.getHeight());
		value.setWeight("" + aunt.getWeight());
		value.setConstellation(aunt.getConstellation());
		value.setZodiacl(aunt.getZodiac());
		value.setSelf_comment(aunt.getSelfComment());
		HashMap<String, Object> map = new HashMap<>();
		result.setCode("1");
		result.setMessage("请求成功");
		map.put("auntInfo", value);
		result.setResult(map);
		return result;
	}

	@Override
	public ApiResult companyList(String screentype, String companytype, String longitude, String latitude,
			String distance_from, String distance_to, String name_letter, String comment_type, String servertype,
			String titletype, String startype, String page) {
		ApiResult result = new ApiResult();
		List<CompanyModel> modelList = companyMapper.selectByCondition(CommonUtils.parseInt(companytype, 3),
				CommonUtils.parseDouble(longitude, 0), CommonUtils.parseDouble(latitude, 0),
				CommonUtils.parseDouble(distance_from, 0), CommonUtils.parseDouble(distance_to, 0), name_letter,
				CommonUtils.parseInt(comment_type, 1), CommonUtils.parseInt(servertype, 1),
				CommonUtils.parseInt(titletype, 1), CommonUtils.parseInt(startype, 1),
				(Integer.parseInt(page) - 1) * 10, 10);
		List<CompanyModel> modelListMore = companyMapper.selectByCondition(CommonUtils.parseInt(companytype, 3),
				CommonUtils.parseDouble(longitude, 0), CommonUtils.parseDouble(latitude, 0),
				CommonUtils.parseDouble(distance_from, 0), CommonUtils.parseDouble(distance_to, 0), name_letter,
				CommonUtils.parseInt(comment_type, 1), CommonUtils.parseInt(servertype, 1),
				CommonUtils.parseInt(titletype, 1), CommonUtils.parseInt(startype, 1), (Integer.parseInt(page)) * 10,
				10);
		result.setCode("1");
		result.setMessage("请求成功");
		HashMap<String, Object> map = new HashMap<>();
		if (modelListMore != null && modelListMore.size() > 0) {
			map.put("havemore", 1);
		} else {
			map.put("havemore", 0);
		}
		List<CompanyValue> auntValues = new ArrayList<>();
		if (modelList != null && modelList.size() > 0) {
			for (CompanyModel model : modelList) {
				CompanyValue value = new CompanyValue();
				value.setCompanyid(model.getCompanyid());
				if (model.getListpic() != null && model.getListpic() > 0) {
					String picurl = getFilePathById(model.getListpic());
					value.setPicurl(picurl);
				} else {

				}
				value.setName(model.getName());
				value.setProfile(model.getProfile());
				auntValues.add(value);
			}
		}
		map.put("companyList", auntValues);
		result.setResult(map);
		return result;
	}

	@Override
	public ApiResult companyDetail(String companyid) {
		ApiResult result = new ApiResult();

		HashMap<String, Object> map = new HashMap<>();
		CompanyWithBLOBs company = companyMapper.selectByPrimaryKey(Integer.parseInt(companyid));
		if (company == null) {
			result.setCode("2");
			result.setMessage("请求失败,公司不存在");
			return result;
		}
		CompanyDetail value = new CompanyDetail();
		value.setCompanyid(company.getCompanyid());
		value.setName(company.getName());
		value.setProfile(company.getProfile());
		value.setType(company.getType().toString());
		value.setCity(company.getCity());
		if (company.getLogoPicid() != null && company.getLogoPicid() > 0) {
			value.setLogo_url(getFilePathById(company.getLogoPicid()));
		}
		List<String> picurllist = new ArrayList<>();
		if (!CommonUtils.isEmptyString(company.getPiclist())) {
			String[] ids = company.getPiclist().split(",");
			for (int i = 0; i < ids.length; i++) {
				if (CommonUtils.isEmptyString(ids[i])) {
					picurllist.add(getFilePathById(Integer.parseInt(ids[i])));
				}
			}
		}
		value.setPicurllist(picurllist);
		value.setYear_create(CommonUtils.getTimeFormat(company.getYearCreate(), null));
		value.setPeople_count(company.getPeopleCount());
		value.setAddress(company.getAddress());
		value.setRelation_phone(company.getRelationPhone());
		List<CompanyRange> rangeList = companyRangeMapper.selectByCompany(company.getCompanyid());
		List<ServerValue> serverList = new ArrayList<>();
		if (rangeList != null && rangeList.size() > 0) {
			for (CompanyRange range : rangeList) {
				ServerValue serverValue = new ServerValue();
				CategorySecond second = categorySecondMapper.selectByPrimaryKey(range.getCategoryid());
				if (null != second) {
					serverValue.setServer_name(second.getName());
					serverValue.setServerid(range.getCategoryid());
					serverList.add(serverValue);
				}
			}
			value.setServerlist(serverList);
		}
		map.put("companyInfo", value);
		List<AuntModel> modelList = auntMapper.selectByCompanyId(company.getCompanyid(), company.getLongitude(),
				company.getLatitude(), 0, 10);
		List<AuntModel> modelListMore = auntMapper.selectByCompanyId(company.getCompanyid(), company.getLongitude(),
				company.getLatitude(), 1, 10);
		if (modelListMore != null && modelListMore.size() > 0) {
			map.put("havemore", 1);
		} else {
			map.put("havemore", 0);
		}
		List<AuntValue> auntValues = new ArrayList<>();
		if (modelList != null && modelList.size() > 0) {
			for (AuntModel model : modelList) {
				AuntValue auntValue = new AuntValue();
				auntValue.setAuntid(model.getAuntid());
				if (model.getAuntid() != null && model.getAuntid() > 0) {
					String picurl = getFilePathById(model.getAuntid());
					auntValue.setAvatarurl(picurl);
				} else {
					auntValue.setAvatarurl(model.getThirdAvatar());
				}
				auntValue.setName(model.getRealName());
				auntValue.setScore(model.getScore());
				auntValue.setAge("" + CommonUtils.getAge(model.getBirthday(), new Date()));
				auntValue.setOrigin_place(model.getOriginPlace());
				auntValue.setDistance("" + model.getDistance());
				auntValues.add(auntValue);
			}
		}

		map.put("auntList", auntValues);
		result.setCode("1");
		result.setMessage("请求成功");
		result.setResult(map);
		return result;
	}

	@Override
	public ApiResult companyDetailMore(String companyid, String page) {
		ApiResult result = new ApiResult();
		result.setCode("1");
		result.setMessage("请求成功");
		HashMap<String, Object> map = new HashMap<>();
		CompanyWithBLOBs company = companyMapper.selectByPrimaryKey(Integer.parseInt(companyid));
		List<AuntModel> modelList = auntMapper.selectByCompanyId(company.getCompanyid(), company.getLongitude(),
				company.getLatitude(), (Integer.parseInt(page) - 1) * 10, 10);
		List<AuntModel> modelListMore = auntMapper.selectByCompanyId(company.getCompanyid(), company.getLongitude(),
				company.getLatitude(), (Integer.parseInt(page)) * 10, 10);
		if (modelListMore != null && modelListMore.size() > 0) {
			map.put("havemore", 1);
		} else {
			map.put("havemore", 0);
		}
		List<AuntValue> auntValues = new ArrayList<>();
		if (modelList != null && modelList.size() > 0) {
			for (AuntModel model : modelList) {
				AuntValue auntValue = new AuntValue();
				auntValue.setAuntid(model.getAuntid());
				if (model.getAuntid() != null && model.getAuntid() > 0) {
					String picurl = getFilePathById(model.getAuntid());
					auntValue.setAvatarurl(picurl);
				} else {
					auntValue.setAvatarurl(model.getThirdAvatar());
				}
				auntValue.setName(model.getRealName());
				auntValue.setScore(model.getScore());
				auntValue.setAge("" + CommonUtils.getAge(model.getBirthday(), new Date()));
				auntValue.setOrigin_place(model.getOriginPlace());
				auntValue.setDistance("" + model.getDistance());
				auntValues.add(auntValue);
			}
		}
		map.put("auntList", auntValues);
		result.setResult(map);
		return result;
	}

	@Override
	public ApiResult search(String keyword, String type, String page) {
		ApiResult result = new ApiResult();
		int typeInt = CommonUtils.parseInt(type, 0);
		int pageInt = CommonUtils.parseInt(page, 1);

		HashMap<String, Object> map = new HashMap<>();
		if (pageInt == 1) {// 第一页
			if (typeInt == 0) {// 全部
				List<SearchAuntModel> auntList = auntMapper.searchAuntByName(keyword, (pageInt - 1) * 10, 10);
				List<SearchCompanyModel> homeList = companyMapper.searchCompanyByName(keyword, (pageInt - 1) * 10, 10,
						1);
				List<SearchCompanyModel> repairList = companyMapper.searchCompanyByName(keyword, (pageInt - 1) * 10, 10,
						0);
				List<SearchCompanyModel> mediumList = companyMapper.searchCompanyByName(keyword, (pageInt - 1) * 10, 10,
						2);
				if (auntList != null && auntList.size() > 0) {
					for (SearchAuntModel model : auntList) {
						model.setAge("" + CommonUtils.getAge(model.getBirthday(), new Date()));
						String avatarurl = getFilePathById(model.getAvatar());
						if (!CommonUtils.isEmptyString(avatarurl)) {
							model.setPicurl(avatarurl);
						} else if (!CommonUtils.isEmptyString(model.getThirdAvatar())) {
							model.setPicurl(model.getThirdAvatar());
						}
						model.setOrigin_place(model.getOriginPlace());
						model.setName(model.getRealName());
					}
					long count = auntMapper.selectSearchCount(keyword);
					if (count > pageInt * 10) {
						map.put("havemoreAunt", 1);
					} else {
						map.put("havemoreAunt", 0);
					}
					map.put("auntList", auntList);
				} else {
					map.put("havemoreAunt", 0);
				}

				if (homeList != null && homeList.size() > 0) {
					for (SearchCompanyModel searchCompanyModel : homeList) {
						searchCompanyModel.setPicurl(getFilePathById(searchCompanyModel.getListpic()));
					}
					long count = companyMapper.selectSearchCount(keyword, 1);
					if (count > pageInt * 10) {
						map.put("havemoreHome", 1);
					} else {
						map.put("havemoreHome", 0);
					}
					map.put("homeList", homeList);
				} else {
					map.put("havemoreHome", 0);
				}

				if (repairList != null && repairList.size() > 0) {
					for (SearchCompanyModel searchCompanyModel : repairList) {
						searchCompanyModel.setPicurl(getFilePathById(searchCompanyModel.getListpic()));
					}
					long count = companyMapper.selectSearchCount(keyword, 0);
					if (count > pageInt * 10) {
						map.put("havemoreRepair", 1);
					} else {
						map.put("havemoreRepair", 0);
					}
					map.put("repairList", repairList);
				} else {
					map.put("havemoreRepair", 0);
				}

				if (mediumList != null && mediumList.size() > 0) {
					for (SearchCompanyModel searchCompanyModel : mediumList) {
						searchCompanyModel.setPicurl(getFilePathById(searchCompanyModel.getListpic()));
					}
					long count = companyMapper.selectSearchCount(keyword, 2);
					if (count > pageInt * 10) {
						map.put("havemoreMedium", 1);
					} else {
						map.put("havemoreMedium", 0);
					}
					map.put("mediumList", mediumList);
				} else {
					map.put("havemoreMedium", 0);
				}

			} else if (typeInt == 1) {// 1.搜索阿姨
				List<SearchAuntModel> auntList = auntMapper.searchAuntByName(keyword, (pageInt - 1) * 10, 10);
				if (auntList != null && auntList.size() > 0) {
					for (SearchAuntModel model : auntList) {
						model.setAge("" + CommonUtils.getAge(model.getBirthday(), new Date()));
						String avatarurl = getFilePathById(model.getAvatar());
						if (!CommonUtils.isEmptyString(avatarurl)) {
							model.setPicurl(avatarurl);
						} else if (!CommonUtils.isEmptyString(model.getThirdAvatar())) {
							model.setPicurl(model.getThirdAvatar());
						}
						model.setOrigin_place(model.getOriginPlace());
						model.setName(model.getRealName());
					}
					long count = auntMapper.selectSearchCount(keyword);
					if (count > pageInt * 10) {
						map.put("havemoreAunt", 1);
					} else {
						map.put("havemoreAunt", 0);
					}
					map.put("auntList", auntList);
				} else {
					map.put("havemoreAunt", 0);
				}
			} else if (typeInt == 2) {// 2.搜索维修公司
				List<SearchCompanyModel> repairList = companyMapper.searchCompanyByName(keyword, (pageInt - 1) * 10, 10,
						0);

				if (repairList != null && repairList.size() > 0) {
					for (SearchCompanyModel searchCompanyModel : repairList) {
						searchCompanyModel.setPicurl(getFilePathById(searchCompanyModel.getListpic()));
					}
					long count = companyMapper.selectSearchCount(keyword, 0);
					if (count > pageInt * 10) {
						map.put("havemoreRepair", 1);
					} else {
						map.put("havemoreRepair", 0);
					}
					map.put("repairList", repairList);
				} else {
					map.put("havemoreRepair", 0);
				}
			} else if (typeInt == 3) {// 3.搜索家政公司
				List<SearchCompanyModel> homeList = companyMapper.searchCompanyByName(keyword, (pageInt - 1) * 10, 10,
						1);
				if (homeList != null && homeList.size() > 0) {
					for (SearchCompanyModel searchCompanyModel : homeList) {
						searchCompanyModel.setPicurl(getFilePathById(searchCompanyModel.getListpic()));
					}
					long count = companyMapper.selectSearchCount(keyword, 1);
					if (count > pageInt * 10) {
						map.put("havemoreHome", 1);
					} else {
						map.put("havemoreHome", 0);
					}
					map.put("homeList", homeList);
				} else {
					map.put("havemoreHome", 0);
				}
			} else if (typeInt == 4) {// 4.搜索中介公司
				List<SearchCompanyModel> mediumList = companyMapper.searchCompanyByName(keyword, (pageInt - 1) * 10, 10,
						2);
				if (mediumList != null && mediumList.size() > 0) {
					for (SearchCompanyModel searchCompanyModel : mediumList) {
						searchCompanyModel.setPicurl(getFilePathById(searchCompanyModel.getListpic()));
					}
					long count = companyMapper.selectSearchCount(keyword, 2);
					if (count > pageInt * 10) {
						map.put("havemoreMedium", 1);
					} else {
						map.put("havemoreMedium", 0);
					}
					map.put("mediumList", mediumList);
				} else {
					map.put("havemoreMedium", 0);
				}
			} else {
				result.setCode("2");
				result.setMessage("请求失败,错误的类型");
				return result;
			}
		} else {
			if (typeInt == 1) {// 1.搜索阿姨
				List<SearchAuntModel> auntList = auntMapper.searchAuntByName(keyword, (pageInt - 1) * 10, 10);
				if (auntList != null && auntList.size() > 0) {
					for (SearchAuntModel model : auntList) {
						model.setAge("" + CommonUtils.getAge(model.getBirthday(), new Date()));
						String avatarurl = getFilePathById(model.getAvatar());
						if (!CommonUtils.isEmptyString(avatarurl)) {
							model.setPicurl(avatarurl);
						} else if (!CommonUtils.isEmptyString(model.getThirdAvatar())) {
							model.setPicurl(model.getThirdAvatar());
						}
						model.setOrigin_place(model.getOriginPlace());
						model.setName(model.getRealName());
					}
					long count = auntMapper.selectSearchCount(keyword);
					if (count > pageInt * 10) {
						map.put("havemoreAunt", 1);
					} else {
						map.put("havemoreAunt", 0);
					}
					map.put("auntList", auntList);
				} else {
					map.put("havemoreAunt", 0);
				}
			} else if (typeInt == 2) {// 2.搜索维修公司
				List<SearchCompanyModel> repairList = companyMapper.searchCompanyByName(keyword, (pageInt - 1) * 10, 10,
						0);

				if (repairList != null && repairList.size() > 0) {
					for (SearchCompanyModel searchCompanyModel : repairList) {
						searchCompanyModel.setPicurl(getFilePathById(searchCompanyModel.getListpic()));
					}
					long count = companyMapper.selectSearchCount(keyword, 0);
					if (count > pageInt * 10) {
						map.put("havemoreRepair", 1);
					} else {
						map.put("havemoreRepair", 0);
					}
					map.put("repairList", repairList);
				} else {
					map.put("havemoreRepair", 0);
				}
			} else if (typeInt == 3) {// 3.搜索家政公司
				List<SearchCompanyModel> homeList = companyMapper.searchCompanyByName(keyword, (pageInt - 1) * 10, 10,
						1);
				if (homeList != null && homeList.size() > 0) {
					for (SearchCompanyModel searchCompanyModel : homeList) {
						searchCompanyModel.setPicurl(getFilePathById(searchCompanyModel.getListpic()));
					}
					long count = companyMapper.selectSearchCount(keyword, 1);
					if (count > pageInt * 10) {
						map.put("havemoreHome", 1);
					} else {
						map.put("havemoreHome", 0);
					}
					map.put("homeList", homeList);
				} else {
					map.put("havemoreHome", 0);
				}
			} else if (typeInt == 4) {// 4.搜索中介公司
				List<SearchCompanyModel> mediumList = companyMapper.searchCompanyByName(keyword, (pageInt - 1) * 10, 10,
						2);
				if (mediumList != null && mediumList.size() > 0) {
					for (SearchCompanyModel searchCompanyModel : mediumList) {
						searchCompanyModel.setPicurl(getFilePathById(searchCompanyModel.getListpic()));
					}
					long count = companyMapper.selectSearchCount(keyword, 2);
					if (count > pageInt * 10) {
						map.put("havemoreMedium", 1);
					} else {
						map.put("havemoreMedium", 0);
					}
					map.put("mediumList", mediumList);
				} else {
					map.put("havemoreMedium", 0);
				}
			} else {
				result.setCode("2");
				result.setMessage("请求失败,错误的类型");
				return result;
			}
		}

		result.setCode("1");
		result.setMessage("请求成功");
		result.setResult(map);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult likeAunt(String auntid, String userid) {
		ApiResult result = new ApiResult();

		AuntLike likeDate = auntLikeMapper.selectLikeData(CommonUtils.parseInt(userid, 0),
				CommonUtils.parseInt(auntid, 0));
		if (likeDate != null) {
			auntLikeMapper.deleteByPrimaryKey(likeDate.getDataid());
			result.setCode("1");
			result.setMessage("取消关注成功");
		} else {
			likeDate = new AuntLike();
			likeDate.setAuntid(CommonUtils.parseInt(auntid, 0));
			likeDate.setUserid(CommonUtils.parseInt(userid, 0));
			likeDate.setAddtime(new Date());

			auntLikeMapper.insert(likeDate);
			result.setCode("1");
			result.setMessage("关注成功");
		}

		Long count = auntLikeMapper.selectLikeCount(CommonUtils.parseInt(auntid, 0));
		HashMap<String, Object> map = new HashMap<>();
		if (count == null) {
			map.put("count", 0);
		} else {
			map.put("count", count);
		}
		result.setResult(map);
		return result;
	}

}
