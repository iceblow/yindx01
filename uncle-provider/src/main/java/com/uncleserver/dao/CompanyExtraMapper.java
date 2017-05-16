package com.uncleserver.dao;

import java.math.BigDecimal;

import com.uncleserver.model.AuntExtra;
import com.uncleserver.model.CompanyExtra;

public interface CompanyExtraMapper {
    int deleteByPrimaryKey(Integer dataid);

    int insert(CompanyExtra record);

    int insertSelective(CompanyExtra record);

    CompanyExtra selectByPrimaryKey(Integer dataid);

    int updateByPrimaryKeySelective(CompanyExtra record);

    int updateByPrimaryKey(CompanyExtra record);
    
    CompanyExtra selectByCompanyId(Integer companyid);
    
    CompanyExtra selectByAccesstoken(String accesstoken);
    
    int getMaxId();
    
    BigDecimal getAllCompanyBalance();
    
    BigDecimal getAllCompanyUseTotal();
}