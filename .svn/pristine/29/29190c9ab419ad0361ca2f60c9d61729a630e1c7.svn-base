package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.RedPacketRecord;

public interface RedPacketRecordMapper {
    int deleteByPrimaryKey(Integer dataid);

    int insert(RedPacketRecord record);

    int insertSelective(RedPacketRecord record);

    RedPacketRecord selectByPrimaryKey(Integer dataid);

    int updateByPrimaryKeySelective(RedPacketRecord record);

    int updateByPrimaryKey(RedPacketRecord record);
    
    List<RedPacketRecord> selectByUserIdAndPage(Integer userid, Integer user_type, Integer offset, Integer pageSize);
}