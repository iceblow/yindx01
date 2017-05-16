package com.uncleserver.service.manage.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.uncleserver.model.Aunt;
import com.uncleserver.model.Company;
import com.uncleserver.model.CompanyWithBLOBs;
import com.uncleserver.model.PointRecord;
import com.uncleserver.model.User;
import com.uncleserver.model.Result.PQuery;
import com.uncleserver.model.Result.QueryResult;
import com.uncleserver.service.api.Impl.BaseServiceImpl;
import com.uncleserver.service.manage.ManageUserService;

@Service("manageUserService")
public class ManageUserServiceImpl extends BaseServiceImpl implements ManageUserService {

	@Override
	public QueryResult<User> getAppUser(PQuery pquery, String name, String phone) {
		List<User> user = userMapper.selectUserByPhoneAndName(pquery.getStartPage(), pquery.getRows(), phone, name);
		if (user != null) {
			for (User users : user) {
				if (users.getRealName() == null) {
					users.setRealName("未填写");
				}
				if (users.getPhone().equals("")) {
					users.setPhone("未填写");
				}
				if (users.getSex() == null) {
					users.setSex("未填写");
				}

				if (users.getBirthday() != null) {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
					String dateString = formatter.format(users.getBirthday());
					users.setBirthdays(dateString);
				} else {
					users.setBirthdays("未填写");
				}
				if (users.getAddtime() != null) {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
					String dateString = formatter.format(users.getAddtime());
					users.setAddtimes(dateString);
				}
			}
		}
		long count = userMapper.selectUserByPhoneAndNameCount(phone, name);
		QueryResult<User> result = new QueryResult<>(user, count, pquery.getPage(), pquery.getRows());
		return result;
	}

	@Override
	public QueryResult<Company> getCompanyUser(PQuery pquery, String name, String phone) {
		List<Company> user = companyMapper.selectCompanyByPhoneAndName(pquery.getStartPage(), pquery.getRows(), phone,
				name);
		if (user != null) {
			for (Company company : user) {
				if (company.getName() == null) {
					company.setName("未填写");
				}
				if (company.getPhone().equals("")) {
					company.setPhone("未填写");
				}
				if (company.getAddtime() != null) {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
					String dateString = formatter.format(company.getAddtime());
					company.setAddtimes(dateString);
				}
			}
		}
		long count = companyMapper.selectCompanyByPhoneAndNameCount(phone, name);
		QueryResult<Company> result = new QueryResult<>(user, count, pquery.getPage(), pquery.getRows());
		return result;
	}

	@Override
	public QueryResult<Aunt> getAuntUser(PQuery pquery, String name, String phone) {
		List<Aunt> user = auntMapper.selectAuntByPhoneAndName(pquery.getStartPage(), pquery.getRows(), phone, name);
		if (user != null) {
			for (Aunt aunt : user) {
				if (aunt.getRealName() == null) {
					aunt.setRealName("未填写");
				}
				if (aunt.getPhone().equals("")) {
					aunt.setPhone("未填写");
				}
				if (aunt.getSex() == null) {
					aunt.setSex("未填写");
				}
				if (aunt.getBirthday() == null) {
					aunt.setBirthdays("未填写");
				} else {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
					String dateString = formatter.format(aunt.getBirthday());
					aunt.setBirthdays(dateString);
				}
				if (aunt.getAddtime() != null) {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
					String dateString = formatter.format(aunt.getAddtime());
					aunt.setAddtimes(dateString);
				}
			}
		}
		long count = auntMapper.selectAuntByPhoneAndNameCount(phone, name);
		QueryResult<Aunt> result = new QueryResult<>(user, count, pquery.getPage(), pquery.getRows());
		return result;
	}

	@Override
	public void updateAuntUserState(int auntid, int state) {
		auntMapper.updateAuntUserState(auntid, state);
	}

	@Override
	public void updateCompanyUserState(int companyid, int stateDel) {
		companyMapper.updateCompanyUserState(companyid, stateDel);
	}

	@Override
	public String getcompanyByid(int companyid) {
		CompanyWithBLOBs company = companyMapper.selectByPrimaryKey(companyid);
		return company.getName();
	}

	@Override
	public String getAuntByid(int id) {
		return auntMapper.selectByPrimaryKey(id).getRealName();
	}

	@Override
	public int deluser(int companyid) {
		int result = companyMapper.deluser(companyid);
		return result;

	}

	@Override
	public int delAunt(int auntid) {
		int result = auntMapper.delAunt(auntid);
		return result;

	}

	@Override
	public User getAppUserByid(int id) {
		User result = null;
		result = userMapper.selectByPrimaryKey(id);
		if (result.getAddtime() != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
			String dateString = formatter.format(result.getAddtime());
			result.setAddtimes(dateString);
		}
		if (result.getBirthday() != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
			String dateString = formatter.format(result.getBirthday());
			result.setBirthdays(dateString);
		}
		return result;
	}

	@Override
	public Aunt getAuntUserByid(int id) {
		Aunt result = null;
		result = auntMapper.selectByPrimaryKey(id);
		if (result != null) {
			if (result.getAddtime() != null) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
				String dateString = formatter.format(result.getAddtime());
				result.setAddtimes(dateString);
			}
			if (result.getBirthday() != null) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
				String dateString = formatter.format(result.getBirthday());
				result.setBirthdays(dateString);
			}
			if (result.getState() == 0) {
				result.setStates("待审核");
			} else if (result.getState() == 1) {
				result.setStates("正常");
			} else if (result.getState() == 2) {
				result.setStates("冻结");
			}
			if (result.getKingState() == 0) {
				result.setKingStates("不是");
			} else if (result.getKingState() == 1) {
				result.setKingStates("是");
			}
		}
		return result;
	}

	@Override
	public Company getCompanyUserByid(int companyid) {
		Company result = null;
		result = companyMapper.getCompanyUserByid(companyid);
		if (result != null) {
			if (result.getAddtime() != null) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
				String dateString = formatter.format(result.getAddtime());
				result.setAddtimes(dateString);
			}

			if (result.getYearCreate() != null) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
				String dateString = formatter.format(result.getYearCreate());
				result.setYearCreates(dateString);
			}
			if (result.getType() == 0) {
				result.setTypes("维修公司");
			} else if (result.getType() == 1) {
				result.setTypes("家政公司");
			} else if (result.getType() == 1) {
				result.setTypes("中介公司");
			}
			if (result.getStateDel() == 0) {
				result.setStateDels("待审核");
			} else if (result.getStateDel() == 1) {
				result.setStateDels("正常");
			} else if (result.getStateDel() == 2) {
				result.setStateDels("冻结");
			}
			if (result.getTitle() == 1) {
				result.setTitles("会长");
			} else if (result.getTitle() == 2) {
				result.setTitles("常务副会长");
			} else if (result.getTitle() == 3) {
				result.setTitles("副会长");
			} else if (result.getTitle() == 4) {
				result.setTitles("理事");
			} else if (result.getTitle() == 5) {
				result.setTitles("会员");
			}

		}
		return result;
	}

	@Override
	public int changPrice(int id, int point) {
		int result = 0;
		if (userMapper.updateByUserId(id, point) != 0) {
			PointRecord record = new PointRecord();
			record.setAddtime(new Date());
			record.setCount(point);
			record.setUserid(id);
			result = userMapper.insertPointRecord(record);
		}
		return result;
	}


}
