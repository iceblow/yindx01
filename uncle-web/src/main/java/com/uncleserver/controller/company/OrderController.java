package com.uncleserver.controller.company;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.uncleserver.common.CommonUtils;
import com.uncleserver.controller.BaseController;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;
import com.uncleserver.modelVo.ApiResult;
import com.uncleserver.modelVo.CompanyOrderVo;
import com.uncleserver.service.api.AuntHomeService;
import com.uncleserver.service.company.OrderService;

@Controller(value = "orderCompany")
@RequestMapping("/company/order")
public class OrderController extends BaseController {
	@Resource(name = "orderCompanyService")
	private OrderService orderCompanyService;

	@Autowired
	private AuntHomeService auntHomeService;

	@RequestMapping("/pendingOrder")
	public ModelAndView pendingOrder(HttpSession session, String page) {
		ModelAndView mav = new ModelAndView();
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		return mav;
	}

	@RequestMapping("/inServiceOrder")
	public ModelAndView inServiceOrder(HttpSession session, String page) {
		ModelAndView mav = new ModelAndView();
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		return mav;
	}

	@RequestMapping("/historyOrder")
	public ModelAndView historyOrder(HttpSession session, String page) {
		ModelAndView mav = new ModelAndView();
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		return mav;
	}

	@RequestMapping("/getPendingOrder")
	@ResponseBody
	public QueryResult<CompanyOrderVo> getPendingOrder(HttpSession session, PQuery pquery) {
		session.setAttribute("page", pquery.getPage());
		Object object = session.getAttribute("companyId");
		int companyid = 0;
		if (object != null) {
			companyid = CommonUtils.parseInt(object.toString(), 0);
		}
		QueryResult<CompanyOrderVo> result = null;
		try {
			result = orderCompanyService.getPendingOrder(pquery, companyid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/getInServiceOrder")
	@ResponseBody
	public QueryResult<CompanyOrderVo> getInServiceOrder(HttpSession session, PQuery pquery) {
		session.setAttribute("page", pquery.getPage());
		Object object = session.getAttribute("companyId");
		int companyid = 0;
		if (object != null) {
			companyid = CommonUtils.parseInt(object.toString(), 0);
		}
		QueryResult<CompanyOrderVo> result = null;
		try {
			result = orderCompanyService.getInServiceOrder(pquery, companyid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/getHistoryOrder")
	@ResponseBody
	public QueryResult<CompanyOrderVo> getHistoryOrder(HttpSession session, PQuery pquery) {
		session.setAttribute("page", pquery.getPage());
		Object object = session.getAttribute("companyId");
		int companyid = 0;
		if (object != null) {
			companyid = CommonUtils.parseInt(object.toString(), 0);
		}
		QueryResult<CompanyOrderVo> result = null;
		try {
			result = orderCompanyService.getHistoryOrder(pquery, companyid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/orderDetail")
	@ResponseBody
	public Map<String, Object> orderDetail(String orderid) {
		Map<String, Object> map = new HashMap<>();
		try {
			CompanyOrderVo order = orderCompanyService.orderDetail(orderid);
			map.put("code", "0");
			map.put("order", order);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", "1");
		}

		return map;
	}

	@RequestMapping("/orderDetail1")
	@ResponseBody
	public Map<String, Object> orderDetail1(String orderid) {
		Map<String, Object> map = new HashMap<>();
		try {
			CompanyOrderVo order = orderCompanyService.orderDetail1(orderid);
			map.put("code", "0");
			map.put("order", order);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", "1");
		}

		return map;
	}

	/**
	 * 接单或者抢单
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/accepeOrder")
	@ResponseBody
	public String acceptOrder(HttpSession session, String orderidUser, String orderid) {
		Map<String, Object> map = new HashMap<String, Object>();
		ApiResult result = getApiResult();

		result = auntHomeService.acceptOrder(CommonUtils.parseInt(session.getAttribute("companyId").toString(), 0), 1,
				CommonUtils.parseInt(orderid, 0), CommonUtils.parseInt(orderidUser, 0),0,0);

		map.put("code", 1);
		map.put("message", result.getM());

		return JSON.toJSONString(map);

	}

	/**
	 * 拒单
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/refuseOrder")
	@ResponseBody
	public String refuseOrder(HttpSession session, String orderid, String orderidUser, String reason, String content) {
		Map<String, Object> map = new HashMap<String, Object>();
		ApiResult result = getApiResult();
		result = auntHomeService.refuseOrder(session.getAttribute("companyId").toString(), "1", orderid, orderidUser,
				reason, content);
		map.put("code", 1);
		map.put("message", result.getM());
		return JSON.toJSONString(map);

	}
}
