package com.uncleserver.service.company.Impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.uncleserver.dao.AuntCashMapper;
import com.uncleserver.dao.CompanyExtraMapper;
import com.uncleserver.dao.OrderAuntMapper;
import com.uncleserver.model.CompanyExtra;
import com.uncleserver.service.company.StatisticService;

@Service(value="statisticService")
public class StatisticServiceImpl implements StatisticService {

	@Resource
	private OrderAuntMapper orderAuntMapper;
	
	@Resource
	private CompanyExtraMapper companyExtraMapper;
	
	@Resource
	private AuntCashMapper auntCashMapper;
	
	@Override
	public long getCompanyAuntOrderCount(Integer companyid, String startDate, String endDate) {
		return orderAuntMapper.getCompanyAuntOrderCount(companyid, startDate, endDate);
	}
	
	@Override
	public CompanyExtra getExtraByCompanyId(Integer companyid) {
		return companyExtraMapper.selectByCompanyId(companyid);
	}
	
	@Override
	public Float getCompanyTotalPrice(Integer companyid, String startDate, String endDate) {
		return orderAuntMapper.getCompanyTotalPrice(companyid, startDate, endDate);
	}
	
	@Override
	public Float getTotalCashByCompany(Integer companyid, String startDate, String endDate) {
		return auntCashMapper.getTotalCashByCompany(companyid, startDate, endDate);
	}
	
	@Override
	public Float getTotalTimeByCompany(Integer companyid, String startDate, String endDate, String type) {
		return orderAuntMapper.getTotalTimeByCompany(companyid, startDate, endDate, type);
	}

	@Override
	public BigDecimal getAllCompanyBalance() {
		return companyExtraMapper.getAllCompanyBalance();
	}

	@Override
	public BigDecimal getAllCompanyUseTotal() {
		return companyExtraMapper.getAllCompanyUseTotal();
	}
}
