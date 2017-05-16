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
import com.uncleserver.controller.BaseController;
import com.uncleserver.model.CategorySecond;
import com.uncleserver.model.Order;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;
import com.uncleserver.service.manage.ManageOrderService;
import com.uncleserver.service.manage.ManagerOthersService;


@Controller
@RequestMapping("/manage/order")
public class ManageOrderController extends BaseController {

	@Resource
	private ManageOrderService manageOrderService;
	@Resource
	private ManagerOthersService managerOthersService;

	/**
	 * 待处理订单
	 * 
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping("/todoOrderList")
	public ModelAndView todoOrderList(HttpSession session, String page) {
		ModelAndView mav = new ModelAndView();
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		return mav;
	}

	/**
	 * 待处理订单列表
	 * 
	 * @param session
	 * @param pquery
	 * @return
	 */
	@RequestMapping("/todoList")
	@ResponseBody
	public QueryResult<Order> todoList(HttpSession session, PQuery pquery) {
		session.setAttribute("page", pquery.getPage());
		return manageOrderService.pageOrder(pquery, 0);
	}
	
	/**
	 * 查询待处理订单列表
	 * 
	 * @param session
	 * @param pquery
	 * @return
	 */
	@RequestMapping("/getTodoOrder")
	@ResponseBody
	public QueryResult<Order> getTodoOrder(HttpSession session, PQuery pquery,String ordernum,Integer categoryid,String phone) {
		session.setAttribute("page", pquery.getPage());
		return manageOrderService.getTodoOrder(pquery, 0,ordernum,categoryid,phone);
	}
	/*
	 * 查看订单
	 */
	@RequestMapping("/editOrder")
	@ResponseBody
	public ModelAndView editOrder(String orderid) {
		ModelAndView mav = new ModelAndView();
		Order order = manageOrderService.selectOrder(orderid);
		mav.addObject("order",order);
		mav.setViewName("manage/order/editOrder");
		return mav;
	}
	/*
	 * 获取订单类型
	 */
	@RequestMapping("/getCategory")
	public void getCategory(HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<CategorySecond> list = managerOthersService.getAllCategorySecond();
		map.put("list", list);
		CommonUtils.setMaptoJson(response, map);
	}

	/**
	 * 已完成订单
	 * 
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping("/completeOrderList")
	public ModelAndView completeOrderList(HttpSession session, String page) {
		ModelAndView mav = new ModelAndView();
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		return mav;
	}

	/**
	 * 已完成订单列表
	 * 
	 * @param session
	 * @param pquery
	 * @return
	 */
	@RequestMapping("/completeList")
	@ResponseBody
	public QueryResult<Order> completeList(HttpSession session, PQuery pquery) {
		session.setAttribute("page", pquery.getPage());
		return manageOrderService.pageOrder(pquery, 1);
	}

	/**
	 * 异常订单
	 * 
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping("/exceptionOrderList")
	public ModelAndView exceptionOrderList(HttpSession session, String page) {
		ModelAndView mav = new ModelAndView();
		if (!CommonUtils.isEmptyString(page)) {
			session.setAttribute("page", page);
		}
		return mav;
	}

	/**
	 * 异常订单列表
	 * 
	 * @param session
	 * @param pquery
	 * @return
	 */
	@RequestMapping("/exceptionList")
	@ResponseBody
	public QueryResult<Order> exceptionList(HttpSession session, PQuery pquery) {
		session.setAttribute("page", pquery.getPage());
		return manageOrderService.pageOrder(pquery, 2);
	}
	
	@RequestMapping("/dealOrder")
	public void dealOrder(String orderid,HttpServletResponse response) {
		manageOrderService.dealOrder(orderid);
	}
	@RequestMapping("/completeOrder")
	public void completeOrder(String orderid,HttpServletResponse response) {
		manageOrderService.completeOrder(orderid);
	}
}
