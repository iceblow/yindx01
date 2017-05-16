package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.OrderThird;


public interface OrderThirdMapper {
    int deleteByPrimaryKey(Integer dataid);

    int insert(OrderThird record);

    int insertSelective(OrderThird record);

    OrderThird selectByPrimaryKey(Integer dataid);

    int updateByPrimaryKeySelective(OrderThird record);

    int updateByPrimaryKey(OrderThird record);
    
    List<OrderThird> selectByOrderid(Integer dataid);
}