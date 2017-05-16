package com.uncleserver.controller.company;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.controller.BaseController;
import com.uncleserver.modelVo.ApiResult;
import com.uncleserver.service.company.RegistService;

@Controller
@RequestMapping("/company")
public class RegistController extends BaseController {

	@Autowired
	private RegistService registService;

	@RequestMapping("/toRegist")
	public ModelAndView toRegist(HttpServletRequest req) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("company/regist");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping("/doRegist")
	public ApiResult doRegist(HttpServletRequest req, String phone, String sendVCode, String pwd, String company_type,
			String company_name, String company_ads) {

		ApiResult result = new ApiResult();
		String sessionId = req.getSession().getId().toLowerCase();
		if (CommonUtils.isEmptyString(phone) || CommonUtils.isEmptyString(pwd) || CommonUtils.isEmptyString(sendVCode)
				|| CommonUtils.isEmptyString(company_type) || CommonUtils.isEmptyString(company_name)
				|| CommonUtils.isEmptyString(company_ads)) {
			result.setCode("101");
			result.setMessage("缺少接口参数");
			return result;
		}
		try {
			result = registService.registerUser(phone, pwd, sendVCode, company_type, company_name, company_ads,
					sessionId);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}

	@RequestMapping("/getRegistVCode")
	@ResponseBody
	public ApiResult getVCode(String phone, String type) {
		ApiResult result = new ApiResult();
		try {
			if (CommonUtils.isEmptyString(phone) || CommonUtils.isEmptyString(type)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
			if (!type.equals("0")) {
				result.setCode("2");
				result.setMessage("不支持的验证码类型");
				return result;
			}
			result = registService.getVCode(phone, CommonUtils.parseInt(type, 0));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}

	}
}
