package com.uncleserver.service.api;

import com.uncleserver.model.Company;
import com.uncleserver.model.CompanyExtra;
import com.uncleserver.model.User;
import com.uncleserver.model.UserExtra;
import com.uncleserver.modelVo.ApiResult;

public interface AuntOrderService extends BaseService {

	ApiResult orderList(String userid, int user_type, String accesstoken);

	ApiResult orderListHis(String userid, int user_type, Integer serverid, Integer comment_type, Integer page,
			String accesstoken);

	ApiResult orderDetail(String userid, int user_type, Integer orderid, String accesstoken, String type);

	// ApiResult cancel(String userid,Integer orderid,String accesstoken);

	ApiResult cancelOrder(String userid, Integer orderid, String reason, String content, String accesstoken);

	ApiResult complaintOrder(String userid, Integer orderid, String content, String accesstoken);

	ApiResult out(String userid, Integer orderid, String accesstoken);

	ApiResult start(String userid, Integer orderid, String price, String month, String accesstoken);

	ApiResult end(String userid, Integer orderid, String accesstoken, String price);
	
	ApiResult modifyPrice(String userid,Integer orderid,String price,String month,String accesstoken);
	
	ApiResult booking(Company company, String serverid, String addressid, String server_time, String book_type,
			String app_type, String picids, String book, String aunt_type, String auntids, String expect_time,
			String day_time, String thing_count, String third_json, String need_tools, String reason_mark,
			String order_target_json, String m_count, String w_count, String tip_price, String order_type,
			String foodselect, String price);

	ApiResult order(Company company, String tempid, String pay_type);

	ApiResult getCanUseCoupon(Company company, String orderid, String month);

	ApiResult cancel(Company company, String orderid);

	ApiResult cancelOrder(Company company, String orderid, String reason, String content, String pay_type);

	ApiResult payOrder(Company company, String orderid);

	ApiResult pay(Company company, CompanyExtra extra, String orderid, String couponid, String pay_type);

	ApiResult payOrderMonth(Company company, String orderid);

	ApiResult payMonth(Company company, String orderid, String couponid, String pay_type, String month);

	ApiResult complaintOrder(Company company, String orderid, String content);

	ApiResult commentOrder(Company company, String orderid, String content, String reason, String score1, String score2,
			String score3);
	
	ApiResult orderList(Company company, int type, int page);

	ApiResult orderDetail(Company company, int orderid);

	ApiResult payDeposit(Company company, String orderid, String pay_type);

	ApiResult addTip(Company company, String orderid, String count, String pay_type, String openid);
	
	ApiResult calcelBook(Company company, String orderid);

	ApiResult comfirmWages(Company company, String orderid);

	ApiResult comfirmOrder(Company company, String orderid);

	ApiResult comfirmMultiOrder(Company company, String orderid);
}
