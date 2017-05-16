package com.uncleserver.dao;

import com.uncleserver.model.AuntInvitation;

public interface AuntInvitationMapper {
    int deleteByPrimaryKey(Integer dataid);

    int insert(AuntInvitation record);

    int insertSelective(AuntInvitation record);

    AuntInvitation selectByPrimaryKey(Integer dataid);

    int updateByPrimaryKeySelective(AuntInvitation record);

    int updateByPrimaryKey(AuntInvitation record);
}