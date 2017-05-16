package com.uncleserver.controller.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.controller.BaseController;
import com.uncleserver.model.Area;
import com.uncleserver.model.Category;
import com.uncleserver.model.CategoryCity;
import com.uncleserver.model.CategorySecond;
import com.uncleserver.model.CategoryThird;
import com.uncleserver.model.Cities;
import com.uncleserver.model.Proviences;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;
import com.uncleserver.modelVo.SetPriceResult;
import com.uncleserver.service.manage.ManageSystemService;

@Controller
@RequestMapping("/manage/system")
public class ManageSystemController extends BaseController {
	@Resource
	private ManageSystemService manageSystemService;

	@RequestMapping("/serviceCategory")
	public ModelAndView serviceCategory(HttpSession session, String page) {
		ModelAndView mav = new ModelAndView();
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		List<Category> categorys = manageSystemService.selectOneCategory();
		List<CategorySecond> categorySeconds = manageSystemService.selectTwoCategory();
		mav.addObject("categorys", categorys);
		mav.addObject("categorySeconds", categorySeconds);
		return mav;
	}

	@RequestMapping("/getServiceCategory")
	@ResponseBody
	public QueryResult<Category> getServiceCategory(HttpSession session, PQuery pquery) {
		session.setAttribute("page", pquery.getPage());
		QueryResult<Category> result = null;
		try {
			result = manageSystemService.getServiceCategory(pquery);
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
	@RequestMapping("/updateCat")
	@ResponseBody
	public Map<String, Object> updateCat(String name,byte is,String id) {
		Map<String, Object> map = new HashMap<>();
		try {
			List<Category> category = manageSystemService.selectByName(name);
			if (category != null && category.size() > 0) {
				map.put("code", 1);
				return map;
			}
			manageSystemService.updateCat(name,is,id);
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
			if (CommonUtils.isEmptyString(category)) {
				return result;
			}
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
		List<Proviences> city = manageSystemService.selectproviences();
		mav.addObject("city", city);
		return mav;
	}
	@RequestMapping("/serviceArea")
	public ModelAndView serviceArea(HttpSession session, String page) {
		ModelAndView mav = new ModelAndView();
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		List<Proviences> city = manageSystemService.selectproviences();
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
	@RequestMapping("/getServiceArea")
	@ResponseBody
	public QueryResult<CategoryCity> getServiceArea(HttpSession session, PQuery pquery, String city) {
		session.setAttribute("page", pquery.getPage());
		QueryResult<CategoryCity> result = null;
		if (CommonUtils.isEmptyString(city)) {
			return result;
		}
		try {
			result = manageSystemService.getServiceArea(pquery, city);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/updatePrice")
	@ResponseBody
	public Map<String, Object> updatePrice(String categoryid, String third_categoryid, String price,
			String deposit_price, String price_small, String city) {
		Map<String, Object> map = new HashMap<>();
		try {
			manageSystemService.saveOrUpdate(CommonUtils.parseInt(categoryid, 0),
					CommonUtils.parseInt(third_categoryid, 0), price, deposit_price, price_small, city);
			map.put("code", 0);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}
	@RequestMapping("/selectCity")
	@ResponseBody
	public Map<String, Object> selectCity(String id) {
		Map<String, Object> map = new HashMap<>();
		try {
			List<Cities> cities =  manageSystemService.selectCity(CommonUtils.parseInt(id, 0));
			map.put("list", cities);
			map.put("code", 0);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}
	@RequestMapping("/selectArea")
	@ResponseBody
	public Map<String, Object> selectArea(String id) {
		Map<String, Object> map = new HashMap<>();
		try {
			List<Area> cities =  manageSystemService.selectArea(CommonUtils.parseInt(id, 0));
			map.put("list", cities);
			map.put("code", 0);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}
	@RequestMapping("/getCategorySecond")
	@ResponseBody
	public Map<String, Object> getCategorySecond() {
		Map<String, Object> map = new HashMap<>();
		try {
			List<CategorySecond> list =  manageSystemService.getCategorySecond();
			map.put("list", list);
			map.put("code", 0);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}
	@RequestMapping("/addArea")
	@ResponseBody
	public Map<String, Object> addArea(String city,String area,String provience,String second) {
		Map<String, Object> map = new HashMap<>();
		try {
			manageSystemService.addArea(city,area,provience,second);
			map.put("code", 0);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}
	@RequestMapping("/delArea")
	@ResponseBody
	public Map<String, Object> delArea(String id) {
		Map<String, Object> map = new HashMap<>();
		try {
			manageSystemService.delArea(id);
			map.put("code", 0);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}
}
