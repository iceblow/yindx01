package com.uncleserver.service.manage;

import java.util.List;

import com.uncleserver.model.Admin;
import com.uncleserver.model.Permission;
import com.uncleserver.model.Role;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;

public interface ManageAdminService {

	QueryResult<Admin> pageAdmin(PQuery pquery);

	QueryResult<Role> pageRole(PQuery pquery);

	QueryResult<Permission> pagePermission(PQuery pquery);
	
	Admin selectAdminByAccount(String account);
	
	void addAdmin(Admin admin);
	
	List<Role> getSelectRole();
	
	Admin selectByPrimaryKey(Integer adminid);
	
	int updateByPrimaryKeySelective(Admin record);
	
	int selectCountByAccount(String account,Integer adminid);
}
