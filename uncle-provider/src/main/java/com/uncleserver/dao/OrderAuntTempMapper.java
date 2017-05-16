package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.OrderAuntTemp;
import com.uncleserver.model.OrderTargetTemp;

public interface OrderAuntTempMapper {
    int deleteByPrimaryKey(Integer dataid);

    int insert(OrderAuntTemp record);

    int insertSelective(OrderAuntTemp record);

    OrderAuntTemp selectByPrimaryKey(Integer dataid);

    int updateByPrimaryKeySelective(OrderAuntTemp record);

    int updateByPrimaryKey(OrderAuntTemp record);
    
    List<OrderAuntTemp> selectByOrderid(Integer orderid);
}