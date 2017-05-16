package com.uncleserver.model;

import java.util.Date;

public class PickAddress {
	private Integer addressid;

	private String provience;

	private String city;

	private String area;

	private Integer userid;

	private Double longitude;

	private Double latitude;

	private String phone;

	private String rname;

	private String sex;

	private String addressname;

	private String addressdetail;

	private Short isdefault;

	private Date addtime;

	private int choose_state;

	private int fast_state;

	public Integer getAddressid() {
		return addressid;
	}

	public void setAddressid(Integer addressid) {
		this.addressid = addressid;
	}

	public String getProvience() {
		return provience;
	}

	public void setProvience(String provience) {
		this.provience = provience == null ? null : provience.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area == null ? null : area.trim();
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname == null ? null : rname.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public String getAddressname() {
		return addressname;
	}

	public void setAddressname(String addressname) {
		this.addressname = addressname == null ? null : addressname.trim();
	}

	public String getAddressdetail() {
		return addressdetail;
	}

	public void setAddressdetail(String addressdetail) {
		this.addressdetail = addressdetail == null ? null : addressdetail.trim();
	}

	public Short getIsdefault() {
		return isdefault;
	}

	public void setIsdefault(Short isdefault) {
		this.isdefault = isdefault;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public int getChoose_state() {
		return choose_state;
	}

	public void setChoose_state(int choose_state) {
		this.choose_state = choose_state;
	}

	public int getFast_state() {
		return fast_state;
	}

	public void setFast_state(int fast_state) {
		this.fast_state = fast_state;
	}

}