package com.uncleserver.controller.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.common.Constant;
import com.uncleserver.controller.BaseController;
import com.uncleserver.model.Cities;
import com.uncleserver.model.HomeItem;
import com.uncleserver.model.Proviences;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;
import com.uncleserver.modelVo.AddItemVo;
import com.uncleserver.modelVo.HomeAdContentVo;
import com.uncleserver.modelVo.HomeAdVo;
import com.uncleserver.modelVo.HomeItemVo;
import com.uncleserver.service.manage.ManageHomeService;

@Controller
@RequestMapping("/manage/home")
public class ManageHomeController extends BaseController {

	@Resource
	private ManageHomeService manageHomeService;

	/*
	 * @Autowired private FileService fileService;
	 */

	/**
	 * 跳转首页按钮管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/goiconlist")
	public ModelAndView goiconlist(HttpSession session, String page) {
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		ModelAndView mav = new ModelAndView();
		List<Proviences> listProviences = manageHomeService.selectProviences();
		mav.addObject("list", listProviences);
		mav.setViewName("manage/homeicon/iconlist");
		return mav;
	}

	/**
	 * 跳转首页按钮管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/gocontentlist")
	public ModelAndView gocontentlist(HttpSession session, String page) {
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		ModelAndView mav = new ModelAndView();
		List<Proviences> listProviences = manageHomeService.selectProviences();
		mav.addObject("list", listProviences);
		mav.setViewName("manage/homeitem/homeitemlist");
		return mav;
	}

	/**
	 * 跳转到banner页面
	 * 
	 * @return
	 */
	@RequestMapping("/bannerManage")
	public ModelAndView bannerManage(HttpSession session, String page) {
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		ModelAndView modelAndView = new ModelAndView();
		List<Cities> cities = manageHomeService.selectAllCitys();
		modelAndView.addObject("list", cities);
		modelAndView.setViewName("manage/banner/bannerManage");
		return modelAndView;
	}

	/**
	 * banner查询
	 * 
	 * @return
	 */
	@RequestMapping("/getBanner")
	@ResponseBody
	public QueryResult<HomeAdVo> getBanner(HttpSession session, PQuery pQuery, Integer city) {
		String cityName = "";
		if (city == null) {
			cityName = "诸暨市";
		} else {
			Cities cityes = manageHomeService.selectCityByCityId(city);
			cityName = cityes.getName();
		}
		QueryResult<HomeAdVo> queryResult = manageHomeService.getBanner(pQuery, cityName);
		session.setAttribute("page", pQuery.getPage());
		return queryResult;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping("/delBanner")
	@ResponseBody
	public int delBanner(Integer dataid) {
		int result;
		result = manageHomeService.deleteHomeAd(dataid);
		return result;
	}

	/**
	 * 查询首页按钮信息
	 * 
	 * @param pquery
	 * @return
	 */
	@RequestMapping("/selectItemlist")
	@ResponseBody
	public QueryResult<HomeItemVo> selectItemlist(HttpSession session, PQuery pquery, Integer cityid, Integer type) {
		String cityName = "";
		if (cityid == null) {
			cityName = "杭州市";
		} else {
			Cities citys = manageHomeService.selectCityByCityId(cityid);
			if (null != citys) {
				cityName = citys.getName();
			}
		}
		if (type == null) {
			type = 1;
		}
		QueryResult<HomeItemVo> result = manageHomeService.geHomeItemByCity(pquery, cityName, type);
		session.setAttribute("page", pquery.getPage());
		return result;
	}

	/**
	 * 查询首页按钮信息
	 * 
	 * @param pquery
	 * @return
	 */
	@RequestMapping("/selectHomeItemlist")
	@ResponseBody
	public QueryResult<AddItemVo> selectHomeItemlist(HttpSession session, PQuery pquery, Integer cityid, Integer type) {
		String cityName = "";
		if (cityid == null) {
			cityName = "杭州市";
		} else {
			Cities citys = manageHomeService.selectCityByCityId(cityid);
			if (null != citys) {
				cityName = citys.getName();
			}
		}
		if (type == null) {
			type = 1;
		}
		QueryResult<AddItemVo> result = manageHomeService.selectHomeItemlist(pquery, cityName, type);
		session.setAttribute("page", pquery.getPage());
		return result;
	}

	/**
	 * 查询首页按钮信息
	 * 
	 * @param pquery
	 * @return
	 */
	@RequestMapping("/seachiconlist")
	@ResponseBody
	public QueryResult<HomeItemVo> seachiconlist(HttpSession session, PQuery pquery, String city) {
		if (CommonUtils.isEmptyString(city)) {
			city = "杭州市";
		}
		QueryResult<HomeItemVo> result = manageHomeService.geHomeIconByCity(pquery, city);
		session.setAttribute("page", pquery.getPage());
		return result;
	}

	/**
	 * 获取对应的城市
	 * 
	 * @param provienceid
	 * @param response
	 */
	@RequestMapping("/changeprovience")
	public void changeprovience(Integer provienceid, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Cities> listCities = manageHomeService.selectCitiesByProvienceid(provienceid);
		map.put("cityes", listCities);
		CommonUtils.setMaptoJson(response, map);
	}

	/**
	 * 获取对应的城市
	 * 
	 * @param provienceid
	 * @param response
	 */
	@RequestMapping("/changeAdType")
	public void changeAdType(Integer type, Integer cityid, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		/*
		 * List<Cities> listCities =
		 * manageHomeService.selectCitiesByProvienceid(provienceid);
		 * map.put("cityes", listCities);
		 */
		String cityName = "";
		if (cityid == null) {
			cityName = Constant.DEFAULT_REGION;
		} else {
			Cities cities = manageHomeService.selectCityByCityId(cityid);
			cityName = cities.getName();
		}
		List<HomeAdContentVo> homeAdContentVos = manageHomeService.getHomeAdContentList(type, cityName);
		map.put("contentList", homeAdContentVos);
		CommonUtils.setMaptoJson(response, map);
	}

	/**
	 * 查询首页信息
	 * 
	 * @param cityid
	 * @param pquery
	 * @return
	 */
	@RequestMapping("/selectcontentlist")
	@ResponseBody
	public QueryResult<HomeItemVo> selectcontentlist(String cityid, PQuery pquery) {
		String cityName = "";
		if (CommonUtils.isEmptyString(cityid)) {
			cityName = "杭州市";
		} else {
			Integer cityId = Integer.parseInt(cityid);
			Cities city = manageHomeService.selectCityByCityId(cityId);
			cityName = city.getName();
		}
		QueryResult<HomeItemVo> result = manageHomeService.geHomeIconByCity(pquery, cityName);
		return result;
	}

	/**
	 * 删除首页按钮信息
	 * 
	 * @param iconid
	 * @return
	 */
	@RequestMapping("/delicon")
	@ResponseBody
	public int delicon(Integer iconid) {
		int result;
		result = manageHomeService.deleteicon(iconid);
		return result;
	}

	/**
	 * 上升排序
	 * 
	 * @param iconid
	 * @return
	 */
	@RequestMapping("/upiconsort")
	@ResponseBody
	public int upiconsort(Integer iconid, String cityName) {
		int result;
		int type = 2;
		result = manageHomeService.UpOrDowniconSort(iconid, type, cityName);
		return result;
	}

	/**
	 * 下降排序
	 * 
	 * @param iconid
	 * @return
	 */
	@RequestMapping("/downiconsort")
	@ResponseBody
	public int downiconsort(Integer iconid, String cityName) {
		int result;
		int type = 1;
		result = manageHomeService.UpOrDowniconSort(iconid, type, cityName);
		return result;
	}

	/**
	 * 上升排序
	 * 
	 * @param iconid
	 * @return
	 */
	@RequestMapping("/upcontentsort")
	@ResponseBody
	public int upcontentsort(Integer dataid, String cityName) {
		int result;
		int type = 2;
		HomeItem homeItem = manageHomeService.getHomeItem(dataid);
		int typeI = homeItem.getType();
		result = manageHomeService.UpOrDownContentSort(dataid, (byte) type, homeItem.getCity(), typeI);
		return result;
	}

	/**
	 * 上升排序
	 * 
	 * @param iconid
	 * @return
	 */
	@RequestMapping("/upBannersort")
	@ResponseBody
	public int upBannersort(Integer dataid) {
		int result;
		int type = 2;
		String cityName = manageHomeService.getHomeAd(dataid).getCity();
		result = manageHomeService.UpOrDownBannerSort(dataid, type, cityName);
		return result;
	}

	/**
	 * 下降排序
	 * 
	 * @param iconid
	 * @return
	 */
	@RequestMapping("/downBannersort")
	@ResponseBody
	public int downBannersort(Integer dataid) {
		int result;
		int type = 1;
		String cityName = manageHomeService.getHomeAd(dataid).getCity();
		result = manageHomeService.UpOrDownBannerSort(dataid, type, cityName);
		return result;
	}

	/**
	 * 下降排序
	 * 
	 * @param iconid
	 * @return
	 */
	@RequestMapping("/downcontentsort")
	@ResponseBody
	public int downcontentsort(Integer dataid, String cityName) {
		int result;
		int type = 1;
		HomeItem homeItem = manageHomeService.getHomeItem(dataid);
		int typeI = homeItem.getType();
		result = manageHomeService.UpOrDownContentSort(dataid, (byte) type, homeItem.getCity(), typeI);
		return result;
	}

	/**
	 * 删除首页按钮信息
	 * 
	 * @param iconid
	 * @return
	 */
	@RequestMapping("/delcontent")
	@ResponseBody
	public int delcontent(Integer dataid) {
		int result;
		result = manageHomeService.deleteContent(dataid);
		return result;
	}

	/**
	 * 跳转新增首页内容
	 * 
	 * @return
	 */
	@RequestMapping("/goaddHomeAd")
	public ModelAndView goaddHomeAd(HttpSession session, String page) {
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		ModelAndView mav = new ModelAndView();
		List<Cities> cities = manageHomeService.selectAllCitys();
		mav.addObject("list", cities);
		mav.setViewName("manage/banner/addBanner");
		return mav;
	}

	/**
	 * 插入首页内容
	 * 
	 * @param cityid
	 * @param type
	 * @param childid
	 * @param fileId
	 * @return
	 */
	@RequestMapping("/inserticon")
	@ResponseBody
	public int insericon(Integer cityid, Integer categoryid) {
		int result;
		if (cityid == null || categoryid == null) {
			result = 101;
			return result;
		}
		result = manageHomeService.insertIcon(cityid, categoryid);
		return result;
	}

	/**
	 * 插入首页内容
	 * 
	 * @param cityid
	 * @param type
	 * @param childid
	 * @param fileId
	 * @return
	 */
	@RequestMapping("/addBannerData")
	@ResponseBody
	public int addBannerData(Integer city, Integer type, String linkurl, Integer contentid, Integer fileid) {
		int result;
		String cityName = "";
		if (fileid == null) {
			result = 101;
			return result;
		}
		if (city == null) {
			cityName = Constant.DEFAULT_REGION;
		}
		Cities cityes = manageHomeService.selectCityByCityId(city);
		if (cityes != null) {
			cityName = cityes.getName();
		}

		result = manageHomeService.addBannerData(cityName, type, linkurl, contentid, fileid);
		return result;
	}

	/**
	 * 跳转新增首页内容
	 * 
	 * @return
	 */
	@RequestMapping("/goaddicon")
	public ModelAndView goaddicon(HttpSession session, String page) {
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		ModelAndView mav = new ModelAndView();
		List<Proviences> listProviences = manageHomeService.selectProviences();
		mav.addObject("list", listProviences);
		mav.setViewName("manage/homeicon/addhomeicon");
		return mav;
	}

	/**
	 * 跳转新增首页内容
	 * 
	 * @return
	 */
	@RequestMapping("/goaddcontent")
	public ModelAndView goaddcontent(HttpSession session, String page) {
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		ModelAndView mav = new ModelAndView();
		List<Proviences> listProviences = manageHomeService.selectProviences();
		mav.addObject("list", listProviences);
		mav.setViewName("manage/homeitem/addhomecontent");
		return mav;
	}

	/**
	 * 插入首页内容
	 * 
	 * @param cityid
	 * @param type
	 * @param childid
	 * @param fileId
	 * @return
	 */
	@RequestMapping("/insertcontent")
	@ResponseBody
	public int insertcontent(Integer cityid, Integer type, String[] goodstips) {
		int result;
		if (cityid == null || type == null || goodstips == null) {
			result = 101;
		}
		result = manageHomeService.insertcontent(cityid, type, goodstips);
		return result;
	}

}