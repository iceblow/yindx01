package com.uncleserver.dao;

import java.util.List;

import com.uncleserver.model.CompanyRange;

public interface CompanyRangeMapper {
    int deleteByPrimaryKey(Integer dataid);

    int insert(CompanyRange record);

    int insertSelective(CompanyRange record);

    CompanyRange selectByPrimaryKey(Integer dataid);

    int updateByPrimaryKeySelective(CompanyRange record);

    int updateByPrimaryKey(CompanyRange record);
    
    List<CompanyRange> selectByCompanyid(Integer companyid,Integer categoryid);
    
    List<CompanyRange> selectByCompany(Integer companyid);
}