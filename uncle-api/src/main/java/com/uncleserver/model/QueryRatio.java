package com.uncleserver.model;

import java.io.Serializable;
import java.util.Date;

import com.uncleserver.common.CommonUtils;

public class QueryRatio implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ratioid;
	private String city;
	private String categoryid;
	private  String server_type;
	private String ratio;
	private String addtime;
	private String name;
	
	public String getRatioid() {
		return ratioid;
	}
	public void setRatioid(String ratioid) {
		this.ratioid = ratioid;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	public String getServer_type() {
		return server_type;
	}
	public void setServer_type(String server_type) {
		if("1".equals(server_type)){
			this.server_type = "阿姨";
			return;
		}
		else if("2".equals(server_type)){
			this.server_type = "公司";
			return;
		}else{
			this.server_type = server_type;
		}
		
	}
	
	public String getRatio() {
		return ratio;
	}
	public void setRatio(String ratio) {
		this.ratio = ratio;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = CommonUtils.getTimeFormat(addtime, "yyyy-MM-dd HH:mm:ss");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
