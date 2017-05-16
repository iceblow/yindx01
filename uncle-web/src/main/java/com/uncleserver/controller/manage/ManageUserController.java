package com.uncleserver.controller.manage;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.controller.BaseController;
import com.uncleserver.model.Aunt;
import com.uncleserver.model.Company;
import com.uncleserver.model.User;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;
import com.uncleserver.service.manage.ManageUserService;

@Controller
@RequestMapping("/manage/user")
public class ManageUserController extends BaseController {
	@Resource
	private ManageUserService manageUserService;

	@RequestMapping("/appUser")
	public ModelAndView appUser(HttpSession session, String page) {
		ModelAndView mav = new ModelAndView();
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		return mav;
	}

	@RequestMapping("/getAppUser")
	@ResponseBody
	public QueryResult<User> getAppUser(HttpSession session, PQuery pquery, String name, String phone) {
		session.setAttribute("page", pquery.getPage());
		QueryResult<User> result = null;
		try {
			result = manageUserService.getAppUser(pquery, name, phone);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/getAppUserByid")
	@ResponseBody
	public User getAppUserByid(String id) {
		User result = null;
		result = manageUserService.getAppUserByid(CommonUtils.parseInt(id, 0));
		return result;

	}

	@RequestMapping("/companyUser")
	public ModelAndView companyUser(HttpSession session, String page) {
		ModelAndView mav = new ModelAndView();
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		return mav;
	}

	@RequestMapping("/getCompanyUser")
	@ResponseBody
	public QueryResult<Company> getCompanyUser(HttpSession session, PQuery pquery, String name, String phone) {
		session.setAttribute("page", pquery.getPage());
		QueryResult<Company> result = null;
		try {
			result = manageUserService.getCompanyUser(pquery, name, phone);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/getCompanyUserByid")
	@ResponseBody
	public Company getCompanyUserByid(String id) {
		Company result = null;
		result = manageUserService.getCompanyUserByid(CommonUtils.parseInt(id, 0));
		return result;

	}

	@RequestMapping("/auntUser")
	public ModelAndView auntUser(HttpSession session, String page) {
		ModelAndView mav = new ModelAndView();
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		return mav;
	}

	@RequestMapping("/getAuntUser")
	@ResponseBody
	public QueryResult<Aunt> getAuntUser(HttpSession session, PQuery pquery, String name, String phone) {
		session.setAttribute("page", pquery.getPage());
		QueryResult<Aunt> result = null;
		try {
			result = manageUserService.getAuntUser(pquery, name, phone);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/getAuntUserByid")
	@ResponseBody
	public Aunt getAuntUserByid(String id) {
		Aunt result = null;
		result = manageUserService.getAuntUserByid(CommonUtils.parseInt(id, 0));
		return result;

	}

	@RequestMapping("/updateAuntUserState")
	@ResponseBody
	public void updateAuntUserState(String auntid, String state) {
		manageUserService.updateAuntUserState(Integer.parseInt(auntid), Integer.parseInt(state));
	}

	@RequestMapping("/updateCompanyUserState")
	@ResponseBody
	public void updateCompanyUserState(String companyid, String stateDel) {
		manageUserService.updateCompanyUserState(Integer.parseInt(companyid), Integer.parseInt(stateDel));
	}

	@RequestMapping("/deluser")
	@ResponseBody
	public int deluser(String companyid) {
		int result;
		result = manageUserService.deluser(CommonUtils.parseInt(companyid, 0));
		return result;
	}

	@RequestMapping("/delAunt")
	@ResponseBody
	public int delAunt(String id) {
		int result=0;
		result = manageUserService.delAunt(CommonUtils.parseInt(id, 0));
		return result;
	}

	@RequestMapping("/changPrice")
	@ResponseBody
	public int changPrice(String id, String point) {
		int result = 0;
		result=manageUserService.changPrice(CommonUtils.parseInt(id, 0),CommonUtils.parseInt(point, 0));
		return result;

	}

}
