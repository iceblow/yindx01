package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.AuntSkill;


public interface AuntSkillMapper {
    int deleteByPrimaryKey(Integer dataid);

    int insert(AuntSkill record);

    int insertSelective(AuntSkill record);

    AuntSkill selectByPrimaryKey(Integer dataid);

    int updateByPrimaryKeySelective(AuntSkill record);

    int updateByPrimaryKey(AuntSkill record);
    
    List<AuntSkill> selectByAuntId(Integer auntid,Integer categoryid);
    
    List<AuntSkill>  selectByAuntIdTo(Integer auntid);
    
    int deleteByAuntid(Integer auntid);
}