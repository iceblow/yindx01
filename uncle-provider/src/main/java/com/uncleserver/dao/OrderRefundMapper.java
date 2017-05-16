package com.uncleserver.dao;

import com.uncleserver.model.OrderRefund;

public interface OrderRefundMapper {
    int deleteByPrimaryKey(Integer dataid);

    int insert(OrderRefund record);

    int insertSelective(OrderRefund record);

    OrderRefund selectByPrimaryKey(Integer dataid);

    int updateByPrimaryKeySelective(OrderRefund record);

    int updateByPrimaryKey(OrderRefund record);
    
    Long selectOrderRefundCount();
    
    OrderRefund selectByOrderNum(String ordernum);
}