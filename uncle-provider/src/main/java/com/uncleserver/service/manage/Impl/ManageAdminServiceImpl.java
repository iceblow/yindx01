package com.uncleserver.service.manage.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.dao.AdminMapper;
import com.uncleserver.dao.PermissionMapper;
import com.uncleserver.dao.RoleMapper;
import com.uncleserver.model.Admin;
import com.uncleserver.model.Permission;
import com.uncleserver.model.Role;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;
import com.uncleserver.service.manage.ManageAdminService;

@Service("manageAdminService")
public class ManageAdminServiceImpl implements ManageAdminService {

	@Resource
	private AdminMapper adminMapper;
	
	@Resource
	private RoleMapper roleMapper;
	
	@Resource
	private PermissionMapper permissionMapper;
	
	@Override
	public QueryResult<Admin> pageAdmin(PQuery pquery){
		List<Admin> list = adminMapper.managePageAdmin(pquery.getStartPage(), pquery.getRows());
		long total = adminMapper.managePageAdminCount();
		for (Admin admin : list) {
			Role role = roleMapper.selectByPrimaryKey(admin.getRoleid());
			if (role != null) {
				admin.setRolename(role.getRolename());
			}
			
			admin.setStringtime(CommonUtils.getTimeFormat(admin.getAddtime(), "yyyy-MM-dd HH:mm:ss"));
		}
		return new QueryResult<Admin>(list, total, pquery.getPage(), pquery.getRows());
	}
	
	@Override
	public QueryResult<Role> pageRole(PQuery pquery) {
		List<Role> list = roleMapper.managePageRole(pquery.getStartPage(), pquery.getRows());
		long total = roleMapper.managePageRoleCount();
		for (Role role : list) {
			if (role.getPermissionids() != null) {
				String permissions = "";
				String ids[] = role.getPermissionids().split(",");
				for (String id : ids) {
					Permission permission = permissionMapper.selectByPrimaryKey(CommonUtils.parseInt(id, 0));
					if (permission != null) {
						permissions += permission.getName() + ",";
					}
				}
				role.setPermissions(permissions);
			}
		}
		return new QueryResult<Role>(list, total, pquery.getPage(), pquery.getRows());
	}
	
	@Override
	public QueryResult<Permission> pagePermission(PQuery pquery) {
		List<Permission> list = permissionMapper.managePagePermission(pquery.getStartPage(), pquery.getRows());
		long total = permissionMapper.managePagePermissionCount();
		for (Permission permission : list) {
			if (permission.getFid() == 0) {
				permission.setFname("一级权限");
			} else {
				Permission fp = permissionMapper.selectByPrimaryKey(permission.getFid());
				permission.setFname(fp.getName());
			}
		}
		return new QueryResult<Permission>(list, total, pquery.getPage(), pquery.getRows());
	}
	
	@Override
	public Admin selectAdminByAccount(String account){
		return adminMapper.selectByAccount(account);
	}
	
	@Override
	public void addAdmin(Admin admin) {
		adminMapper.insert(admin);
	}
	
	@Override
	public List<Role> getSelectRole() {
		return roleMapper.getAllRole();
	}
	
	@Override
	public Admin selectByPrimaryKey(Integer adminid) {
		return adminMapper.selectByPrimaryKey(adminid);
	}
	
	@Override
	public int updateByPrimaryKeySelective(Admin admin) {
		return adminMapper.updateByPrimaryKeySelective(admin);
	}
	
	@Override
	public int selectCountByAccount(String account,Integer adminid) {
		return adminMapper.selectCountByAccount(account,adminid);
	}

}
