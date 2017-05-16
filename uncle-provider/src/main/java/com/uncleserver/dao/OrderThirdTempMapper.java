package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.OrderThirdTemp;

public interface OrderThirdTempMapper {
    int deleteByPrimaryKey(Integer dataid);

    int insert(OrderThirdTemp record);

    int insertSelective(OrderThirdTemp record);

    OrderThirdTemp selectByPrimaryKey(Integer dataid);
    
    List<OrderThirdTemp> selectByOrderid(Integer dataid);

    int updateByPrimaryKeySelective(OrderThirdTemp record);

    int updateByPrimaryKey(OrderThirdTemp record);
    
    

}