package com.uncleserver.service.manage.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.dao.CategorySecondMapper;
import com.uncleserver.dao.OrderMapper;
import com.uncleserver.model.CategorySecond;
import com.uncleserver.model.Order;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;
import com.uncleserver.service.manage.ManageOrderService;

@Service("manageOrderService")
public class ManageOrderServiceImpl implements ManageOrderService {

	@Resource
	private OrderMapper orderMapper;
	
	@Resource
	private CategorySecondMapper categorySecondMapper;
	
	@Override
	public QueryResult<Order> pageOrder(PQuery pquery, Integer type) {
		List<Order> list = orderMapper.managePageOrder(pquery.getStartPage(), pquery.getRows(), type);
		long total = orderMapper.managePageOrderCount(type);
		for (Order order : list) {
			CategorySecond category = categorySecondMapper.selectByPrimaryKey(order.getCategoryid());
			if (category != null) {
				order.setCategoryname(category.getName());
			}
		}
		return new QueryResult<Order>(list, total, pquery.getPage(), pquery.getRows());
	}

	@Override
	public QueryResult<Order> getTodoOrder(PQuery pquery, Integer type,String ordernum,Integer categoryid,String phone) {
		if(CommonUtils.isEmptyString(ordernum)){
			ordernum = null;
		}
		if(categoryid==-1){
			categoryid = null;
		}
		if(CommonUtils.isEmptyString(phone)){
			phone = null;
		}
		List<Order> list = orderMapper.managePageOrderList(pquery.getStartPage(), pquery.getRows(), type,ordernum,categoryid,phone);
		long total = orderMapper.managePageOrderListCount(type,ordernum,categoryid,phone);
		for (Order order : list) {
			CategorySecond category = categorySecondMapper.selectByPrimaryKey(order.getCategoryid());
			if (category != null) {
				order.setCategoryname(category.getName());
			}
		}
		return new QueryResult<Order>(list, total, pquery.getPage(), pquery.getRows());
	}

	@Override
	public Order selectOrder(String orderid) {
		Order order = orderMapper.selectByPrimaryKey(Integer.parseInt(orderid));
		CategorySecond category = categorySecondMapper.selectByPrimaryKey(order.getCategoryid());
		if (category != null) {
			order.setCategoryname(category.getName());
		}
		if(order.getPosterType()==0){
			order.setStrPosterType("用户");
		}else{
			order.setStrPosterType("公司");
		}
		if(order.getOrderType()==0){
			order.setStrOrderType("正式单");
		}else{
			order.setStrOrderType("试单");
		}
		if(order.getState()==0){
			order.setStrState("待付定金");
		}
		if(order.getState()==1){
			order.setStrState("待接单");
		}
		if(order.getState()==2){
			order.setStrState("未出发");
		}
		if(order.getState()==3){
			order.setStrState("已出发");
		}
		if(order.getState()==4){
			order.setStrState("已到达");
		}
		if(order.getState()==5){
			order.setStrState("服务完成");
		}
		if(order.getState()==6){
			order.setStrState("订单完成");
		}
		if(order.getState()==7){
			order.setStrState("已退单");
		}
		if(order.getState()==8){
			order.setStrState("被拒单");
		}
		if(order.getState()==9){
			order.setStrState("预约失败无人抢单");
		}
		if(order.getState()==10){
			order.setStrState("待确认");
		}
		if(order.getState()==11){
			order.setStrState("待支付第一次维修金");
		}
		if(order.getState()==12){
			order.setStrState("服务完成待确认状态");
		}
		if(order.getState()==13){
			order.setStrState("用户申请退单");
		}
		if(order.getState()==14){
			order.setStrState("退单待支付");
		}
		if(order.getState()==15){
			order.setStrState("长时间未支付取消、主动取消");
		}
		if(order.getComplaintState()==0){
			order.setStrComplaintState("未投诉");
		}else{
			order.setStrComplaintState("已投诉");
		}
		if(order.getCommentState()==0){
			order.setStrCommentState("未评价");
		}else{
			order.setStrCommentState("已评价");
		}
		if(order.getAcceptType()==0){
			order.setStrAcceptType("指定");
		}else{
			order.setStrAcceptType("抢单");
		}
		if(order.getOrderSource()==0){
			order.setStrOrderSource("安卓客户端");
		}
		if(order.getOrderSource()==1){
			order.setStrOrderSource("IOS客户端");
		}
		if(order.getOrderSource()==2){
			order.setStrOrderSource("微信端");
		}
		if(order.getOrderSource()==3){
			order.setStrOrderSource("企业端手机版");
		}
		if(order.getOrderSource()==4){
			order.setStrOrderSource("企业端管理后台");
		}
		return order;
	}

	@Override
	public int dealOrder(String orderid) {
		Order order = orderMapper.selectByPrimaryKey(Integer.parseInt(orderid));
		order.setComplaintState((short)0);
		return orderMapper.updateByPrimaryKey(order);
	}

	@Override
	public int completeOrder(String orderid) {
		Order order = orderMapper.selectByPrimaryKey(Integer.parseInt(orderid));
		order.setState((short)6);
		return orderMapper.updateByPrimaryKey(order);
	}
}
