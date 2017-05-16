package com.uncleserver.common.alipay;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;


public class AlipayRefundTrade {
	
	public static boolean alipayRefundRequest(String out_request_no,String out_trade_no,
			String trade_no, double refund_amount) {

		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.OPEN_WAY, AlipayConfig.appid,
				AlipayConfig.private_key, "json", "utf-8", AlipayConfig.ali_public_key, "RSA"
		);
		AlipayTradeRefundRequest  request = new AlipayTradeRefundRequest();

		request.setBizContent("{" +
				"    \"out_trade_no\":\""+out_trade_no+"\"," +
				"    \"trade_no\":\""+trade_no+"\"," +
				"    \"refund_amount\":"+refund_amount+"," +
				"    \"out_request_no\":\""+out_request_no+"\"," +
				"    \"refund_reason\":\"商户退款\""  +
				"  }");

		AlipayTradeRefundResponse  response;
		try {
				response = alipayClient.execute(request);
			if (response.isSuccess()) {
				System.out.println(response.getBody());
			} else {
				System.out.println(response.getBody());
			}
			return response.isSuccess();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return false;
	}

}
