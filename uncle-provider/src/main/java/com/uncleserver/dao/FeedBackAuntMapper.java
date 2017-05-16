package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.FeedBackAunt;

public interface FeedBackAuntMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FeedBackAunt record);

    int insertSelective(FeedBackAunt record);

    FeedBackAunt selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FeedBackAunt record);

    int updateByPrimaryKey(FeedBackAunt record);
    //从何处开始
    List<FeedBackAunt> getFeedBackAuntByPage(int start,int limit);
    //计算一共有多少个反馈
    long getFeedCount();
}