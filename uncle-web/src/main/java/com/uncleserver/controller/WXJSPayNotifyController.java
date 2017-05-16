package com.uncleserver.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uncleserver.common.wxpay.GetWxOrderno;
import com.uncleserver.service.api.UserService;

@Controller
@RequestMapping("/wxjs")
public class WXJSPayNotifyController extends BaseController {

	@Resource
	private UserService userService;

	/**
	 * 校验信息是否是从微信服务器发过来的。
	 * 
	 * @param weChat
	 * @param out
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/notify")
	public void wxpaycallback(HttpServletRequest request, HttpServletResponse response) {
		// System.out.println("微信回调");
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader((ServletInputStream) request.getInputStream()));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			// System.out.println(sb.toString());
			Map map = GetWxOrderno.doXMLParse(sb.toString());
			String return_code = (String) map.get("return_code");
			String result_code = (String) map.get("result_code");
			if ("SUCCESS".equals(return_code)) {
				if ("SUCCESS".equals(result_code)) {

					String attach = (String) map.get("attach");
					String transaction_id = (String) map.get("transaction_id");
					String openid = (String) map.get("openid");
					System.out.println("attach:" + attach);
					// mallService.paySuccess(attach,0,transaction_id);
					// goodsService.paySuccess(2,attach,transaction_id,openid);
					userService.paySuccess(2, attach, transaction_id, openid);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
