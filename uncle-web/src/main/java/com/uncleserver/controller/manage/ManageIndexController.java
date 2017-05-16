package com.uncleserver.controller.manage;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.uncleserver.common.CommonUtils;
import com.uncleserver.controller.BaseController;
import com.uncleserver.model.Area;
import com.uncleserver.model.Aunt;
import com.uncleserver.model.AuntBanner;
import com.uncleserver.model.AuntPush;
import com.uncleserver.model.Category;
import com.uncleserver.model.CategorySecond;
import com.uncleserver.model.CategoryThird;
import com.uncleserver.model.Cities;
import com.uncleserver.model.Company;
import com.uncleserver.model.HomeAd;
import com.uncleserver.model.HomeBanner;
import com.uncleserver.model.Proviences;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;
import com.uncleserver.modelVo.ApiResult;
import com.uncleserver.modelVo.SetPriceResult;
import com.uncleserver.service.manage.ManageIndexService;
import com.uncleserver.service.manage.ManageSystemService;

@Controller
@RequestMapping("/manage/index")
public class ManageIndexController extends BaseController {
	@Resource
	private ManageSystemService manageSystemService;

	@Resource(name = "manageIndexService")
	private ManageIndexService manageIndexService;

	@RequestMapping("/appBannerList")
	public ModelAndView appBannerList(HttpSession session, String page) {
		ModelAndView mav = new ModelAndView();
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		List<Proviences> pro = manageIndexService.getAllProviences();
		mav.addObject("pro", pro);
		return mav;
	}

	@RequestMapping("/getAppBannerList")
	@ResponseBody
	public QueryResult<HomeBanner> getAppBannerList(HttpSession session, PQuery pquery, String cityid,
			String provienceid, String area,String type, String contentid) {
		session.setAttribute("page", pquery.getPage());
		QueryResult<HomeBanner> result = null;
		try {
			result = manageIndexService.pageHomeBanner(pquery, area, type, contentid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/changeprovience")
	@ResponseBody
	public String changeprovience(String id) {
		List<Cities> list = manageIndexService.getCitiesByPro(id);
		Gson gson = new Gson();
		gson.toJson(list);
		return gson.toJson(list);
	}

	@RequestMapping("/selectArea")
	@ResponseBody
	public Map<String, Object> selectArea(String id) {
		Map<String, Object> map = new HashMap<>();
		try {
			List<Area> cities = manageIndexService.selectArea(CommonUtils.parseInt(id, 0));
			map.put("list", cities);
			map.put("code", 0);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}

	@RequestMapping("/addappbanner")
	public ModelAndView addappbanner(HttpServletRequest req, HttpSession session, String page) {
		ModelAndView mav = new ModelAndView();
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		List<Proviences> pro = manageIndexService.getAllProviences();
		mav.addObject("pro", pro);
		return mav;
	}

	@RequestMapping("/doAddAppBanner")
	@ResponseBody
	public ApiResult doAddSignSet(String key, Byte type, String city, String contentid) {
		ApiResult result = new ApiResult();
		int i = 0;
		try {
			Integer picid = manageIndexService.saveFile(key);
			HomeBanner banner = new HomeBanner();
			banner.setAddtime(new Date());
			banner.setCity(city);
			banner.setContentid(contentid);
			banner.setPicid(picid);
			banner.setSort(1);
			banner.setType(type);
			i = manageIndexService.saveAppBanner(banner);
			if (i == 1) {
				result.setCode("1");
				result.setMessage("保存成功");
			} else {
				result.setCode("0");
				result.setMessage("保存失败");
			}

		} catch (Exception e) {
			result.setCode("0");
			result.setMessage("客户端出错");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/doEditAppBanner")
	@ResponseBody
	public ApiResult doEditSignSet(String id, String key, Byte type, String city, String contentid) {
		ApiResult result = new ApiResult();
		int i = 0;
		try {
			HomeBanner banner = new HomeBanner();
			if (!StringUtils.isEmpty(key)) {
				Integer picid = manageIndexService.saveFile(key);
				banner.setPicid(picid);
			}
			banner.setCity(city);
			banner.setContentid(contentid);
			banner.setType(type);
			banner.setBannerid(CommonUtils.parseInt(id, 0));
			i = manageIndexService.editAppBanner(banner);
			if (i == 1) {
				result.setCode("1");
				result.setMessage("修改成功");
			} else {
				result.setCode("0");
				result.setMessage("修改失败");
			}

		} catch (Exception e) {
			result.setCode("0");
			result.setMessage("服务端出错");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/delappbanner")
	@ResponseBody
	public ApiResult delappbanner(String id) {
		ApiResult result = new ApiResult();
		try {
			int i = manageIndexService.delappbanner(id);
			if (i == 1) {
				result.setCode("1");
				result.setMessage("删除成功");
			} else {
				result.setCode("0");
				result.setMessage("删除失败");
			}
		} catch (Exception e) {
			result.setCode("0");
			result.setMessage("服务端出错");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/upappbanner")
	@ResponseBody
	public ApiResult upappbanner(String id) {
		ApiResult result = new ApiResult();
		try {
			int i = manageIndexService.upappbanner(id);
			if (i == 1) {
				result.setCode("1");
				result.setMessage("上升成功");
			} else if (i == 2) {
				result.setCode("102");
				result.setMessage("已经是最大值了");
			} else {
				result.setCode("0");
				result.setMessage("上升失败");
			}
		} catch (Exception e) {
			result.setCode("0");
			result.setMessage("服务端出错");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/downappbanner")
	@ResponseBody
	public ApiResult downsort(String id) {
		ApiResult result = new ApiResult();
		try {
			int i = manageIndexService.downsort(id);
			if (i == 1) {
				result.setCode("1");
				result.setMessage("下降成功");
			} else if (i == 2) {
				result.setCode("102");
				result.setMessage("已经是最小值了");
			} else {
				result.setCode("0");
				result.setMessage("下降失败");
			}
		} catch (Exception e) {
			result.setCode("0");
			result.setMessage("服务端出错");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/editappbanner")
	public ModelAndView editappbanner(HttpServletRequest req, HttpSession session, String page) {
		ModelAndView mav = new ModelAndView();
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		String id = req.getParameter("id");
		HomeBanner banner = manageIndexService.getHomeBannerById(id);
		mav.addObject("banner", banner);
		Cities city = manageIndexService.getgetAreaByCityName(banner.getCity());
		mav.addObject("city", city);
		List<Company> c = manageIndexService.getAllCompany();
		mav.addObject("cmp", c);
		List<Aunt> aunt = manageIndexService.getAllAunt();
		mav.addObject("aunt", aunt);
		String img = manageIndexService.getPath(banner.getPicid());
		mav.addObject("img", img);
		List<Proviences> pro = manageIndexService.getAllProviences();
		mav.addObject("pro", pro);
		return mav;
	}

	@RequestMapping("/getPath")
	@ResponseBody
	public String getPath(Integer picid) {
		return manageIndexService.getPath(picid);
	}

	@RequestMapping("/auntBannerList")
	public ModelAndView auntBannerList(HttpSession session, String page) {
		ModelAndView mav = new ModelAndView();
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		return mav;
	}

	@RequestMapping("/getAuntBannerList")
	@ResponseBody
	public QueryResult<AuntBanner> getAuntBannerList(HttpSession session, PQuery pquery, String type,
			String contentid) {
		session.setAttribute("page", pquery.getPage());
		QueryResult<AuntBanner> result = null;
		try {
			result = manageIndexService.pageAuntBanner(pquery, type, contentid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/addauntbanner")
	public ModelAndView addauntbanner(HttpServletRequest req, HttpSession session, String page) {
		ModelAndView mav = new ModelAndView();
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		List<Proviences> pro = manageIndexService.getAllProviences();
		mav.addObject("pro", pro);
		return mav;
	}

	@RequestMapping("/doAddAuntBanner")
	@ResponseBody
	public ApiResult doAddAuntBanner(String key, Byte type, String contentid) {
		ApiResult result = new ApiResult();
		int i = 0;
		try {
			Integer picid = manageIndexService.saveFile(key);
			AuntBanner banner = new AuntBanner();
			banner.setAddtime(new Date());
			banner.setContentid(contentid);
			banner.setPicid(picid);
			banner.setSort(1);
			banner.setType(type);
			i = manageIndexService.saveAuntBanner(banner);
			if (i == 1) {
				result.setCode("1");
				result.setMessage("保存成功");
			} else {
				result.setCode("0");
				result.setMessage("保存失败");
			}

		} catch (Exception e) {
			result.setCode("0");
			result.setMessage("客户端出错");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/doEditAuntBanner")
	@ResponseBody
	public ApiResult doEditAuntBanner(String id, String key, Byte type, String contentid) {
		ApiResult result = new ApiResult();
		int i = 0;
		try {
			AuntBanner banner = new AuntBanner();
			if (!StringUtils.isEmpty(key)) {
				Integer picid = manageIndexService.saveFile(key);
				banner.setPicid(picid);
			}
			banner.setContentid(contentid);
			banner.setType(type);
			banner.setBannerid(CommonUtils.parseInt(id, 0));
			i = manageIndexService.editAuntBanner(banner);
			if (i == 1) {
				result.setCode("1");
				result.setMessage("修改成功");
			} else {
				result.setCode("0");
				result.setMessage("修改失败");
			}

		} catch (Exception e) {
			result.setCode("0");
			result.setMessage("服务端出错");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/delauntbanner")
	@ResponseBody
	public ApiResult delauntbanner(String id) {
		ApiResult result = new ApiResult();
		try {
			int i = manageIndexService.delauntbanner(id);
			if (i == 1) {
				result.setCode("1");
				result.setMessage("删除成功");
			} else {
				result.setCode("0");
				result.setMessage("删除失败");
			}
		} catch (Exception e) {
			result.setCode("0");
			result.setMessage("服务端出错");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/upauntbanner")
	@ResponseBody
	public ApiResult upauntbanner(String id) {
		ApiResult result = new ApiResult();
		try {
			int i = manageIndexService.upauntbanner(id);
			if (i == 1) {
				result.setCode("1");
				result.setMessage("上升成功");
			} else if (i == 2) {
				result.setCode("102");
				result.setMessage("已经是最大值了");
			} else {
				result.setCode("0");
				result.setMessage("上升失败");
			}
		} catch (Exception e) {
			result.setCode("0");
			result.setMessage("服务端出错");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/downauntbanner")
	@ResponseBody
	public ApiResult downauntbannersort(String id) {
		ApiResult result = new ApiResult();
		try {
			int i = manageIndexService.downauntbannersort(id);
			if (i == 1) {
				result.setCode("1");
				result.setMessage("下降成功");
			} else if (i == 2) {
				result.setCode("102");
				result.setMessage("已经是最小值了");
			} else {
				result.setCode("0");
				result.setMessage("下降失败");
			}
		} catch (Exception e) {
			result.setCode("0");
			result.setMessage("服务端出错");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/editauntbanner")
	public ModelAndView editauntbanner(HttpServletRequest req, HttpSession session, String page) {
		ModelAndView mav = new ModelAndView();
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		String id = req.getParameter("id");
		AuntBanner banner = manageIndexService.getAuntBannerById(id);
		mav.addObject("banner", banner);
		List<Company> c = manageIndexService.getAllCompany();
		mav.addObject("cmp", c);
		List<Aunt> aunt = manageIndexService.getAllAunt();
		mav.addObject("aunt", aunt);
		String img = manageIndexService.getPath(banner.getPicid());
		mav.addObject("img", img);
		List<Proviences> pro = manageIndexService.getAllProviences();
		mav.addObject("pro", pro);
		return mav;
	}

	@RequestMapping("/getAllCompany")
	@ResponseBody
	public String getAllcompany(HttpSession session, PQuery pquery) {
		Gson g = new Gson();
		List<Company> c = manageIndexService.getAllCompany();
		// result.setCode("1");
		// result.setResult(s);
		return g.toJson(c);
	}

	@RequestMapping("/getAllAunt")
	@ResponseBody
	public String getAllAunt(HttpSession session, PQuery pquery) {
		Gson g = new Gson();
		List<Aunt> c = manageIndexService.getAllAunt();
		return g.toJson(c);
	}

	@RequestMapping("/appADList")
	public ModelAndView appADList(HttpSession session, String page) {
		ModelAndView mav = new ModelAndView();
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		return mav;
	}

	@RequestMapping("/getAppADList")
	@ResponseBody
	public QueryResult<HomeAd> getAppADList(HttpSession session, PQuery pquery) {
		session.setAttribute("page", pquery.getPage());
		QueryResult<HomeAd> result = null;
		try {
			result = manageIndexService.pageHomeAd(pquery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/auntPush")
	public ModelAndView auntPush(HttpSession session, String page) {
		ModelAndView mav = new ModelAndView();
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		return mav;
	}

	@RequestMapping("/getAuntPush")
	@ResponseBody
	public QueryResult<AuntPush> getAuntPush(HttpSession session, PQuery pquery) {
		session.setAttribute("page", pquery.getPage());
		QueryResult<AuntPush> result = null;
		try {
			result = manageIndexService.pageAuntPush(pquery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/getDetail")
	@ResponseBody
	public Map<String, Object> getDetail(String id) {
		Map<String, Object> map = new HashMap<>();
		try {
			Category category = manageSystemService.getDetail(CommonUtils.parseInt(id, 0));
			List<CategorySecond> categorySeconds = manageSystemService.selectByCategoryId(CommonUtils.parseInt(id, 0));
			if (category != null) {
				map.put("name", category.getName());
			}
			map.put("category", categorySeconds);
			map.put("code", 0);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}

	@RequestMapping("/updateCategory")
	@ResponseBody
	public Map<String, Object> updateCategory(String dataid, String id) {
		Map<String, Object> map = new HashMap<>();
		try {
			manageSystemService.updateCategory(CommonUtils.parseInt(dataid, 0), CommonUtils.parseInt(id, 0));
			map.put("code", 0);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}

	@RequestMapping("/deleteCategory")
	@ResponseBody
	public Map<String, Object> deleteCategory(String categoryid) {
		Map<String, Object> map = new HashMap<>();
		try {
			List<CategorySecond> categorySeconds = manageSystemService
					.selectByCategoryId(CommonUtils.parseInt(categoryid, 0));
			if (categorySeconds != null && categorySeconds.size() > 0) {
				map.put("code", 1);
				return map;
			}
			manageSystemService.deleteCategory(CommonUtils.parseInt(categoryid, 0));
			map.put("code", 0);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}

	@RequestMapping("/addCategory")
	@ResponseBody
	public Map<String, Object> addCategory(String name) {
		Map<String, Object> map = new HashMap<>();
		try {
			List<Category> category = manageSystemService.selectByName(name);
			if (category != null && category.size() > 0) {
				map.put("code", 1);
				return map;
			}
			manageSystemService.addCategory(name);
			map.put("code", 0);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}

	@RequestMapping("/serviceTwoCategory")
	public ModelAndView serviceTwoCategory(HttpSession session, String page) {
		ModelAndView mav = new ModelAndView();
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		List<CategorySecond> categorySeconds = manageSystemService.selectTwoCategory();
		// mav.addObject("category", category);
		// mav.addObject("ThreeCategory",ThreeCategory);
		// mav.addObject("oneCategory", oneCategory);
		mav.addObject("categorySeconds", categorySeconds);
		return mav;
	}

	@RequestMapping("/getServiceTwoCategory")
	@ResponseBody
	public QueryResult<CategoryThird> getServiceTwoCategory(HttpSession session, PQuery pquery, String category,
			String ThreeCategory) {
		session.setAttribute("page", pquery.getPage());
		QueryResult<CategoryThird> result = null;
		try {
			result = manageSystemService.getServiceTwoCategory(pquery, category, ThreeCategory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/getNext")
	@ResponseBody
	public Map<String, Object> getNext(String category) {
		Map<String, Object> map = new HashMap<>();
		try {
			List<CategoryThird> categoryThirds = manageSystemService.selectByCategoryIds(category);
			map.put("category", categoryThirds);
			map.put("code", 0);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}

	@RequestMapping("/deleteCategoryTwo")
	@ResponseBody
	public Map<String, Object> deleteCategoryTwo(String categoryid) {
		Map<String, Object> map = new HashMap<>();
		try {
			List<CategoryThird> categorySeconds = manageSystemService
					.selectByCategoryIdTwo(CommonUtils.parseInt(categoryid, 0));
			if (categorySeconds != null && categorySeconds.size() > 0) {
				map.put("code", 1);
				return map;
			}
			manageSystemService.deleteCategoryTwo(CommonUtils.parseInt(categoryid, 0));
			map.put("code", 0);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}

	@RequestMapping("/addCategoryTwo")
	@ResponseBody
	public Map<String, Object> addCategoryTwo(String name, String categoryNone, String threeCategoryTwo) {
		Map<String, Object> map = new HashMap<>();
		try {
			List<CategoryThird> category = manageSystemService.selectByNameTwo(name, categoryNone, threeCategoryTwo);
			if (category != null && category.size() > 0) {
				map.put("code", 1);
				return map;
			}
			manageSystemService.addCategoryTwo(name, categoryNone, threeCategoryTwo);
			map.put("code", 0);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}

	@RequestMapping("/servicePrice")
	public ModelAndView servicePrice(HttpSession session, String page) {
		ModelAndView mav = new ModelAndView();
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		List<Cities> city = manageSystemService.selectCities();
		mav.addObject("city", city);
		return mav;
	}

	@RequestMapping("/getServicePrice")
	@ResponseBody
	public QueryResult<SetPriceResult> getServicePrice(HttpSession session, PQuery pquery, String city) {
		session.setAttribute("page", pquery.getPage());
		QueryResult<SetPriceResult> result = null;
		if (CommonUtils.isEmptyString(city)) {
			return result;
		}
		try {
			result = manageSystemService.getServicePrice(pquery, city);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/selectProvience")
	@ResponseBody
	public Map<String, Object> selectCity() {
		Map<String, Object> map = new HashMap<>();
		try {
			List<Proviences> Proviences =  manageIndexService.getAllProviences();
			map.put("list", Proviences);
			map.put("code", 0);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}


}
