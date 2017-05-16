package com.uncleserver.controller.manage;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.uncleserver.common.CommonUtils;
import com.uncleserver.common.alipay.AlipayConfig;
import com.uncleserver.common.wxpay.WxTransfers;
import com.uncleserver.controller.BaseController;
import com.uncleserver.model.AuntCash;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;
import com.uncleserver.modelVo.ApiResult;
import com.uncleserver.modelVo.AuntCashVo;
import com.uncleserver.service.manage.ManageAuntService;

@Controller
@RequestMapping("/manage/aunt")
public class ManageAuntController extends BaseController {

	@Resource
	private ManageAuntService manageAuntService;

	@RequestMapping("/cashlist")
	public String cashlist(Map<String, Object> map, String page) {
		if (CommonUtils.isEmptyString(page))
			page = "1";
		map.put("page", page);
		return "/manage/aunt/cashlist";
	}

	/**
	 * 提现记录查询
	 * 
	 * @param pQuery
	 * @param account
	 * @param phone
	 * @param idnum
	 * @return
	 */
	@RequestMapping("/cashlistJson")
	@ResponseBody
	public QueryResult<AuntCashVo> cashlistJson(PQuery pQuery) {
		QueryResult<AuntCashVo> queryResult = manageAuntService.auntCashPageQuery(pQuery);
		return queryResult;
	}

	/**
	 * 提现记录查询
	 * 
	 * @param pQuery
	 * @param account
	 * @param phone
	 * @param idnum
	 * @return
	 */
	@RequestMapping("/passCash")
	@ResponseBody
	public ApiResult passCash(Integer id,HttpServletRequest request) {
		ApiResult result = new ApiResult();
		if (id != null) {
			AuntCash auntCash = manageAuntService.getAuntCashById(id);
			if (auntCash != null) {
				auntCash.setState((byte) 3);
				manageAuntService.saveAuntCash(auntCash);
				if (auntCash.getType() == AuntCash.alipay_type) {
					boolean transferResult = aliTransfer(auntCash.getCashid().toString(), "ALIPAY_LOGONID",
							auntCash.getAccount(), auntCash.getMoney().toString(), auntCash.getName(),
							CommonUtils.getTimeFormat(auntCash.getAddtime(), "yyyy-MM-dd HH:mm:ss") + "提现申请通过，转账"
									+ auntCash.getMoney().toString() + "元");
					if (transferResult) {
						result.setCode("1");
						result.setMessage("提现申请通过，转账中!");
					} else {
						result.setCode("0");
						result.setMessage("请求错误，转账失败!");
					}
				} else {//微信转账
					boolean transferResult = WxTransfers.doTransfers(auntCash.getCashid().toString(), auntCash.getMoney().floatValue(), "olgJbw3DaGmmpw3hbNui5Y2cFexE", CommonUtils.getIpAddr(request));
					if (transferResult) {
						result.setCode("1");
						result.setMessage("提现申请通过，转账中!");
					} else {
						result.setCode("0");
						result.setMessage("请求错误，转账失败!");
					}
				}
			}else{
				result.setCode("0");
				result.setMessage("转账失败，数据不存在!");
			}
		}else{
			result.setCode("0");
			result.setMessage("转账失败，数据不存在!");
		}
		return result;
	}

	/**
	 * 提现记录查询
	 * 
	 * @param pQuery
	 * @param account
	 * @param phone
	 * @param idnum
	 * @return
	 */
	@RequestMapping("/refuseCash")
	@ResponseBody
	public ApiResult refuseCash(Integer id) {
		ApiResult result = new ApiResult();
		if (id != null) {
			AuntCash auntCash = manageAuntService.getAuntCashById(id);
			if (auntCash != null) {
				auntCash.setState((byte) 2);
				manageAuntService.saveAuntCash(auntCash);
				result.setCode("1");
				result.setMessage("拒绝提现!");
			}
		}
		return result;
	}

	public boolean aliTransfer(String out_no, String payee_type, String account, String amount, String pay_real_name,
			String remark) {

		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayConfig.appid,
				AlipayConfig.private_key, "json", "utf-8", AlipayConfig.ali_public_key, "RSA");
		AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
		request.setBizContent("{" + "    \"out_biz_no\":\"" + out_no + "\"," + "    \"payee_type\":\"" + payee_type
				+ "\"," + "    \"payee_account\":\"" + account + "\"," + "    \"amount\":\"" + amount + "\","
				+ "    \"payee_real_name\":\"" + pay_real_name + "\"," + "    \"remark\":\"" + remark + "\"" + "  }");
		AlipayFundTransToaccountTransferResponse response;
		try {
			response = alipayClient.execute(request);
			return response.isSuccess();
		} catch (AlipayApiException e) {
			e.printStackTrace();
			return false;
		}
	}
}
