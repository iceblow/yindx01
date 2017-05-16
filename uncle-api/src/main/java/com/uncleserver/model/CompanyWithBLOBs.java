package com.uncleserver.model;

public class CompanyWithBLOBs extends Company {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String piclist;

	private String companyDetail;

	public String getPiclist() {
		return piclist;
	}

	public void setPiclist(String piclist) {
		this.piclist = piclist == null ? null : piclist.trim();
	}

	public String getCompanyDetail() {
		return companyDetail;
	}

	public void setCompanyDetail(String companyDetail) {
		this.companyDetail = companyDetail == null ? null : companyDetail.trim();
	}
}