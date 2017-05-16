package com.uncleserver.model;

import java.io.Serializable;
import java.util.Date;

public class Ratio implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer ratioid;

	private String city;

	private Integer categoryid;

	private Short serverType;

	private Float ratio;

	private Date addtime;
	
	private String strServerType;
	
	private String strCategoryid;

	public String getStrServerType() {
		return strServerType;
	}

	public void setStrServerType(String strServerType) {
		this.strServerType = strServerType;
	}

	public String getStrCategoryid() {
		return strCategoryid;
	}

	public void setStrCategoryid(String strCategoryid) {
		this.strCategoryid = strCategoryid;
	}

	public Integer getRatioid() {
		return ratioid;
	}

	public void setRatioid(Integer ratioid) {
		this.ratioid = ratioid;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public Integer getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}

	public Short getServerType() {
		return serverType;
	}

	public void setServerType(Short serverType) {
		this.serverType = serverType;
	}

	public Float getRatio() {
		return ratio;
	}

	public void setRatio(Float ratio) {
		this.ratio = ratio;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
}