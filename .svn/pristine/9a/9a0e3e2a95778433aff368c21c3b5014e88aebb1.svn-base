package com.uncleserver.service.company.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.model.CategorySecond;
import com.uncleserver.model.Order;
import com.uncleserver.model.OrderAunt;
import com.uncleserver.model.OrderPool;
import com.uncleserver.model.OrderThirdTemp;
import com.uncleserver.model.User;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;
import com.uncleserver.modelVo.CompanyOrderVo;
import com.uncleserver.service.api.Impl.BaseServiceImpl;
import com.uncleserver.service.company.OrderService;

@Service(value="orderCompanyService")
public class OrderServiceImpl extends BaseServiceImpl implements OrderService{
	@Override
	public QueryResult<CompanyOrderVo> getPendingOrder(PQuery pquery,int companyid) {
		List<OrderPool> order = orderPoolMapper.getPendingOrder( pquery.getStartPage(), pquery.getRows(),companyid);
		List<CompanyOrderVo> com = new ArrayList<>();
		for (OrderPool orderPool : order) {
			if(orderPool != null){
				CompanyOrderVo vo = new CompanyOrderVo();
				Order o = orderMapper.selectByPrimaryKey(orderPool.getOrderid_user());
				if(o != null){
					vo.setOrdernum(o.getOrdernum());
					vo.setGotime(CommonUtils.getTimeFormat(o.getServerStartTime(), "HH:mm")+"-"+CommonUtils.getTimeFormat(o.getServerEndTime(), "HH:mm"));
					vo.setPrice(orderPool.getPrice().toString());
					vo.setOrderid(o.getOrderid());
					if(orderPool.getTipState() == 0){
						vo.setIsprice("没有");
					}else{
						vo.setIsprice("有");
					}
					vo.setAddress(o.getAddressdetail());
					vo.setId(orderPool.getOrderid());
					if(o.getState() == 1){
						vo.setOrdertype(o.getOrderType());
					}else if(o.getState() == 2){
						vo.setOrdertype(3);
					}else if(orderPool.getAuntid()==0){
						vo.setOrdertype(1);
					}
					CategorySecond categorySecond = categorySecondMapper.selectByPrimaryKey(o.getCategoryid());
					if(categorySecond != null){
						vo.setType(categorySecond.getName());
					}
				}
				com.add(vo);
			}
		}
//				
		long count = orderPoolMapper.getPendingOrderCount(companyid);
		QueryResult<CompanyOrderVo> result = new QueryResult<>(com, count, pquery.getPage(), pquery.getRows());
		return result;
	}

	@Override
	public QueryResult<CompanyOrderVo> getHistoryOrder(PQuery pquery, int companyid) {
		List<CompanyOrderVo> com = new ArrayList<>();
		List<OrderAunt> orderAunts = orderAuntMapper.getHistoryOrder(pquery.getStartPage(), pquery.getRows(), companyid);
		for (OrderAunt orderAunt : orderAunts) {
			CompanyOrderVo vo = new CompanyOrderVo();
			vo.setGotime(CommonUtils.getTimeFormat(orderAunt.getServerStartTime(), "HH:mm") + "-" + CommonUtils.getTimeFormat(orderAunt.getServerEndTime(), "HH:mm"));
			vo.setPrice(orderAunt.getLastPrice().toString());
			vo.setOrderid(orderAunt.getOrderid());
			if (orderAunt.getTipPrice() == null && orderAunt.getTipPrice().equals("0")) {
				vo.setIsprice("没有");
			} else {
				vo.setIsprice("有");
			}
			vo.setAddress(orderAunt.getAddressdetail());
			CategorySecond categorySecond = categorySecondMapper.selectByPrimaryKey(orderAunt.getCategoryid());
			if (categorySecond != null) {
				vo.setType(categorySecond.getName());
			}
			if(orderAunt.getState() == 6){
				vo.setState("已支付");
			}else if(orderAunt.getState() == 7){
				vo.setState("已退单");
			}
			com.add(vo);
		}
		long count = orderAuntMapper.getHistoryOrderCount(companyid);
		QueryResult<CompanyOrderVo> result = new QueryResult<>(com, count, pquery.getPage(), pquery.getRows());
		return result;
	}

	@Override
	public QueryResult<CompanyOrderVo> getInServiceOrder(PQuery pquery, int companyid) {
		List<CompanyOrderVo> com = new ArrayList<>();
		List<OrderAunt> orderAunts = orderAuntMapper.getInServiceOrder(pquery.getStartPage(), pquery.getRows(), companyid);
		for (OrderAunt orderAunt : orderAunts) {
			CompanyOrderVo vo = new CompanyOrderVo();
			vo.setGotime(CommonUtils.getTimeFormat(orderAunt.getServerStartTime(), "HH:mm") + "-" + CommonUtils.getTimeFormat(orderAunt.getServerEndTime(), "HH:mm"));
			vo.setPrice(orderAunt.getLastPrice().toString());
			vo.setOrderid(orderAunt.getOrderid());
			if (orderAunt.getTipPrice() == null && orderAunt.getTipPrice().equals("0")) {
				vo.setIsprice("没有");
			} else {
				vo.setIsprice("有");
			}
			vo.setAddress(orderAunt.getAddressdetail());
			CategorySecond categorySecond = categorySecondMapper.selectByPrimaryKey(orderAunt.getCategoryid());
			if (categorySecond != null) {
				vo.setType(categorySecond.getName());
			}
			if(orderAunt.getState() == 2){
				vo.setState("未出发");
			}else if(orderAunt.getState() == 3){
				vo.setState("已出发");
			}else if(orderAunt.getState() == 4){
				vo.setState("已到达");
			}else if(orderAunt.getState() == 3){
				vo.setState("待支付");
			}
			com.add(vo);
		}
		long count = orderAuntMapper.getInServiceOrderCount(companyid);
		QueryResult<CompanyOrderVo> result = new QueryResult<>(com, count, pquery.getPage(), pquery.getRows());
		return result;
	}
	@Override
	public CompanyOrderVo orderDetail(String orderid) {
		CompanyOrderVo vo = new CompanyOrderVo();
		Order order = orderMapper.selectByPrimaryKey(CommonUtils.parseInt(orderid, 0));
		if(order != null){
			List<OrderThirdTemp> orderThirdTemp = orderThirdTempMapper.selectByOrderid(order.getOrderid());
			if(orderThirdTemp != null && orderThirdTemp.size() > 0){
				String contant = "";
				for (OrderThirdTemp o : orderThirdTemp) {
					contant += o.getName()+"/";
				}
				vo.setContant(contant.substring(0,contant.length() - 1));
			}
			CategorySecond categorySecond = categorySecondMapper.selectByPrimaryKey(order.getCategoryid());
			if(categorySecond != null){
				vo.setType(categorySecond.getName());
			}
			if(order.getServerStartTime()!=null && order.getServerEndTime()!=null)
			vo.setGotime(CommonUtils.getTimeFormat(order.getServerStartTime(), "yyyy-MM-dd HH:mm") + "-" + CommonUtils.getTimeFormat(order.getServerEndTime(), "HH:mm"));
			vo.setGoprice(order.getDepositPrice().toString());
			vo.setYutime(order.getExpectTime().toString());
			vo.setRe(order.getBook());
			User user = userMapper.selectByPrimaryKey(order.getUserid());
			if(user != null){
				vo.setAddress(order.getRname()+order.getAddressdetail());
			}
			if(!CommonUtils.isEmptyString(order.getPicIds())){
				String[] picid = order.getPicIds().split(",");
				String [] pic = new String[picid.length];
				for (int i = 0; i<picid.length;i++) {
					String img = getFilePathById(CommonUtils.parseInt(picid[i], 0));
					pic[i] = img;
				}
				vo.setImg(pic);
			}
		}
		return vo;
	}

	@Override
	public CompanyOrderVo orderDetail1(String orderid) {
		CompanyOrderVo vo = new CompanyOrderVo();
		OrderAunt order = orderAuntMapper.selectByPrimaryKey(CommonUtils.parseInt(orderid, 0));
		if(order != null){
			List<OrderThirdTemp> orderThirdTemp = orderThirdTempMapper.selectByOrderid(order.getOrderid());
			if(orderThirdTemp != null && orderThirdTemp.size() > 0){
				String contant = "";
				for (OrderThirdTemp o : orderThirdTemp) {
					contant += o.getName()+"/";
				}
				vo.setContant(contant.substring(0,contant.length() - 1));
			}
			CategorySecond categorySecond = categorySecondMapper.selectByPrimaryKey(order.getCategoryid());
			if(categorySecond != null){
				vo.setType(categorySecond.getName());
			}
			if(order.getServerStartTime()!=null && order.getServerEndTime()!=null)
			vo.setGotime(CommonUtils.getTimeFormat(order.getServerStartTime(), "yyyy-MM-dd HH:mm") + "-" + CommonUtils.getTimeFormat(order.getServerEndTime(), "HH:mm"));
			vo.setGoprice(order.getDepositPrice().toString());
			vo.setYutime(order.getExpectTime().toString());
			vo.setRe(order.getBook());
			User user = userMapper.selectByPrimaryKey(order.getUserid());
			if(user != null){
				vo.setAddress(order.getRname()+order.getAddressdetail());
			}
			if(!CommonUtils.isEmptyString(order.getPicIds())){
				String[] picid = order.getPicIds().split(",");
				String [] pic = new String[picid.length];
				for (int i = 0; i<picid.length;i++) {
					String img = getFilePathById(CommonUtils.parseInt(picid[i], 0));
					pic[i] = img;
				}
				vo.setImg(pic);
			}
		}
		return vo;
	}
}
