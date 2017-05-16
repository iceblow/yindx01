package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.MessageDetail;

public interface MessageDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MessageDetail record);

    int insertSelective(MessageDetail record);

    MessageDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MessageDetail record);

    int updateByPrimaryKey(MessageDetail record);
    
    List<MessageDetail> selectByMessageid(Integer messageid);
    
    void deleteByMessageid(Integer messageid);
    
    List<MessageDetail> selectByPage(Integer messageid,int offest, int pageSize);
    
    Long selectPageCount(int messageid);
    
    MessageDetail selectLastByMid(int messageid);
}