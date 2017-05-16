package com.uncleserver.service.manage.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.dao.AdminMapper;
import com.uncleserver.model.Admin;
import com.uncleserver.service.manage.ManageLoginService;

@Service("manageLoginService")
public class ManageLoginServiceImpl implements ManageLoginService{
	@Resource
	private AdminMapper adminMapper;
	@Override
	public Admin checkLogin(String logincode, String username, String password) {
		Admin admin = adminMapper.selectByNP(CommonUtils.cleanXSS(username),password);
			return admin;
	}

}
