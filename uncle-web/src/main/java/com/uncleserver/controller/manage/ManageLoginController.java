package com.uncleserver.controller.manage;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.controller.BaseController;
import com.uncleserver.model.Admin;
import com.uncleserver.service.manage.ManageLoginService;
import com.uncleserver.web.common.IndentifyingCode;

@Controller
@RequestMapping("/manage")
public class ManageLoginController extends BaseController {

	@Resource
	private ManageLoginService manageLoginService;

	@RequestMapping("/home")
	public String indexManage() {
		return "manage/home";
	}

	@RequestMapping("/login")
	public ModelAndView login(String message, String username) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", message);
		modelAndView.addObject("username", username);
		modelAndView.setViewName("manage/login");
		return modelAndView;
	}

	@RequestMapping("/toLogin")
	public ModelAndView toLogin(String logincode, String username, String password, HttpSession session) {
		ModelAndView apiResult = new ModelAndView();
		try {
			if (CommonUtils.isEmptyString(logincode)) {
				apiResult.addObject("message", "验证码不能为空");
				apiResult.addObject("username", username);
				apiResult.setViewName("redirect:/manage/login");
				return apiResult;
			}
			if (CommonUtils.isEmptyString(username)) {
				apiResult.addObject("message", "账号不能为空");
				apiResult.addObject("username", username);
				apiResult.setViewName("redirect:/manage/login");
				return apiResult;
			}
			if (CommonUtils.isEmptyString(password)) {
				apiResult.addObject("message", "密码不能为空");
				apiResult.addObject("username", username);
				apiResult.setViewName("redirect:/manage/login");
				return apiResult;
			}
			// 从session中获取验证码
			String checkCode = (String) session.getAttribute("imgcode");
			if (CommonUtils.isEmptyString(checkCode)) {
				apiResult.addObject("message", "验证码丢失");
				apiResult.addObject("username", username);
				apiResult.setViewName("redirect:/manage/login");
				return apiResult;
			}
			// 将用户输入的验证码进行验证
			logincode = logincode.toUpperCase();
			if (!checkCode.equals(logincode)) {
				apiResult.addObject("message", "验证码不正确");
				apiResult.addObject("username", username);
				apiResult.setViewName("redirect:/manage/login");
				return apiResult;
			}
			password = CommonUtils.cleanXSS(password);
			for (int i = 0; i < 3; i++) {
				password = CommonUtils.MD5(password);
			}
			// AdminInfo adminInfo =
			// adminInfoMapper.selectByNP(CommonUtils.cleanXSS(username),password);
			Admin admin = manageLoginService.checkLogin(logincode, username, password);
			if (admin == null) {
				apiResult.addObject("message", "账号或密码错误");
				apiResult.addObject("username", username);
				apiResult.setViewName("redirect:/manage/login");
				return apiResult;
			}
			session.setAttribute("adminId", admin.getAdminid());
			session.setAttribute("loginName", username);
			// apiResult.addObject("message","登录成功");
			apiResult.setViewName("redirect:/manage/home");
			return apiResult;
		} catch (Exception e) {
			e.printStackTrace();
			apiResult.addObject("message", "服务器异常");
			apiResult.addObject("username", username);
			apiResult.setViewName("redirect:/manage/login");
			return apiResult;
		}
	}

	@RequestMapping("/toLoginOut")
	public ModelAndView toLoginOut(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		session.invalidate();
		modelAndView.setViewName("redirect:/manage/login");
		return modelAndView;
	}

	@RequestMapping("/createImg")
	public void createImg(HttpServletRequest req, HttpSession session, HttpServletResponse res) throws IOException {
		// 生成验证码及图片
		Object[] objs = IndentifyingCode.createImage();
		// 将验证码存入session
		String imgcode = (String) objs[0];
		session.setAttribute("imgcode", imgcode);
		// 将图片输出给浏览器
		res.setContentType("image/jpeg");
		BufferedImage img = (BufferedImage) objs[1];
		// 该输出流由tomcat创建，目标是浏览器
		OutputStream os = res.getOutputStream();
		ImageIO.write(img, "jpeg", os);
		os.close();
	}
}
