package com.uncleserver.controller.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.controller.BaseController;
import com.uncleserver.modelVo.ApiResult;
import com.uncleserver.service.api.AuntOrderService;

@Controller
@RequestMapping("/auntapi/order")
public class AuntOrderController extends BaseController {
	@Autowired
	private AuntOrderService auntOrderService;

	/**
	 * 设置当前状态
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/orderList")
	@ResponseBody
	public ApiResult orderList(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}

			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}
			String userid = request.getParameter("userid");
			String user_type = request.getParameter("user_type");

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(user_type)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			result = auntOrderService.orderList(userid, CommonUtils.parseInt(user_type, 0), accesstoken);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 设置当前状态
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/orderListHis")
	@ResponseBody
	public ApiResult orderListHis(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}

			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}
			String userid = request.getParameter("userid");
			String user_type = request.getParameter("user_type");
			String serverid = request.getParameter("serverid");
			String comment_type = request.getParameter("comment_type");
			String page = request.getParameter("page");

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(user_type)
					|| CommonUtils.isEmptyString(page)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
			int serveridI = CommonUtils.parseInt(serverid, 0);
			int comment_typeI = CommonUtils.parseInt(comment_type, 0);
			Integer serveridIn = null;
			Integer comment_typeIn = null;                                                                   
			if (serveridI == 0) {

			} else {
				serveridIn = serveridI;
			}
			if (comment_typeI == 0) {

			} else {
				comment_typeIn = comment_typeI;  //
			}
			String accesstoken = request.getParameter("accesstoken");
			result = auntOrderService.orderListHis(userid, CommonUtils.parseInt(user_type, 0), serveridIn,
					comment_typeIn, CommonUtils.parseInt(page, 0), accesstoken);                     
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 设置当前状态
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/orderDetail")
	@ResponseBody
	public ApiResult orderDetail(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}

			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}
			String userid = request.getParameter("userid");
			String user_type = request.getParameter("user_type");
			String orderid = request.getParameter("orderid");
			String type = request.getParameter("type");// 1.首页 2.订单列表

			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(user_type)
					|| CommonUtils.isEmptyString(orderid) || CommonUtils.isEmptyString(type)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			result = auntOrderService.orderDetail(userid, CommonUtils.parseInt(user_type, 0),
					CommonUtils.parseInt(orderid, 0), accesstoken, type);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 设置当前状态
	 * 
	 * @param request
	 * @return
	 */
	// @RequestMapping("/cancel")
	// @ResponseBody
	// public ApiResult cancel(HttpServletRequest request) {
	// ApiResult result = getApiResult();
	//
	// try {
	//
	// if (!checkMethod(request.getMethod())) {
	// result.setCode("103");
	// result.setMessage("非法请求方式,仅支持POST");
	// return result;
	// }
	//
	// if (!checkPermission(request)) {
	// result.setCode("104");
	// result.setMessage("验证失败,请求被拒绝");
	// return result;
	// }
	// String userid = request.getParameter("userid");
	// String orderid = request.getParameter("orderid");
	//
	// if (CommonUtils.isEmptyString(userid) ||
	// CommonUtils.isEmptyString(orderid)) {
	// result.setCode("101");
	// result.setMessage("缺少接口参数");
	// return result;
	// }
	//
	// String accesstoken = request.getParameter("accesstoken");
	// // result = auntOrderService.cancel(userid,
	// // CommonUtils.parseInt(orderid, 0), accesstoken);
	// return result;
	// } catch (Exception e) {
	// e.printStackTrace();
	// result.setCode("102");
	// result.setMessage("服务器端异常");
	// return result;
	// }
	// }

	/**
	 * 设置当前状态
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/cancelOrder")
	@ResponseBody
	public ApiResult cancelOrder(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}

			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}
			String userid = request.getParameter("userid");
			String orderid = request.getParameter("orderid");
			String reason = request.getParameter("reason");
			String content = request.getParameter("content");
			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(orderid)
					|| CommonUtils.isEmptyString(reason)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			result = auntOrderService.cancelOrder(userid, CommonUtils.parseInt(orderid, 0), reason, content,
					accesstoken);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 设置当前状态
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/complaintOrder")
	@ResponseBody
	public ApiResult complaintOrder(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}

			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}
			String userid = request.getParameter("userid");
			String orderid = request.getParameter("orderid");
			String content = request.getParameter("content");
			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(orderid)
					|| CommonUtils.isEmptyString(content)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			result = auntOrderService.complaintOrder(userid, CommonUtils.parseInt(orderid, 0), content, accesstoken);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 设置当前状态
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/out")
	@ResponseBody
	public ApiResult out(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}

			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}
			String userid = request.getParameter("userid");
			String orderid = request.getParameter("orderid");
			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(orderid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			result = auntOrderService.out(userid, CommonUtils.parseInt(orderid, 0), accesstoken);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 设置当前状态
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/start")
	@ResponseBody
	public ApiResult start(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}

			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}
			String userid = request.getParameter("userid");
			String orderid = request.getParameter("orderid");
			String price = request.getParameter("price");
			String month = request.getParameter("month");
			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(orderid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			result = auntOrderService.start(userid, CommonUtils.parseInt(orderid, 0), price, month, accesstoken);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	/**
	 * 设置当前状态
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/end")
	@ResponseBody
	public ApiResult end(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}

			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}
			String userid = request.getParameter("userid");
			String orderid = request.getParameter("orderid");
			String price = request.getParameter("price");
			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(orderid)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			result = auntOrderService.end(userid, CommonUtils.parseInt(orderid, 0), accesstoken, price);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}
	
	/**
	 * 设置当前状态
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/modifyPrice")
	@ResponseBody
	public ApiResult modifyPrice(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {

			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}

			if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}
			String userid = request.getParameter("userid");
			String orderid = request.getParameter("orderid");
			String price = request.getParameter("price");
			String month = request.getParameter("month");
			if (CommonUtils.isEmptyString(userid) || CommonUtils.isEmptyString(orderid) ||  CommonUtils.isEmptyString(price)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			String accesstoken = request.getParameter("accesstoken");
			result = auntOrderService.modifyPrice(userid, CommonUtils.parseInt(orderid, 0), price,month, accesstoken);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}
	
	
}
