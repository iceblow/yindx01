package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.FeedBack;

public interface FeedBackMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FeedBack record);

    int insertSelective(FeedBack record);

    FeedBack selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FeedBack record);

    int updateByPrimaryKey(FeedBack record);
    
    List< FeedBack> selectByPageAmt(int page,int limit);
    
    long getFeedCount();
}