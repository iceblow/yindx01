package com.uncleserver.controller.company;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.uncleserver.common.CommonUtils;
import com.uncleserver.common.Constant;
import com.uncleserver.controller.BaseController;
import com.uncleserver.model.Aunt;
import com.uncleserver.model.CompanyWithBLOBs;
import com.uncleserver.model.FileInfo;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;
import com.uncleserver.service.company.CompanyService;

@Controller
@RequestMapping("/company/company")
public class CompanyController extends BaseController {

	@Resource
	private CompanyService companyService;

	/**
	 * 企业信息
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/companyInfo")
	public ModelAndView companyInfo(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Integer companyid = (Integer) session.getAttribute("companyId");
		CompanyWithBLOBs company = (CompanyWithBLOBs) companyService
				.selectCompanyByCid(CommonUtils.parseInt(companyid + "", 0));
		FileInfo logo = companyService.selectFileInfo(company.getLogoPicid());
		if (logo != null) {
			company.setLogourl(Constant.DOMAINOFBUCKET + "/" + logo.getFilePath());
		}
		FileInfo listpic = companyService.selectFileInfo(company.getListpic());
		if (listpic != null) {
			company.setListpicurl(Constant.DOMAINOFBUCKET + "/" + listpic.getFilePath());
		}
		FileInfo piclist = companyService.selectFileInfo(CommonUtils.parseInt(company.getPiclist(), 0));
		if (piclist != null) {
			company.setPicurllist(Constant.DOMAINOFBUCKET + "/" + piclist.getFilePath());
		}
		mav.addObject("company", company);
		return mav;
	}

	@RequestMapping("/saveCompany")
	@ResponseBody
	public String saveCompany(HttpSession session, String name, CompanyWithBLOBs company) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer companyId = (Integer) session.getAttribute("companyId");
		CompanyWithBLOBs cp = (CompanyWithBLOBs) companyService.selectCompanyByCid(companyId);

		if (cp == null) {
			map.put("code", 0);
			map.put("message", "公司信息错误，请重新登录!");
			return JSON.toJSONString(map);
		}

		// logo
		FileInfo logo = companyService.selectFileInfo(cp.getLogoPicid());
		if (company.getLogourl() != null && company.getLogourl().length() > 0) {
			if (logo == null) {
				cp.setLogoPicid(companyService.saveFileInfo(company.getLogourl()));
			} else if (!(logo.getFilePath().equals(company.getLogourl()))) {
				cp.setLogoPicid(companyService.saveFileInfo(company.getLogourl()));
				logo.setState((byte) 0);
				companyService.updateFileInfo(logo);
			}
		}

		// 列表图片
		FileInfo listpic = companyService.selectFileInfo(cp.getListpic());
		if (company.getListpicurl() != null && company.getListpicurl().length() > 0) {
			if (listpic == null) {
				cp.setListpic(companyService.saveFileInfo(company.getListpicurl()));
			} else if (!(listpic.getFilePath().equals(company.getListpicurl()))) {
				cp.setListpic(companyService.saveFileInfo(company.getListpicurl()));
				listpic.setState((byte) 0);
				companyService.updateFileInfo(listpic);
			}
		}

		// 荣誉图片
		FileInfo piclist = companyService.selectFileInfo(CommonUtils.parseInt(cp.getPiclist(), 0));
		if (company.getPicurllist() != null && company.getPicurllist().length() > 0) {
			if (piclist == null) {
				cp.setPiclist(companyService.saveFileInfo(company.getPicurllist()) + "");
			} else if (!(piclist.getFilePath().equals(company.getPicurllist()))) {
				cp.setPiclist(companyService.saveFileInfo(company.getPicurllist()) + "");
				piclist.setState((byte) 0);
				companyService.updateFileInfo(piclist);
			}
		}
		cp.setName(company.getName());
		cp.setType(company.getType());
		cp.setProfile(company.getProfile());
		cp.setYearCreate(company.getYearCreate());
		cp.setPeopleCount(company.getPeopleCount());
		cp.setCity(company.getCity());
		cp.setAddress(company.getAddress());
		cp.setRelationPeople(company.getRelationPeople());
		cp.setRelationPhone(company.getRelationPhone());
		cp.setMainBusiness(company.getMainBusiness());
		cp.setCompanyDetail(company.getCompanyDetail());
		companyService.updateByPrimaryKeyWithBLOBs(cp);

		map.put("code", 1);
		map.put("message", "保存成功!");
		return JSON.toJSONString(map);
	}

	/**
	 * 员工列表页
	 * 
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping("/stafflist")
	public ModelAndView stafflist(HttpSession session, String page) {
		ModelAndView mav = new ModelAndView();
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		return mav;
	}

	/**
	 * 获取员工列表
	 * 
	 * @param session
	 * @param pquery
	 * @return
	 */
	@RequestMapping("/getStaffList")
	@ResponseBody
	public QueryResult<Aunt> getStaffList(HttpSession session, PQuery pquery) {
		session.setAttribute("page", pquery.getPage());
		Integer companyId = (Integer) session.getAttribute("companyId");
		QueryResult<Aunt> result = null;
		try {
			result = companyService.getStaffList(pquery, companyId, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 新增员工窗口
	 * 
	 * @param session
	 * @param pquery
	 * @return
	 */
	@RequestMapping("/addStaffList")
	@ResponseBody
	public QueryResult<Aunt> addStaffList(HttpSession session, PQuery pquery, String keywords) {
		QueryResult<Aunt> result = null;
		try {
			result = companyService.getStaffList(pquery, 0, keywords);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 新增员工
	 * 
	 * @param session
	 * @param ids
	 * @return
	 */
	@RequestMapping("/addStaff")
	@ResponseBody
	public String addStaff(HttpSession session, Integer[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer companyid = (Integer) session.getAttribute("companyId");
		if (ids != null) {
			List<Aunt> list = companyService.selectByids(ids);
			for (Aunt aunt : list) {
				if (aunt.getCompanyid() != 0) {
					map.put("code", 0);
					map.put("message", "该阿姨不是个体阿姨!");
					return JSON.toJSONString(map);
				}
			}
			for (Aunt aunt : list) {
				aunt.setCompanyid(companyid);
				companyService.updateAunt(aunt);
				map.put("code", 1);
				map.put("message", "添加成功!");
			}
		} else {
			map.put("code", 0);
			map.put("message", "未选择阿姨!");
		}
		return JSON.toJSONString(map);
	}

	/**
	 * 删除员工
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delStaff")
	@ResponseBody
	public String delStaff(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Aunt aunt = companyService.selectById(id);
		if (aunt != null) {
			aunt.setCompanyid(0);
			companyService.updateAunt(aunt);
			map.put("code", 1);
			map.put("message", "删除成功!");
		} else {
			map.put("code", 0);
			map.put("message", "删除失败!");
		}
		return JSON.toJSONString(map);
	}

	/**
	 * 员工详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/staffDetail")
	@ResponseBody
	public String staffDetail(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Aunt aunt = companyService.selectById(id);
		if (aunt != null) {
			String portrait = companyService.getFilePathById(aunt.getAvatar());
			if (portrait == null) {
				portrait = aunt.getThirdAvatar();
			}
			aunt.setPortrait(portrait);
			map.put("code", 1);
			map.put("aunt", aunt);
		} else {
			map.put("code", 0);
		}
		return JSON.toJSONString(map);
	}
}
