package com.uncleserver.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uncleserver.model.SerPrice;
import com.uncleserver.modelVo.SetPriceResult;

public interface SerPriceMapper {
    int deleteByPrimaryKey(Integer dataid);

    int insert(SerPrice record);

    int insertSelective(SerPrice record);

    SerPrice selectByPrimaryKey(Integer dataid);

    int updateByPrimaryKeySelective(SerPrice record);

    int updateByPrimaryKey(SerPrice record);
    
    SerPrice selectByCategoryId(Integer categoryid);
    
    SerPrice selectByCategoryAndThirdId(Integer categoryid,Integer third_categoryid);
    
    SerPrice selectByCategoryAndCity(Integer categoryid,String city);

	List<SetPriceResult> getServicePrice(@Param(value="startPage")int startPage, @Param(value="rows")int rows);

	long getServicePriceCount();

	List<SetPriceResult> getServicePriceOther(@Param(value="city") String city);

	SerPrice selectByCategoryIdAndCity(Integer categoryid, String city);

	SerPrice selectByCategoryAndThirdIdAndCity(Integer categoryid,Integer third_categoryid, String city);
}