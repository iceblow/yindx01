package com.uncleserver.controller.manage;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.controller.BaseController;
import com.uncleserver.model.AuntMessageSys;
import com.uncleserver.model.Cities;
import com.uncleserver.model.MessageSys;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;
import com.uncleserver.service.manage.ManagePuseService;
import com.uncleserver.service.manage.ManageUserService;
import com.uncleserver.service.manage.ManagerOthersService;

@Controller
@RequestMapping("/manage/puse")
public class ManagePuseControll extends BaseController {
	@Resource
	private ManagePuseService managePuseService;
	@Resource
	private ManageUserService manageUserService;
	@Autowired
	private ManagerOthersService managerOthersService;

	@RequestMapping("/puseList")
	public ModelAndView getPuseList(HttpSession session, String page) {
		ModelAndView mav = new ModelAndView();
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		mav.setViewName("manage/puse/puselist");
		return mav;
	}

	@RequestMapping("/getPuseList")
	@ResponseBody
	public QueryResult<MessageSys> getPuseList(HttpSession session, PQuery pquery) {
		session.setAttribute("page", pquery.getPage());
		QueryResult<MessageSys> result = null;
		try {
			result = managePuseService.getPuseList(pquery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/puseAuntList")
	public ModelAndView getPuseAuntList(HttpSession session, String page) {
		ModelAndView mav = new ModelAndView();
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		mav.setViewName("manage/puse/puseAuntlist");
		return mav;
	}

	@RequestMapping("/getPuseAuntList")
	@ResponseBody
	public QueryResult<AuntMessageSys> getPuseAuntList(HttpSession session, PQuery pquery) {
		session.setAttribute("page", pquery.getPage());
		QueryResult<AuntMessageSys> result = null;
		try {
			result = managePuseService.getPuseAuntList(pquery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/getcompanyByid")
	@ResponseBody
	public String getcompanyByid(String companyId) {
		String companyName = manageUserService.getcompanyByid(CommonUtils.parseInt(companyId, 0));
		return companyName;
	}

	@RequestMapping("/getAuntByid")
	@ResponseBody
	public String getAuntByid(String id) {
		String companyName = manageUserService.getAuntByid(CommonUtils.parseInt(id, 0));
		return companyName;
	}

	@RequestMapping("/delAuntPuse")
	@ResponseBody
	public int delAuntPuse(String id) {
		int result = 0;
		result = managePuseService.delAuntPuse(CommonUtils.parseInt(id, 0));
		return result;

	}

	@RequestMapping("/delPuse")
	@ResponseBody
	public int delPuse(String id) {
		int result = 0;
		result = managePuseService.delPuse(CommonUtils.parseInt(id, 0));
		return result;

	}

	@RequestMapping("/goaddPuse")
	@ResponseBody
	public ModelAndView goaddPuse(HttpSession session, String page) {
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		ModelAndView mav = new ModelAndView();
		List<Cities> cities =  managerOthersService.getAllCities();
		mav.addObject("cities",cities);
		mav.setViewName("manage/puse/addPuse");
		return mav;

	}

	@RequestMapping("/insertPuse")
	@ResponseBody
	public int insertPuse(String type, String title, String detail, String linkTitle, String linkType,
			String linkContent, String city) {
		int result;
		result = managePuseService.addPuse(type, title, detail, linkTitle, linkType, linkContent, city);
		return result;
	}
	
	@RequestMapping("/goaddAuntPuse")
	@ResponseBody
	public ModelAndView goaddAuntPuse(HttpSession session, String page) {
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		ModelAndView mav = new ModelAndView();
		List<Cities> cities =  managerOthersService.getAllCities();
		mav.addObject("cities",cities);
		mav.setViewName("manage/puse/addAuntPuse");
		return mav;

	}
	
	@RequestMapping("/insertAuntPuse")
	@ResponseBody
	public int insertAuntPuse(String type, String title, String detail, String linkTitle, String linkType,
			String linkContent, String city) {
		int result;
		result = managePuseService.addAuntPuse(type, title, detail, linkTitle, linkType, linkContent, city);
		return result;
	}
}
