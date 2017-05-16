package com.uncleserver.modelVo;

import java.util.Date;

public class SearchAuntModel {
	private int auntid;
	private String picurl;
	private String name;
	private String origin_place;
	private float score;
	private String age;

	private Integer avatar;
	private String thirdAvatar;
	private String realName;
	private String originPlace;
	private Date birthday;

	public int getAuntid() {
		return auntid;
	}

	public void setAuntid(int auntid) {
		this.auntid = auntid;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrigin_place() {
		return origin_place;
	}

	public void setOrigin_place(String origin_place) {
		this.origin_place = origin_place;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Integer getAvatar() {
		return avatar;
	}

	public void setAvatar(Integer avatar) {
		this.avatar = avatar;
	}

	public String getThirdAvatar() {
		return thirdAvatar;
	}

	public void setThirdAvatar(String thirdAvatar) {
		this.thirdAvatar = thirdAvatar;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getOriginPlace() {
		return originPlace;
	}

	public void setOriginPlace(String originPlace) {
		this.originPlace = originPlace;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
