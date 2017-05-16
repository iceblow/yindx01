package com.uncleserver.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uncleserver.model.PointRecord;
import com.uncleserver.model.User;

public interface UserMapper {
	int deleteByPrimaryKey(Integer userid);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer userid);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	Long selectUserCountByPhone(String phone);// 查询该手机号用户的数量

	User selectUserByPhone(String phone);// 通过手机号码查询用户
	
	User selectUserBySinaId(String openid);// 通过手机号码查询用户
	
	User selectUserByQQId(String openid);// 通过手机号码查询用户
	
	User selectUserByWxId(String openid);// 通过手机号码查询用户

	List<User> selectUserByPhoneAndName(@Param(value="startPage")int startPage, @Param(value="rows")int rows,
			@Param(value="phone")String phone,@Param(value="name") String name); // 通过手机号姓名码查询用户

	long selectUserByPhoneAndNameCount(@Param(value="phone")String phone, @Param(value="name")String name);
	
	User selectUserByWxUnionId(String unioid);
	
	List<User> selectBirthdayUser();
	
	int updateByUserId(@Param(value="id")int id,@Param(value="point")int point);
	
	int insertPointRecord(PointRecord record);
	
	User selectUserByCode(String Code);// 通过手机号码查询用户

	List<User> selectAllUser();
}