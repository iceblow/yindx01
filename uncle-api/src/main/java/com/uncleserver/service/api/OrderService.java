package com.uncleserver.service.api;

import java.util.List;

import com.uncleserver.model.Order;
import com.uncleserver.model.OrderAunt;
import com.uncleserver.model.User;
import com.uncleserver.model.UserExtra;
import com.uncleserver.modelVo.ApiResult;

public interface OrderService extends BaseService {

	ApiResult getServerCategory();
	
	ApiResult getServerCategory(String city);

	ApiResult canBookListMap(String longitude, String latitude, String serverid, String thirdids);

	ApiResult canBookAuntList(String longitude, String latitude, String serverid, String thirdids);

	ApiResult canBookCompanyList(String longitude, String latitude, String serverid, String thirdids);

	ApiResult booking(User user, String serverid, String addressid, String server_time, String book_type,
			String app_type, String picids, String book, String aunt_type, String auntids, String expect_time,
			String day_time, String thing_count, String third_json, String need_tools, String reason_mark,
			String order_target_json, String m_count, String w_count, String tip_price, String order_type,
			String foodselect, String price);

	ApiResult order(User user, String tempid, String pay_type);

	ApiResult getCanUseCoupon(User user, String orderid, String month);

	ApiResult cancel(User user, String orderid);

	ApiResult cancelOrder(User user, String orderid, String reason, String content, String pay_type);

	ApiResult payOrder(String userid, String orderid);

	ApiResult pay(User user, UserExtra extra, String orderid, String couponid, String pay_type);

	ApiResult payOrderMonth(String userid, String orderid);

	ApiResult payMonth(String userid, String orderid, String couponid, String pay_type, String month);

	ApiResult complaintOrder(String userid, String orderid, String content);

	ApiResult commentOrder(String userid, String orderid, String content, String reason, String score1, String score2,
			String score3);

	ApiResult getAuntServer(int auntid);

	ApiResult getCompanyServer(int companyid);

	ApiResult getThirdCategory(int serverid);

	ApiResult orderList(int userid, int type, int page);

	ApiResult orderDetail(int userid, int orderid);

	ApiResult payDeposit(User user, String orderid, String pay_type);

	ApiResult addTip(User user, String orderid, String count, String pay_type, String openid);

	List<Order> selectOrderFailed();

	List<Order> selectOrderUnComfirmed();

	List<Order> selectOVerTimeOrderFailed();

	int updateOrder(Order record);

	int updateAuntOrder(OrderAunt orderAunt);

	int deleteOrderPoolDate(int orderid);

	List<OrderAunt> selectAuntOrderByUserOrder(int orderid);

	void messageToUserOrderFailed(Order order);

	void messageToAuntOrderFailed(OrderAunt orderaunt);

	List<Order> selectTwoHourAlermOrder(String stime, String etime);

	void sendAlarmMessageToUser(Order order);

	void sendAlarmMessageToAunt(Order order);

	ApiResult calcelBook(User user, String orderid);

	ApiResult comfirmWages(User user, String orderid);

	ApiResult comfirmOrder(User user, String orderid);

	ApiResult comfirmMultiOrder(User user, String orderid);

}
