package com.uncleserver.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uncleserver.model.Company;
import com.uncleserver.model.CompanyWithBLOBs;
import com.uncleserver.model.User;
import com.uncleserver.modelVo.CompanyModel;
import com.uncleserver.modelVo.SearchCompanyModel;

public interface CompanyMapper {
	int deleteByPrimaryKey(Integer companyid);

	int insert(CompanyWithBLOBs record);

	int insertSelective(CompanyWithBLOBs record);

	CompanyWithBLOBs selectByPrimaryKey(Integer companyid);

	int updateByPrimaryKeySelective(CompanyWithBLOBs record);

	int updateByPrimaryKeyWithBLOBs(CompanyWithBLOBs record);

	int updateByPrimaryKey(CompanyWithBLOBs record);

	List<CompanyWithBLOBs> selectByDistance(Double longitude, Double latitude, Double distance);

	List<CompanyModel> selectByCondition(@Param("companytype") int companytype, @Param("longitude") double longitude,
			@Param("latitude") double latitude, @Param("distance_from") double distance_from,
			@Param("distance_to") double distance_to, @Param("name_letter") String name_letter,
			@Param("comment_type") int comment_type, @Param("servertype") int servertype, @Param("agetype") int agetype,
			@Param("yeartype") int yeartype, @Param("offest") int offest, @Param("pageSize") int pageSize);

	List<SearchCompanyModel> searchCompanyByName(String keyword, Integer pageIndex, Integer pageCount, Integer type);

	Long selectSearchCount(String keyword, Integer type);

	Company selectByNP(String cleanXSS, String password);

	Long selectUserCountByPhone(String phone);// 查询该手机号用户的数量

	CompanyWithBLOBs selectUserByPhone(String phone);// 通过手机号码查询用户

	List<CompanyWithBLOBs> selectByCityAndType(String cityName, Integer type, Integer startPage, Integer rows);// 通过手机号码查询用户

	List<CompanyWithBLOBs> selectByCity(String cityName);

	long manageCountConpanyByCityAndType(String cityName, Integer type);

	List<Company> selectCompanyByPhoneAndName(@Param(value = "startPage") int startPage,
			@Param(value = "rows") int rows, @Param(value = "phone") String phone, @Param(value = "name") String name); // 通过手机号姓名码查询用户

	long selectCompanyByPhoneAndNameCount(@Param(value = "phone") String phone, @Param(value = "name") String name);


	boolean exitName(String name);// 查看此公司名称是否被祖册

	int getMaxId();

	List<Company> getAllCompany();

	void updateCompanyUserState(@Param("companyid") int companyid, @Param("stateDel") int stateDel);
	
	int deluser(@Param("companyid") int companyid);
	
	Company getCompanyUserByid(@Param("companyid") int companyid);

}