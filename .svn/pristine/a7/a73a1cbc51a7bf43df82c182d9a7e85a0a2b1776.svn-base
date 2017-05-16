package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.AuntMessage;


public interface AuntMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuntMessage record);

    int insertSelective(AuntMessage record);

    AuntMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuntMessage record);

    int updateByPrimaryKey(AuntMessage record);
    
    Long selectUnRead(Integer userid,int type);
    
    List<AuntMessage> selectByPage(Integer userid,int type);
    
    AuntMessage selectByUserIdAndType(int userid,int type);
}