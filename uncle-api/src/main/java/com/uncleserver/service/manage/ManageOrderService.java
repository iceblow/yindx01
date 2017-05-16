package com.uncleserver.service.manage;

import com.uncleserver.model.Order;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;

public interface ManageOrderService {

	QueryResult<Order> pageOrder(PQuery pquery, Integer type);
	QueryResult<Order> getTodoOrder(PQuery pquery, Integer type,String ordernum,Integer categoryid,String phone);
	Order selectOrder(String orderid);
	int dealOrder(String orderid);
	int completeOrder(String orderid);
}
