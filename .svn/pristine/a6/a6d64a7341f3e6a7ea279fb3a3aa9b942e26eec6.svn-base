package com.uncleserver.controller.website;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.controller.BaseController;
import com.uncleserver.model.User;
import com.uncleserver.modelVo.ApiResult;
import com.uncleserver.service.api.UserService;

@Controller
@RequestMapping("/website/login")
public class WebsiteLoginController extends BaseController{
	
	@Resource
	private UserService userService;

	@RequestMapping("/regist")
	@ResponseBody
	ApiResult regist(String phone, String vcode,
			String password,HttpSession session) {
		ApiResult result = getApiResult();
		try {
			if (CommonUtils.isEmptyString(phone)||CommonUtils.isEmptyString(vcode)||CommonUtils.isEmptyString(password)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
			result = userService.registerUser(phone, password, vcode, session.getId().toLowerCase());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
		}
		return result;
	}
	
	
	@RequestMapping("/getVcode")
	@ResponseBody
	ApiResult getVcode(String phone){
		ApiResult result = getApiResult();
		try {
			if (CommonUtils.isEmptyString(phone)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}
			result = userService.sendVcode(phone, 0);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}
	
	@RequestMapping("/login")
	@ResponseBody
	ApiResult login(String phone,String password,HttpSession session){
		ApiResult result = getApiResult();
		User user = userService.userByPhone(phone);
		if (user == null) {
			result.setCode("2");
			result.setMessage("登录失败,用户不存在");
			return result;
		}

		if (!password.equals(user.getPassword())) {
			result.setCode("3");
			result.setMessage("登录失败,密码错误");
			return result;
		}
		session.setAttribute("suser", user);
		result.setCode("1");
		result.setMessage("登录成功");
		return result;
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	ApiResult logout(String phone,String password,HttpSession session){
		ApiResult result = getApiResult();
		session.invalidate();
		result.setCode("1");
		result.setMessage("退出登录成功");
		return result;
	}
}
