package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.Order;
import com.uncleserver.model.OrderPay;

public interface OrderPayMapper {
    int deleteByPrimaryKey(Integer dataid);

    int insert(OrderPay record);

    int insertSelective(OrderPay record);

    OrderPay selectByPrimaryKey(Integer dataid);

    int updateByPrimaryKeySelective(OrderPay record);

    int updateByPrimaryKey(OrderPay record);
    
    OrderPay selectByOrderId(Integer orderid);
    
    List<OrderPay> getTipList(Integer orderid);
    
    OrderPay getdownPayMent(Integer orderid);
    
    Long selectOrderNumCount();
    
    List<OrderPay> selectByOrderNum(String ordernum);
}