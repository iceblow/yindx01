package com.uncleserver.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uncleserver.model.Aunt;
import com.uncleserver.modelVo.AuntModel;
import com.uncleserver.modelVo.SearchAuntModel;
import com.uncleserver.modelVo.SearchStaffModel;
import com.uncleserver.modelVo.StaffModel;

public interface AuntMapper {
	int deleteByPrimaryKey(Integer auntid);

	int insert(Aunt record);

	int insertSelective(Aunt record);

	Aunt selectByPrimaryKey(Integer auntid);

	int updateByPrimaryKeySelective(Aunt record);

	int updateByPrimaryKey(Aunt record);

	List<AuntModel> selectByCondition(@Param("longitude") double longitude, @Param("latitude") double latitude,
			@Param("distance_from") double distance_from, @Param("distance_to") double distance_to,
			@Param("name_letter") String name_letter, @Param("comment_type") Integer comment_type,
			@Param("servertype") Integer servertype, @Param("agetype") Integer agetype,
			@Param("yeartype") Integer yeartype, @Param("offest") Integer offest, @Param("pageSize") Integer pageSize);

	List<AuntModel> selectByCompanyId(Integer companyid, double longitude, double latitude, Integer offest,
			Integer pageSize);

	List<SearchAuntModel> searchAuntByName(String keyword, int pageIndex, int pageCount);

	Long selectSearchCount(String keyword);

	Long selectUserCountByPhone(String phone);// 查询该手机号用户的数量

	Aunt selectUserByPhone(String phone);// 通过手机号码查询用户

	Aunt selectUserBySinaId(String openid);// 通过手机号码查询用户

	Aunt selectUserByQQId(String openid);// 通过手机号码查询用户

	Aunt selectUserByWxId(String openid);// 通过手机号码查询用户

	List<StaffModel> selectStaffListPage(int userid, int pageindex, int pagecount);

	Long selectAllStaffCount(int useridInt);

	List<SearchStaffModel> searchUnCompanyAunt(String keyword, int pageindex, int pagecount);

	Long searchUnCompanyAuntCount(String keyword);

	List<Aunt> selectAuntByCompanyId(@Param("companyid") Integer companyId, @Param("keywords") String keywords, @Param(value="startPage")int startPage, @Param(value="rows")int rows);

	long countAuntByCompanyId(@Param("companyid") Integer companyId, @Param("keywords") String keywords);
	

	List<Aunt> selectAnutsByCompanyId(Integer companyid);

	List<Aunt> selectByids(Integer[] ids);

	List<Aunt> selectAuntByPhoneAndName(@Param(value="startPage")int startPage, @Param(value="rows")int rows,
			@Param(value="phone")String phone,@Param(value="name") String name); // 通过手机号姓名码查询用户

	long selectAuntByPhoneAndNameCount(@Param(value="phone")String phone, @Param(value="name")String name);
	
	List<Aunt> selectAllAunt();
	
	Aunt selectUserByCode(String code);// 通过手机号码查询用户
	
	void updateAuntUserState(@Param("auntid") int auntid,@Param("state") int state);
	
	int delAunt(@Param("auntid") int auntid);
	
}