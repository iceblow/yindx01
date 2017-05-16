package com.uncleserver.controller.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.uncleserver.common.CommonUtils;
import com.uncleserver.controller.BaseController;
import com.uncleserver.modelVo.ApiResult;
import com.uncleserver.service.api.FileService;


@Controller
@RequestMapping("/api/file")
public class FileController extends BaseController {
	
	@Autowired
	private FileService fileService;
	
	/**
	 * 获取文件上传token
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getUploadToken")
	@ResponseBody
	public ApiResult getUploadToken(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {
			//String filePath = request.getParameter("filePath");
			//String overwrite = request.getParameter("overwrite");
			if (!checkMethod(request.getMethod())) {
				result.setCode("103");
				result.setMessage("非法请求方式,仅支持POST");
				return result;
			}

			/*if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}*/
			/*if (CommonUtils.isEmptyString(filePath)||CommonUtils.isEmptyString(overwrite)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}*/
			result = fileService.getUploadToken();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}
	
	
	@RequestMapping("/getUploadTokenWeb")
	@ResponseBody
	public String getUploadTokenWeb(HttpServletRequest request) {
		
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			//String filePath = request.getParameter("filePath");
			//String overwrite = request.getParameter("overwrite");
			
			if (!checkMethod(request.getMethod())) {
				result.put("code", 103);
				result.put("message", "非法请求方式,仅支持POST");
				return new Gson().toJson(result);
			}

			/*if (!checkPermission(request)) {
				result.setCode("104");
				result.setMessage("验证失败,请求被拒绝");
				return result;
			}*/
			/*if (CommonUtils.isEmptyString(filePath)||CommonUtils.isEmptyString(overwrite)) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}*/
			return fileService.getUploadTokenWeb();
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", 103);
			result.put("message", "非法请求方式,仅支持POST");
			return new Gson().toJson(result);
		}
	}
	
	
	
	/**
	 * 上传图片路径
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/uploadFilePath")
	@ResponseBody
	public ApiResult uploadFilePath(HttpServletRequest request) {
		ApiResult result = getApiResult();

		try {
			String key = request.getParameter("key");
			String fileType = request.getParameter("fileType");
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
			if (CommonUtils.isEmptyString(key) || CommonUtils.isEmptyString(fileType) ) {
				result.setCode("101");
				result.setMessage("缺少接口参数");
				return result;
			}

			result = fileService.uploadFilePath(key, Integer.parseInt(fileType));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("102");
			result.setMessage("服务器端异常");
			return result;
		}
	}
	
}
