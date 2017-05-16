package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.Message;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
    
    Long selectUnRead(Integer userid);
    
    List<Message> selectByPage(Integer userid);
    
    Message selectByUserIdAndType(int userid,int messagetype);
}