package com.uncleserver.service.manage;

import java.util.List;

import com.uncleserver.model.Aunt;
import com.uncleserver.model.Company;
import com.uncleserver.model.User;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;

public interface ManageUserService {

	QueryResult<User> getAppUser(PQuery pquery, String name, String phone);

	QueryResult<Company> getCompanyUser(PQuery pquery, String name, String phone);

	QueryResult<Aunt> getAuntUser(PQuery pquery, String name, String phone);

	void updateAuntUserState(int auntid, int state);

	void updateCompanyUserState(int companyid, int stateDel);

	String getcompanyByid(int companyid);

	String getAuntByid(int id);

	int deluser(int companyid);

	int delAunt(int id);

	User getAppUserByid(int id);

	Aunt getAuntUserByid(int id);

	Company getCompanyUserByid(int id);

	int changPrice(int id, int point);

}
