package com.uncleserver.service.company.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.dao.CompanyMapper;
import com.uncleserver.model.Company;
import com.uncleserver.service.company.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService{
	@Resource
	private CompanyMapper companyMapper;
	@Override
	public Company checkLogin(String logincode, String username, String password) {
		Company company = companyMapper.selectByNP(CommonUtils.cleanXSS(username),password);
			return company;
	}

}
