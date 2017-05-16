package com.uncleserver.service.company;

import com.uncleserver.model.Order;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;
import com.uncleserver.modelVo.CompanyOrderVo;
public interface OrderService {

	QueryResult<CompanyOrderVo> getPendingOrder(PQuery pquery, int companyid);

	CompanyOrderVo orderDetail(String orderid);

	QueryResult<CompanyOrderVo> getHistoryOrder(PQuery pquery, int companyid);

	QueryResult<CompanyOrderVo> getInServiceOrder(PQuery pquery, int companyid);

	CompanyOrderVo orderDetail1(String orderid);

}
