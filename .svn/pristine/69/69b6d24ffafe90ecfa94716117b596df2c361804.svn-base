package com.uncleserver.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uncleserver.model.AuntCash;
import com.uncleserver.modelVo.AuntCashVo;

public interface AuntCashMapper {
    int deleteByPrimaryKey(Integer cashid);

    int insert(AuntCash record);

    int insertSelective(AuntCash record);

    AuntCash selectByPrimaryKey(Integer cashid);

    int updateByPrimaryKeySelective(AuntCash record);

    int updateByPrimaryKey(AuntCash record);
    
    Float getTotalCashByCompany(@Param(value="companyid") Integer companyid, @Param(value="startDate") String startDate, @Param(value="endDate") String endDate);

    List<AuntCashVo> getAuntCashList(@Param(value="startPage")Integer startPage,@Param(value="rows")Integer rows);
    
    Long auntCashListCount();
}