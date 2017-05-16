package com.uncleserver.service.company;

import java.math.BigDecimal;

import com.uncleserver.model.CompanyExtra;

public interface StatisticService {

	long getCompanyAuntOrderCount(Integer companyid, String startDate, String endDate);
	
	CompanyExtra getExtraByCompanyId(Integer companyid);
	
	BigDecimal getAllCompanyBalance();
	
	BigDecimal getAllCompanyUseTotal();
	
	Float getCompanyTotalPrice(Integer companyid, String startDate, String endDate);
	
	Float getTotalCashByCompany(Integer companyid, String startDate, String endDate);
	
	Float getTotalTimeByCompany(Integer companyid, String startDate, String endDate, String type);
}
