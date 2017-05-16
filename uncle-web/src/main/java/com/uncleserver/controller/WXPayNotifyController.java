package com.uncleserver.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.uncleserver.common.wxpay.GetWxOrderno;
import com.uncleserver.service.api.UserService;


@Controller
@RequestMapping("/wx")
public class WXPayNotifyController extends BaseController{

	@Resource
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(value = "/notify")
	public void notify(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		System.out.println("微信回调");
		BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }
		try {
			 Map map = GetWxOrderno.doXMLParse(sb.toString());
			 String attach  = (String) map.get("attach");
			 String transaction_id = (String) map.get("transaction_id"); 
			 String openid = (String) map.get("openid");
			 System.out.println("attach:" + attach);
//			 mallService.paySuccess(attach,0,transaction_id);
			 //goodsService.paySuccess(2,attach,transaction_id,openid);
			 userService.paySuccess(2, attach, transaction_id, openid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
//        System.out.println(sb);
        //sb为微信返回的xml
	}
}
